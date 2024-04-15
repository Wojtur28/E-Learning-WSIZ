package org.example.elearningsecurity.user;

public enum RoleType {

    ADMINISTRATOR("administrator"),
    USER("user");

    private String name;

    RoleType(String name) {
        this.name = name;
    }
}
