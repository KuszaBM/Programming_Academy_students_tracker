package tracker;

public class UserAddpointLoop extends UserInterfaceLoop{

    public UserAddpointLoop(AppData trackerData) {
        super(trackerData);
        this.escapeStatement = "back";
    }

    @Override
    public String checkInput(String input) {
        boolean isCorrect = true;
        String[] splitedInput = input.split(" ");
        if (splitedInput.length != 5) {
            return null;
        } else {
            for (int i = 0; i < 5; i++) {
                char[] splitString = splitedInput[i].toCharArray();
                for(int j = 0; j < splitString.length; j++) {
                    if (splitString[j] < 48 || splitString[j] > 57) {
                        isCorrect = false;
                        return null;
                    }
                }
                if(!isCorrect) {
                    return null;
                }
            }
            if (isCorrect) {
                return input;
            } else {
                return null;
            }
        }
    }

    private String getIdFromInput (String inputString) {
        String[] inputArray = inputString.split(" ");
        return inputArray[0];
    }

    private boolean ifProperId(String input) {
        char[] splitString = input.toCharArray();
        for(int j = 0; j < splitString.length; j++) {
            if (splitString[j] < 48 || splitString[j] > 57) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected void printInterfaceMessage() {System.out.println("Enter an id and points or 'back' to return");}

    private int[] processInput(String input) {
        String[] inputArray = input.split(" ");
        int[] inputAsInts = new int[5];
        for (int i = 0; i < 5; i++) {
            inputAsInts[i] = Integer.parseInt(inputArray[i]);
            if (inputAsInts[i] < 0) {
                return null;
            }
        }
        return inputAsInts;
    }

    @Override
    public void userInputLoop() {
        boolean loop = true;

        printInterfaceMessage();

        while (loop) {
            String inputString = this.InputRequest();
            if (inputString.equals(this.escapeStatement)) {
                loop = false;
            } else {
                if (ifProperId(getIdFromInput(inputString))) {
                    if (this.trackerData.isStudentWithId(Integer.parseInt(getIdFromInput(inputString)))) {
                        if (checkInput(inputString) != null) {
                            int[] inputData = processInput(inputString);
                            if (inputData != null) {
                                if (this.trackerData.isStudentWithId(inputData[0])) {
                                    trackerData.modifyStudentPoints(inputData);
                                    System.out.println("Points updated");
                                } else {
                                    System.out.println("No student found for id=" + inputData[0]);
                                }
                            } else {
                                System.out.println("Incorrect points format");
                            }
                        } else {
                            System.out.println("Incorrect points format");
                        }
                    } else {
                        System.out.println("No student found for id=" + getIdFromInput(inputString));
                    }
                } else {
                    System.out.println("No student found for id=" + getIdFromInput(inputString));
                }


            }

        }
    }
}