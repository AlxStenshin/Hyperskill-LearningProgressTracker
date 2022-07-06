package ru.alxstn.data;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class StudentProgress implements Comparable<StudentProgress>{
    private final long studentId;
    private int coursePoints;
    private final String courseName;
    private double courseProgress = 0;

    public StudentProgress(Student student, String course) {
        this.studentId = student.getId();
        this.courseName = course;

        Points allPoints = student.getTotalPoints();
        int SPRING_MAX = 550;
        int DATABASES_MAX = 480;
        int DSA_MAX = 400;
        int JAVA_MAX = 600;
        switch (course) {
            case "Java":
                this.coursePoints = allPoints.getJavaPoints();
                setProgress(coursePoints, JAVA_MAX);
                break;
            case "DSA":
                this.coursePoints = allPoints.getDsaPoints();
                setProgress(coursePoints, DSA_MAX);
                break;
            case "Databases":
                this.coursePoints = allPoints.getDatabasesPoints();
                setProgress(coursePoints, DATABASES_MAX);
                break;
            case "Spring":
                this.coursePoints = allPoints.getSpringPoints();
                setProgress(coursePoints, SPRING_MAX);
                break;
            default:
        }
    }

    public long getStudentId() {
        return studentId;
    }

    public int getCoursePoints() {
        return coursePoints;
    }

    public double getCourseProgress() {
        return courseProgress;
    }

    public String getCourseName() {
        return courseName;
    }

    protected static double calcProgress(int currentValue, int maxValue) {
        BigDecimal bd = BigDecimal.valueOf(((double)currentValue/(double)maxValue)*100);
        bd = bd.setScale(1, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    protected boolean isNotEmpty() {
        return coursePoints > 0;
    }

    protected boolean isFinished() {
        return courseProgress == 100;
    }

    private void setProgress(int currentValue, int maxValue) {
        this.courseProgress = calcProgress(currentValue, maxValue);
    }

    @Override
    public int compareTo(StudentProgress o) {
        return Integer.compare(this.coursePoints, o.coursePoints);
    }
}
