package com.caochf.lucene.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileScanUtil {
    static String SCAN_PATH = "F:\\work\\solr-8.7.0\\example";// 检索的根目录.
    static Map<String,List<String>> pointMap = new HashMap<String,List<String>>();// Map<keyword,List<filePath>
    static List<String> keywordList = new ArrayList<String>();
    static {
        // 设置要检索的关键字.
        setKeyWordList();
    }

    public static void main(String[] args){
        System.out.println("文件内容检索-----begin!");
        boolean scanFlag = scanFilePath(SCAN_PATH);
        if(scanFlag){
            System.out.println("找到需要检索的内容！pointMap="+pointMap);
        }

        System.out.println("文件内容检索-----end!");
    }

    /**
     * 设置要检索的关键字.
     */
    private static void setKeyWordList() {
        keywordList.add("10.10.4.87");
    }

    /**
     * 检索目录.
     * @param filePath
     * @return
     */
    public static boolean scanFilePath(String filePath){
        File dirFile = new File(filePath);
        boolean existFlag = false;
        if(dirFile!=null&&dirFile.exists()&&dirFile.isDirectory()){
            List<File> listFiles = collectFileList(dirFile);
            for(File file:listFiles){
                String fileContent = scanFileContent(file);
                if(getExistFlag(fileContent,file)){
                    existFlag = true;
                }
            }
        }else{
            System.out.println("路径【"+filePath+"】不是目录，请重新输入！");
        }
        return existFlag;
    }

    /**
     * 取目录下的所有具体文件.
     * @param dirFile
     * @return
     */
    private static List<File> collectFileList(File dirFile) {
        List<File> fileList = new ArrayList<File>();
        File[] listFiles = dirFile.listFiles();
        if(listFiles!=null&&listFiles.length>0){
            for(File f:listFiles){
                if(f.isDirectory()){
                    fileList.addAll(collectFileList(f));
                }else{
                    fileList.add(f);
                }
            }
        }
        return fileList;
    }

    /**
     * 文件内容读取.
     * @param file
     * @return
     */
    public static String scanFileContent(File file){
        String fileContent = null;
        if(file!=null&&!file.isDirectory()){
            fileContent = FileReadUtil.readFileContent(file);
        }
        return fileContent;
    }

    /**
     * 文件内容中判断关键字是否存在.
     * @param fileContent
     * @param file
     * @return
     */
    public static boolean getExistFlag(String fileContent,File file){
        boolean existFlag = false;
        if(fileContent!=null&&keywordList!=null&&keywordList.size()>0){
            for(String keyword:keywordList){
                if(keyword!=null&&!"".equals(keyword)){
                    if(fileContent.indexOf(keyword)>-1){
                        existFlag = true;
                        pointMap.put(keyword,getFilePathList(pointMap.get(keyword),file.getPath()));
                    }
                }
            }
        }
        return existFlag;
    }

    /**
     * 添加新的文件路径.
     * @param curKeywordList
     * @param path
     * @return
     */
    private static List<String> getFilePathList(List<String> curKeywordList, String path) {
        List<String> resultList = curKeywordList;
        if(resultList==null){
            resultList = new ArrayList<String>();
        }
        resultList.add(path);

        return resultList;
    }
}
