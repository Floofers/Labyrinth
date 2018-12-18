package com.kun0113.labyrinth.info_holder;

import com.kun0113.labyrinth.sprites.Sprite;
import com.kun0113.labyrinth.sprites.movable.Enemy;
import com.kun0113.labyrinth.sprites.movable.Player;
import com.kun0113.labyrinth.tools.grid.Grid;

import java.io.Serializable;
import java.util.ArrayList;

public class Level implements Serializable {
    // LOCAL VARIABLES
    private ArrayList<Sprite> sprites;
    private ArrayList<Enemy> enemies;
    private Player player;
    private Grid grid;

    // CONSTRUCTORS
    public Level() {
        this.sprites = new ArrayList<>();
        this.enemies = new ArrayList<>();
        this.player = null;
        this.grid = new Grid();
    }

    // ADDING SPRITES
    public void addSprite(Sprite sprite) {
        this.sprites.add(sprite);
        this.grid.setCollidable(sprite);
    }

    public void addEnemy(Enemy enemy) {
        this.enemies.add(enemy);
        this.grid.setCollidable(enemy);
    }

    // REMOVING SPRITES
    public void removeSprite(Sprite sprite) {
        this.sprites.remove(sprite);
    }

    public void removeEnemy(Enemy enemy) {
        this.enemies.remove(enemy);
    }

    // SETTERS
    public void setPlayer(Player player) {
        this.player = player;
        if (this.player != null) {
            this.player.setGrid(grid);
        }
    }

    // GETTERS
    public ArrayList<Sprite> getSprites() {
        return (this.sprites);
    }

    public ArrayList<Enemy> getEnemies() {
        return (this.enemies);
    }

    public Player getPlayer() {
        return (this.player);
    }

    public Grid getGrid() {
        return (this.grid);
    }
}
