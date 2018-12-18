package com.kun0113.labyrinth.enums;

public enum EMovementInput {
    LEFT(0),
    RIGHT(1),
    UP(2),
    DOWN(3);

    // LOCAL VARIABLES
    private final int id;

    // CONSTRUCTORS
    EMovementInput(int id) {
        this.id = id;
    }

    // SETTERS
    public int getId() {
        return (this.id);
    }
}
