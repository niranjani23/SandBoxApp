package com.playbox.niranjani.sandboxapp.commonUtils;

package com.playbox.niranjani.sandboxapp.commonUtils;

import android.os.SystemClock;
import androidx.annotation.NonNull;
import androidx.test.espresso.EspressoException;
import androidx.test.uiautomator.UiObjectNotFoundException;
import io.reactivex.exceptions.Exceptions;
import me.lyft.android.logging.L;

/**
 * <h1>Generic Ui</h1>
 * An abstract Ui class. Defines generic protected methods for other Ui classes to inherit.
 */
public abstract class Ui {

    protected static final int DEFAULT_TIMEOUT = 15000;
    protected static final int DEFAULT_IF_NEEDED_TIMEOUT = 5000;
    private static final int INTERVAL = 166; // 10 frames.

    protected Ui() {}

    public interface UiInteraction {

        void interact() throws Throwable;
    }

    public static class UiInteractionException extends RuntimeException {

        public UiInteractionException(String message) {
            super(message);
        }
    }

    private static class HandledInterruptionException extends RuntimeException {}

    protected static void genericInteract(@NonNull UiInteraction interaction, long timeoutMs) {
        final long startTimeMs = SystemClock.elapsedRealtime();

        Throwable lastUiInteractionException;

        do {
            try {
                interaction.interact();
                return;
            } catch (Throwable throwable) {
                // If EspressoException was thrown by UI interact — condition of UI interaction is not met yet and we need to spin interval cycle.
                // Otherwise it's unexpected exception and we should throw it so the test fails.
                if (throwable instanceof EspressoException
                        || throwable instanceof AssertionError
                        || throwable instanceof UiObjectNotFoundException
                        || throwable instanceof UiInteractionException) {
                    lastUiInteractionException = throwable;

//                    if (timeoutMs > 0) {
//                        try {
//                            Thread.sleep(INTERVAL);
//                        } catch (InterruptedException interrupted) {
//                            throw Exceptions.propagate(interrupted);
//                        }
//                    }
                } else if (throwable instanceof HandledInterruptionException) {
                    // If we handled an interruption, start over with a new timeout
                    genericInteract(interaction, timeoutMs);
                    return;
                } else {
                    throw Exceptions.propagate(throwable);
                }
            }
        } while (SystemClock.elapsedRealtime() - startTimeMs <= timeoutMs);

        final long resultingTimeMs = SystemClock.elapsedRealtime() - startTimeMs;

        // Do not put `lastUiInteractionException` as a cause since it has references to Views and will leak memory through test runner.
        final UiInteractionException uiInteractionException = new UiInteractionException(
                "UI interaction did not succeed after " + resultingTimeMs + "ms, check logcat for details."
                        + "\n"
                        + lastUiInteractionException.getMessage()
        );

        uiInteractionException.setStackTrace(lastUiInteractionException.getStackTrace());

        throw uiInteractionException;
    }

    /**
     * Interacts with the Ui but each time the interaction fails it tries to handle interruptions before trying the interaction again
     */
    protected static void interactWithInterruptions(@NonNull UiInteraction mainInteraction, long timeoutMs,
                                                    @NonNull UiInteraction... handleInterruptions) {
        genericInteract(() -> {
            try {
                genericInteract(mainInteraction, 0);
            } catch (UiInteractionException e) {
                // The mainInteraction failed, so try to handle any interruptions
                for (UiInteraction handleInterruption : handleInterruptions) {
                    boolean handledSomething = genericInteractWithUiIfNeeded(handleInterruption, 0);
                    if (handledSomething) {
                        throw new HandledInterruptionException();
                    }
                }
                throw e; // Re-throw the exception so we retry the mainInteraction
            }
        }, timeoutMs);
    }

    /**
     * Interacts with the Ui but ignores any UiInteractionException if a view is not found.
     *
     * @return true if the interaction succeeded
     */
    protected static boolean genericInteractWithUiIfNeeded(@NonNull UiInteraction interaction, long timeoutMs) {
        try {
            genericInteract(interaction, timeoutMs);
            return true;
        } catch (UiInteractionException e) {
            L.d("Ignoring UiInteractionException: " + e.getMessage());
            return false;
        }
    }
}

