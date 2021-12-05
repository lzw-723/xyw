package ml.lzwi;

import java.io.File;
import java.util.Optional;

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
    private Optional<Callback> callback = Optional.empty();
    private Thread thread;

    private XywHelper() {
    }

    public void setCallback(Callback callback) {
        this.callback = Optional.ofNullable(callback);
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
        loop = true;
        thread = new Thread(() -> {
            callback.ifPresent((action -> {
                action.onStart();
            }));

            while (loop) {
                XywUtil.Result result = XywUtil.get(XywUtil.Action.check, ddddd, upass);
                callback.ifPresent((action -> {
                    action.onAction(Action.check, result);
                    if (!result.isSuccess()) {
                        Result result2 = XywUtil.get(Action.login, ddddd, upass);
                        action.onAction(Action.login, result2);
                    }
                }));

                try {
                    Thread.sleep(4000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            callback.ifPresent((action -> {
                action.onStop();
            }));
        });
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
