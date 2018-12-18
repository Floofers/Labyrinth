package com.kun0113.labyrinth.tools;

import com.kun0113.labyrinth.enums.ESpriteType;
import com.kun0113.labyrinth.info_holder.Lock;
import com.kun0113.labyrinth.sprites.Sprite;
import com.kun0113.labyrinth.sprites.movable.Enemy;
import com.kun0113.labyrinth.sprites.movable.Player;

public class SpriteFactory {
    public Sprite createSprite(String command) {
        command = command.toLowerCase();
        Sprite result = null;

        switch (command) {
            case "sprite":
                result = new Sprite();
                result.setImage();
                result.setType("Sprite");
                break;
            case "wall":
                result = new Sprite();
                result.setImage(ESpriteType.WALL.getImageName());
                result.setType("Wall");
                break;
            case "coin":
                result = new Sprite();
                result.setImage(ESpriteType.COIN.getImageName());
                result.setType("Coin");
                break;
            case "goal":
                result = new Sprite();
                result.setImage(ESpriteType.GOAL.getImageName());
                result.setType("Goal");
                break;
            case "player":
                result = new Player();
                result.setImage(ESpriteType.PLAYER.getImageName());
                result.setType("Player");
                break;
            case "enemy":
                result = new Enemy();
                result.setImage(ESpriteType.ENEMY.getImageName());
                result.setType("Enemy");
                break;
        }

        if (command.contains("lock")) {
            LockBuilder lockBuilder = new LockBuilder();
            lockBuilder.setWall(command);
            lockBuilder.setColour(command);
            lockBuilder.setFileType(command);
            Lock lock = lockBuilder.build();
            result = new Sprite();
            result.setImage(lockBuilder.getName());
            result.setType("Lock" + lock.getColour());
        } else if (command.contains("key")) {
            result = new Sprite();
            if (command.contains("red")) {
                result.setImage(ESpriteType.KEY_RED.getImageName());
                result.setType("KeyRed");
            } else if (command.contains("green")) {
                result.setImage(ESpriteType.KEY_GREEN.getImageName());
                result.setType("KeyGreen");
            } else {
                result.setImage(ESpriteType.KEY_BLUE.getImageName());
                result.setType("KeyBlue");
            }
        }
        return (result);
    }
}
