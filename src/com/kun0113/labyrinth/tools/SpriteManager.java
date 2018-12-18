package com.kun0113.labyrinth.tools;

import com.kun0113.labyrinth.info_holder.Level;
import com.kun0113.labyrinth.interfaces.IRenderable;
import com.kun0113.labyrinth.interfaces.IUpdatable;
import com.kun0113.labyrinth.sprites.Sprite;
import com.kun0113.labyrinth.sprites.movable.Enemy;
import com.kun0113.labyrinth.sprites.movable.Player;

import java.util.ArrayList;

public class SpriteManager {
    // LOCAL VARIABLES
    private ArrayList<Sprite> sprites;
    private ArrayList<Enemy> enemies;
    private Player player;

    // CONSTRUCTORS
    public SpriteManager() {
        this.sprites = new ArrayList<>();
        this.enemies = new ArrayList<>();
        player = null;
    }

    // UPDATING AND RENDERING
    public void update() {
        if (player != null) {
            player.update();
        }
        for (Enemy enemy : enemies) {
            enemy.update();
        }
        for (Sprite sprite : sprites) {
            if (sprite instanceof IUpdatable) {
                ((IUpdatable) sprite).update();
            }
        }
    }

    public void render() {
        if (player != null) {
            player.render();
        }
        for (Enemy enemy : enemies) {
            enemy.render();
        }
        for (Sprite sprite : sprites) {
            if (sprite instanceof IRenderable) {
                ((IRenderable) sprite).render();
            }
        }
    }

    // SPRITE INITIALISING
    public void loadLevel(Level level) {
        this.clear();
        this.sprites = level.getSprites();
        this.enemies = level.getEnemies();
        this.player = level.getPlayer();
    }

    // SPRITE REMOVAL
    public void removeSprite(Sprite sprite) {
        this.sprites.remove(sprite);
    }

    public void removeEnemy(Enemy enemy) {
        this.enemies.remove(enemy);
    }

    public void clear() {
        this.sprites.clear();
        this.enemies.clear();
        this.player = null;
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
}
