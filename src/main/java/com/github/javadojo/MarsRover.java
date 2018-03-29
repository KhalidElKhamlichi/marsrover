package com.github.javadojo;

import java.util.ArrayList;
import java.util.List;

/**
 * The Mars rover is programmed to drive around Mars.
 * Its programming is very simple. The commands are the following:
 * <dl>
 *     <dt>s</dt>
 *     <dd>drive in a straight line</dd>
 *     <dt>r</dt>
 *     <dd>turn right</dd>
 *     <dt>l</dt>
 *     <dd>turn left</dd>
 * </dl>
 *
 * Note that the Mars rover always land at the <code>X</code> and starts by facing east.
 * 
 * The Mars rover can send a 2D string representation of its path back to Mission Control. The following character are
 * used with the following meanings:
 * <dl>
 *     <dt>X</dt>
 *     <dd>where the Mars rover landed</dd>
 *     <dt>*</dt>
 *     <dd>current position of the Mars rover</dd>
 *     <dt>-</dt>
 *     <dd>path in the west-east direction</dd>
 *     <dt>|</dt>
 *     <dd>path in the north-south direction</dd>
 *     <dt>+</dt>
 *     <dd>a place where the Mars rover turned or a crossroad</dd>
 *     <dt>S</dt>
 *     <dd>a place where a sample was taken</dd>
 * </dl>
 */
public class MarsRover {

    static final String LINE_SEPARATOR = System.getProperty("line.separator");

    private FacingState facingState;
    private RoverPath roverPath = new RoverPath();

    public MarsRover(String operations) {
        facingState = new EastState(this);

        startOperations(operations);
    }

    private void startOperations(String operations) {
        for (int i = 0; i < operations.length(); i++) {
            startOperation(operations.charAt(i));
        }
    }

    private void startOperation(char operation) {
        switch (operation) {
            case 's':
                moveForward();
                break;
            case 'l':
                turnLeft();
                break;
            case 'r':
                turnRight();
                break;
            case 'S':
                takeSample();
                break;
            default:
                throw new UnsupportedOperationException();
        }
    }

    public String path() {
        return roverPath.draw();
    }

    public MarsRover turnLeft() {
        roverPath.addCrossroad();
        facingState.turnLeft();
        return this;
    }

    public MarsRover turnRight() {
        roverPath.addCrossroad();
        facingState.turnRight();
        return this;
    }

    public MarsRover moveForward() {
        facingState.moveForward();
        return this;
    }

    public MarsRover takeSample() {
        roverPath.takeSample();
        return this;
    }

    public void moveEast() {
        roverPath.moveEast();
    }

    public void moveNorth() {
        roverPath.moveNorth();
    }

    public void moveSouth() {
        roverPath.moveSouth();
    }

    public void moveWest() {
        roverPath.moveWest();
    }

    public void setState(FacingState state) {
        this.facingState = state;
    }
}
