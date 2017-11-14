package masr.common;

import org.json.JSONArray;
import org.json.JSONException;

public class JSONConverter {
    public static int[] convertJSONArrayToIntegerArray(JSONArray jsonArray) throws JSONException {
        int[] res = new int[jsonArray.length()];

        for (int i = 0; i < jsonArray.length(); i++) {
            res[i] = (int) jsonArray.get(i);
        }

        return res;
    }
}
