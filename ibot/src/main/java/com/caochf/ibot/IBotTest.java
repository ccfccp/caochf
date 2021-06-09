package com.caochf.ibot;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import qa.engine.bot.sdk.ask.*;
import qa.engine.bot.sdk.ask.util.Constant;

import java.util.Scanner;

public class IBotTest {


    public static void main(String args[]) throws CloudNotInitializedException {
        final Log logger = LogFactory.getLog(IBotTest.class);
        // TODO Auto-generated method stub
        // 应用id
        String appKey = "SuMpcxwvYiY=";
        // 应用秘钥
        String appSecret = "tqRAHNDE/mI=";
        // 输入的询问内容
        String question = "你叫什么名字";

        // 初始化智能问答接口
        AskRequest askRequest = new AskRequest(appKey, appSecret, question, Constant.PRIMARY_TYPE, "",
                Constant.CUSTOM_PLATFORM);

        // 创建问答服务
        AskService askService = CloudServiceFactory.getInstance().createAskService();

        // init中设置CloudConfiguration参数
        CloudConfiguration cloudConfig = new CloudConfiguration();
        cloudConfig.setTalkUrl("http://10.10.4.87:8369/dialog-bot/service/qa/bot/sdk/talk");
        cloudConfig.setSimilarityUrl("http://10.10.4.87:8369/dialog-bot/service/qa/bot/sdk/similarities");
        cloudConfig.setMediaUrl("http://10.10.4.87:8369/dialog-bot/service/qa/bot/sdk/media");
        askService.init(cloudConfig);

        // 获取第一次询问的产生的sessionId
        AskResponse askResponse = null;
        SimilarityQuestionResponse simiRes = null;
        askResponse = askService.ask(askRequest);
        String sessionId = askResponse.getResponseAnswer().getSessionid();
        System.out.println("获取的sessionId："+sessionId);

        // 循环输入
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            question = scanner.nextLine();
            askRequest = new AskRequest(appKey, appSecret, question, Constant.PRIMARY_TYPE, sessionId,
                    Constant.CUSTOM_PLATFORM);
            try {

                //获取问题答案
                askResponse = askService.ask(askRequest);
                //获取相似列表
                simiRes = askService.similarityQuestion(askRequest, 4, 0.5);
                String answer = askResponse.getAnswer();
                if (askResponse.getResponseAnswer().getAnsType().equals("TM") && askResponse.getResponseAnswer().getTextMenu() != null) {
                    System.out.println("    文字菜单内容，请调用文字菜单接口解析：");
                    System.out.println(askResponse.getResponseAnswer().getTextMenu());
                }
                if(askResponse.getResponseAnswer().getAnsType().equals("T")){
                    System.out.print("    文本类型的答案：");
                    System.out.println(answer);
                }
                //素材类型(GT:图文 IMG:图片 AU:语音 VI:视频)，可以对每种类型的文件分别检测,这里只列举其中一种
                if(askResponse.getResponseAnswer().getAnsType().equals("AU")){
                    //获取媒体文件
                    MediaResponse mediaR = askService.getMedia(askRequest,askResponse.getResponseAnswer().getMediaId());
                    System.out.print("    音频文件：");
                    System.out.println(mediaR.getMedia().getMediaName());
                }
                System.out.print("    相似问题列表：");
                System.out.println(simiRes.getSimilarityQuestionAll().getAnswers());

            } catch (CloudNotInitializedException e) {
                if (logger.isErrorEnabled()) {
                    logger.error(e);
                }
            }
        }

    }

}
