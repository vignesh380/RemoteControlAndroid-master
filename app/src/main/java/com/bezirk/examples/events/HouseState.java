package com.bezirk.examples.events;

import com.bezirk.middleware.messages.Event;

import java.util.List;

/**
 * Created by vicky on 10/22/2016.
 */

public class HouseState {
    //People
    public List<Person> People;

    //Tasks
    public  List<Task> Tasks;

    public HouseState() {
    }

    /**
     *
     * @param people People
     * @param tasks Tasks
     */
    public HouseState(List<Person> people, List<Task> tasks) {
        People = people;
        Tasks = tasks;
    }
}