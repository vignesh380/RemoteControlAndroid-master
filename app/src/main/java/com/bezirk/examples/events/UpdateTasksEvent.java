package com.bezirk.examples.events;

import com.bezirk.middleware.messages.Event;

import java.util.List;

/**
 * Created by vicky on 10/22/2016.
 */

public class UpdateTasksEvent extends Event {

    public List<Task> tasks;

}
