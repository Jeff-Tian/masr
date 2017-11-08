package masr.ali;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Date;
import java.util.Locale;
import java.util.SimpleTimeZone;

public class Signature {
    public static String sign(String method, String accept, String body, String contentType, String path, String secret) {
        String s = getStringToSign(method, accept, body, contentType, new Date(), path);

        return HMACSha1(s, secret);
    }

    public static String HMACSha1(String s, String secret) {
        String result;
        try {
            SecretKeySpec signingKey = new SecretKeySpec(secret.getBytes(), "HmacSHA1");
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(signingKey);
            byte[] rawHmac = mac.doFinal(s.getBytes());
            result = Base64.getEncoder().encodeToString(rawHmac);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();

            throw new Error("Failed to generate HMAC:" + e.getMessage());
        }

        return result;
    }

    public static String getStringToSign(String method, String accept, String body, String contentType, Date date, String path) {
        return method + "\n" + accept + "\n" + encode(body) + "\n" + contentType + "\n" + toGMTString(date) + "\n" + path;
    }

    public static String toGMTString(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss z", Locale.UK);
        df.setTimeZone(new SimpleTimeZone(0, "GMT"));

        return df.format(date);
    }

    public static String encode(String s) {
        if (s == null) {
            return null;
        }

        String encodeStr = "";
        byte[] utfBytes = s.getBytes();
        MessageDigest md;

        try {
            md = MessageDigest.getInstance("MD5");
            md.update(utfBytes);

            byte[] md5Bytes = md.digest();

            encodeStr = Base64.getEncoder().encodeToString(md5Bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();

            throw new Error("Failed to generate MD5 : " + e.getMessage());
        }

        return encodeStr;
    }
}
