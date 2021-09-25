package ml.lzwi.util;

public class Jsonp2JsonUtil {
    public static String jsonp2Json(String jsonp) {
        jsonp.replaceAll(" ", ""); //去除多余空格干扰
        int index = jsonp.indexOf("(");
        if (index != -1) {
            jsonp = jsonp.substring(index + 1, jsonp.lastIndexOf(")"));
        }
        return jsonp;
    }
}
