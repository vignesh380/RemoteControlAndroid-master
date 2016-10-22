package com.bezirk.examples.events;

import com.bezirk.middleware.messages.Event;

import java.util.List;

/**
 * Created by vicky on 10/22/2016.
 */

public class UpdateTasksEvent extends BaseEvent {
    public UpdateTasksEvent(List<Task> tasks) {
        this.tasks = tasks;
    }

    public UpdateTasksEvent() {
    }

    public UpdateTasksEvent(String senderId, String receiverId, List<Task> tasks) {
        super(senderId, receiverId);
        this.tasks = tasks;
    }

    public List<Task> tasks;
}