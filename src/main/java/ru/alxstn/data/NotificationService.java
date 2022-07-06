package ru.alxstn.data;

import java.util.*;
import java.util.stream.Collectors;

public class NotificationService {

    private final Set<String> sessionNotificationRecipients = new HashSet<>();

    private String buildNotification(Student s, String course) {
        sessionNotificationRecipients.add(s.getEmail());
        return  "To: " + s.getEmail() + "\n" +
                "Re: Your Learning Progress \n" +
                "Hello, " + s.getName() + " " + s.getLastname() + "!" +
                "You have accomplished our " + course + " course!";
    }

        public void buildNotifications() {
        List<String> courses = Arrays.asList("Java", "DSA", "Databases", "Spring");
        for (String course : courses) {
            List<StudentProgress> completedCourses = StudentStorage.getStream()
                    .map((student) -> new StudentProgress(student, course))
                    .filter(StudentProgress::isFinished)
                    .filter(StudentStorage::notificationRequired)
                    .collect(Collectors.toList());

            for (StudentProgress sp : completedCourses) {
                System.out.println(buildNotification(
                        Objects.requireNonNull(StudentStorage.findStudentById(Long.toString(sp.getStudentId()))),
                        sp.getCourseName()));
                StudentStorage.updateFinishedCourses(Long.toString(sp.getStudentId()), sp.getCourseName());
            }
        }
    }

    public int getRecipientsCount(){
        int count = sessionNotificationRecipients.size();
        sessionNotificationRecipients.clear();
        return count;
    }
}
