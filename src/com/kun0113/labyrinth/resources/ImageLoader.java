package com.kun0113.labyrinth.resources;

import javafx.scene.image.Image;

public class ImageLoader {
    public Image getImage(String fileName) {
        Image image = null;
        try {
            image = new Image(String.valueOf(this.getClass().getResource("images/" + fileName)));
        } catch (RuntimeException e) {
            e.printStackTrace();
            image = null;
        }

        return image;
    }
}
