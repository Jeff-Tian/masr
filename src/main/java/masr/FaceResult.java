package masr;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.hateoas.ResourceSupport;

public class FaceResult extends ResourceSupport {
    private final double smilePercent;
    private final double faceAppearancePercent;
    private final double faceMeanArea;
    private final double faceAreaDeviation;

    @JsonCreator
    public FaceResult(double smilePercent, double faceAppearancePercent, double faceMeanArea, double faceAreaDeviation) {
        this.smilePercent = smilePercent;
        this.faceAppearancePercent = faceAppearancePercent;
        this.faceMeanArea = faceMeanArea;
        this.faceAreaDeviation = faceAreaDeviation;
    }

    public double getSmilePercent() {
        return smilePercent;
    }

    public double getFaceAppearancePercent() {
        return faceAppearancePercent;
    }

    public double getFaceMeanArea() {
        return faceMeanArea;
    }

    public double getFaceAreaDeviation() {
        return faceAreaDeviation;
    }
}
