package com.kun0113.labyrinth.tools.handlers;

import com.kun0113.labyrinth.info_holder.Level;
import com.kun0113.labyrinth.sprites.Sprite;
import com.kun0113.labyrinth.sprites.movable.Enemy;
import com.kun0113.labyrinth.sprites.movable.Player;
import com.kun0113.labyrinth.tools.SpriteManager;
import javafx.scene.Group;
import com.kun0113.labyrinth.resources.LevelLoader;

import java.util.ArrayList;

public class LevelHandler {
    // LOCAL VARIABLES
    private Group root;
    private Level currentLevel;
    private SpriteManager spriteManager;

    // CONSTRUCTORS
    public LevelHandler() {
        this.root = new Group();
        this.currentLevel = null;
        this.spriteManager = new SpriteManager();
    }

    // LEVEL SPRITE MANAGEMENT
    public void update() {
        this.resetCollision();
        this.spriteManager.update();
        this.removeDead();
    }

    public void render() {
        this.spriteManager.render();
    }

    private void removeDead() {
        if (getPlayer() != null && !getPlayer().getAlive()) {
            this.root.getChildren().remove(getPlayer().getImage());
            this.currentLevel.setPlayer(null);
        }
        ArrayList<Sprite> removeThis = new ArrayList<>();
        for (Sprite sprite : this.currentLevel.getSprites()) {
            if (!sprite.getAlive()) {
                this.root.getChildren().remove(sprite.getImage());
                removeThis.add(sprite);
            }
        }
        for (Enemy enemy : this.currentLevel.getEnemies()) {
            if (!enemy.getAlive()) {
                this.root.getChildren().remove(enemy.getImage());
                removeThis.add((Sprite) enemy);
            }
        }
        for (Sprite sprite : removeThis) {
            this.currentLevel.getGrid().removeCollidable(sprite);
            this.currentLevel.removeSprite(sprite);
        }
    }

    private void resetCollision() {
        this.currentLevel.getGrid().clearCollision();
        for (Enemy enemy : this.currentLevel.getEnemies()) {
            this.currentLevel.getGrid().setCollidable(enemy);
        }
        for (Sprite sprite : this.currentLevel.getSprites()) {
            this.currentLevel.getGrid().setCollidable(sprite);
        }
    }

    // LEVEL LOADING
    public void loadLevel(Level level) {
        this.currentLevel = level;

        spriteManager.loadLevel(level);
        this.initLevel();
    }

    public boolean loadLevel(String levelName) {
        LevelLoader levelLoader = new LevelLoader();
        this.currentLevel = levelLoader.loadLevel(levelName);
        if (this.currentLevel == null) {
            return false;
        } else {
            this.spriteManager.loadLevel(currentLevel);
            this.initLevel();
            return (true);
        }
    }

    // LEVEL INITIALISATION
    private void initLevel() {
        this.root.getChildren().clear();

        for (Sprite sprite : currentLevel.getSprites()) {
            root.getChildren().add(sprite.getImage());
        }
        for (Enemy enemy : currentLevel.getEnemies()) {
            root.getChildren().add(enemy.getImage());
        }
        Player player = currentLevel.getPlayer();
        if (player != null) {
            this.root.getChildren().add(currentLevel.getPlayer().getImage());
        }
    }

    public void clear() {
        this.spriteManager.clear();
        this.currentLevel = null;
        this.root.getChildren().clear();
    }

    // GETTERS
    public Group getRoot() {
        return (this.root);
    }

    public Level getCurrentLevel() {
        return (this.currentLevel);
    }

    public Player getPlayer() {
        return (this.spriteManager.getPlayer());
    }
}
