package tracker;

abstract class InterfaceFactory {
    abstract UserInterfaceLoop createInterface(String type, AppData trackerData);

    UserInterfaceLoop buildInterface(String type, AppData trackerData) {
        UserInterfaceLoop userInterface = createInterface(type, trackerData);
        if (userInterface == null) {
            System.out.println("Cannot build that interface");
            return null;
        }
        return userInterface;
    }

}