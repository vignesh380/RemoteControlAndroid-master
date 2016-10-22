package com.bezirk.examples.events;


/**
 * Created by yazid on 22-Oct-16.
 */
public class Task {
    //Id of the last created task
    private static int lastId = 0;

    //Task id
    private int id;
    public int getId(){

        return id;
    }

    //Person responsible for the task
    public Person assignedTo;

    //Task name
    public String name;

    //Task description
    public String description;

    //Task completion status: true means done, false means still not done
    public boolean done;

    //Constructor
    public Task(boolean done, Person assignedTo, String name, String description) {
        this.done = done;
        lastId++;
        this.id = lastId;
        this.assignedTo = assignedTo;
        this.name = name;
        this.description = description;
    }
}