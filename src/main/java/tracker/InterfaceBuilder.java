package tracker;

public class InterfaceBuilder extends InterfaceFactory{

    @Override
    UserInterfaceLoop createInterface(String type, AppData trackerData) {
        if (type.equals("mainInterface")) {
            return new UserMainLoop(trackerData);
        } else if (type.equals("addStudentInterface")) {
            return new UserAddStudentLoop(trackerData);
        } else if (type.equals("addPointsInterface")) {
            return new UserAddpointLoop(trackerData);
        } else if (type.equals("userFindLoop")) {
            return new UserFindLoop(trackerData);
        } else if (type.equals("userStatisticLoop")) {
            return new UserStatisticLoop(trackerData);
        } else {
            return null;
        }
    }
}