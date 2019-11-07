package com.playbox.niranjani.sandboxapp;

import android.support.annotation.NonNull;
import com.playbox.niranjani.sandboxapp.commonUtils.Ui.UiInteraction;

class Ui extends com.lyft.android.instrumentation.Ui {

    private Ui() {}

    private int DEFAULT_TIMEOUT = 2000;

    public static void interactWithUi(@NonNull UiInteraction interaction) {
        genericInteract(interaction, DEFAULT_TIMEOUT);
    }

    public static void interactWithUi(@NonNull UiInteraction interaction, long timeoutMs) {
        genericInteract(interaction, timeoutMs);
    }

}
