package ml.lzwi.cli;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.alibaba.fastjson.JSONObject;

import ml.lzwi.util.XywHelper;
import ml.lzwi.util.XywHelper.Action;

/**
 * @author lzw-723
 * 命令行入口
*/
public class MainCli {
    /**
     *
     */
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat();

    public static void main(String[] args) throws InterruptedException {
        System.out.println("——————程序开始运行——————");
        while (true) {

            Thread.sleep(2000);

            JSONObject result = XywHelper.get(Action.check);

            if (result == null) {
                System.out.println("请检查物理连接！");
                break;
            }

            System.out.println("当前时间：" + SIMPLE_DATE_FORMAT.format(new Date()));
            System.out.println("正在检查登录状态……");

            if (read(result)) {
                System.out.println("已登录。");
            } else {
                System.out.println("未登录，正尝试重新登录……");
                result = XywHelper.get(Action.login);
                if (read(result)) {
                    System.out.println("登录成功！");
                } else {
                    System.out.println("登录失败，请检查配置！");
                }
            }

            System.out.println("——————休眠2秒——————");

        }
    }

    private static boolean read(JSONObject jsonObject) {
        if (jsonObject == null) {
            return false;
        }
        return jsonObject.getShort("result") == 1;
    }
}
