package org.app4_quilles.ihm.cli.menu;

/**
 * Exception thrown when the menu faces a problem,
 * such as bad arguments.
 */
public class MenuException extends RuntimeException {
    public MenuException() {super();}
    public MenuException(String msg) {super(msg);}
}
