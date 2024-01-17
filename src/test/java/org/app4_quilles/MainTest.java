package org.app4_quilles;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class MainTest {
    @Test
    public void newArrayListsHaveNoElements() {
        assertEquals("array is empty", new ArrayList<Integer>().size(), 0);
    }
}
