package masr.common;

import java.util.Arrays;

public class Stats {
    private final double data[];
    private final double mean;

    public Stats(double[] data) {
        this.data = data;

        this.mean = Arrays.stream(this.data).reduce(0, (prev, current) -> prev + current) / this.data.length;
    }

    public double getMean() {
        return mean;
    }

    public double getSampleStandardDeviation() {
        return Math.sqrt(
                Arrays
                        .stream(this.data)
                        .map(d -> d - this.getMean())
                        .map(d -> d * d)
                        .reduce(0, (prev, current) -> prev + current)
                        /
                        (this.data.length - 1)
        );
    }
}
