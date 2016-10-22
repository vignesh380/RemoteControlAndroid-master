package com.bezirk.examples.events;

import com.bezirk.middleware.messages.Event;


/**
 * Created by vicky on 10/22/2016.
 */

public class AliveEvent extends BaseEvent {
    public AliveEvent(String senderId, String receiverId) {
        super(senderId, receiverId);
    }
}