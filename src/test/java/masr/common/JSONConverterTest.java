package masr.common;

import org.json.JSONArray;
import org.junit.Test;

import static org.junit.Assert.*;

public class JSONConverterTest {
    @Test
    public void convertJSONArrayToIntegerArray() throws Exception {
        assertArrayEquals(new int[]{1, 2, 3, 4}, JSONConverter.convertJSONArrayToIntegerArray(new JSONArray("[1, 2, 3, 4]")));
    }

}