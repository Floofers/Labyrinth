package com.kun0113.labyrinth.sprites.movable;

import com.kun0113.labyrinth.enums.ELockColour;
import com.kun0113.labyrinth.info_holder.Inventory;
import com.kun0113.labyrinth.sprites.Sprite;
import com.kun0113.labyrinth.tools.grid.Grid;

public class Player extends SpriteMovable {
    // LOCAL VARIABLES
    private Inventory inventory;
    private Grid grid;
    private boolean victorious;

    // CONSTRUCTORS
    public Player() {
        super();
        this.inventory = new Inventory();
        this.victorious = false;
    }

    // MOVEMENT METHODS
    @Override
    protected void move() {
        this.moveHorizontally();
        this.moveVertically();
    }

    @Override
    public void update() {
        this.move();
    }

    private void moveHorizontally() {
        double xPosition = this.newPosition.getValue(0);
        double[] yPositions = new double[2];
        yPositions[0] = this.newPosition.getValue(1);
        yPositions[1] = this.newPosition.getValue(1) + (this.image.getFitHeight());

        if (this.speed.getValue(0) > 0) {
            xPosition += this.image.getFitWidth() + this.speed.getValue(0);
        } else if (this.speed.getValue(0) < 0) {
            xPosition += this.speed.getValue(0);
        } else {
            return;
        }

        boolean collisionMin = grid.getCollision(xPosition, yPositions[0]);
        boolean collisionMax = grid.getCollision(xPosition, yPositions[1]);

        if (collisionMin || collisionMax) {
            Sprite collider;
            if (collisionMin) {
                collider = grid.getCollisionType(xPosition, yPositions[0]);
            } else {
                collider = grid.getCollisionType(xPosition, yPositions[1]);
            }
            this.collisionType(collider, 0);
        }
        this.newPosition.setValue(0, this.newPosition.getValue(0) + this.speed.getValue(0));
    }

    private void moveVertically() {
        double yPosition = this.newPosition.getValue(1);
        double[] xPositions = new double[2];
        xPositions[0] = this.newPosition.getValue(0);
        xPositions[1] = this.newPosition.getValue(0) + (this.image.getFitWidth());

        if (this.speed.getValue(1) > 0) {
            yPosition += this.image.getFitHeight() + this.speed.getValue(1);
        } else if (this.speed.getValue(1) < 0) {
            yPosition += this.speed.getValue(1);
        } else {
            return;
        }

        boolean collisionMin = grid.getCollision(xPositions[0], yPosition);
        boolean collisionMax = grid.getCollision(xPositions[1], yPosition);

        if (collisionMin || collisionMax) {
            Sprite collider;
            if (collisionMin) {
                collider = grid.getCollisionType(xPositions[0], yPosition);
            } else {
                collider = grid.getCollisionType(xPositions[1], yPosition);
            }
            this.collisionType(collider, 1);
        }
        this.newPosition.setValue(1, this.newPosition.getValue(1) + this.speed.getValue(1));
    }

    // COLLISION METHODS
    private void collisionType(Sprite collider, int index) {
        if (collider != null) {
            switch (collider.getType()) {
                case "Sprite":
                case "Wall":
                    stop(index);
                    break;
                case "Coin":
                    collider.setAlive(false);
                    this.inventory.incrementCoins();
                    break;
                case "Goal":
                    this.victorious = true;
                    break;
                case "Enemy":
                    this.setAlive(false);
                    break;
                case "KeyRed":
                    collider.setAlive(false);
                    this.inventory.setRedKey(true);
                    break;
                case "KeyGreen":
                    collider.setAlive(false);
                    this.inventory.setGreenKey(true);
                    break;
                case "KeyBlue":
                    collider.setAlive(false);
                    this.inventory.setBlueKey(true);
                    break;
            }
            if (collider.getType().toLowerCase().contains("lock")) {
                if (this.lockCollision(collider)) {
                    stop(index);
                } else {
                    collider.setAlive(false);
                }
            }
        }
    }

    private boolean lockCollision(Sprite collider) {
        if (collider.getType().contains(ELockColour.RED.getName())) {
            return (!this.inventory.getRedKey());
        } else if (collider.getType().contains(ELockColour.GREEN.getName())) {
            return (!this.inventory.getGreenKey());
        } else if (collider.getType().contains(ELockColour.BLUE.getName())) {
            return (!this.inventory.getBlueKey());
        }
        return true;
    }

    private void stop(int index) {
        double position = this.newPosition.getValue(index);
        double speed = this.speed.getValue(index);

        if (speed > 0) {
            position -= (position % Grid.SCALE);
            position += Grid.SCALE - 0.01;
        } else if (speed < 0) {
            position -= (position % Grid.SCALE);
        }
        speed = 0;

        this.newPosition.setValue(index, position);
        this.speed.setValue(index, speed);
    }

    // SETTERS
    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public void setVictorious(boolean victorious) {
        this.victorious = victorious;
    }

    // GETTERS
    public boolean getVictorious() {
        return (this.victorious);
    }
}
