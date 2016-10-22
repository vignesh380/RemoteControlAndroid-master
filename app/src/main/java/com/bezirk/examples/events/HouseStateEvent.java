package com.bezirk.examples.events;

/**
 * Created by vicky on 10/22/2016.
 */

public class HouseStateEvent extends BaseEvent {
    public HouseStateEvent(String senderId, String receiverId, HouseState houseState) {
        super(senderId, receiverId);
        this.houseState = houseState;
    }

    public HouseStateEvent() {
    }

    public HouseState houseState;
}