package com.github.javadojo.Orientations;

import com.github.javadojo.MarsRover;

public class EastState implements FacingState {

    private final MarsRover marsRover;

    public EastState(MarsRover marsRover) {
        this.marsRover = marsRover;
    }

    @Override
    public void moveForward() {
        marsRover.moveEast();
    }

    @Override
    public void turnLeft() {
        marsRover.setState(new NorthState(marsRover));
    }

    @Override
    public void turnRight() {
        marsRover.setState(new SouthState(marsRover));
    }

}
