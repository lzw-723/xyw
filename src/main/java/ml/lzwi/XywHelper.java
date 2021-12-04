package ml.lzwi;

import java.io.File;

import ml.lzwi.util.ConfigHelper;
import ml.lzwi.util.XywUtil;
import ml.lzwi.util.ConfigHelper.Config;
import ml.lzwi.util.XywUtil.Action;
import ml.lzwi.util.XywUtil.Result;

public class XywHelper {
    private final static XywHelper XYW_HELPER = new XywHelper();

    // private static final String API_URL = "http://192.168.167.46";
    private static final String CONFIG_FILE_PATH = new File(System.getProperty("user.dir"), "config.properties")
            .getAbsolutePath();

    private long ddddd = 0L;
    private String upass = "";
    private boolean loop = true;
    private Callback callback;
    private Thread thread = new Thread(() -> {
        callback.onStart();

        while (loop) {
            XywUtil.Result result = XywUtil.get(XywUtil.Action.check, ddddd, upass);
            callback.onAction(Action.check, result);

            if (!result.isSuccess()) {
                result = XywUtil.get(Action.login, ddddd, upass);
                callback.onAction(Action.login, result);
            }

            try {
                Thread.sleep(4000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        callback.onStop();
    });

    private XywHelper() {
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public static XywHelper getInstance() {
        return XYW_HELPER;
    }

    public void init() {
        loadConfig();
    }

    public void loadConfig() {
        Config config = ConfigHelper.readFromFile(CONFIG_FILE_PATH);
        ddddd = config.getDdddd();
        upass = config.getUpass();
    }

    public void start() {
        thread.start();
    }

    public void stop() {
        loop = false;
    }

    public static abstract class Callback {

        public abstract void onStart();

        public abstract void onStop();

        public abstract void onAction(Action action, Result result);
    }
}
