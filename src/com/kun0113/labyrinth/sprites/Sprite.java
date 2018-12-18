package com.kun0113.labyrinth.sprites;

import com.kun0113.labyrinth.info_holder.Position;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import com.kun0113.labyrinth.resources.ImageLoader;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class Sprite implements Serializable {
    // LOCAL VARIABLES
    protected transient ImageView image;
    protected String imageName;
    protected String type;
    protected boolean alive;
    protected Position savedPosition;

    // CONSTRUCTORS
    public Sprite() {
        this.image = null;
        this.setImage("_missing.png");
        this.type = "Sprite";
        this.alive = true;
        this.savedPosition = null;
    }

    // SETTERS
    public void setImage() {
        this.setImage(this.imageName);
    }

    public void setPosition(double x, double y) {
        this.image.setX(x);
        this.image.setY(y);
        if (this.savedPosition == null) {
            this.savedPosition = new Position(x, y);
        }
    }

    public void setImage(String imageName) {
        ImageLoader rL = new ImageLoader();

        this.imageName = imageName;
        Image temp = rL.getImage(imageName);
        if (temp != null) {
            image = new ImageView(temp);
            this.image.setFitWidth(temp.getWidth());
            this.image.setFitHeight(temp.getHeight());
        }
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    // GETTERS
    public ImageView getImage() {
        return (this.image);
    }

    public boolean getAlive() {
        return (this.alive);
    }

    public String getType() {
        return (this.type);
    }

    // SERIALISATION
    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        this.setImage();
        this.loadOriginalPosition();
    }

    private void loadOriginalPosition() {
        double x = this.savedPosition.getValue(0);
        double y = this.savedPosition.getValue(1);
        this.setPosition(x, y);
    }
}
