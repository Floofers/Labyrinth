package com.kun0113.labyrinth.tools.grid;

import com.kun0113.labyrinth.Labyrinth;
import com.kun0113.labyrinth.sprites.Sprite;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class Grid implements Serializable {
    // LOCAL VARIABLES
    public static final int SCALE = 10;
    public static final int TILES_COUNT = (int) ((Labyrinth.GAME_WIDTH / Grid.SCALE) *
            (Labyrinth.GAME_HEIGHT / Grid.SCALE));
    private Tile[] tiles;

    // CONSTRUCTORS
    public Grid() {
        initialise();
    }

    // INITIALISATION
    private void initialise() {
        tiles = new Tile[TILES_COUNT];

        int columnCount = (int) (Labyrinth.GAME_WIDTH / Grid.SCALE);
        int lineCount = (int) (Labyrinth.GAME_HEIGHT / Grid.SCALE);

        for (int x = 0; x < columnCount; x++) {
            for (int y = 0; y < lineCount; y++) {
                int index = this.getIndex(x * Grid.SCALE, y * Grid.SCALE);
                tiles[index] = new Tile(x * Grid.SCALE, y * Grid.SCALE);
            }
        }

        for (Tile tile : tiles) {
            if (tile != null && tile.getY() > Labyrinth.GAME_HEIGHT - Grid.SCALE) {
                System.out.println(tile.getX());
            }
        }
    }

    // COLLISION METHODS
    public void setCollidable(Sprite object) {
        ImageView temp = object.getImage();

        int xCount = (int) (temp.getFitWidth() / Grid.SCALE);
        int yCount = (int) (temp.getFitHeight() / Grid.SCALE);

        for (int i = 0; i < xCount; i++) {
            for (int j = 0; j < yCount; j++) {
                this.tiles[this.getIndex(temp.getX() + i * Grid.SCALE,
                        temp.getY() + j * Grid.SCALE)].setCollidable(object);
            }
        }
    }

    public void removeCollidable(Sprite object) {
        ImageView temp = object.getImage();

        int xCount = (int) (temp.getFitWidth() / Grid.SCALE);
        int yCount = (int) (temp.getFitHeight() / Grid.SCALE);

        for (int i = 0; i < xCount; i++) {
            for (int j = 0; j < yCount; j++) {
                this.tiles[this.getIndex(temp.getX() + i * Grid.SCALE,
                        temp.getY() + j * Grid.SCALE)].setCollidable(null);
            }
        }
    }

    public void clearCollision() {
        for (int i = 0; i < tiles.length; i++) {
            tiles[i].setCollidable(null);
        }
    }

    // GETTERS
    public boolean getCollision(double x, double y) {
        return (this.tiles[this.getIndex(x, y)].getCollidable());
    }

    public Sprite getCollisionType(double x, double y) {
        return (tiles[getIndex(x, y)].getObject());
    }

    private int getIndex(double x, double y) {
        int columnCount = (int) (Labyrinth.GAME_WIDTH / Grid.SCALE);
        int yNorm = (int) (y / Grid.SCALE);
        int xNorm = (int) (x / Grid.SCALE);
        int result = (yNorm * columnCount) + xNorm;
        return (result);
    }

    // SERIALISATION
    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        //initialise();
    }
}