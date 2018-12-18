package com.kun0113.labyrinth.info_holder;

import java.io.Serializable;

public class Position implements Serializable {
    // LOCAL VARIABLES
    protected double[] values;

    // CONSTRUCTORS
    public Position() {
        this(0, 0);
    }

    public Position(double x, double y) {
        values = new double[]{x, y};
    }

    // SETTERS
    public void setValue(int index, double value) {
        this.values[index] = value;
    }

    // GETTERS
    public double getValue(int index) {
        return (this.values[index]);
    }

    public double[] getValues() {
        return (this.values);
    }

    // OTHER METHODS
    public boolean equals(Position position) {
        if (values.length == position.getValues().length) {
            for (int i = 0; i < values.length; i++) {
                if (values[i] != position.getValue(i)) {
                    return (false);
                }
            }
        } else {
            return (false);
        }
        return (true);
    }
}
