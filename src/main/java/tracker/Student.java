package tracker;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Student {

    private int studentID;
    private String firstName;
    private String lastName;
    private String studentEmail;
    private TreeMap<String, Integer> StudentCourses;

    private HashMap<String, Integer> NotifyTable;

    public Student(String firstName, String lastName, String studentEmail) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentEmail = studentEmail;
        this.StudentCourses = new TreeMap<>();
        this.NotifyTable = new HashMap<>();

    }

    public void setCourses(ArrayList<Course> courses) {
        for(Course course : courses) {
            this.StudentCourses.put(course.getCourseName(), 0);
            this.NotifyTable.put(course.getCourseName(), 0);
        }

    }

    public boolean isNotified(String courseName) {
        if (NotifyTable.get(courseName) == 1){
            return true;
        } else {
            return false;
        }
    }

    public void setNotifyTable(String courseName) {
        NotifyTable.remove(courseName);
        NotifyTable.put(courseName, 1);
    }

    public float getCourseProgress(Course course, int studentPoints) {
        float points = course.getPointsToPass();
        float studentPointsAsFloat = studentPoints;
        BigDecimal studentProgress = BigDecimal.valueOf(studentPointsAsFloat/points * 100);
        return studentProgress.setScale(1, RoundingMode.HALF_UP).floatValue();
    }

    public void addPointsToCourse(String courseName, int points) {
        int currentPoints = this.StudentCourses.get(courseName);
        this.StudentCourses.remove(courseName);
        this.StudentCourses.put(courseName, currentPoints + points);
    }

    public void setID(int newStudentID) {
        this.studentID = newStudentID;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getStudentID() {
        return studentID;
    }

    public int getCoursePoints (String courseName) {
        return StudentCourses.get(courseName);
    }

    public int getDatabasesPoints() {

        return StudentCourses.get("Databases");
    }

    public int getDsaPoints() {
        return StudentCourses.get("DSA");
    }

    public int getJavaPoints() {
        return StudentCourses.get("Java");
    }

    public int getSpringPoints() {
        return StudentCourses.get("Spring");
    }
}