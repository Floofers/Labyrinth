package com.kun0113.labyrinth.resources;


import com.kun0113.labyrinth.info_holder.Level;

import java.io.IOException;
import java.io.ObjectInputStream;

public class LevelLoader {
    public Level loadLevel(String levelName) {
        ObjectInputStream input = null;
        try {
            //input = new ObjectInputStream(new FileInputStream("src/com.kun0113.labyrinth.resources/levels/"+levelName));
            input = new ObjectInputStream((this.getClass().getResourceAsStream("levels/" + levelName)));

        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }

        Level result = null;
        if (input != null) {
            try {
                result = (Level) input.readObject();
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        }
        return (result);
    }
}