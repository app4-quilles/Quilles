package org.app4_quilles.ihm.cli.menu;

/**
 * Function to call when a menu option is selected.
 */
@FunctionalInterface
public interface Action {
    void action();
}