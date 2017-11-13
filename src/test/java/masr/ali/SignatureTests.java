package masr.ali;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;


public class SignatureTests {
    @Test
    public void testSignature() {
        assertEquals("8+oNp2tVroioJ/GHnAkeqPratqc=", Signature.HMACSha1("", "xxx"));
    }

    @Test
    public void testToGMTString() {
        assertEquals("Wed, 07 Nov 3917 16:00:00 GMT", Signature.toGMTString(new Date(2017, 10, 8)));
    }

    @Test
    public void testEncode() {
        assertEquals("1B2M2Y8AsgTpgAmY7PhCfg==", Signature.encode(""));
    }

    @Test
    public void testStringToSign() {
        assertEquals("POST\n" +
                "application/json\n" +
                "BTusywYj5p8LO7idH/nNMw==\n" +
                "application/json\n" +
                "Wed, 07 Nov 3917 16:00:00 GMT\n" +
                "/face/attribute", Signature.getStringToSign("POST", "application/json", "{\"Content\":\"\", \"type\":1}", "application/json", new Date(2017, 10, 8), "/face/attribute"));
    }

    @Test
    public void testSign() {
        Date date = new Date(2017, 10, 9);
        assertEquals("O7j1uIVg9mH59hmJSP1dingSBYg=", Signature.sign("POST", "application/json", "", "application/json", date, "/face/attribute", "xxx"));
    }
}
