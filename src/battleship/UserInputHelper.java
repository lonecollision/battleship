package battleship;

import java.util.Scanner;

public class UserInputHelper {
    private static Scanner input;
    // Closes input to prevent resource leak
    public void cleanup(){ input.close(); }
    // Takes in input for the main menu
    public int menuInput(int options) {
        input = new Scanner(System.in);
        while(true) {
            System.out.printf("Enter values from 1 to %s: ", options);
            try {
                int menuOption = Integer.parseInt(input.nextLine());
                if (menuOption <= 0 || menuOption > options) {
                    System.out.println("You have entered a value out of range! Try again.");
                }
                else {
                    System.out.println();
                    return menuOption;
                }
            }
            catch (Exception e) {
                System.out.println("Invalid input! Try again.");
            }
        }

    }
    // Takes in input for continuing or not
    public String continueInput(String yes, String no) {
        input = new Scanner(System.in);
        boolean inputValid = false;
        String option = null;
        while (!inputValid) {
            System.out.printf("\nWould you like to continue? %s/%s: ", yes, no);
            try {
                option = input.nextLine();
                if (option.equals(yes) || option.equals(no)) {
                    inputValid = true;
                    System.out.println();
                }
                else {
                    System.out.printf("Please enter either %s or %s! Try again.%n", yes, no);
                }
            }
            catch(Exception e) {
                System.out.println("Invalid input! Try again.");
            }
        }
        return option;
    }
    // Takes in board input (entering a coordinate)
    public int boardInput(int boardLength, String coordinate) {
        input = new Scanner(System.in);
        int value = 0;
        boolean validInt = false;
        String parsedInt;
        while(!validInt) {
            System.out.printf("%s Coordinate: ", coordinate);
            parsedInt = input.nextLine();
            try {
                value = Integer.parseInt(parsedInt);
                if (value < 0 || value > boardLength - 1) {
                    System.out.println("You've entered a value out of range! Try again.");
                }
                else {
                    validInt = true;
                }
            }
            catch(Exception e) {
                System.out.println("You've entered a value of the wrong type! Try again.");
            }
        }
        return value;
    }
}