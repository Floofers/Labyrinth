package com.kun0113.labyrinth.interfaces;

public interface IInertia {
    void accelerateX(boolean right);

    void accelerateY(boolean down);

    void slowX();

    void slowY();
}