package tracker;

import java.util.*;

public class AppData {

    private LinkedHashMap<String, Student> ListOfStudents;
    private LinkedHashMap<Integer, String> accesList;
    private ArrayList<Course> availableCourses;
    private int lastIndex;


    public int setNextIndex() {
        this.lastIndex = lastIndex + 1;
        return this.lastIndex;

    }
    public AppData() {
        this.ListOfStudents = new LinkedHashMap<>();
        this.accesList = new LinkedHashMap<>();
        this.availableCourses = new ArrayList<>();
        this.lastIndex = 9999;
        this.availableCourses.add(new Course("Java", 600));
        this.availableCourses.add(new Course("DSA", 400));
        this.availableCourses.add(new Course("Databases", 480));
        this.availableCourses.add(new Course("Spring", 550));
    }
    public void addStudents(Student newStudent, ArrayList<Course> courses) {
        newStudent.setStudentID(setNextIndex());
        newStudent.setCourses(this.availableCourses);
        this.ListOfStudents.putIfAbsent(newStudent.getStudentEmail(), newStudent);
        this.accesList.put(newStudent.getStudentID(), newStudent.getStudentEmail());
    }

    public void printListOfStudents() {
        if(this.ListOfStudents.isEmpty()) {
            System.out.println("No students found");
        } else {
            System.out.println("Students:");
            for (var entry : this.ListOfStudents.entrySet()) {
                System.out.println(entry.getValue().getStudentID());
            }
        }
    }

    public ArrayList<Course> getCourses() {
        return this.availableCourses;
    }



    public void printStudentProgress(int studentId) {
     Student student = this.ListOfStudents.get(this.accesList.get(studentId));
     System.out.printf("%d points; Java=%d; DSA=%d; Databases=%d; Spring=%d",
             student.getStudentID(),
             student.getJavaPoints(),
             student.getDsaPoints(),
             student.getDatabasesPoints(),
             student.getSpringPoints()
     );
    }

    public boolean isStudentWithId(int id) {
        return accesList.containsKey(id);
    }

    public boolean isEmailTaken(String email) {
        return ListOfStudents.containsKey(email);
    }

    public void modifyStudentPoints(int[] inputsArray) {
        Student processedStudent = this.ListOfStudents.get(this.accesList.get(inputsArray[0]));
       // Course[] coursesArray = new Course[availableCourses.size()];
      //  coursesArray = availableCourses.toArray(coursesArray);
        for (int i = 1; i < inputsArray.length; i++) {
            if (inputsArray[i] != 0) {
                if(processedStudent.getCoursePoints(this.availableCourses.get(i-1).getCourseName()) == 0) {
                    this.availableCourses.get(i-1).incrementEnrolledStudents();
                    this.availableCourses.get(i-1).enrollStudent(inputsArray[0]);
                }
                processedStudent.addPointsToCourse(this.availableCourses.get(i - 1).getCourseName(), inputsArray[i]);
                this.availableCourses.get(i-1).incrementSubmissions();
                this.availableCourses.get(i-1).addSubmissions(inputsArray[i]);
            }
        }
    }

    public void printStudentsStatisticCourse(String courseName) {
        String space1 = "         ";
        String space2 = "        ";
        String space3 = "       ";
        String usedSpace;

        Course processedCourse = null;
        for(Course course : availableCourses) {
            if(courseName.equals(course.getCourseName())) {
                processedCourse = course;
            }
        }
        ArrayList<Student> enrolledStudents = new ArrayList<>();

        for (Integer i : processedCourse.getEnrolledStudentsList()) {
            enrolledStudents.add(this.ListOfStudents.get(this.accesList.get(i)));
        }
        Comparator<Student> studentsPointsComparator = (s1, s2) ->
                Integer.valueOf(s1.getCoursePoints(courseName)).compareTo(s2.getCoursePoints(courseName));
        enrolledStudents.sort(studentsPointsComparator.reversed());
        for (Student student : enrolledStudents) {
            if(student.getCoursePoints(courseName) > 9 && student.getCoursePoints(courseName) < 100 ) {
                usedSpace = space2;
            } else if (student.getCoursePoints(courseName) > 99) {
                usedSpace = space3;
            } else {
                usedSpace = space1;
            }
            System.out.println(student.getStudentID() + " " + student.getCoursePoints(courseName) + usedSpace
                    + student.getCourseProgress(processedCourse, student.getCoursePoints(courseName)) + "%");
        }
    }

    public LinkedHashMap<String, Student> getListOfStudents() {
        return this.ListOfStudents;
    }
}