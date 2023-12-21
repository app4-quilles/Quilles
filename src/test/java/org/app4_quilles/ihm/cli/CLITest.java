package org.app4_quilles.ihm.cli;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import org.app4_quilles.ihm.cli.menu.MenuException;
import org.app4_quilles.ihm.cli.menu.MenuOption;
import org.junit.Test;

public class CLITest {
    
    @Test
    public void requireInputMethod() {
        assertThrows("A scanner must be provided", MenuException.class, () -> {
            new CLI(null);
        });
    }

    @Test
    public void acceptSystemInput() {
        try {
            CLI cli = new CLI(System.in);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Did not accept system input.");
        }
    }

    private ByteArrayInputStream genUserInput(String input) {
        // how to simulate user input: https://stackoverflow.com/a/6416179
        return new ByteArrayInputStream(input.getBytes());
    }

    @Test
    public void acceptCustomInput() {
        try {
            CLI cli = new CLI(genUserInput(""));
        } catch (Exception e) {
            e.printStackTrace();
            fail("Did not accept custom input.");
        }
    }

    @Test
    public void refusesEmptyMenu() {
        try {
            final CLI cli = new CLI(genUserInput(""));

            assertThrows("Refuses empty menu", MenuException.class, () -> {
                cli.showMenu("title", new ArrayList<>());
            });

        } catch (Exception e) {
            e.printStackTrace();
            fail("CLI init failure");
        }
    }

    @Test
    public void acceptSingleItemMenu() {
        try {
            final CLI cli = new CLI(genUserInput(""));

            cli.showMenu("title", new ArrayList<>(Arrays.asList(
                new MenuOption("title")
            )));

        } catch (Exception e) {
            e.printStackTrace();
            fail("CLI init failure");
        }
    }

    @Test
    public void acceptMultipleItemsMenu() {
        try {
            final CLI cli = new CLI(genUserInput(""));

            cli.showMenu("title", new ArrayList<>(Arrays.asList(
                new MenuOption("title"),
                new MenuOption("title")
            )));

        } catch (Exception e) {
            e.printStackTrace();
            fail("CLI init failure");
        }
    }
}
