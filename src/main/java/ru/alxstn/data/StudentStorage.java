package ru.alxstn.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Stream;

public class StudentStorage {
    private static final List<Student> list = new ArrayList<>();

    public static boolean checkEmailExists(String email) {
        for (Student s : list) {
            if (s.getEmail().toLowerCase(Locale.ROOT).equals(email.toLowerCase(Locale.ROOT)))
                return true;
        }
        return false;
    }

    public static Stream<Student> getStream(){
        return list.stream();
    }

    public static void addNewStudent(Student student) {
        list.add(student);
    }

    public static int getStudentsListSize() {
        return list.size();
    }

    public static void updatePoints(String id, Points points) {
        for (Student s : list) {
            if (Long.toString(s.getId()).equals(id)) {
                s.addToPointsList(points);
                System.out.println("Points updated.");
                return;
            }
        }
        System.out.println("No student is found for id=" + id + ".");
    }

    public static boolean notificationRequired(StudentProgress progress){
        return !Objects.requireNonNull(findStudentById(Long.toString(progress.getStudentId()))).
                getSentNotifications().contains(progress.getCourseName());
    }

    public static void updateFinishedCourses(String id, String course) {
        for (Student s : list) {
            if (Long.toString(s.getId()).equals(id)) {
                s.addFinishedCourses(course);
                return;
            }
        }
    }

    public static Student findStudentById(String id) {
        for (Student s : list) {
            if (Long.toString(s.getId()).equals(id)) {
                return s;
            }
        }
        return null;
    }

    public static String findById(String id) {
        Student s = findStudentById(id);
        if (s == null)
            return "No student is found for id=" + id + ".";
       else return s.toString();
    }

    public static void printAll() {
        if (!list.isEmpty()) {
            System.out.println("Students:");
            for (Student s : StudentStorage.list)
                System.out.println(s.getId());
        }
        else
            System.out.println("No students found.");
    }
}
