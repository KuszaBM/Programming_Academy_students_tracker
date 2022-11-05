package tracker;

public class LearningProgressTracker {
    AppData appData;
    public LearningProgressTracker() {
        this.appData = new AppData();
    }
    public void run() {
        InterfaceBuilder interfaceBuilder = new InterfaceBuilder();
        UserInterfaceLoop mainUserInterface = interfaceBuilder.createInterface("mainInterface", appData);
        mainUserInterface.userInputLoop();
    }
}