package com.kun0113.labyrinth;

import com.kun0113.labyrinth.enums.EGameState;
import com.kun0113.labyrinth.enums.ESpriteType;
import com.kun0113.labyrinth.info_holder.GameState;
import com.kun0113.labyrinth.info_holder.Level;
import com.kun0113.labyrinth.info_holder.Position;
import com.kun0113.labyrinth.sprites.movable.Player;
import com.kun0113.labyrinth.tools.GameStateObserver;
import com.kun0113.labyrinth.tools.LevelBuilder;
import com.kun0113.labyrinth.tools.handlers.InputHandler;
import com.kun0113.labyrinth.tools.handlers.LevelHandler;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.kun0113.labyrinth.resources.LevelLoader;
import com.kun0113.labyrinth.resources.LevelSaver;

public class Labyrinth extends Application {
    // STATIC CONSTANTS
    public static final int GAME_DIMENSIONS = 2;
    public static final double GAME_WIDTH = 800.0;
    public static final double GAME_HEIGHT = 600.0;
    private static final String VERSION = "0.3";

    // LOCAL VARIABLES
    private Stage stage;
    private Scene scene;
    private LevelHandler levelHandler;
    private InputHandler inputHandler;
    private GameState gameState;
    private int levelNumber = 0;

    // OTHER METHODS
    public static void main(String[] args) {
        launch(args);
    }

    // MAIN METHODS
    @Override
    public void start(Stage primaryStage) {
        // Initialise essentials
        this.stage = primaryStage;
        this.levelHandler = new LevelHandler();
        this.scene = new Scene(levelHandler.getRoot(), Labyrinth.GAME_WIDTH, Labyrinth.GAME_HEIGHT);
        this.inputHandler = new InputHandler(scene);

        // Initialise GameState and its observers
        gameState = new GameState();
        GameStateObserver gameStateObserver = new GameStateObserver(this);
        gameStateObserver.setObservable(gameState);
        gameState.addObserver(gameStateObserver);

        // Set the game state
        gameState.setState(EGameState.LEVEL);

        // Create a level, if needed

        // Prepare and create the game loop
        this.inputHandler.initialise(scene);
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                // Update
                Player player = levelHandler.getPlayer();
                if (player != null) {
                    inputHandler.moveSprite(player);
                }
                levelHandler.update();
                if (player != null) {
                    if (!player.getAlive()) {
                        gameState.setState(EGameState.RESTART_LEVEL);
                    } else if (player.getVictorious()) {
                        player.setVictorious(false);
                        gameState.setState(EGameState.LEVEL);
                    }
                }

                // Render
                levelHandler.render();
            }
        };

        // finish initialisation
        stage.setOnCloseRequest(e -> {
            this.quitGame();
        });
        stage.setTitle("Labyrinth, " + Labyrinth.VERSION);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.sizeToScene();

        // start the game
        stage.show();
        animationTimer.start();
    }

    // STATES
    public void loadLevel() {
        this.levelNumber += 1;
        Level level = this.loadLevel("LEVEL" + this.levelNumber);
        if (level == null) {
            this.quitGame();
            return;
        }
        levelHandler.loadLevel(level);
    }

    public void restartLevel() {
        this.levelHandler.clear();
        this.levelNumber -= 1;
    }

    public void quitGame() {
        this.stage.close();
    }

    // LEVEL CREATING, SAVING and LOADING
    private Level createLevel() {
        LevelBuilder levelBuilder = new LevelBuilder();
        levelBuilder.fillMap(ESpriteType.WALL.getName());

        levelBuilder.removeSprites(4 * 50, 2 * 50, 8, 2);
        levelBuilder.removeSprites(4 * 50, 4 * 50, 3, 4);
        levelBuilder.removeSprites(4 * 50, 8 * 50, 6, 2);

        levelBuilder.setPlayer((6 * 50) - 25, (3 * 50) - 25);
        levelBuilder.addSprites(ESpriteType.GOAL.getName(), 10 * 50, 2 * 50, 2, 2);
        levelBuilder.addSprite("blue key", (9 * 50) - 25, (9 * 50) - 25);
        levelBuilder.addSprite("blue lock", 8 * 50, 3 * 50);
        levelBuilder.addSprite("blue lock wall", 8 * 50, 2 * 50);

        return (levelBuilder.build());
    }

    private void saveLevel(Level level, String levelName) {
        LevelSaver levelSaver = new LevelSaver();
        levelSaver.saveLevel(level, levelName);
    }

    private Level loadLevel(String levelName) {
        LevelLoader levelLoader = new LevelLoader();
        return (levelLoader.loadLevel(levelName));
    }
}