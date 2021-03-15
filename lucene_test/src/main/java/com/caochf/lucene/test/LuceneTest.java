package com.caochf.lucene.test;

import org.apache.poi.extractor.ExtractorFactory;
import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;


public class LuceneTest {
    public static final String INDEX_PATH = "D:\\temp\\index";
    public static void main(String args[]) throws Exception {
//        deleteAllIndex();
//        String dirPath = "D:\\temp\\桌面文档整理-20201204\\数据湖平台";
//        dirIndex(dirPath);
        searchIndex("1");
    }

    public static IndexWriter getIndexWriter() throws Exception{
        // 第一步：创建一个java工程，并导入jar包。
        // 第二步：创建一个indexwriter对象。
        Directory directory = FSDirectory.open(new File(INDEX_PATH).toPath());
        // Directory directory = new RAMDirectory();//保存索引到内存中 （内存索引库）
        Analyzer analyzer = new IKAnalyzer();
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        return new IndexWriter(directory, config);
    }

    /**
     * 删除所有索引信息.
     * @throws Exception
     */
    public static void deleteAllIndex() throws Exception {
        IndexWriter indexWriter = getIndexWriter();
        indexWriter.deleteAll();
        indexWriter.close();
    }

    /**
     * 目录索引
     * @throws Exception
     */
    public static void dirIndex(String dirPath) throws Exception {
        // 第一步创建一个indexwriter对象
        IndexWriter indexWriter = getIndexWriter();
        // 第三步创建Filed域,将field添加到document对象中
        File f = new File(dirPath);
        File[] listFiles = f.listFiles();
        System.out.println("listFiles.length="+listFiles.length);
        for (File file : listFiles) {
            // 文件路径
            String file_path = file.getPath();
            System.out.println(file_path);
            if(file.isDirectory()){
                System.out.println(file_path+"目录，暂略");
                continue;
            }

            // 第二步创建Document对象
            Document document = new Document();
            // 文件名称
            String file_name = file.getName();
            Field fileNameField = new TextField("fileName", file_name, Field.Store.YES);
            // 文件大小
            long file_size = FileUtils.sizeOf(file);
            Field fileSizeField = new LongField("fileSize", file_size, Field.Store.YES);

            Field filePathField = new StoredField("filePath", file_path);
            // 文件内容
            String file_content = null;
            try {
                file_content = ExtractorFactory.createExtractor(file).getText();
            }catch(Exception e){
                file_content = FileUtils.readFileToString(file);
            }
            System.out.println("文件【"+file_path+"】的内容长度【"+file_size+"】---------------------------------------------"+(file_content==null?0:file_content.length()));
            Field fileContentField = new TextField("fileContent", file_content, Field.Store.YES);
            document.add(fileNameField);
            document.add(fileSizeField);
            document.add(filePathField);
            document.add(fileContentField);
            // 第四步：使用indexwriter对象将document对象写入索引库，此过程进行索引创建。并将索引和document对象写入索引库
            indexWriter.addDocument(document);
        }
        // 第五步：关闭IndexWriter对象
        indexWriter.close();
    }

    /**
     * 按表索引.
     * @param conn
     * @param tableNameList
     */
    public static void tableIndex(Connection conn, List<String> tableNameList){

    }

    /**
     * 使用sql索引.
     * @param conn
     * @param sql
     */
    public void sqlIndex(Connection conn,String sql){

    }

    /**
     * 检索索引信息.
     * @param searchCondition
     * @return
     */
    public static ScoreDoc[] searchIndex(String searchCondition) throws IOException {
        ScoreDoc[] scoreDocs = null;
        //创建Directory对象，指定索引位置
        Directory directory= FSDirectory.open(new File(INDEX_PATH).toPath());
        //创建indexReader对象，读取索引库内容
        IndexReader indexReader= DirectoryReader.open(directory);
        //创建indexSearcher对象
        IndexSearcher indexSearcher=new IndexSearcher(indexReader);
        //创建Query查询对象
        Query query=new TermQuery(new Term("fileName",searchCondition));
        //执行查询，获取到文档对象
        TopDocs topDocs = indexSearcher.search(query, 10);
        System.out.println("一共获取文档数："+topDocs.totalHits+"--------------------------");
        //获取文档列表
        scoreDocs=topDocs.scoreDocs;
        for (ScoreDoc item:scoreDocs){
            //获取文档ID
            int docld=item.doc;
            //取出文档
            Document doc = indexSearcher.doc(docld);
            //获取到文档域中数据
            System.out.println("fileName:"+doc.get("fileName"));
            System.out.println("fileSize:"+doc.get("fileSize"));
            System.out.println("filePath:"+doc.get("filePath"));
            System.out.println("fileContent:"+doc.get("fileContent"));
            System.out.println("========================================================");
        }
        //关闭资源
        indexReader.close();
        return scoreDocs;
    }
}
