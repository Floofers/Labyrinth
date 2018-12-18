package com.kun0113.labyrinth.info_holder;

import com.kun0113.labyrinth.enums.EGameState;
import com.kun0113.labyrinth.interfaces.IObservable;
import com.kun0113.labyrinth.interfaces.IObserver;

import java.util.ArrayList;

public class GameState implements IObservable {
    // LOCAL VARIABLES
    private String state;
    private ArrayList<IObserver> observers;

    // CONSTRUCTORS
    public GameState() {
        this.state = "";
        this.observers = new ArrayList<>();
    }

    // SETTERS
    public void setState(EGameState state) {
        this.state = state.getName();
        this.notifyAllObservers();
    }

    // GETTERS
    public String getState() {
        return (this.state);
    }

    // OBSERVER FUNCTIONALITY
    @Override
    public void addObserver(IObserver observer) {
        this.observers.add(observer);
    }

    @Override
    public void removeObserver(IObserver observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyAllObservers() {
        for (IObserver observer : observers) {
            observer.update();
        }
    }
}
