package com.kun0113.labyrinth.tools.handlers;

import com.kun0113.labyrinth.Labyrinth;
import com.kun0113.labyrinth.enums.EMovementInput;
import com.kun0113.labyrinth.sprites.movable.SpriteMovable;
import javafx.scene.Scene;

public class InputHandler {
    // LOCAL VARIABLES
    private boolean[] movementInput;

    // CONSTRUCTORS
    public InputHandler(Scene scene) {
        final int directionsCount = Labyrinth.GAME_DIMENSIONS * 2;
        this.movementInput = new boolean[directionsCount];
        for (int i = 0; i < directionsCount; i++) {
            this.movementInput[i] = false;
        }

        this.initialise(scene);
    }

    // SPRITE MOVEMENT
    public void moveSprite(SpriteMovable sprite) {
        if (movementInput[EMovementInput.LEFT.getId()] != movementInput[EMovementInput.RIGHT.getId()]) {
            if (movementInput[EMovementInput.LEFT.getId()]) {
                sprite.accelerateX(false);
            } else {
                sprite.accelerateX(true);
            }
        } else {
            sprite.slowX();
        }
        if (movementInput[EMovementInput.UP.getId()] != movementInput[EMovementInput.DOWN.getId()]) {
            if (movementInput[EMovementInput.UP.getId()]) {
                sprite.accelerateY(false);
            } else {
                sprite.accelerateY(true);
            }
        } else {
            sprite.slowY();
        }
    }

    // SCENE INTEGRATION
    public void initialise(Scene scene) {
        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case LEFT:
                case A:
                    movementInput[EMovementInput.LEFT.getId()] = true;
                    break;
                case RIGHT:
                case D:
                    movementInput[EMovementInput.RIGHT.getId()] = true;
                    break;
                case UP:
                case W:
                    movementInput[EMovementInput.UP.getId()] = true;
                    break;
                case DOWN:
                case S:
                    movementInput[EMovementInput.DOWN.getId()] = true;
                    break;
            }
        });
        scene.setOnKeyReleased(e -> {
            switch (e.getCode()) {
                case LEFT:
                case A:
                    movementInput[EMovementInput.LEFT.getId()] = false;
                    break;
                case RIGHT:
                case D:
                    movementInput[EMovementInput.RIGHT.getId()] = false;
                    break;
                case UP:
                case W:
                    movementInput[EMovementInput.UP.getId()] = false;
                    break;
                case DOWN:
                case S:
                    movementInput[EMovementInput.DOWN.getId()] = false;
                    break;
            }
        });
    }
}
