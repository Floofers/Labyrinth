package com.kun0113.labyrinth.tools;

import com.kun0113.labyrinth.Labyrinth;
import com.kun0113.labyrinth.enums.ESpriteType;
import com.kun0113.labyrinth.info_holder.Level;
import com.kun0113.labyrinth.info_holder.Position;
import com.kun0113.labyrinth.interfaces.ILevelBuilder;
import com.kun0113.labyrinth.sprites.Sprite;
import com.kun0113.labyrinth.sprites.movable.Enemy;
import com.kun0113.labyrinth.sprites.movable.Player;

import java.util.ArrayList;

public class LevelBuilder implements ILevelBuilder {
    // LOCAL VARIABLES
    private Level levelTemp;

    // CONSTRUCTORS
    public LevelBuilder() {
        levelTemp = new Level();
    }

    // LEVEL CREATION
    @Override
    public Level build() {
        Level level = new Level();
        for (Sprite sprite : levelTemp.getSprites()) {
            level.addSprite(sprite);
        }
        for (Enemy enemy : levelTemp.getEnemies()) {
            level.addEnemy(enemy);
        }
        if (levelTemp.getPlayer() != null) {
            level.setPlayer(levelTemp.getPlayer());
        }

        return (level);
    }

    // LEVEL PREPARATION
    @Override
    public ILevelBuilder createBorder() {
        int xCount = (int) (Labyrinth.GAME_WIDTH / 50);
        int yCount = (int) (Labyrinth.GAME_HEIGHT / 50);

        for (int x = 0; x < xCount; x++) {
            this.addSprite(ESpriteType.WALL.getName(), x * 50, 0);
            this.addSprite(ESpriteType.WALL.getName(), x * 50, Labyrinth.GAME_HEIGHT - 50);
        }
        for (int y = 0; y < yCount; y++) {
            this.addSprite(ESpriteType.WALL.getName(), 0, y * 50);
            this.addSprite(ESpriteType.WALL.getName(), Labyrinth.GAME_WIDTH - 50, y * 50);
        }
        return (this);
    }

    @Override
    public ILevelBuilder addSprites(String command, double x, double y, int countX, int countY) {
        for (int a = 0; a < countX; a++) {
            for (int b = 0; b < countY; b++) {
                this.addSprite(command,
                        (a * 50) + x, (b * 50) + y);
            }
        }

        return (this);
    }

    @Override
    public ILevelBuilder fillMap(String command) {
        int countX = (int) (Labyrinth.GAME_WIDTH) / 50;
        int countY = (int) (Labyrinth.GAME_HEIGHT) / 50;
        return (this.addSprites(command, 0, 0, countX, countY));
    }

    @Override
    public ILevelBuilder addSprite(String command, double x, double y) {
        SpriteFactory spriteFactory = new SpriteFactory();
        Sprite sprite = spriteFactory.createSprite(command);
        sprite.setPosition(x, y);

        this.levelTemp.addSprite(sprite);
        return (this);
    }

    @Override
    public ILevelBuilder addEnemy(double x, double y) {
        return (this.addEnemy(x, y, (Position) null));
    }

    public ILevelBuilder addEnemy(double x, double y, Position... positions) {
        SpriteFactory spriteFactory = new SpriteFactory();
        Enemy enemy = (Enemy) spriteFactory.createSprite(ESpriteType.ENEMY.getName());
        enemy.setPosition(x, y);
        if (positions != null) {
            for (Position position : positions) {
                enemy.addDestination(position);
            }
        }
        this.levelTemp.addEnemy(enemy);
        return (this);
    }

    @Override
    public ILevelBuilder removeSprite(double x, double y) {
        Sprite temp = null;
        for (Sprite sprite : this.levelTemp.getSprites()) {
            if (sprite.getImage().getX() == x && sprite.getImage().getY() == y) {
                temp = sprite;
                break;
            }
        }
        if (temp != null) {
            this.levelTemp.removeSprite(temp);
        }
        return (this);
    }

    @Override
    public ILevelBuilder removeEnemy(double x, double y) {
        Enemy temp = null;
        for (Enemy enemy : this.levelTemp.getEnemies()) {
            if (enemy.getImage().getX() == x && enemy.getImage().getY() == y) {
                temp = enemy;
                break;
            }
        }
        if (temp != null) {
            this.levelTemp.removeEnemy(temp);
        }
        return (this);
    }

    @Override
    public ILevelBuilder removeSprites(double x, double y) {
        ArrayList<Sprite> temp = new ArrayList<>();
        for (Sprite sprite : this.levelTemp.getSprites()) {
            if (sprite.getImage().getX() == x && sprite.getImage().getY() == y) {
                temp.add(sprite);
            }
        }
        for (Sprite sprite : temp) {
            this.levelTemp.removeSprite(sprite);
        }
        return (this);
    }

    @Override
    public ILevelBuilder removeSprites(double x, double y, int countX, int countY) {
        for (int a = 0; a < countX; a++) {
            for (int b = 0; b < countY; b++) {
                this.removeSprite(x + (a * 50), y + (b * 50));
            }
        }
        return (this);
    }

    @Override
    public ILevelBuilder setPlayer(double x, double y) {
        SpriteFactory spriteFactory = new SpriteFactory();
        Player player = (Player) spriteFactory.createSprite(ESpriteType.PLAYER.getName());
        player.setPosition(x, y);
        levelTemp.setPlayer(player);

        return (this);
    }
}
