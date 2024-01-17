package org.app4_quilles.ihm.cli;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.Function;

import org.app4_quilles.ihm.cli.menu.MenuException;
import org.app4_quilles.ihm.cli.menu.MenuOption;

public class CLI {

    public final Scanner scanner;

    /**
     * Prepares a new CLI interface.
     * @param inputStream InputStream to use. Useful for mocking purposes. The provided scanner must **NOT** be closed.
     * @throws Exception
     */
    CLI(InputStream inputStream) throws MenuException {
        if (inputStream == null) throw new MenuException("InputStream must be specified.");
        this.scanner = new Scanner(inputStream);
    }

    CLI() throws MenuException {
        this(System.in);
    }

    /**
     * Returns if the scanner can eat a string
     * @return
     */
    public boolean scannerHasString() {return scanner.hasNextLine();}

    public int getInputInt(String promptMsg, int min, int max) {
        return 0;
    }
    public int getInputInt(String promptMsg) {
        return 0;
    }

    /**
     * Ask the user for a string while the input is invalid
     * and return the result
     * @param promptMsg
     * @param validityFunction function that returns if a string is a valid input
     * @return
     */
    public String getInputString(String promptMsg, Function<String, Boolean> validityFunction) {
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
            
            if (invalid) System.out.println("invalid input!");
        }

        return response;
    }

    /**
     * Ask the user for a string while the input is invalid
     * and return the result
     * @param promptMsg
     * @return
     */
    public String getInputString(String promptMsg) {
        return getInputString(promptMsg, (str) -> {return true;});   
    }

    public int showMenu(String title, ArrayList<MenuOption> options, int page) {
        return 0;
    }
    public int showMenu(String title, ArrayList<MenuOption> options) {
        return 0;
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
        } catch (MenuException e) {
            e.printStackTrace();
        }
    }
}
