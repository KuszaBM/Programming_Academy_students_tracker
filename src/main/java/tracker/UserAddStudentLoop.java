package tracker;

public class UserAddStudentLoop extends UserInterfaceLoop {

    public UserAddStudentLoop(AppData trackerData) {
        super(trackerData);
        this.escapeStatement ="back";
    }
    @Override
    protected void printInterfaceMessage() {
        System.out.println("Enter student credentials or 'back' to return:");
    }

    @Override
    public String checkInput(String input) {
        if(input == null) {
            input = "no input";
        }
        return input;
    }

    @Override
    public void userInputLoop() {

        int amountOfAddedStudents = 0;
        boolean loop = true;

        printInterfaceMessage();

        while(loop) {
            String inputString = this.InputRequest();
            if(this.checkInput(inputString).equals(escapeStatement)) {
                loop = false;
                System.out.println("Total " + amountOfAddedStudents + " students have been added");
            } else {
                StudentInserter studentInserter = new StudentInserter();
                if(studentInserter.checkIfCorrectInput(inputString)) {
                    if (!this.trackerData.isEmailTaken(studentInserter.getEmailFromInputStream(inputString))) {
                        this.trackerData.addStudents(studentInserter.createNewStudent(inputString), this.trackerData.getCourses());
                        System.out.println("The student has been added");
                        amountOfAddedStudents++;
                    } else {
                        System.out.println("This email is already taken");
                    }

                }
            }
        }
    }

}