package com.kun0113.labyrinth.sprites.movable;

import com.kun0113.labyrinth.info_holder.Position;
import com.kun0113.labyrinth.info_holder.Vector2;
import com.kun0113.labyrinth.interfaces.IInertia;
import com.kun0113.labyrinth.interfaces.IRenderable;
import com.kun0113.labyrinth.interfaces.IUpdatable;
import com.kun0113.labyrinth.sprites.Sprite;

public abstract class SpriteMovable extends Sprite implements IInertia, IUpdatable, IRenderable {
    // LOCAL VARIABLES
    protected Position newPosition;
    protected Vector2 speed;
    protected double maxSpeed, acceleration, slowdown;

    // CONSTRUCTORS
    public SpriteMovable() {
        super();
        this.type = "SpriteMovable";
        this.newPosition = new Position(this.image.getX(), this.image.getY());
        this.speed = new Vector2(0.0, 0.0);
        this.maxSpeed = 3;
        this.acceleration = 1.5;
        this.slowdown = 2;
    }

    // MOVEMENT METHODS
    protected abstract void move();

    @Override
    public void render() {
        this.image.setX(this.newPosition.getValue(0));
        this.image.setY(this.newPosition.getValue(1));
    }

    @Override
    public void accelerateX(boolean right) {
        if (right) {
            this.speed.setValue(0, this.speed.getValue(0) + this.acceleration);
            if (this.speed.getValue(0) > this.maxSpeed) {
                this.speed.setValue(0, this.maxSpeed);
            }
        } else {
            this.speed.setValue(0, this.speed.getValue(0) - this.acceleration);
            if (this.speed.getValue(0) < -this.maxSpeed) {
                this.speed.setValue(0, -this.maxSpeed);
            }
        }
    }

    @Override
    public void accelerateY(boolean down) {
        if (down) {
            this.speed.setValue(1, this.speed.getValue(1) + this.acceleration);
            if (this.speed.getValue(1) > this.maxSpeed) {
                this.speed.setValue(1, this.maxSpeed);
            }
        } else {
            this.speed.setValue(1, this.speed.getValue(1) - this.acceleration);
            if (this.speed.getValue(1) < -this.maxSpeed) {
                this.speed.setValue(1, -this.maxSpeed);
            }
        }
    }

    @Override
    public void slowX() {
        if (this.speed.getValue(0) >= this.slowdown) {
            this.speed.setValue(0, this.speed.getValue(0) - this.slowdown);
        } else if (this.speed.getValue(0) <= -this.slowdown) {
            this.speed.setValue(0, this.speed.getValue(0) + this.slowdown);
        } else {
            this.speed.setValue(0, 0.0);
        }
    }

    @Override
    public void slowY() {
        if (this.speed.getValue(1) >= this.slowdown) {
            this.speed.setValue(1, this.speed.getValue(1) - this.slowdown);
        } else if (this.speed.getValue(1) <= -this.slowdown) {
            this.speed.setValue(1, this.speed.getValue(1) + this.slowdown);
        } else {
            this.speed.setValue(1, 0.0);
        }
    }

    // OTHER METHODS
    @Override
    public void setPosition(double x, double y) {
        this.image.setX(x);
        this.image.setY(y);
        if (this.savedPosition == null) {
            this.savedPosition = new Position(x, y);
            this.newPosition = new Position(x, y);
        } else if (this.newPosition == null) {
            this.newPosition = new Position(x, y);
        } else {
            this.newPosition.setValue(0, x);
            this.newPosition.setValue(1, y);
        }
    }
}
