package com.kun0113.labyrinth.enums;

public enum EGameState {
    LEVEL("Level"),
    RESTART_LEVEL("Restart Level"),
    EXIT("Exit");

    // LOCAL VARIABLES
    private String name;

    // CONSTRUCTORS
    EGameState(String name) {
        this.name = name;
    }

    // GETTERS
    public String getName() {
        return (this.name);
    }
}
