package ml.lzwi.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import ml.lzwi.config.Config;
import ml.lzwi.config.ConfigHelper;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class XywHelper {
    private static final String API_URL = "http://192.168.167.46";
    private static final String CONFIG_FILE_PATH = "/config.properties";

    private interface Act {

        // callback=dr1003&DDDDD=2111111111&upass=23333&0MKKey=123456&R1=0&R2=&R3=0&R6=0&para=00&v6ip=&terminal_type=1&lang=zh-cn&jsVersion=4.1&v=6041&lang=zh
        @GET("/drcom/login")
        Call<ResponseBody> login(@Query("callback") String callback, @Query("DDDDD") long ddddd,
                @Query("upass") String upass, @Query("0MKKey") long mkkey);

        // callback=dr1002&jsVersion=4.1&v=7401&lang=zh
        @GET("/drcom/chkstatus")
        Call<ResponseBody> check(@Query("callback") String callback);

        // callback=dr1003&jsVersion=4.1&v=8593&lang=zh
        @GET("/drcom/logout")
        Call<ResponseBody> logout(@Query("callback") String callback);

    }

    public enum Action {
        login, check, logout
    }

    private static Retrofit retrofit = new Retrofit.Builder().baseUrl(API_URL).build();

    private static Act act = retrofit.create(Act.class);
    private static Config config = ConfigHelper.readFromFile(CONFIG_FILE_PATH);

    public static JSONObject get(Action action) {
        Call<ResponseBody> call = act.check("");

        switch (action) {
            case login:
                call = act.login("dr", config.getDdddd(), config.getUpass(), 0L);
                break;
            case check:
                call = act.check("");
                break;
            case logout:
                call = act.logout("");
                break;

            default:
                call = act.check("");
                break;
        }

        JSONObject status = null;
        try {
            String jsonp = call.execute().body().string();
            String json = Jsonp2JsonUtil.jsonp2Json(jsonp);
            // System.out.println(json);
            status = JSON.parseObject(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
}
