package com.caochf.lucene.test;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class POITest {
    public static void main(String[] args){
        String filePath = "F:\\work\\浪潮\\svn\\发包\\GCloud-Released\\trunk\\03_紧急包\\17_GCloud-OD\\GCloud_OD_20210313_紧急包_应急\\01 war\\jsp\\od\\dataStandard\\datadict\\代码集导入模板.xlsx";
        InputStream fis = null;

        try {
            fis = new FileInputStream(filePath);
            Workbook wb = new XSSFWorkbook(fis);
            Sheet sheet = wb.getSheetAt(0);
            int rowNum = sheet.getLastRowNum();
            int totalSheets = wb.getNumberOfSheets() - 1;
            System.out.println("rowNum="+rowNum+",totalSheets="+totalSheets);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }


    }
}
