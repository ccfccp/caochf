package com.caochf.lucene.util;


import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;

import java.io.*;

public class FileReadUtil {
    /**
     * 读取文件内容.
     * @param file
     * @return
     */
    public static String readFileContent(File file) {
        String content = "";
        String fileType = CheckFileFormatUtil.getFileType(file);
        if("doc".equals(fileType)){
            content = readWord(file);
        }else if("docx".equals(fileType)){
            content = readWord2007(file);
        }else if("xlsx".equals(fileType)){
            content = readWord2007(file);
        }else if("xls".equals(fileType)){
            content = readWord(file);
        }else if("pdf".equals(fileType)){
            content = readPdf(file);
        }else if("html".equals(fileType)){
            content = readHtml(file);
        }else{ // 其他情况直接按txt方式进行处理.
            content = readTxt(file);
        }

        return content;
    }

    /**
     * 读取word文档内容.使用PDFbox.
     * @param file
     * @return
     */
    public static String readWord(File file) {
        StringBuffer content = new StringBuffer("");// 文档内容
        try {
            HWPFDocument doc = new HWPFDocument(new FileInputStream(file));
            Range range = doc.getRange();
            int paragraphCount = range.numParagraphs();// 段落
            for (int i = 0; i < paragraphCount; i++) {// 遍历段落读取数据
                Paragraph pp = range.getParagraph(i);
                content.append(pp.text());
            }
        } catch (Exception e) {
            System.out.println("读取word文件【"+file.getPath()+"】异常！");
            e.printStackTrace();
        }
        return content.toString().trim();
    }

    /**
     * 读取docx文件内容.
     * @param file
     * @return
     */
    public static String readWord2007(File file){
        StringBuffer content = new StringBuffer("");// 文档内容
        FileInputStream fis = null;
        WordExtractor ex = null;
        try {
            fis = new FileInputStream(file);
            /*HWPFDocument doc = new HWPFDocument(fis);
            String doc1 = doc.getDocumentText();
            System.out.println(doc1);
            content.append(doc1);

            StringBuilder doc2 = doc.getText();
            System.out.println(doc2);
            content.append(doc2);

            Range rang = doc.getRange();
            String doc3 = rang.text();
            System.out.println(doc3);
            content.append(doc3);*/
            ex = new WordExtractor(fis);
            content.append(ex.getText());

        } catch (Exception e) {
            System.out.println("读取word文件【"+file.getPath()+"】异常！");
            e.printStackTrace();
        } finally {
            try {
                if(ex!=null){
                    ex.close();
                }
                if (fis != null) {
                    fis.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        return content.toString().trim();
    }

    /**
     * 读取pdf文档内容.使用PDFbox.
     * @param file
     * @return
     * @throws Exception
     */
    public static String readPdf(File file) {
        StringBuffer content = new StringBuffer("");// 文档内容
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            PDFParser p = new PDFParser(fis);
            p.parse();
            PDFTextStripper ts = new PDFTextStripper();
            content.append(ts.getText(p.getPDDocument()));
        } catch (IOException e) {
            System.out.println("读取pdf文件【"+file.getPath()+"】异常！");
            e.printStackTrace();
        } finally {
            if(fis!=null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return content.toString().trim();
    }

    /**
     * 读取html文件内容.
     * @param file
     * @return
     */
    public static String readHtml(File file) {
        StringBuffer content = new StringBuffer("");
//        File file = new File(urlString);
        FileInputStream fis = null;
        BufferedReader reader = null;
        try {
            fis = new FileInputStream(file);
            // 读取页面
            reader = new BufferedReader(new InputStreamReader(
                    fis,"utf-8"));//这里的字符编码要注意，要对上html头文件的一致，否则会出乱码
            String line = null;
            while ((line = reader.readLine()) != null) {
                content.append(line + "\n");
            }

        } catch (Exception e) {
            System.out.println("读取txt文件【"+file.getPath()+"】异常！");
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                if (fis != null) {
                    fis.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        String contentString = content.toString();
        return contentString;
    }

    /**
     * 读取txt文件.
     * @param file
     * @return
     */
    public static String readTxt(File file) {
        StringBuffer content = new StringBuffer("");// 文档内容
        try {
            FileReader reader = new FileReader(file);
            BufferedReader br = new BufferedReader(reader);
            String s1 = null;
            while ((s1 = br.readLine()) != null) {
                content.append(s1 + "\r");
            }
            br.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString().trim();
    }


}
