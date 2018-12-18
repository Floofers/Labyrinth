package com.kun0113.labyrinth.tools.grid;

import com.kun0113.labyrinth.sprites.Sprite;

import java.io.Serializable;

public class Tile implements Serializable {
    // LOCAL VARIABLES
    private double x, y;
    private boolean collidable;
    private Sprite object;

    // CONSTRUCTORS
    Tile(double x, double y) {
        this.x = x;
        this.y = y;
        this.collidable = false;
        this.object = null;
    }

    // SETTERS
    public void setCollidable(Sprite sprite) {
        if (sprite != null) {
            this.collidable = true;
            this.object = sprite;
            return;
        }
        this.collidable = false;
        this.object = null;
    }

    // GETTERS
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean getCollidable() {
        return collidable;
    }

    public Sprite getObject() {
        return (this.object);
    }
}
