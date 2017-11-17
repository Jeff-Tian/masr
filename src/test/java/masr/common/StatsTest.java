package masr.common;

import org.junit.Test;

import static org.junit.Assert.*;

public class StatsTest {
    private double[] data = new double[]{10, 2, 38, 23, 38, 23, 21};
    private Stats stat = new Stats(data);

    @Test
    public void getMean() {
        assertEquals(22.142857142857142, stat.getMean(), 0);
    }

    @Test
    public void getStandardDeviation() {
        assertEquals(13.284434142114991, stat.getSampleStandardDeviation(), 0);
    }
}