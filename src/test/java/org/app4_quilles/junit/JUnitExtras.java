package org.app4_quilles.junit;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;

public class JUnitExtras {

    /**
     * Object that stores a boolean value.
     * Boolean can't be used in this case, hence this class.
     */
    static private class State {
        public boolean value;
        State(boolean state) {
            value = state;
        }
    }

    /**
     * JUnit 4 does not provide any way to make asynchronous tests.
     * This function provides a workaround using Java's CountDownLatch.
     * 
     * Example: `boolean success = asyncTest((ok) -> { code...; ok.accept(true)});`
     * 
     * @param fn the function to call. It takes as a parameter a consumer to
     * end the test. Specify the success with a boolean as a parameter.
     * @return The test success
     * @throws Exception
     */
    public static boolean asyncTest(Consumer<Consumer<Boolean>> fn) throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        final State success = new State(false);

        // call test
        fn.accept((result) -> {
            // when test ended
            success.value = result; // user chooses if it is a success
            latch.countDown();
        });

        try {
            // timeout is set to 0 because callback execution blocks the thread
            // (executed in the same thread). That means it cannot timeout.
            boolean isOnTime = latch.await(0, TimeUnit.MILLISECONDS);
            if (isOnTime) return success.value;
            else throw new Exception("Unexpected behavior in asyncTest. Make sure you called accept()!");

        } catch (InterruptedException e) {
            e.printStackTrace();
            return false; // thread interrupted / app crash: not OK
        }
    }
}
