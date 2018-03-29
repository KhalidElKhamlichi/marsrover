package com.github.javadojo;

public class SouthState implements FacingState {

    private final MarsRover marsRover;

    public SouthState(MarsRover marsRover) {
        this.marsRover = marsRover;
    }

    @Override
    public void moveForward() {
        marsRover.moveSouth();
    }

    @Override
    public void turnLeft() {
        marsRover.setState(new EastState(marsRover));
    }

    @Override
    public void turnRight() {
        marsRover.setState(new WestState(marsRover));
    }

}
