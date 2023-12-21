package org.app4_quilles.junit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class JUnitExtrasTest {
    @Test
    public void asyncTestOK() {
        try {
            assertTrue("this test should be successful", JUnitExtras.asyncTest((ok) -> {
                ok.accept(true);
                // TODO put this ^^^^^^ in async function
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
                ok.accept(false);
            }));
        } catch (Exception e) {
            e.printStackTrace();
            fail("async behavior problem in KO test");
        }
    }
}
