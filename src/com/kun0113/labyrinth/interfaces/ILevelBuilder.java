package com.kun0113.labyrinth.interfaces;

import com.kun0113.labyrinth.info_holder.Level;

public interface ILevelBuilder {
    Level build();

    ILevelBuilder createBorder();

    ILevelBuilder addSprite(String command, double x, double y);

    ILevelBuilder addEnemy(double x, double y);

    ILevelBuilder addSprites(String command, double x, double y, int countX, int countY);

    ILevelBuilder fillMap(String command);

    ILevelBuilder removeSprite(double x, double y);

    ILevelBuilder removeEnemy(double x, double y);

    ILevelBuilder removeSprites(double x, double y);

    ILevelBuilder removeSprites(double x, double y, int countX, int countY);

    ILevelBuilder setPlayer(double x, double y);
}
