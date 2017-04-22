package com.json;

public class Person {
    private String firstName;

    public Person() {}

    public Person(String firstName) {
        this.firstName = firstName;
    }

    //    @JsonProperty("FirstName")
    public String getFirstName() {
        return firstName;
    }

    //    @JsonProperty("FirstName")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
