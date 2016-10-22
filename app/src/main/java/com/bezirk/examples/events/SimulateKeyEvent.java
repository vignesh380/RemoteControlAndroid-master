package com.bezirk.examples.events;

import com.bezirk.middleware.messages.Event;

public class SimulateKeyEvent extends Event {
    private final int keyIntegerCode; //The integer code for the key

    public SimulateKeyEvent(int keyIntegerCode) {
        this.keyIntegerCode = keyIntegerCode;
    }

    public int getKeyIntegerCode() {
        return keyIntegerCode;
    }
}
