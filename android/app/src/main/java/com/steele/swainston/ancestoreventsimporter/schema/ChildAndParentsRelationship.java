package com.steele.swainston.ancestoreventsimporter.schema;

public class ChildAndParentsRelationship {
    private String id;
    private FamilyMember mother;
    private FamilyMember father;
    private FamilyMember child;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public FamilyMember getMother() {
        return mother;
    }

    public void setMother(FamilyMember mother) {
        this.mother = mother;
    }

    public FamilyMember getFather() {
        return father;
    }

    public void setFather(FamilyMember father) {
        this.father = father;
    }

    public FamilyMember getChild() {
        return child;
    }

    public void setChild(FamilyMember child) {
        this.child = child;
    }
}
