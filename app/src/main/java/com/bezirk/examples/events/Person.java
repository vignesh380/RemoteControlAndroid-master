package com.bezirk.examples.events;

/**
 * Created by yazid on 22-Oct-16.
 */

public class Person {
    //Zirk id of the person's phone
    public String phoneZirkId;

    //Name of the person
    public String name;


    //Status of the person: in, out, asleep
    public PersonStatus status;

    /**
     *
     * @param phoneZirkId Zirk id of the person's phone
     * @param name Person name
     * @param status Person status
     */
    public Person(String phoneZirkId, String name, PersonStatus status) {
        this.phoneZirkId = phoneZirkId;
        this.name = name;
        this.status = status;
    }
}