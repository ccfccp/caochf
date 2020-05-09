package com.ccf.kettle;


import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.repository.RepositoryDirectoryInterface;
import org.pentaho.di.repository.kdr.KettleDatabaseRepository;
import org.pentaho.di.repository.kdr.KettleDatabaseRepositoryMeta;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;

/**
 * User: mingfly
 * Mail:mingfly@163.com
 * Date: 13-7-9
 * Time: 上午10:11
 */
public class KettleMain {
    public static void main(String[] args) throws KettleException {
        //初始化环境
        KettleEnvironment.init();
        //创建DB资源库
        KettleDatabaseRepository repository=new KettleDatabaseRepository();
        DatabaseMeta databaseMeta=new DatabaseMeta("kettle_repo","mysql","jdbc","127.0.0.1","kettle_repository","3306","root","1");
        //选择资源库
        KettleDatabaseRepositoryMeta kettleDatabaseRepositoryMeta=new KettleDatabaseRepositoryMeta("Kettle","Kettle","Transformation description",databaseMeta);
        repository.init(kettleDatabaseRepositoryMeta);
        //连接资源库
        repository.connect("admin","admin");
        RepositoryDirectoryInterface directoryInterface=repository.loadRepositoryDirectoryTree();
        //选择转换
        TransMeta transMeta=repository.loadTransformation("trans_1",directoryInterface.findDirectory("trans"),null,true,null);
        Trans trans=new Trans(transMeta);
        trans.execute(null);
        trans.waitUntilFinished();//等待直到数据结束
        if(trans.getErrors()>0){
            System.out.println("transformation error");
        }else{
            System.out.println("transformation successfully");
        }
    }
}