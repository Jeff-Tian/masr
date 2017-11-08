package masr.face;

import com.alibaba.fastjson.JSONObject;
import masr.ali.Signature;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Date;

public class SmileDetector {
    public static String detect(String imagePath) {
        JSONObject json = new JSONObject();
        json.put("type", "1");
        json.put("content", "");

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost request = new HttpPost("https://dtplus-cn-shanghai.data.aliyuncs.com/face/attribute");

        try {
            StringEntity params = new StringEntity(json.toString());
            request.addHeader("content-type", "application/json");
            request.addHeader("Accept", "application/json");
            request.addHeader("Authorization", "Dataplus LTAIS5QycUcfofJP:" + Signature.sign("POST", "application/json", json.toString(), "application/json", "/face/attribute", "fqeIDGTlhKouSoYHzS2eoXhHQESfQr"));
            request.addHeader("Date", new Date().toString());
            request.setEntity(params);

            HttpResponse response = httpClient.execute(request);

            if (response != null) {
                String result = EntityUtils.toString(response.getEntity());
                return result;
            }

            return "";
        } catch (IOException e) {
            e.printStackTrace();

            return "";
        } finally {
            request.abort();
        }
    }
}
