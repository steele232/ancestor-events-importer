package com.steele.swainston.ancestoreventsimporter.schema;

import java.util.List;

public class Person {
    private String id;
    private DisplayPerson display;
    private List<Fact> facts;

    public List<Fact> getFacts() {
        return facts;
    }

    public void setFacts(List<Fact> facts) {
        this.facts = facts;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DisplayPerson getDisplay() {
        return display;
    }

    public void setDisplay(DisplayPerson display) {
        this.display = display;
    }
}
