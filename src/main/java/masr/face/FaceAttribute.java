package masr.face;

import masr.ali.Signature;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Date;

public class FaceAttribute {
    public static JSONObject detect(String base64ImageContent) throws JSONException {
        JSONObject json = new JSONObject();

        json.put("type", "1");
        json.put("content", base64ImageContent);

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost request = new HttpPost("https://dtplus-cn-shanghai.data.aliyuncs.com/face/attribute");

        try {
            Date date = new Date();
            StringEntity params = new StringEntity(json.toString());
            request.addHeader("content-type", "application/json");
            request.addHeader("Accept", "application/json");
            request.addHeader("Authorization", "Dataplus LTAIS5QycUcfofJP:" + Signature.sign("POST", "application/json", json.toString(), "application/json", date, "/face/attribute", "fqeIDGTlhKouSoYHzS2eoXhHQESfQr"));
            request.addHeader("Date", Signature.toGMTString(date));
            request.setEntity(params);

            HttpResponse response = httpClient.execute(request);

            if (response != null) {
                String result = EntityUtils.toString(response.getEntity());
                return new JSONObject(result);
            }

            return new JSONObject("{}");
        } catch (IOException e) {
            e.printStackTrace();

            return new JSONObject("{}");
        } finally {
            request.abort();
        }
    }
}
