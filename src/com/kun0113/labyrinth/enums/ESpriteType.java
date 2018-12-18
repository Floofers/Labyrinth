package com.kun0113.labyrinth.enums;

public enum ESpriteType {
    WALL("Wall", "Brick_1x1.png"),
    COIN("Coin", "Coin.gif"),
    GOAL("Goal", "Goal.png"),
    PLAYER("Player", "Player.gif"),
    ENEMY("Enemy", "Enemy.gif"),
    KEY_RED("Key", "Key_Red.png"),
    KEY_GREEN("Key", "Key_Green.png"),
    KEY_BLUE("Key", "Key_Blue.png");

    // LOCAL VARIABLES
    private final String name;
    private final String imageName;

    // CONSTRUCTORS
    ESpriteType(String name, String imageName) {
        this.name = name;
        this.imageName = imageName;
    }

    // GETTERS
    public String getName() {
        return (this.name);
    }

    public String getImageName() {
        return (this.imageName);
    }
}