package com.jacob.test;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class TTT {
    /**
     * @Title: main
     * @Description: TODO
     * @param args
     *            void
     * @author Liu_xg
     * @date 2018年10月16日上午8:39:38
     */
    public static void main(String[] args) {
        String filePath = "F:\\workspaces\\myworkspaces\\jacob_test\\src\\main\\java\\com\\jacob\\test\\test.txt";
        //   "res/";
        readTxtFile(filePath);
    }

    /**
     * 读取txt文本内容
     *
     * @Title: readTxtFile
     * @Description: TODO
     * @param filePath
     *            void
     * @author Liu_xg
     * @date 2018年10月16日上午8:59:57
     */
    public static void readTxtFile(String filePath) {
        try {
            String encoding = "UTF8";
            File file = new File(filePath);
            if (file.isFile() && file.exists()) { // 判断文件是否存在
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    speak(lineTxt);
                }
                read.close();
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }

    }

    /**
     * 语音输出读取的文件
     *
     * @Title: speak
     * @Description: TODO
     * @param value
     *            void
     * @author Liu_xg
     * @date 2018年10月16日上午8:59:20
     */
    private static void speak(String value) {
        // ？？ 这个Sapi.SpVoice是需要安装什么东西吗，感觉平白无故就来了
        ActiveXComponent sap = new ActiveXComponent("Sapi.SpVoice");
        // Dispatch是做什么的？
        Dispatch sapo = (Dispatch) sap.getObject();
        try {

            // 音量 0-100
            sap.setProperty("Volume", new Variant(100));
            // 语音朗读速度 -10 到 +10
            sap.setProperty("Rate", new Variant(-1));

            Variant defalutVoice = sap.getProperty("Voice");

            Dispatch dispdefaultVoice = (Dispatch) defalutVoice.toDispatch();
            Variant allVoices = Dispatch.call(sapo, "GetVoices");
            Dispatch dispVoices = (Dispatch) allVoices.toDispatch();

            Dispatch setvoice = Dispatch.call(dispVoices, "Item", new Variant(1)).toDispatch();
            ActiveXComponent voiceActivex = new ActiveXComponent(dispdefaultVoice);
            ActiveXComponent setvoiceActivex = new ActiveXComponent(setvoice);

            Variant item = Dispatch.call(setvoiceActivex, "GetDescription");
            // 执行朗读
            Dispatch.call(sapo, "Speak", new Variant(value));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sapo.safeRelease();
            sap.safeRelease();
        }
    }
}
