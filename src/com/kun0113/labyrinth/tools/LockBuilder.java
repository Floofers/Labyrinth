package com.kun0113.labyrinth.tools;

import com.kun0113.labyrinth.enums.ELockColour;
import com.kun0113.labyrinth.info_holder.Lock;
import com.kun0113.labyrinth.interfaces.ILockBuilder;

public class LockBuilder implements ILockBuilder {
    // LOCAL VARIABLES
    private Lock lockTemp;

    // CONSTRUCTORS
    public LockBuilder() {
        lockTemp = new Lock();
    }

    // LOCK CREATION
    @Override
    public Lock build() {
        Lock lock = new Lock();
        lock.setWall(lockTemp.isWall());
        lock.setColour(lockTemp.getColour());
        lock.setFileType(lockTemp.getFileType());
        return lock;
    }

    // LOCK PREPARATION
    @Override
    public String getName() {
        String lockFileName = "Lock";
        if (lockTemp.isWall()) {
            lockFileName += "Wall";
        }
        lockFileName += "_" + lockTemp.getColour() + lockTemp.getFileType();
        return (lockFileName);
    }

    @Override
    public ILockBuilder setWall(final String command) {
        if (command.contains("wall".toLowerCase())) {
            this.lockTemp.setWall(true);
        } else {
            this.lockTemp.setWall(false);
        }
        return (this);
    }

    @Override
    public ILockBuilder setColour(final String command) {
        if (command.toLowerCase().contains(ELockColour.RED.getName().toLowerCase())) {
            this.lockTemp.setColour(ELockColour.RED.getName());
        } else if (command.toLowerCase().contains(ELockColour.GREEN.getName().toLowerCase())) {
            this.lockTemp.setColour(ELockColour.GREEN.getName());
        } else if (command.toLowerCase().contains(ELockColour.BLUE.getName().toLowerCase())) {
            this.lockTemp.setColour(ELockColour.BLUE.getName());
        }
        return (this);
    }

    @Override
    public ILockBuilder setFileType(final String command) {
        if (command.toLowerCase().contains(ELockColour.RED.getName().toLowerCase())) {
            this.lockTemp.setFileType(ELockColour.RED.getFileType());
        } else if (command.toLowerCase().contains(ELockColour.GREEN.getName().toLowerCase())) {
            this.lockTemp.setFileType(ELockColour.GREEN.getFileType());
        } else if (command.toLowerCase().contains(ELockColour.BLUE.getName().toLowerCase())) {
            this.lockTemp.setFileType(ELockColour.BLUE.getFileType());
        }
        return (this);
    }
}