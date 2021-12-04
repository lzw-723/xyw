package ml.lzwi.util;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import ml.lzwi.AppTest;
import ml.lzwi.util.ConfigHelper.Config;

public class ConfigHelperTest {

    @Test
    public void testNewConfig() {
        Config config = ConfigHelper.newConfig(2333333666L, "326666");
        assertEquals(2333333666L, config.getDdddd());
        assertEquals("326666", config.getUpass());
    }

    @Test
    public void testReadFromFile() throws IOException {
        String testFilePath = AppTest.class.getClassLoader().getResource("test.properties").getFile();
        Config config = ConfigHelper.readFromFile(testFilePath);
        assertEquals(2333333666L, config.getDdddd());
        assertEquals("326666", config.getUpass());
    }
}
