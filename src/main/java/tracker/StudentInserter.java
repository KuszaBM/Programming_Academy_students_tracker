package tracker;

public class StudentInserter {

    public Student createNewStudent(String credentials) {
        String[] splitCredentials = credentials.split(" ");
        String firstName = splitCredentials[0];
        String email = splitCredentials[splitCredentials.length - 1];
        String tempLastName = splitCredentials[1];

        if(splitCredentials.length > 3){
            for(int i = 2; i < splitCredentials.length - 1; i++) {
                tempLastName = String.join(" ", tempLastName, splitCredentials[i]);
            }
        }
        return new Student(firstName, tempLastName, email);
    }

    public String getEmailFromInputStream(String inputString) {
        String[] splitInput = inputString.split(" ");
        return splitInput[splitInput.length - 1];
    }


    public boolean checkIfCorrectInput(String inputString) {
        boolean isCorrect = false;
        String[] splitInput = inputString.split(" ");

        //create array that signals on which input string is incorrect
        boolean[] incorrectPosition = new boolean[splitInput.length];

        if(splitInput.length < 3) {
            System.out.println("Incorrect credentials.");
        } else{
            for(int i = 0; i < splitInput.length - 1; i++) {
                if(!isCorrectName(splitInput[i])) incorrectPosition[i] = true;
            }
            if(!isCorrectEmail(splitInput[splitInput.length-1])) incorrectPosition[splitInput.length-1] = true;
            for (int i = 0; i < incorrectPosition.length; i++) {
                if(incorrectPosition[i]) {
                    if (i == 0) {
                        System.out.println("Incorrect first name.");
                    } else if (i == incorrectPosition.length-1) {
                        System.out.println("Incorrect email.");
                    } else {
                        System.out.println("Incorrect last name.");
                    }
                    isCorrect = false;
                    break;
                } else isCorrect = true;
            }
        }
        return isCorrect;
    }

    private boolean isEnglishLetterUpper(char inputCharacter) {
        return inputCharacter >= 65 && inputCharacter <= 90;

    }
    private boolean isEnglishLetterLower(char inputCharacter){
        return inputCharacter >= 97 && inputCharacter <= 122;
    }
    private boolean isValidCharacter(char inputCharacter) {
        return inputCharacter == 39 || inputCharacter == 45;
    }

    private boolean isEnglishLetter(char inputCharacter) {
        if(isEnglishLetterUpper(inputCharacter) || isEnglishLetterLower(inputCharacter)) {
            return true;
        } else {
            return false;
        }
    }
    private boolean isCorrectName(String inputString) {
        boolean isCorrect = true;
        char[] inputArray = inputString.toCharArray();
        if(isEnglishLetter(inputArray[0]) && isEnglishLetter(inputArray[inputString.length()-1]) && inputString.length() > 1) {
            for (int i = 1; i < inputString.length() - 1; i++) {
                if(isEnglishLetter(inputArray[i]) || isValidCharacter(inputArray[i])) {
                    if(isValidCharacter(inputArray[i]) && isValidCharacter(inputArray[i+1])) {
                        isCorrect = false;
                        break;
                    } else if (isEnglishLetterUpper(inputArray[i]) && !isValidCharacter(inputArray[i-1])) {
                        isCorrect = false;
                        break;
                    }
                } else {
                    isCorrect = false;
                    break;
                }
            }
        } else {
            isCorrect = false;
        }
        return isCorrect;
    }
    private boolean isCorrectEmail(String inputString) {
        boolean isCorrect = false;
        String emailRegex  = "^[A-Za-z0-9.]+@[A-Za-z0-9]+\\.[a-zA-Z0-9]+$";
        if (inputString.matches(emailRegex)) {
            isCorrect = true;
        }
        return isCorrect;
    }
}