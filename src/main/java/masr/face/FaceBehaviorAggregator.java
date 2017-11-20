package masr.face;

import masr.common.Stats;

import java.io.Serializable;
import java.util.Arrays;

public class FaceBehaviorAggregator implements Serializable {
    private final float smile;
    private final float faceRepresent;
    private final double meanFaceArea;
    private final double faceAreaDeviation;
    private final float meanFaceAreaRatio;
    private final float faceAreaRatioDeviation;
    private final double meanFacePositionX;
    private final double facePositionXDeviation;
    private final double meanFacePositionY;
    private final double facePositionYDeviation;

    public FaceBehaviorAggregator(float smile, float faceRepresent, double meanFaceArea, double faceAreaDeviation, float meanFaceAreaRatio, float faceAreaRatioDeviation, double meanFacePositionX, double facePositionXDeviation, double meanFacePositionY, double facePositionYDeviation) {
        this.smile = smile;
        this.faceRepresent = faceRepresent;
        this.meanFaceArea = meanFaceArea;
        this.faceAreaDeviation = faceAreaDeviation;
        this.meanFaceAreaRatio = meanFaceAreaRatio;
        this.faceAreaRatioDeviation = faceAreaRatioDeviation;
        this.meanFacePositionX = meanFacePositionX;
        this.facePositionXDeviation = facePositionXDeviation;
        this.meanFacePositionY = meanFacePositionY;
        this.facePositionYDeviation = facePositionYDeviation;
    }

    public static FaceBehaviorAggregator from(FaceBehavior[] fbs) throws Exception {
        if (fbs.length <= 1) {
            throw new Exception("Need more FaceBehaviors to aggregate!");
        }

        long smileFaces = Arrays.stream(fbs).filter(fb -> fb.getSmile()).count();
        long faces = Arrays.stream(fbs).filter(fb -> fb.getFaceNumber() == 1).count();
        Stats stats = new Stats(Arrays.stream(fbs).mapToDouble(fb -> (double) fb.getFaceArea()).toArray());
        double meanFaceArea = stats.getMean();
        double faceAreaDeviation = stats.getSampleStandardDeviation();

        Stats faceXPositionStats = new Stats(Arrays.stream(fbs).mapToDouble(fb -> fb.getFacePosition()[0]).toArray());
        Stats faceYPositionStats = new Stats(Arrays.stream(fbs).mapToDouble(fb -> fb.getFacePosition()[1]).toArray());

        Stats ratioStats = new Stats(Arrays.stream(fbs).mapToDouble(fb -> fb.getFaceAreaRatio()).toArray());

        return new FaceBehaviorAggregator(smileFaces / fbs.length, faces / fbs.length, meanFaceArea, faceAreaDeviation, 0, 0, faceXPositionStats.getMean(), faceXPositionStats.getSampleStandardDeviation(), faceYPositionStats.getMean(), faceYPositionStats.getSampleStandardDeviation());
    }

    public float getSmile() {
        return smile;
    }

    public float getFaceRepresent() {
        return faceRepresent;
    }

    public double getMeanFaceArea() {
        return meanFaceArea;
    }

    public double getFaceAreaDeviation() {
        return faceAreaDeviation;
    }

    public float getMeanFaceAreaRatio() {
        return meanFaceAreaRatio;
    }

    public float getFaceAreaRatioDeviation() {
        return faceAreaRatioDeviation;
    }

    public double getMeanFacePositionX() {
        return meanFacePositionX;
    }

    public double getFacePositionXDeviation() {
        return facePositionXDeviation;
    }

    public double getMeanFacePositionY() {
        return meanFacePositionY;
    }

    public double getFacePositionYDeviation() {
        return facePositionYDeviation;
    }
}
