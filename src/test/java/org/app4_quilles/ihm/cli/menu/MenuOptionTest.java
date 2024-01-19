package org.app4_quilles.ihm.cli.menu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.app4_quilles.junit.JUnitExtras;
import org.junit.Test;

public class MenuOptionTest {

    @Test
    public void constructsCorrectlyWithoutCallback() {
        MenuOption mo = new MenuOption("title");
        assertEquals("title is correct", "title", mo.getTitle());
    }

    @Test(timeout = 500)
    public void constructsCorrectlyWithCallback() {
        try {
            assertTrue("MenuOption is callable", JUnitExtras.asyncTest((ok) -> {
                MenuOption mo = new MenuOption("title", () -> {ok.accept(true);});
                assertEquals("title is correct", "title", mo.getTitle());
                mo.call();
            }));
        } catch (Exception e) {
            e.printStackTrace();
            fail("async test failed.");
        }
    }
}
