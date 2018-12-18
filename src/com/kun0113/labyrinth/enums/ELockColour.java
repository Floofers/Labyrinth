package com.kun0113.labyrinth.enums;

public enum ELockColour {
    RED("Red", ".png"),
    GREEN("Green", ".png"),
    BLUE("Blue", ".png");

    // LOCAL VARIABLES
    private final String name;
    private final String fileType;

    // CONSTRUCTORS
    ELockColour(String name, String fileType) {
        this.name = name;
        this.fileType = fileType;
    }

    // GETTERS
    public String getName() {
        return name;
    }

    public String getFileType() {
        return fileType;
    }
}
