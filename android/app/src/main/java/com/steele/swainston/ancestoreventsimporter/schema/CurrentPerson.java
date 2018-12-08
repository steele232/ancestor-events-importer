package com.steele.swainston.ancestoreventsimporter.schema;

import java.util.List;

public class CurrentPerson {
    private String description;
    private List<Person> persons;
    private List<ChildAndParentsRelationship> childAndParentsRelationships;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public List<ChildAndParentsRelationship> getChildAndParentsRelationships() {
        return childAndParentsRelationships;
    }

    public void setChildAndParentsRelationships(List<ChildAndParentsRelationship> childAndParentsRelationships) {
        this.childAndParentsRelationships = childAndParentsRelationships;
    }
}