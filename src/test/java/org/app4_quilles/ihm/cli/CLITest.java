package org.app4_quilles.ihm.cli;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;

import org.app4_quilles.ihm.cli.menu.MenuException;
import org.app4_quilles.ihm.cli.menu.MenuOption;
import org.app4_quilles.junit.JUnitExtras;
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
        } catch (MenuException e) {
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
        } catch (MenuException e) {
            e.printStackTrace();
            fail("Did not accept custom input.");
        }
    }

    @Test
    public void refusesEmptyMenu() {
        try {
            final CLI cli = new CLI(genUserInput("\n"));

            assertThrows("Refuses empty menu", MenuException.class, () -> {
                cli.showMenu("title", new ArrayList<>());
            });

        } catch (MenuException e) {
            e.printStackTrace();
            fail("CLI init failure: " + e.getMessage());
        }
    }

    @Test
    public void acceptSingleItemMenu() {
        try {
            final CLI cli = new CLI(genUserInput("0\n"));

            cli.showMenu("title", new ArrayList<>(Arrays.asList(
                new MenuOption("title")
            )));

        } catch (MenuException e) {
            e.printStackTrace();
            fail("CLI init failure: " + e.getMessage());
        }
    }

    @Test
    public void acceptMultipleItemsMenu() {
        try {
            final CLI cli = new CLI(genUserInput("0\n"));

            cli.showMenu("title", new ArrayList<>(Arrays.asList(
                new MenuOption("a"),
                new MenuOption("b")
            )));

        } catch (MenuException e) {
            e.printStackTrace();
            fail("CLI init failure: " + e.getMessage());
        }
    }

    @Test(timeout = 500)
    public void acceptsFirstUserInput() {
        try {
            final CLI cli = new CLI(genUserInput("0\n"));

            assertTrue("accepts the first possible user input", JUnitExtras.asyncTest((ok) -> {
                cli.showMenu("title", new ArrayList<>(Arrays.asList(
                    new MenuOption("a", () -> {
                        ok.accept(true);
                    }),
                    new MenuOption("b", () -> {
                        ok.accept(false);
                    }),
                    new MenuOption("c", () -> {
                        ok.accept(false);
                    })
                )));
            }));

        } catch (MenuException e) {
            e.printStackTrace();
            fail("CLI init failure: " + e.getMessage());
        }
    }

    @Test(timeout = 500)
    public void acceptsMiddleUserInput() {
        try {
            final CLI cli = new CLI(genUserInput("1\n"));

            assertTrue("accepts a user input from somewhere in the middle of the list", JUnitExtras.asyncTest((ok) -> {
                cli.showMenu("title", new ArrayList<>(Arrays.asList(
                    new MenuOption("a", () -> {
                        ok.accept(false);
                    }),
                    new MenuOption("b", () -> {
                        ok.accept(true);
                    }),
                    new MenuOption("c", () -> {
                        ok.accept(false);
                    })
                )));
            }));

        } catch (MenuException e) {
            e.printStackTrace();
            fail("CLI init failure: " + e.getMessage());
        }
    }

    @Test(timeout = 500)
    public void acceptsLastUserInput() {
        try {
            final CLI cli = new CLI(genUserInput("2\n"));

            assertTrue("accepts the last possible user input", JUnitExtras.asyncTest((ok) -> {
                cli.showMenu("title", new ArrayList<>(Arrays.asList(
                    new MenuOption("a", () -> {
                        ok.accept(false);
                    }),
                    new MenuOption("b", () -> {
                        ok.accept(false);
                    }),
                    new MenuOption("c", () -> {
                        ok.accept(true);
                    })
                )));
            }));

        } catch (MenuException e) {
            e.printStackTrace();
            fail("CLI init failure: " + e.getMessage());
        }
    }

    @Test(timeout = 500)
    public void refusesTooLowInput() {
        try {
            //doesn't throw, so a valid option is necessary to end the test
            final CLI cli = new CLI(genUserInput("-1\n1\n"));

            assertTrue("refuses too low user input", JUnitExtras.asyncTest((ok) -> {
                cli.showMenu("title", new ArrayList<>(Arrays.asList(
                    new MenuOption("a", () -> {
                        ok.accept(false);
                    }),
                    new MenuOption("b", () -> {
                        ok.accept(true);
                    }),
                    new MenuOption("c", () -> {
                        ok.accept(false);
                    })
                )));
            }));

        } catch (MenuException e) {
            e.printStackTrace();
            fail("CLI init failure: " + e.getMessage());
        }
    }

    @Test(timeout = 500)
    public void refusesTooHighUserInput() {
        try {
            //doesn't throw, so a valid option is necessary to end the test
            final CLI cli = new CLI(genUserInput("3\n1\n"));

            assertTrue("refuses too high user input", JUnitExtras.asyncTest((ok) -> {
                cli.showMenu("title", new ArrayList<>(Arrays.asList(
                    new MenuOption("a", () -> {
                        ok.accept(false);
                    }),
                    new MenuOption("b", () -> {
                        ok.accept(true);
                    }),
                    new MenuOption("c", () -> {
                        ok.accept(false);
                    })
                )));
            }));

        } catch (MenuException e) {
            e.printStackTrace();
            fail("CLI init failure: " + e.getMessage());
        }
    }

    @Test(timeout = 500)
    public void pressEnterToConfirmWorksOnEnter() {
        try {
            final CLI cli = new CLI(genUserInput("\n"));

            assertTrue("press enter to confirm works on enter", JUnitExtras.asyncTest((ok) -> {
                cli.pressEnterToConfirm();
                ok.accept(!cli.scannerHasString());
            }));

        } catch (MenuException e) {
            e.printStackTrace();
            fail("CLI init failure: " + e.getMessage());
        }
    }

    @Test(timeout = 500)
    public void acceptsStringInput() {
        String test = "text";
        try {
            final CLI cli = new CLI(genUserInput(test));

            assertTrue("accepts string input", JUnitExtras.asyncTest((ok) -> {
                String result = cli.getInputString("please give some input");
                ok.accept(result.equals(test));
            }));

        } catch (MenuException e) {
            e.printStackTrace();
            fail("CLI init failure: " + e.getMessage());
        }
    }

    @Test(timeout = 500)
    public void acceptsConditionalStringInput() {
        String test = "ok";
        try {
            final CLI cli = new CLI(genUserInput(test));

            assertTrue("accepts conditional string input", JUnitExtras.asyncTest((ok) -> {
                String result = cli.getInputString("please give some input", ((text) -> text.length() < 10));
                ok.accept(result.equals(test));
            }));

        } catch (MenuException e) {
            e.printStackTrace();
            fail("CLI init failure: " + e.getMessage());
        }
    }

    @Test(timeout = 500)
    public void refusesInvalidStringInput() {
        try {
            //doesn't throw, so a valid option is necessary to end the test
            final CLI cli = new CLI(genUserInput("thisIsTooLongForTheInput\nThisIsOk"));

            assertTrue("refuses invalid string input", JUnitExtras.asyncTest((ok) -> {
                String result = cli.getInputString("please give some input", ((text) -> text.length() < 10));
                ok.accept(result.equals("ThisIsOk"));
            }));

        } catch (MenuException e) {
            e.printStackTrace();
            fail("CLI init failure: " + e.getMessage());
        }
    }

    @Test(timeout = 500)
    public void acceptsIntInput() {
        Integer test = 5;
        try {
            final CLI cli = new CLI(genUserInput(test.toString() + "\n"));

            assertTrue("accepts integer input", JUnitExtras.asyncTest((ok) -> {
                Integer result = cli.getInputInt("please give some input");
                ok.accept(result.equals(test));
            }));

        } catch (MenuException e) {
            e.printStackTrace();
            fail("CLI init failure: " + e.getMessage());
        }
    }

    @Test(timeout = 500)
    public void acceptsConditionalIntInput() {
        Integer test = 5;
        try {
            final CLI cli = new CLI(genUserInput(test.toString() + "\n"));

            assertTrue("accepts conditional integer input", JUnitExtras.asyncTest((ok) -> {
                Integer result = cli.getInputInt("please give some input", 0, 10);
                ok.accept(result.equals(test));
            }));

        } catch (MenuException e) {
            e.printStackTrace();
            fail("CLI init failure: " + e.getMessage());
        }
    }

    @Test(timeout = 500)
    public void refusesTooHighIntegerInput() {
        try {
            //doesn't throw, so a valid option is necessary to end the test
            final CLI cli = new CLI(genUserInput("15\n5\n"));

            assertTrue("refuses too high integer input", JUnitExtras.asyncTest((ok) -> {
                Integer result = cli.getInputInt("please give some input", 0, 10);
                ok.accept(result.equals(5));
            }));

        } catch (MenuException e) {
            e.printStackTrace();
            fail("CLI init failure: " + e.getMessage());
        }
    }

    @Test(timeout = 500)
    public void refusesTooLowIntegerInput() {
        try {
            //doesn't throw, so a valid option is necessary to end the test
            final CLI cli = new CLI(genUserInput("-5\n5\n"));

            assertTrue("refuses too low integer input", JUnitExtras.asyncTest((ok) -> {
                Integer result = cli.getInputInt("please give some input", 0, 10);
                ok.accept(result.equals(5));
            }));

        } catch (MenuException e) {
            e.printStackTrace();
            fail("CLI init failure: " + e.getMessage());
        }
    }
}
