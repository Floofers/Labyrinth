package com.kun0113.labyrinth.resources;

import com.kun0113.labyrinth.info_holder.Level;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class LevelSaver {
    public void saveLevel(Level level, String levelName) {
        ObjectOutputStream output = null;
        try {
            output = new ObjectOutputStream(new FileOutputStream("src/com/kun0113/labyrinth/resources/levels/" + levelName));
            //output = new ObjectOutputStream(new FileOutputStream(String.valueOf(this.getClass().getResource("levels/" + levelName))));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (output != null) {
            try {
                output.writeObject(level);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}