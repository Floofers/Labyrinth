package com.kun0113.labyrinth.info_holder;

public class Vector2 extends Position {
    // CONSTRUCTORS
    public Vector2() {
        super();
    }

    public Vector2(double x, double y) {
        super(x, y);
    }

    public Vector2(Position beginning, Position end) {
        super(end.getValue(0) - beginning.getValue(0),
                end.getValue(1) - beginning.getValue(1));
    }

    // OTHER METHODS
    public void multiply(double multiplier) {
        for (int i = 0; i < this.values.length; i++) {
            values[i] *= multiplier;
        }
    }

    public double getMagnitude() {
        return (Math.sqrt((this.values[0] * this.values[0]) + (this.values[1] * this.values[1])));
    }

    public Vector2 getNormalised() {
        double magnitude = this.getMagnitude();
        double x = this.values[0] / magnitude;
        double y = this.values[1] / magnitude;
        return (new Vector2(x, y));
    }

    public Vector2 getNormalised(double speed) {
        Vector2 result = this.getNormalised();
        result.multiply(speed);
        return (result);
    }
}
