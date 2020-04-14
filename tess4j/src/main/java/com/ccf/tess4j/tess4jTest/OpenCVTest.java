package com.ccf.tess4j.tess4jTest;


import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;

import java.io.File;

public class OpenCVTest {
    public static void main(String[] args) {
        System.out.println("Welcome to OpenCV " + Core.VERSION);
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat m = Mat.eye(3, 3, CvType.CV_8UC1);
        System.out.println("m = " + m.dump());


        String fileName = "F:\\tmp\\4.jpg"; //设置图片的路径
        if (!new File(fileName).exists()){
            System.out.println("文件不存在");
        }else{

            Mat srcImg = Imgcodecs.imread(fileName);  //opencv读取
            if (srcImg.empty()){
                System.out.println("加载图片失败！");
            }else{
                HighGui.imshow("image",srcImg); //显示
                HighGui.waitKey(0);
            }
        }
//        Imgcodecs.im
    }
}
