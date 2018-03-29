package com.github.javadojo.Orientations;

import com.github.javadojo.MarsRover;

public class NorthState implements FacingState {

    private final MarsRover marsRover;

    public NorthState(MarsRover marsRover) {
        this.marsRover = marsRover;
    }

    @Override
    public void moveForward() {
        marsRover.moveNorth();
    }

    @Override
    public void turnLeft() {
        marsRover.setState(new WestState(marsRover));
    }

    @Override
    public void turnRight() {
        marsRover.setState(new EastState(marsRover));
    }

}
