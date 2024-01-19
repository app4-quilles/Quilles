package org.app4_quilles.ihm.cli.menu;

/**
 * Callback function type to use in method signatures.
 * It allows an argument as follows (lambdas):
 * "() -> {code}" or "(() -> value)"
 */
@FunctionalInterface
public interface Action {
    void action();
}