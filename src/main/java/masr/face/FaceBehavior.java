package masr.face;

import masr.common.ImageHelper;
import masr.common.JSONConverter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Objects;

public class FaceBehavior implements Serializable {
    private final Boolean smile;
    private final Integer faceNumber;
    private final int faceArea;
    private final float[] facePosition;
    private final int[] photoSize;

    public FaceBehavior(Boolean smile, Integer faceNumber, int faceArea, float[] facePosition, int[] photoSize) {
        this.smile = smile;
        this.faceNumber = faceNumber;
        this.faceArea = faceArea;
        this.facePosition = facePosition;
        this.photoSize = photoSize;
    }

    public static FaceBehavior detectFromImage(String base64ImageContent) throws JSONException, IOException {
        JSONObject json = FaceAttribute.detect(base64ImageContent);

        System.out.println("FaceAttribute Result = " + json);

        JSONArray face_rect = (JSONArray) json.get("face_rect");
        System.out.println("Expression = " + json.get("expression"));
        int[] expression = JSONConverter.convertJSONArrayToIntegerArray((JSONArray) json.get("expression"));
        int faceNumber = (int) json.get("face_num");

        int[] photoSize = ImageHelper.getImageSizeFromBase64String(base64ImageContent);

        return new FaceBehavior(Arrays.equals(expression, new int[]{1}), faceNumber, FaceBehavior.computeFaceArea(face_rect), FaceBehavior.computeFacePosition(face_rect), photoSize);
    }

    public static FaceBehavior fromAliFaceAttribute(JSONObject attr, String base64Image) throws JSONException, IOException {
        JSONArray face_rect = (JSONArray) attr.get("face_rect");
        return new FaceBehavior(Arrays.equals(((org.json.JSONArray) attr.get("expression")).join(",").split(","), new String[]{"1"}), (Integer) attr.get("face_num"), FaceBehavior.computeFaceArea(face_rect), FaceBehavior.computeFacePosition(face_rect), ImageHelper.getImageSizeFromBase64String(base64Image));
    }

    public static int computeFaceArea(JSONArray face_rect) throws JSONException {
        return (int) face_rect.get(2) * (int) face_rect.get(3);
    }

    public static float[] computeFacePosition(JSONArray face_rect) throws JSONException {
        return new float[]{((int) face_rect.get(0) + (int) face_rect.get(2)) / 2, ((int) face_rect.get(1) + (int) face_rect.get(3)) / 2};
    }

    public Boolean getSmile() {
        return smile;
    }

    public Integer getFaceNumber() {
        return faceNumber;
    }

    public int getFaceArea() {
        return faceArea;
    }

    public int[] getPhotoSize() {
        return photoSize;
    }

    public float[] getFacePosition() {
        return facePosition;
    }

    public double getFaceAreaRatio() {
        if (this.getPhotoSize()[0] > 0 && this.getPhotoSize()[1] > 0) {
            return this.getFaceArea() / (this.getPhotoSize()[0] * this.getPhotoSize()[1]);
        }

        return 0;
    }
}
