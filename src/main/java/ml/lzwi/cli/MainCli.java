package ml.lzwi.cli;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import com.alibaba.fastjson.JSONObject;

import ml.lzwi.XywHelper;
import ml.lzwi.util.XywUtil;
import ml.lzwi.util.XywUtil.Action;
import ml.lzwi.util.XywUtil.Result;

/**
 * 命令行入口
 * 
 * @author lzw-723
 */
public class MainCli {
    /**
     *
     */
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat();

    public static void main(String[] args) throws InterruptedException {
        // long ddddd = 0L;
        // String upass = "";

        // System.out.println("——————程序开始运行——————");

        // Scanner scanner = new Scanner(System.in);
        // // System.out.println("请输入学号：");
        // // ddddd = scanner.nextLong();
        // // System.out.println("请输入密码：");
        // // upass = scanner.next();
        // while (true) {

        // Thread.sleep(2000);

        // JSONObject result = XywUtil.get(Action.check);

        // if (result == null) {
        // System.out.println("请检查物理连接！");
        // break;
        // }

        // System.out.println("当前时间：" + SIMPLE_DATE_FORMAT.format(new Date()));
        // System.out.println("正在检查登录状态……");

        // if (read(result)) {
        // System.out.println("已登录。");
        // } else {
        // System.out.println("未登录，正尝试重新登录……");
        // result = XywUtil.get(Action.login);
        // if (read(result)) {
        // System.out.println("登录成功！");
        // } else {
        // System.out.println("登录失败，请检查配置！");
        // }
        // }

        // System.out.println("——————休眠2秒——————");

        // }

        XywHelper xywHelper = XywHelper.getInstance();
        xywHelper.init();
        xywHelper.setCallback(new XywHelper.Callback() {

            @Override
            public void onStart() {
                // TODO Auto-generated method stub
                log("开始运行");
            }

            @Override
            public void onStop() {
                // TODO Auto-generated method stub
                log("停止运行");
            }

            @Override
            public void onAction(Action action, Result result) {
                // TODO Auto-generated method stub
                switch (action) {
                    case login:
                        log("尝试登陆：" + (result.isSuccess() ? "成功" : "失败"));
                        break;
                    case check:
                        log("检查登陆状态：" + (result.isSuccess() ? "已登陆" : "未登陆"));
                        break;
                    case logout:
                        log("尝试注销登陆：" + (result.isSuccess() ? "成功" : "失败"));
                        break;

                    default:
                        break;
                }
            }

        });
        xywHelper.start();
    }

    public static void log(String msg) {
        System.out.printf("[%s] %s\n", SIMPLE_DATE_FORMAT.format(new Date()), msg);
    }

    // private static boolean read(JSONObject jsonObject) {
    // if (jsonObject == null) {
    // return false;
    // }
    // return jsonObject.getShort("result") == 1;
    // }
}
