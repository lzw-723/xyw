package ml.lzwi.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigHelper {
    public static Config newConfig(long ddddd, String upass) {
        
        Config config = new Config();

        config.setDdddd(ddddd);
        config.setUpass(upass);

        return config;
    }

    public static Config readFromFile(String filePath) {

        Config config = null;
        Properties properties = new Properties();

        try {

            File file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            properties.load(new FileInputStream(file));

            long ddddd = Long.valueOf(properties.getProperty("ddddd", "0"));
            String upass = properties.getProperty("upass", "");

            config = newConfig(ddddd, upass);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return config;
    }
}
