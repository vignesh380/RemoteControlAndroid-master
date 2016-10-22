package com.bezirk.examples.events;

/**
 * Created by vicky on 10/22/2016.
 */

public class SetAsleepEvent extends BaseEvent {
    public boolean asleep;

    public SetAsleepEvent(boolean asleep) {
        this.asleep = asleep;
    }

    public SetAsleepEvent() {
    }

    public SetAsleepEvent(String senderId, String receiverId, boolean asleep) {
        super(senderId, receiverId);
        this.asleep = asleep;
    }
}