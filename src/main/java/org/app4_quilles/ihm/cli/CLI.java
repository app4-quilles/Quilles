package org.app4_quilles.ihm.cli;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.Function;

import org.app4_quilles.ihm.cli.menu.Action;
import org.app4_quilles.ihm.cli.menu.MenuException;
import org.app4_quilles.ihm.cli.menu.MenuOption;

public class CLI {

    public final Scanner scanner;
    public boolean testMode;

    /**
     * Prepares a new CLI interface.
     * @param inputStream InputStream to use. Useful for mocking purposes. The provided scanner must **NOT** be closed.
     * @param testMode if testing through JUnit, this should be true. In other words, if the input stream is not System.in, this should be true.
     * @throws MenuException
     */
    CLI(InputStream inputStream, boolean testMode) throws MenuException {
        if (inputStream == null) throw new MenuException("InputStream must be specified.");
        this.scanner = new Scanner(inputStream);
        this.testMode = testMode;
    }

    /**
     * Prepares a new CLI interface.
     * @param inputStream InputStream to use. Useful for mocking purposes. The provided scanner must **NOT** be closed.
     * @throws MenuException
     */
    CLI(InputStream inputStream) throws MenuException {
        this(inputStream, true);
    }

    /**
     * Prepares a new CLI interface.
     * @throws MenuException
     */
    CLI() throws MenuException {
        this(System.in, false);
    }

    public boolean getTestMode() {return testMode;}

    /**
     * Returns if the scanner can eat a string
     * @return
     */
    public boolean scannerHasString() {return scanner.hasNextLine();}

    /**
     * Returns if the scanner can eat an int
     * @return
     */
    public boolean scannerHasInt() {return scanner.hasNextInt();}

    /**
     * Asks the user for an integer while the input is not valid
     * and return the result
     * 
     * In test mode, only one attempt is allowed and -1 is returned on wrong input.

     * @param promptMsg display message
     * @param min min value, included
     * @param max max value, included
     * @param onInvalidInput callback when the user enters an invalid value
     * @return user's response
     */
    public int getInputInt(String promptMsg, int min, int max, Action onInvalidInput) {
        int response = -1;

        boolean invalid = true;

        // re-ask until the input is valid
        while (invalid) {
            System.out.print(promptMsg + ": ");
            
            try {
                response = scanner.nextInt();
                invalid = (response > max || response < min);
            } catch (InputMismatchException e) {
                invalid = true;
                // empty the queue to not cause an infinite loop
                // due to the invalid value not being consumed.
                scanner.next();
            }
            
            if (invalid) {
                System.out.println("invalid input!");
                onInvalidInput.action();
                if (testMode) return -1;
            }
        }

        scanner.nextLine(); // consume the \n not consumed by nextInt()

        return response;
    }

    /**
     * Asks the user for an integer while the input is not valid
     * and return the result
     * @param promptMsg display message
     * @param min min value, included
     * @param max max value, included
     * @return user's response
     */
    public int getInputInt(String promptMsg, int min, int max) {
        return getInputInt(promptMsg, min, max, () -> {});
    }

    /**
     * Asks the user for an integer while the input is not valid
     * and return the result
     * @param promptMsg display message
     * @return user's response
     */
    public int getInputInt(String promptMsg) {
        return getInputInt(promptMsg, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    /**
     * Ask the user for a string while the input is invalid
     * and return the result.
     * 
     * In test mode, only one attempt is allowed and "" is returned on wrong input.
     * 
     * @param promptMsg display message
     * @param validityFunction function that returns if a string is a valid input
     * @param onInvalidInput callback when the user enters an invalid value
     * @return user's response
     */
    public String getInputString(String promptMsg, Function<String, Boolean> validityFunction, Action onInvalidInput) {
        String response = null;

        boolean invalid = true;

        // re-ask until the input is valid
        while (invalid) {
            System.out.print(promptMsg + ": ");
            
            try {
                response = scanner.nextLine();
                invalid = !validityFunction.apply(response);
            } catch (InputMismatchException e) {
                invalid = true;
                // empty the queue to not cause an infinite loop
                // due to the invalid value not being consumed.
                scanner.next();
            }
            
            if (invalid) {
                System.out.println("invalid input!");
                onInvalidInput.action();
                if (testMode) return "";
            }
        }

        return response;
    }

    /**
     * Ask the user for a string while the input is invalid
     * and return the result
     * @param promptMsg display message
     * @param validityFunction function that returns if a string is a valid input
     * @return user's response
     */
    public String getInputString(String promptMsg, Function<String, Boolean> validityFunction) {
        return getInputString(promptMsg, validityFunction, () -> {});
    }

    /**
     * Ask the user for a string while the input is invalid
     * and return the result
     * @param promptMsg display message
     * @return user's response
     */
    public String getInputString(String promptMsg) {
        return getInputString(promptMsg, (str) -> {return true;});   
    }

    /**
     * Shows a menu of options, with an index associated for each option.
     * Asks the user for one of the options, and execute the associated action.
     * @param title
     * @param options (at least one must be provided)
     * @param onInvalidInput callback when the user enters an invalid value
     * @return the selected value
     */
    public int showMenu(String title, ArrayList<MenuOption> options, Action onInvalidInput) {
        if (options == null || options.isEmpty()) {
            throw new MenuException("At least one option must be provided");
        }

        // display
        System.out.println("================ ["+title+"] ================");

        int minInput = 0;
        int maxInput = options.size() - 1;
        
        for (int i = minInput; i <= maxInput; i++) {
            System.out.println(i + " - " + options.get(i).getTitle());
        }

        // input
        int response = getInputInt("-> action ("+minInput+" to "+maxInput+")", minInput, maxInput, onInvalidInput);
        if (response == -1 && testMode) return -1; // in test mode, only one attempt is allowed and -1 is returned on invalid value.

        // action
        System.out.println("================ [ => " + options.get(response).getTitle() + "] ================");
        options.get(response).call();
        return response;
    }

    /**
     * Shows a menu of options, with an index associated for each option.
     * Asks the user for one of the options, and execute the associated action.
     * @param title
     * @param options (at least one must be provided)
     * @return the selected value
     */
    public int showMenu(String title, ArrayList<MenuOption> options) {
        return showMenu(title, options, () -> {});
    }

    /**
     * Put the program on hold until the user presses Enter.
     * @param promptMsg message to display
     */
    public void pressEnterToConfirm(String promptMsg) {
        System.out.print(promptMsg + " [press Enter]");
        scanner.nextLine();
    }

    public void pressEnterToConfirm() {
        pressEnterToConfirm("");
    }

    /**
     * main for testing CLI behavior manually.
     * @param args
     */
    public static void main(String[] args) {
        CLI cli;
        try {
            cli = new CLI();
            cli.pressEnterToConfirm();
            System.out.println(cli.getInputString("string"));
            System.out.println(cli.getInputString("string with at most 10 chars", ((str) -> str.length() < 10)));
            
            System.out.println(cli.getInputInt("int"));
            System.out.println(cli.getInputInt("int between 0 and 5", 0, 5, () -> {System.out.println("BAD");}));
            
            System.out.println(cli.showMenu("menu", new ArrayList<>(Arrays.asList(
                new MenuOption("yes", () -> {System.out.println("yes");}),
                new MenuOption("no", () -> {System.out.println("no");})
            ))));
        } catch (MenuException e) {
            e.printStackTrace();
        }
    }
}
