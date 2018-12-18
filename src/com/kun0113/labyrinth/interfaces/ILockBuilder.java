package com.kun0113.labyrinth.interfaces;

import com.kun0113.labyrinth.info_holder.Lock;

public interface ILockBuilder {
    Lock build();

    String getName();

    ILockBuilder setWall(final String command);

    ILockBuilder setColour(final String command);

    ILockBuilder setFileType(final String command);
}