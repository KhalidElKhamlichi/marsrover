package com.github.javadojo.Orientations;

import com.github.javadojo.MarsRover;

public class WestState implements FacingState {

    private final MarsRover marsRover;

    public WestState(MarsRover marsRover) {
        this.marsRover = marsRover;
    }

    @Override
    public void moveForward() {
        marsRover.moveWest();
    }

    @Override
    public void turnLeft() {
        marsRover.setState(new SouthState(marsRover));
    }

    @Override
    public void turnRight() {
        marsRover.setState(new NorthState(marsRover));
    }

}
