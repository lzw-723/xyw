package ml.lzwi.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class XywUtil {
    private static String API_URL = "http://192.168.167.46";
    // private static String CONFIG_FILE_PATH = new
    // File(System.getProperty("user.dir"), "config.properties").getAbsolutePath();

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

    public static void setAPIURL(String url) {
        retrofit = new Retrofit.Builder().baseUrl(API_URL).build();
    }

    /**
     * 执行请求（登录、检查状态或是登出）
     * 
     * @param action 请求动作
     * @param ddddd  账号
     * @param upass  密码
     * @return JSONObject
     */
    public static Result get(Action action, long ddddd, String upass) {
        Call<ResponseBody> call = act.check("");

        switch (action) {
            case login:
                call = act.login("dr", ddddd, upass, 0L);
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

        Result result = new Result(null);
        try {
            String jsonp = call.execute().body().string();
            String json = Jsonp2JsonUtil.jsonp2Json(jsonp);
            JSONObject status = JSON.parseObject(json);
            result = new Result(status);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * InnerXywUtil
     */
    public static class Result {
        private boolean success = false;
        private JSONObject data = null;

        public Result(JSONObject data) {
            this.success = data == null || data.getShort("result") == 1;
            this.data = data;
        }

        public boolean isSuccess() {
            return success;
        }

        protected void setSuccess(boolean success) {
            this.success = success;
        }

        public JSONObject getData() {
            return data;
        }

        protected void setData(JSONObject data) {
            this.data = data;
        }

    }
}
