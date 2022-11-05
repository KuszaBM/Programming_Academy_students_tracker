package tracker;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Set;

public class Notify {

    private LinkedHashMap<String, Student> listOfStudents;
    private ArrayList<Course> listOfCourses;

    private int notifyCounter;

    public Notify(LinkedHashMap<String, Student> listOfStudents, ArrayList<Course> listOfCourses) {
        this.listOfStudents = new LinkedHashMap<>();
        this.listOfCourses = new ArrayList<>();
        this.listOfCourses = listOfCourses;
        this.listOfStudents = listOfStudents;
        this.notifyCounter = 0;
    }

    public void notifyStudents () {
        Set<String> keys = listOfStudents.keySet();
        int counter = 0;
       for (String student : keys) {
           int studentCounter = 0;
           for (Course course : listOfCourses) {

               if(listOfStudents.get(student).getCoursePoints(course.getCourseName()) >= course.getPointsToPass()
                       && !listOfStudents.get(student).isNotified(course.getCourseName())) {
                   System.out.println("To: " + student);
                   System.out.println("Re: Your Learning Progress");
                   System.out.printf("Hello, %s %s! You have accomplished our %s course!\n",
                           listOfStudents.get(student).getFirstName(),
                           listOfStudents.get(student).getLastName(),
                           course.getCourseName());
                   listOfStudents.get(student).setNotifyTable(course.getCourseName());
                   studentCounter++;
               }
           }
           if (studentCounter > 0) {
               counter++;
           }
       }
       System.out.printf("Total %d students have been notified\n", counter);
    }



}