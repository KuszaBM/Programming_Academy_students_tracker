package tracker;

import java.util.LinkedList;

public class Course implements Comparable<Course> {
    private final String courseName;
    private int submissions;
    private int enrolledStudents;
    private final int pointsToPass;


    private LinkedList<Integer> studentsSubmissions;
    private LinkedList<Integer> enrolledStudentsList;

    public Course(String courseName, int pointsToPass) {
        this.courseName = courseName;
        this.submissions = 0;
        this.enrolledStudents = 0;
        this.pointsToPass = pointsToPass;
        this.studentsSubmissions = new LinkedList<>();
        this.enrolledStudentsList = new LinkedList<>();
    }

    public void addSubmissions (int newScore) {
        this.studentsSubmissions.add(newScore);
    }

    public void enrollStudent(int studentID) {
        this.enrolledStudentsList.add(studentID);
    }

    public int getAverageScore () {
        int scoreSum = 0;
        for (Integer i : studentsSubmissions) {
            scoreSum = scoreSum + i;
        }
        return scoreSum/studentsSubmissions.size();
    }

    public void incrementSubmissions () {
        this.submissions++;
    }

    public void incrementEnrolledStudents() {this.enrolledStudents++;}

    public int getEnrolledStudents() {
        return enrolledStudents;
    }

    public void setEnrolledStudents(int enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }

    public int getSubmissions() {
        return this.studentsSubmissions.size();
    }

    public void setSubmissions(int submissions) {
        this.submissions = submissions;
    }

    public String getCourseName() {
        return courseName;
    }
    public int getPointsToPass () {
        return pointsToPass;
    }
    public LinkedList<Integer> getEnrolledStudentsList() {
        return enrolledStudentsList;
    }

    @Override
    public int compareTo( Course otherCourse) {
        return this.getCourseName().compareTo(otherCourse.getCourseName());

    }
}