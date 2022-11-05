package tracker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.function.BiFunction;
import java.util.function.Function;

public class UserStatisticLoop extends UserInterfaceLoop{
    public UserStatisticLoop(AppData trackerData) {
        super(trackerData);
        this.escapeStatement = "back";
    }

    @Override
    protected void printInterfaceMessage() {
        System.out.println("Type the name of a course to see details or 'back' to quit");

    }

    public String checkInput(String input) {
        if(input == null) {
            input = "no input";
        } else if (isCourseName(input)) {
        //   input = giveProperName(input);
            input = input;
        }
        return input;
    }

    public boolean isCourseName (String input) {
        for(Course course : this.trackerData.getCourses()) {
            if (course.getCourseName().toLowerCase().equals(input.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
    public String giveProperName(String input) {
        for (Course course : this.trackerData.getCourses()) {
            if (course.getCourseName().toLowerCase().equals(input.toLowerCase())) {
               input = course.getCourseName();
            }
        }
        return input;
    }


    public void printMainStatistic(ArrayList<Course> StatisticArray) {

        String noInfo = "n/a";
        String mostPopular;
        String lestPopular;
        String highestActivity;
        String lowestActivity;
        String easiestCourse;
        String hardestCourse;



        if (StatisticArray.size() == 0) {
            mostPopular = noInfo;
            lestPopular = noInfo;
            highestActivity = noInfo;
            lowestActivity = noInfo;
            easiestCourse = noInfo;
            hardestCourse = noInfo;

            System.out.println("Most popular: " + mostPopular);
            System.out.println("Least popular: " + lestPopular);
            System.out.println("Highest activity: " + highestActivity);
            System.out.println("Lowest activity: " + lowestActivity);
            System.out.println("Easiest course: " + easiestCourse);
            System.out.println("Hardest course: " + hardestCourse);
        } else if (StatisticArray.size() == 1) {
            mostPopular = StatisticArray.get(0).getCourseName();
            lestPopular = noInfo;
            highestActivity = StatisticArray.get(0).getCourseName();
            lowestActivity = noInfo;
            easiestCourse = StatisticArray.get(0).getCourseName();
            hardestCourse = noInfo;

            System.out.println("Most popular: " + mostPopular);
            System.out.println("Least popular: " + lestPopular);
            System.out.println("Highest activity: " + highestActivity);
            System.out.println("Lowest activity: " + lowestActivity);
            System.out.println("Easiest course: " + easiestCourse);
            System.out.println("Hardest course: " + hardestCourse);
        } else {
            Function<Integer, Void> printer = (v) -> {
                for (int i = 0; i < v-1; i++) {
                    System.out.print(StatisticArray.get(i).getCourseName() + ", ");
                }
                return null;
            };
            Function<Integer, Void> printerDown = v -> {
                for (int i = StatisticArray.size() - 1 -v; i > StatisticArray.size()-1 ; i++) {
                    System.out.print(StatisticArray.get(i).getCourseName() + ", ");
                }
                return null;
            };
            if (StatisticArray.size() > 1) {

                Comparator<Course> popularComparator = Comparator.comparing(Course::getEnrolledStudents).reversed();
                StatisticArray.sort(popularComparator);
                mostPopular = StatisticArray.get(0).getCourseName();
                lestPopular = StatisticArray.get(StatisticArray.size() - 1).getCourseName();

                int temp1 = multiplePositionInStatisticCheck("enrolled", StatisticArray)[0];
                int temp2 = multiplePositionInStatisticCheck("enrolled", StatisticArray)[1];
                if (temp1 > 1 || temp2 > 1) {
                    System.out.print("Most popular: ");
                    printer.apply(temp1);
                    mostPopular = StatisticArray.get(temp1-1).getCourseName();
                    System.out.println(mostPopular);

                    System.out.print("Least popular: ");
                    if (temp2 == 0) {
                        lestPopular = noInfo;
                    } else if (temp2 > 1){
                        printer.apply(temp2 - 1);
                    }
                    System.out.println(lestPopular);
                } else {
                    System.out.println("Most popular: " + mostPopular);
                    System.out.println("Least popular: " + lestPopular);
                }

                Comparator<Course> activityComparator = Comparator.comparing(Course::getSubmissions).reversed();
                StatisticArray.sort(activityComparator);
                highestActivity = StatisticArray.get(0).getCourseName();
                lowestActivity = StatisticArray.get(StatisticArray.size() - 1).getCourseName();

                temp1 = multiplePositionInStatisticCheck("activity", StatisticArray)[0];
                temp2 = multiplePositionInStatisticCheck("activity", StatisticArray)[1];
                if (temp1 > 1 || temp2 > 1) {
                    System.out.print("Highest activity: ");
                    printer.apply(temp1);
                    highestActivity = StatisticArray.get(temp1-1).getCourseName();
                    System.out.println(highestActivity);

                    System.out.print("Lowest activity: ");
                    if (temp2 == 0) {
                        lowestActivity = noInfo;
                    } else {
                        printer.apply(temp2-1);
                    }
                    System.out.println(lowestActivity);
                } else {
                    System.out.println("Highest activity: " + highestActivity);
                    System.out.println("Lowest activity: " + lowestActivity);
                }

                Comparator<Course> difficultyComparator = Comparator.comparing(Course::getAverageScore).reversed();
                StatisticArray.sort(difficultyComparator);
  //              Collections.reverse(StatisticArray);
                easiestCourse = StatisticArray.get(0).getCourseName();
                hardestCourse = StatisticArray.get(StatisticArray.size() - 1).getCourseName();

                temp1 = multiplePositionInStatisticCheck("difficulty", StatisticArray)[0];
                temp2 = multiplePositionInStatisticCheck("difficulty", StatisticArray)[1];
                if (temp1 > 1 || temp2 > 1) {
                    System.out.print("Easiest course: ");
                    printer.apply(temp1);
                    hardestCourse = StatisticArray.get(temp1-1).getCourseName();
                    System.out.println(highestActivity);

                    System.out.print("Hardest course: ");
                    if (temp2 == 0) {
                        hardestCourse = noInfo;
                    } else{
                        printer.apply(temp2-1);
                    }
                    System.out.println(hardestCourse);
                } else {
                    System.out.println("Easiest course: " + easiestCourse);
                    System.out.println("Hardest course: " + hardestCourse);
                }
            }
        }


        }


    public int[] multiplePositionInStatisticCheck (String type, ArrayList<Course> courses) {
        int repMax = 1;
        int repMin = 0;

        if (type.equals("enrolled")) {
            for (int i = 1; i < courses.size(); i++) {
                if (courses.get(0).getEnrolledStudentsList().size() == courses.get(i).getEnrolledStudentsList().size()) {
                    repMax++;
                }
                if(courses.get(courses.size() - 1).getEnrolledStudentsList().size() ==  courses.get(i).getEnrolledStudentsList().size()) {
                    repMin++;
                }
            }
        } else if (type.equals("activity")) {
            for (int i = 1; i < courses.size(); i++) {
                if (courses.get(0).getSubmissions() == courses.get(i).getSubmissions()) {
                    repMax++;
                }
                if(courses.get(courses.size() - 1).getSubmissions() ==  courses.get(i).getSubmissions()) {
                    repMin++;
                }
            }
        } else if (type.equals("difficulty")) {
            for (int i = 1; i < courses.size(); i++) {
                if (courses.get(0).getAverageScore() == courses.get(i).getAverageScore()) {
                    repMax++;
                }
                if(courses.get(courses.size() - 1).getAverageScore() ==  courses.get(i).getAverageScore()) {
                    repMin++;
                }
            }
        }

        if(repMax == courses.size())
        {
            repMin = 0;
        }

        int[] outputTable  = new int[]{repMax, repMin};
        return outputTable;
    }


    @Override
    public void userInputLoop() {
        boolean loop = true;

        ArrayList<Course> StatisticArray = new ArrayList<>();

        for (Course course : this.trackerData.getCourses()){
            if (course.getEnrolledStudents() > 0) {
                StatisticArray.add(course);
            }
        }

        printInterfaceMessage();
        printMainStatistic(StatisticArray);

        while(loop) {
            String inputString = this.InputRequest();
            if(this.checkInput(inputString).equals(this.escapeStatement)) {
                loop = false;
            } else if (isCourseName(checkInput(inputString))) {
                System.out.println(giveProperName(inputString));
                System.out.println("id    points    completed");
                this.trackerData.printStudentsStatisticCourse(giveProperName(inputString));
            } else {
                System.out.println("Unknown course.");
            }
        }
    }
}