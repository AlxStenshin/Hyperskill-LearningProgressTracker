package ru.alxstn.data;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Statistics {

    private static String mostPopular = "n/a";
    private static String leastPopular = "n/a";
    private static String mostActive = "n/a";
    private static String leastActive = "n/a";
    private static String easiest = "n/a";
    private static String hardest = "n/a";

    // The most popular has the biggest number of enrolled students;
    protected static Points calcPopularity(Stream<Student> storage) {
        return storage
                .map(Student::getTotalPoints)
                .map(Points::getCompleted)
                .reduce(new Points(), Points::add);
    }

    // Total sum over all of <Points>
    protected static Points calcTotalSubmittedPoints(Stream<Student> storage) {
        return storage
                .map(Student::getCoursePoints)
                .flatMap(Collection::stream)
                .reduce(new Points(), Points::add);
    }

    // Higher student activity means a bigger number of completed tasks
    // Every non-zero submission over all of <Points>
    protected static Points calcActivity(Stream<Student> storage) {
        return storage
                .map(Student::getCoursePoints)
                .flatMap(Collection::stream)
                .map(Points::getCompleted)
                .reduce(new Points(), Points::add);
    }

    static int getComplexity(int total, int completed) {
        return (total == 0 || completed == 0) ? 0 : total / completed;
    }

    // The easiest course has the highest average grade per assignment;
    protected static Points calcComplexity(Points fullSumm, Points activity) {
        var complexity = new Points();
        complexity.setJavaPoints(getComplexity(fullSumm.getJavaPoints(), activity.getJavaPoints()));
        complexity.setDsaPoints(getComplexity(fullSumm.getDsaPoints(), activity.getDsaPoints()));
        complexity.setDatabasesPoints(getComplexity(fullSumm.getDatabasesPoints(), activity.getDatabasesPoints()));
        complexity.setSpringPoints(getComplexity(fullSumm.getSpringPoints(), activity.getSpringPoints()));

        return complexity;
    }

    protected static int getSize(Stream<Student> storage) {
        return (int) storage
                .map(Student::getCoursePoints)
                .flatMap(Collection::stream)
                .map(Points::getCompleted).count();
    }

    protected static Set<StudentProgress> getCourseLeaders(Stream<Student> storage, String course) {
        return storage
                .map((student) -> new StudentProgress(student, course))
                .filter(StudentProgress::isNotEmpty)
                .sorted(Collections.reverseOrder())
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    protected static String getGreatestValues(Points p) {
        PointsStats ps = new PointsStats(p);
        return ps.getGreatest();
    }

    protected static String getSmallestValues(Points p) {
        PointsStats ps = new PointsStats(p);
        return ps.getSmallest();
    }

    private static void fillStats() {
        var popularity = calcPopularity(StudentStorage.getStream());
        leastPopular = getSmallestValues(popularity);
        mostPopular = getGreatestValues(popularity);

        var activity = calcActivity(StudentStorage.getStream());
        leastActive = getSmallestValues(activity);
        mostActive = getGreatestValues(activity);

        var complexity = calcComplexity(
                calcTotalSubmittedPoints(StudentStorage.getStream()),
                calcActivity(StudentStorage.getStream()));
        easiest = getGreatestValues(complexity);
        hardest = getSmallestValues(complexity);
    }

    public static String getStats() {
        fillStats();
        return "Most popular: " + mostPopular + "\n" +
                "Least popular: " + leastPopular + "\n" +
                "Highest activity: " + mostActive  + "\n" +
                "Lowest activity: " + leastActive  + "\n" +
                "Easiest course: " + easiest + "\n" +
                "Hardest course: " + hardest;
    }

    public static void printTable(String course) {
        ProgressTable table = new ProgressTable();
        Set<StudentProgress> results = getCourseLeaders(StudentStorage.getStream(), course);
        table.print(course, results);
    }
}
