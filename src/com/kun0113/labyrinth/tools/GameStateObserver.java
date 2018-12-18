package com.kun0113.labyrinth.tools;

import com.kun0113.labyrinth.Labyrinth;
import com.kun0113.labyrinth.enums.EGameState;
import com.kun0113.labyrinth.info_holder.GameState;
import com.kun0113.labyrinth.interfaces.IObservable;
import com.kun0113.labyrinth.interfaces.IObserver;

public class GameStateObserver implements IObserver {
    // LOCAL VARIABLES
    private Labyrinth labyrinth;
    private GameState gameState;

    // CONSTRUCTORS
    public GameStateObserver(Labyrinth labyrinth) {
        this.labyrinth = labyrinth;
    }

    // OBSERVER FUNCTIONALITY
    @Override
    public void setObservable(IObservable observable) {
        this.gameState = (GameState) observable;
    }

    @Override
    public void update() {
        switch (gameState.getState()) {
            case "Level":
                labyrinth.loadLevel();
                break;
            case "Restart Level":
                labyrinth.restartLevel();
                gameState.setState(EGameState.LEVEL);
                break;
            case "Exit":
                labyrinth.quitGame();
                break;
        }
    }
}
