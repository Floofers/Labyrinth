package com.kun0113.labyrinth.info_holder;

import java.io.Serializable;

public class Inventory implements Serializable {
    // LOCAL VARIABLES
    private int coins;
    private boolean redKey, greenKey, blueKey;

    // CONSTRUCTORS
    public Inventory() {
        this.coins = 0;
        this.redKey = false;
        this.greenKey = false;
        this.blueKey = false;
    }

    // SETTERS
    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void setRedKey(boolean redKey) {
        this.redKey = redKey;
    }

    public void setGreenKey(boolean greenKey) {
        this.greenKey = greenKey;
    }

    public void setBlueKey(boolean blueKey) {
        this.blueKey = blueKey;
    }

    // GETTERS
    public int getCoins() {
        return coins;
    }

    public boolean getRedKey() {
        return redKey;
    }

    public boolean getGreenKey() {
        return greenKey;
    }

    public boolean getBlueKey() {
        return blueKey;
    }

    // OTHER METHODS
    public void incrementCoins() {
        this.coins++;
    }
}
