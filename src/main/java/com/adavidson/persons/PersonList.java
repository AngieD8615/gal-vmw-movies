package com.adavidson.persons;

import java.util.List;

public class PersonList {
    private List<Person> persons;
    private int size = 0;

    public PersonList () {}

    public PersonList (List<Person> persons) {
        this.persons = persons;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public int getSize() {
        size = persons.size();
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isEmpty() {
        return persons == null || persons.isEmpty();
    }
}
