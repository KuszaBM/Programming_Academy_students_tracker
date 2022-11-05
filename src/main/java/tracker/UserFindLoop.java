package tracker;

public class UserFindLoop extends UserInterfaceLoop{
    public UserFindLoop(AppData trackerData) {
        super(trackerData);
        this.escapeStatement = "back";
    }

    @Override
    protected void printInterfaceMessage() {
        System.out.println("Enter an id or 'back' to return");
    }

    @Override
    public String checkInput(String input) {
        if(this.trackerData.isStudentWithId(Integer.parseInt(input))) {
            return input;
        } else {
            System.out.println("No student is found for id" + input);
            return null;
        }
    }

    @Override
    public void userInputLoop() {
        boolean loop = true;
        printInterfaceMessage();
        while (loop) {
            String input = this.InputRequest();
            if (input.equals(this.escapeStatement)) {
                loop = false;
            } else if (checkInput(input) != null) {
                trackerData.printStudentProgress(Integer.parseInt(input));
            }
        }
    }
}