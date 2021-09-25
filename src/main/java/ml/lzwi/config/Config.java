package ml.lzwi.config;

public class Config {
    private long ddddd = 0L; // 学号
    private String upass = ""; // 密码

    protected Config() {
    }

    public long getDdddd() {
        return ddddd;
    }

    protected void setDdddd(long ddddd) {
        this.ddddd = ddddd;
    }

    public String getUpass() {
        return upass;
    }

    protected void setUpass(String upass) {
        this.upass = upass;
    }
}
