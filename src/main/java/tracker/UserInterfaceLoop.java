package tracker;

public abstract class UserInterfaceLoop implements ScanUserInput {
    public String escapeStatement;
    public InterfaceCommands commands;
    public AppData trackerData;

    public UserInterfaceLoop(AppData trackerData){

        this.commands = new InterfaceCommands();
        this.trackerData = trackerData;
    }
    public void userInputLoop() {
    }

    protected abstract void printInterfaceMessage();

    @Override
    public String checkInput(String input) {
        return null;
    }
}