package com.kun0113.labyrinth.sprites.movable;

import com.kun0113.labyrinth.info_holder.Position;
import com.kun0113.labyrinth.info_holder.Vector2;

import java.util.ArrayList;

public class Enemy extends SpriteMovable {
    // LOCAL VARIABLES
    private Position currentDestination;
    private ArrayList<Position> destinations;

    // CONSTRUCTORS
    public Enemy() {
        super();
        this.maxSpeed /= 2;
        this.destinations = new ArrayList<>();
    }

    // MOVEMENT METHODS
    @Override
    protected void move() {
        this.checkDestination();
        double x = newPosition.getValue(0) + speed.getValue(0);
        double y = newPosition.getValue(1) + speed.getValue(1);

        this.newPosition.setValue(0, x);
        this.newPosition.setValue(1, y);

        double xDif = x - currentDestination.getValue(0);
        double yDif = y - currentDestination.getValue(1);
        if (Math.abs(xDif) <= this.maxSpeed && Math.abs(yDif) <= this.maxSpeed) {
            this.newPosition.setValue(0, this.currentDestination.getValue(0));
            this.newPosition.setValue(1, this.currentDestination.getValue(1));
        }
    }

    @Override
    public void update() {
        move();
    }

    // SETTERS
    public void setPosition(double x, double y) {
        this.image.setX(x);
        this.image.setY(y);
        if (this.savedPosition == null) {
            this.savedPosition = new Position(x, y);
            this.addDestination(savedPosition);
        }
        this.newPosition = new Position(x, y);
    }

    // PATROL METHODS
    public void addDestination(Position destination) {
        this.destinations.add(destination);
    }

    private void checkDestination() {
        if (currentDestination == null) {
            currentDestination = destinations.get(0);
        }
        if (this.newPosition.equals(currentDestination)) {
            for (int i = 0; i < destinations.size(); i++) {
                if (currentDestination.equals(destinations.get(i))) {
                    if (i + 1 < destinations.size()) {
                        this.setDestination(destinations.get(i + 1));
                        break;
                    } else {
                        this.setDestination(destinations.get(0));
                        break;
                    }
                }
            }
        }
    }

    private void setDestination(Position destination) {
        this.currentDestination = destination;
        Vector2 movementDirection = new Vector2(this.newPosition, destination);
        this.speed = movementDirection.getNormalised(maxSpeed);
    }
}
