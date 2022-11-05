package tracker;

import java.util.ArrayList;

class InterfaceCommands {
    ArrayList commands;

    public InterfaceCommands() {
        this.initializationOfCommands();
    }

    private void initializationOfCommands() {
        ArrayList<String> commands = new ArrayList<>();
        this.commands = commands;
        this.setCommands();
    }

    private void setCommands() {
        this.commands.add("exit");
        this.commands.add("add students");
        this.commands.add("back");
        this.commands.add("list");
        this.commands.add("add points");
        this.commands.add("find");
        this.commands.add("statistics");
        this.commands.add("notify");
    }

    public boolean checkIfCommand(String checkingString) {
        boolean isCommand = false;
        try {
            for (int i = 0; i < commands.size(); i++) {
                if (checkingString.equals(commands.get(i))) {
                    isCommand = true;
                    break;
                }
            }
        } catch (NullPointerException e) {
            System.out.println("Command List empty");
        }
        return isCommand;
    }

    public void exit() {
        System.out.println("Bye!");
    }


    public void addStudent(AppData trackerData) {
        InterfaceBuilder builder = new InterfaceBuilder();
        UserInterfaceLoop addStudentLoop = builder.createInterface("addStudentInterface", trackerData);
        addStudentLoop.userInputLoop();
    }

    public void list(AppData trackerData) {
        trackerData.printListOfStudents();
    }

    public void addPoint(AppData trackerData) {
        InterfaceBuilder builder = new InterfaceBuilder();
        UserInterfaceLoop addPointsLoop = builder.createInterface("addPointsInterface", trackerData);
        addPointsLoop.userInputLoop();
    }

    public void find(AppData trackerData) {
        InterfaceBuilder builder = new InterfaceBuilder();
        UserInterfaceLoop userFindLoop = builder.createInterface("userFindLoop", trackerData);
        userFindLoop.userInputLoop();
    }

    public void statistic(AppData trackerData) {
        InterfaceBuilder builder = new InterfaceBuilder();
        UserInterfaceLoop userStatisticLoop = builder.createInterface("userStatisticLoop", trackerData);
        userStatisticLoop.userInputLoop();
    }
    public void notifyStudents(AppData trackerData) {
        Notify notifyStudents = new Notify(trackerData.getListOfStudents(), trackerData.getCourses());
        notifyStudents.notifyStudents();
    }
}