package tracker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public interface ScanUserInput {
    public default String checkInput(String input) {
        return null;
    }
    public default String InputRequest() {
        String temp = null;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String input = reader.readLine();
            temp = input;
        } catch(IOException e){
            System.out.println("No input.");
            temp = "no input";
            return temp;
        }
        return temp;
    }
}