package masr.common;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayHelperTest {
    @Test
    public void convertBytesToHex() throws Exception {
        assertEquals("00", ArrayHelper.convertBytesToHex(new byte[]{0x00}));
        assertEquals("01", ArrayHelper.convertBytesToHex(new byte[]{0x01}));
        assertEquals("02", ArrayHelper.convertBytesToHex(new byte[]{0x02}));
        assertEquals("09", ArrayHelper.convertBytesToHex(new byte[]{0x09}));
        assertEquals("10", ArrayHelper.convertBytesToHex(new byte[]{0x10}));
        assertEquals("1A", ArrayHelper.convertBytesToHex(new byte[]{0x1A}));
    }

}