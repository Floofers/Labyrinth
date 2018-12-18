package com.kun0113.labyrinth.info_holder;

import com.kun0113.labyrinth.enums.ELockColour;

public class Lock {
    // LOCAL VARIABLES
    private boolean wall;
    private String colour;
    private String fileType;

    // CONSTRUCTORS
    public Lock() {
        this.wall = false;
        this.colour = ELockColour.RED.getName();
        this.fileType = ELockColour.RED.getFileType();
    }

    // SETTERS
    public void setWall(boolean wall) {
        this.wall = wall;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    // GETTERS
    public boolean isWall() {
        return wall;
    }

    public String getColour() {
        return colour;
    }

    public String getFileType() {
        return fileType;
    }
}
