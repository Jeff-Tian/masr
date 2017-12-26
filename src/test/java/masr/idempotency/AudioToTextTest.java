package masr.idempotency;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.nio.file.Paths;

import static org.junit.Assert.*;

public class AudioToTextTest {
    @Test
    public void convert() throws Exception {
        Jedis jedis = new Jedis("uat.redis.server");
        jedis.set("3A83337688E10A251F2C67EE1897A715", "result");

        assertEquals("result", AudioToText.convert(Paths.get(getClass().getClassLoader().getResource("hash-test.txt").toURI()).toString()));
    }

}