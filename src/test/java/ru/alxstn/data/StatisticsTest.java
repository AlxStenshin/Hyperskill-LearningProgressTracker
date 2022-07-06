package ru.alxstn.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.alxstn.data.Points;
import ru.alxstn.data.Statistics;
import ru.alxstn.data.Student;

import java.util.Arrays;

class StatisticsTest {

    static Points[] points;
    static Points[] partialPoints;
    static Student[] students;
    static Student[] partialStudents;

    Points expectedPopularity = new Points(4, 4, 4,4);
    Points expectedActivity = new Points(4, 4, 4, 4);
    Points expectedPartialPopularity = new Points(4, 2, 2, 1);
    Points expectedPartialActivity = new Points(4, 2, 2, 1);

    @BeforeAll
    static void setup() {
        points = new Points[] {
                new Points(5, 4, 3, 1),
                new Points(5, 4, 3, 1),
                new Points(5, 4, 3, 1),
                new Points(5, 4, 3, 1)};

        partialPoints = new Points[] {
                new Points(5, 3, 0, 1),
                new Points(5, 0, 3, 0),
                new Points(5, 4, 0, 0),
                new Points(5, 0, 3, 0)};

        students = new Student[] {
                new Student("Ricki", "Trovillion", "address1@mail.com"),
                new Student("Hedwig", "Wally", "address2@mail.com"),
                new Student("Annecorinne", "Ause", "address3@mail.com"),
                new Student("Gwenette", "Anagnos", "address4@mail.com")};

        students[0].addToPointsList(points[0]);
        students[1].addToPointsList(points[1]);
        students[2].addToPointsList(points[2]);
        students[3].addToPointsList(points[3]);

        partialStudents = new Student[] {
                new Student("Ricki", "Trovillion", "address1@mail.com"),
                new Student("Hedwig", "Wally", "address2@mail.com"),
                new Student("Annecorinne", "Ause", "address3@mail.com"),
                new Student("Gwenette", "Anagnos", "address4@mail.com")};

        partialStudents[0].addToPointsList(partialPoints[0]);
        partialStudents[1].addToPointsList(partialPoints[1]);
        partialStudents[2].addToPointsList(partialPoints[2]);
        partialStudents[3].addToPointsList(partialPoints[3]);
    }

    @Test
    void calcFullPopularity() {
        Points p = Statistics.calcPopularity(Arrays.stream(students));
        Assertions.assertEquals(p, expectedPopularity);
    }

    @Test
    void calcPartialPopularity() {
        Points p = Statistics.calcPopularity(Arrays.stream(partialStudents));
        Assertions.assertEquals(p, expectedPartialPopularity);
    }

    @Test
    void calcFullActivity() {
        Points p = Statistics.calcActivity(Arrays.stream(students));
        Assertions.assertEquals(p, expectedActivity);
    }

    @Test
    void calcPartialActivity() {
        Points p = Statistics.calcActivity(Arrays.stream(partialStudents));
        Assertions.assertEquals(p, expectedPartialActivity);
    }

    // Popularity Tests

    @Test
    void getGreatestValuesFromFullPopularity() {
        Assertions.assertEquals("Java DSA Spring Databases",
                Statistics.getGreatestValues(
                Statistics.calcPopularity(Arrays.stream(students))));
    }

    @Test
    void getSmallestValuesFromFullPopularity() {
        Assertions.assertEquals("n/a",
                Statistics.getSmallestValues(
                Statistics.calcPopularity(Arrays.stream(students))));
    }

    @Test
    void getGreatestValuesFromPartialPopularity() {
        Assertions.assertEquals("Java",
                Statistics.getGreatestValues(
                Statistics.calcPopularity(Arrays.stream(partialStudents))));
    }

    @Test
    void getSmallestValuesFromPartialPopularity() {
        Assertions.assertEquals("Spring",
                Statistics.getSmallestValues(
                Statistics.calcPopularity(Arrays.stream(partialStudents))));
    }

    // Activity Tests

    @Test
    void getGreatestValuesFromFullActivity() {
        Assertions.assertEquals("Java DSA Spring Databases",
                Statistics.getGreatestValues(
                Statistics.calcActivity(Arrays.stream(students))));
    }

    @Test
    void getSmallestValuesFromFullActivity() {
        Assertions.assertEquals("n/a",
                Statistics.getSmallestValues(
                Statistics.calcActivity(Arrays.stream(students))));
    }

    @Test
    void getGreatestValuesFromPartialActivity() {
        Assertions.assertEquals("Java",
                Statistics.getGreatestValues(
                Statistics.calcActivity(Arrays.stream(partialStudents))));
    }

    @Test
    void getSmallestValuesFromPartialActivity() {
        Assertions.assertEquals("Spring",
                Statistics.getSmallestValues(
                Statistics.calcActivity(Arrays.stream(partialStudents))));
    }

}