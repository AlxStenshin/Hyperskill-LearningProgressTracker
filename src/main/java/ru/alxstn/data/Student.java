package ru.alxstn.data;

import java.util.ArrayList;
import java.util.List;

public class Student{
    private static long counter = 10_000;
    private final long id;

    private final String name;
    private final String lastname;
    private final String email;
    private final List<Points> coursePoints = new ArrayList<>();
    private final List<String> notificationsSent = new ArrayList<>();

    public List<Points> getCoursePoints() {
        return this.coursePoints;
    }

    public Points getTotalPoints() {
        Points sum = new Points();
        sum = coursePoints.stream().reduce(sum, Points::add);
        return sum;
    }

    public Student(String name, String lastname, String email) {
        this.id = counter++;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public StudentProgress getProgress(String course) {
        return new StudentProgress(this, course);
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void addToPointsList(Points p) {
        coursePoints.add(p);
    }

    public List<String> getSentNotifications(){
        return notificationsSent;
    }

    public void addFinishedCourses(String s) {
        notificationsSent.add(s);
    }

    @Override
    public String toString() {
        Points points = getTotalPoints();
        return id + " points" +
                " Java=" + points.getJavaPoints() +
                "; DSA=" + points.getDsaPoints() +
                "; Databases=" + points.getDatabasesPoints() +
                "; Spring=" + points.getSpringPoints();
    }
}