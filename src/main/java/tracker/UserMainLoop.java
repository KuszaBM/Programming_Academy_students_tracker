package tracker;

public class UserMainLoop extends UserInterfaceLoop {

    public UserMainLoop(AppData trackerData) {
        super(trackerData);
        this.escapeStatement = "exit";
    }

    @Override
    protected void printInterfaceMessage() {
        System.out.println("Learning Progress Tracker");
    }

    @Override
    public String checkInput(String input) {
        if (input == null) {
            input = "no input";
            System.out.println("No input.");
        } else {
            if (this.commands.checkIfCommand(input)) {
                if(input.equals("back")) {
                    System.out.println("Enter 'exit' to exit the program.");
                    input = "unknown command";
                }
            } else if (input.isBlank()) {
                System.out.println("No input");
                input = "no input";
            } else {
                System.out.println("Unknown command!");
                input = "unknown command";
            }
        }
        return input;
    }

    @Override
    public void userInputLoop() {
        String inputString;
        boolean loop = true;
        printInterfaceMessage();
        while (loop){
            inputString = this.InputRequest();
            inputString = this.checkInput(inputString);
            if(inputString.equals(escapeStatement)) {
                this.commands.exit();
                loop = false;
            }else if(inputString.equals("add students")) {
                this.commands.addStudent(this.trackerData);
            } else if (inputString.equals("list")) {
                this.commands.list(this.trackerData);
            } else if (inputString.equals("add points")) {
                this.commands.addPoint(this.trackerData);
            } else if (inputString.equals("find")) {
                this.commands.find(this.trackerData);
            } else if (inputString.equals("statistics")) {
                this.commands.statistic(this.trackerData);
            } else if (inputString.equals("notify")) {
                this.commands.notifyStudents(this.trackerData);
            } else {
                loop = true;
            }
        }
    }
}