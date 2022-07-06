package ru.alxstn.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.alxstn.data.Points;
import ru.alxstn.data.PointsStats;

class PointsStatsTest {

    static PointsStats psd;
    static PointsStats psi;
    static PointsStats psme;
    static PointsStats pssee;
    static PointsStats psaem;
    static PointsStats psaeq;

    @BeforeAll
    static void setup() {
        Points decreasingPoints = new Points(4, 3, 2, 1);
        Points increasingPoints = new Points(1, 2, 3, 4);
        Points middleEmptyPoints = new Points(1, 0, 0, 1);
        Points startsEndsEmptyPoints = new Points(0, 2, 3, 0);
        Points allEmptyPoints = new Points(0, 0, 0, 0);
        Points allEqualsPoints = new Points(3, 3, 3, 3);

        psd = new PointsStats(decreasingPoints);
        psi = new PointsStats(increasingPoints);
        psme = new PointsStats(middleEmptyPoints);
        pssee = new PointsStats(startsEndsEmptyPoints);
        psaem = new PointsStats(allEmptyPoints);
        psaeq = new PointsStats(allEqualsPoints);
    }

    @Test
    void getGreatestOnDecreasing() {
        Assertions.assertEquals("Java", psd.getGreatest());
    }

    @Test
    void getSmallestOnDecreasing() {
        Assertions.assertEquals("Spring", psd.getSmallest());
    }

    @Test
    void getGreatestOnIncreasing() {
        Assertions.assertEquals("Spring", psi.getGreatest());
    }

    @Test
    void getSmallestOnIncreasing() {
        Assertions.assertEquals("Java", psi.getSmallest());
    }

    @Test
    void getGreatestOnMiddleEmpty() {
        Assertions.assertEquals("Java Spring", psme.getGreatest());
    }

    @Test
    void getSmallestOnMiddleEmpty() {
        Assertions.assertEquals("n/a", psme.getSmallest());
    }

    @Test
    void getGreatestOnStartsEndsEmptyPoints() {
        Assertions.assertEquals("Databases", pssee.getGreatest());
    }

    @Test
    void getSmallestOnStartsEndsEmptyPoints() {
        Assertions.assertEquals("DSA", pssee.getSmallest());
    }

    @Test
    void getGreatestOnAllEmpty() {
        Assertions.assertEquals("n/a", psaem.getGreatest());
    }

    @Test
    void getSmallestOnAllEmpty() {
        Assertions.assertEquals("n/a", psaem.getSmallest());
    }

    @Test
    void getGreatestOnAllEquals() {
        Assertions.assertEquals("Java DSA Spring Databases", psaeq.getGreatest());
    }

    @Test
    void getSmallestOnAllEquals() {
        Assertions.assertEquals("n/a", psaeq.getSmallest());
    }
}