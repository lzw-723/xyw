package ml.lzwi.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Jsonp2JsonUtilTest {
    @Test
    public void testJsonp2Json() {
        assertEquals(Jsonp2JsonUtil.jsonp2Json("jsonp({})"), "{}");
        assertEquals(Jsonp2JsonUtil.jsonp2Json("({})"), "{}");
        assertEquals(Jsonp2JsonUtil.jsonp2Json("jsonp({})     "), "{}");
        assertEquals(Jsonp2JsonUtil.jsonp2Json("jsonp({msg:\"()\"})"), "{msg:\"()\"}");
    }
}
