package com.ccf.tess4j.tess4jTest;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;

public class Tess4jTest {
    public static void main(String[] args){
        System.out.println(new File(System.getenv("TESSDATA_PREFIX"),"./tessdata/eng.traineddata").exists());

        System.out.println(System.getenv("TESSDATA_PREFIX"));

        //验证码图片存储地址
        File imageFile = new File("C:\\Users\\caochf\\Desktop\\桌面文档整理\\tmpImg\\4.jpg");
        if(!imageFile.exists()){
            System.out.println("图片不存在");
        }
        Tesseract tessreact = new Tesseract();
        tessreact.setDatapath("E:\\tools\\tess4j\\tesseract-master\\tessdata");
        tessreact.setLanguage("chi_sim");
        String result;
        try {
            result = "测验结果：" + tessreact.doOCR(imageFile);
            System.out.println(result);
        } catch (TesseractException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
