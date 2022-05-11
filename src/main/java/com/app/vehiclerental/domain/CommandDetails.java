package com.app.vehiclerental.domain;

public class CommandDetails {

    private String name;
    private String[] arguments;

    public String getName() {
        return name;
    }

    public String[] getArguments() {
        return arguments;
    }

    public CommandDetails(String name, String[] arguments) {
        this.name = name;
        this.arguments = arguments;
    }
}
