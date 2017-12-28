package masr.idempotency;

import com.alibaba.fastjson.JSON;
import com.iflytek.msp.cpdb.lfasr.model.Message;
import masr.common.FileHelper;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class AudioToText {
    public static String convert(String filePath) throws IOException, NoSuchAlgorithmException {
        Jedis jedis = new Jedis(Objects.equals(System.getenv("NODE_ENV"), "prd") ? "live.redis.server" : "uat.redis.server");
        String hash = "asr_" + FileHelper.getHash(filePath);
        String cache = jedis.get(hash);

        if (cache == null) {
            Message message = Lfasr.Lfasr.convertAudioToText(filePath);

            cache = JSON.toJSONString(message);
            jedis.set(hash, cache);
        }

        return cache;
    }
}
