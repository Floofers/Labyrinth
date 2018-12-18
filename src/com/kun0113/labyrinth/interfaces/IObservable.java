package com.kun0113.labyrinth.interfaces;

public interface IObservable {
    void addObserver(IObserver observer);

    void removeObserver(IObserver observer);

    void notifyAllObservers();
}
