package masr.face;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.DoubleSummaryStatistics;

public class FaceBehaviorAggregator {
    private final float smile;
    private final float faceRepresent;
    private final float meanFaceArea;
    private final double faceAreaDeviation;
    private final float meanFaceAreaRatio;
    private final float faceAreaRatioDeviation;

    public FaceBehaviorAggregator(float smile, float faceRepresent, float meanFaceArea, double faceAreaDeviation, float meanFaceAreaRatio, float faceAreaRatioDeviation) {
        this.smile = smile;
        this.faceRepresent = faceRepresent;
        this.meanFaceArea = meanFaceArea;
        this.faceAreaDeviation = faceAreaDeviation;
        this.meanFaceAreaRatio = meanFaceAreaRatio;
        this.faceAreaRatioDeviation = faceAreaRatioDeviation;
    }

    public static FaceBehaviorAggregator from(FaceBehavior[] fbs) throws Exception {
        if (fbs.length <= 1) {
            throw new Exception("Need more FaceBehaviors to aggregate!");
        }

        long smileFaces = Arrays.stream(fbs).filter(fb -> fb.getSmile()).count();
        long faces = Arrays.stream(fbs).filter(fb -> fb.getFaceNumber() == 1).count();
        long totalFaceArea = Arrays.stream(fbs).map(fb -> fb.getFaceArea()).reduce(0, (prev, current) -> prev + current);
        long meanFaceArea = totalFaceArea / fbs.length;
//        long squaredSum = Arrays.stream(fbs).map(fb -> fb.getFaceArea() - meanFaceArea).map(d -> d * d).reduce(, (prev, current) -> prev + current);
//        double dev = Math.sqrt(squaredSum / fbs.length - 1);

        return new FaceBehaviorAggregator(smileFaces / fbs.length, faces / fbs.length, meanFaceArea, 0, 0, 0);
    }
}
