package com.caochf.lucene.util;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.poi.extractor.ExtractorFactory;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;

public class LuceneUtil {
    public static final String INDEX_PATH = "D:\\temp\\index";

    public static void main(String[] args) throws Exception {
        String dirPath = "D:\\temp\\桌面文档整理-20201204\\数据湖平台";
        dirIndex(dirPath);
    }

    /**
     * 创建一个indexwriter对象.
     * @return
     * @throws Exception
     */
    public static IndexWriter getIndexWriter() throws Exception{
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
            String file_content = FileReadUtil.readFileContent(file);


//            try {
//                file_content = ExtractorFactory.createExtractor(file).getText();
//            }catch(Exception e){
//                file_content = FileUtils.readFileToString(file);
//            }
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
}
