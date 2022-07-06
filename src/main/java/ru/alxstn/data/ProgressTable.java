package ru.alxstn.data;

import java.util.Formatter;
import java.util.Set;

public class ProgressTable {
    private static final int ID_WIDTH = 7;
    private static final int POINTS_WIDTH = 7;
    private static final int PROGRESS_WIDTH = 8;

    private static final String TITLE_FORMAT = "%-" + ID_WIDTH + "s %" + POINTS_WIDTH + "s %" + PROGRESS_WIDTH + "s\n";
    private static final String ITEM_FORMAT = "%-" + ID_WIDTH + "s %" + POINTS_WIDTH + "s %" + PROGRESS_WIDTH + ".1f%%\n";

    private Formatter f = new Formatter(System.out);

    private void printTitle() {
        f.format(TITLE_FORMAT, "id", "points", "completed");
    }

    private void printRow(long id, int points, double progress) {
        f.format(ITEM_FORMAT, id, points, progress);
    }

    public void print(String course, Set<StudentProgress> results) {
        System.out.println(course);
        printTitle();
        for (StudentProgress sp : results)
            printRow(sp.getStudentId(), sp.getCoursePoints(), sp.getCourseProgress());
    }
}
