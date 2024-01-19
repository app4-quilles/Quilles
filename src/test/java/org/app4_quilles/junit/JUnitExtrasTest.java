package org.app4_quilles.junit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class JUnitExtrasTest {
    @Test
    public void asyncTestOK() {
        try {
            assertTrue("this test should be successful", JUnitExtras.asyncTest((ok) -> {
                ok.accept(true); //finish the test with the provided result.
            }));
        } catch (Exception e) {
            e.printStackTrace();
            fail("async behavior problem in OK test");
        }
    }

    @Test
    public void asyncTestKO() {
        try {
            assertFalse("this test should fail", JUnitExtras.asyncTest((ok) -> {
                ok.accept(false); //finish the test with the provided result.
            }));
        } catch (Exception e) {
            e.printStackTrace();
            fail("async behavior problem in KO test");
        }
    }
}
