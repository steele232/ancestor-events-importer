package com.steele.swainston.ancestoreventsimporter.services;

import com.steele.swainston.ancestoreventsimporter.schema.Ancestry;
import com.steele.swainston.ancestoreventsimporter.schema.CurrentPerson;
import com.steele.swainston.ancestoreventsimporter.schema.Person;

public class FamilySearchClient {
    private String[] credentials;

    public FamilySearchClient(String username, String password) {
        credentials = new String[]{username, password};
    }

    private static class FSGetCurrentPersonCall extends FamilySearchApiCall<CurrentPerson> {
        private static final String PATH = "current-person?Expect=200-ok";

        FSGetCurrentPersonCall(String[] credentials, Callback<CurrentPerson> callback) {
            super(credentials, CurrentPerson.class, callback);
        }
    }

    private static class FSGetAncestryCall extends FamilySearchApiCall<Ancestry> {
        private static final String PATH = "ancestry?person=%s";

        FSGetAncestryCall(String[] credentials, Callback<Ancestry> callback) {
            super(credentials, Ancestry.class, callback);
        }
    }

    private static class FSGetPersonCall extends FamilySearchApiCall<Person> {
        private static final String PATH = "persons/%s";

        FSGetPersonCall(String[] credentials, Callback<Person> callback) {
            super(credentials, Person.class, callback);
        }
    }

    public void getCurrentPerson(Callback<CurrentPerson> callback) {
        new FSGetCurrentPersonCall(credentials, callback).execute(FSGetCurrentPersonCall.PATH);
    }

    public void getAncestry(String personId, Callback<Ancestry> callback) {
        new FSGetAncestryCall(credentials, callback)
                .execute(String.format(FSGetAncestryCall.PATH, personId));
    }

    public void getPersonById(String id, Callback<Person> callback) {
        new FSGetPersonCall(credentials, callback)
                .execute(String.format(FSGetPersonCall.PATH, id));
    }
}
