package com.github.javadojo;

import java.util.ArrayList;
import java.util.List;
import static com.github.javadojo.MarsRover.LINE_SEPARATOR;
import static com.github.javadojo.PathSymbols.*;

public class RoverPath {

    private List<List<String>> map = new ArrayList<>();
    private int rowRoverIndex = 0;
    private int columnRoverIndex = 0;


    public RoverPath() {
        map.add(new ArrayList<String>());
        map.get(0).add(LANDING);
    }

    public void moveWest() {
        columnRoverIndex--;
        map.get(map.size()-1).set(columnRoverIndex, WEST_EAST);
    }

    public void moveEast() {
        columnRoverIndex++;
        map.get(map.size()-1).add(WEST_EAST);
    }

    public void moveNorth() {
        rowRoverIndex--;
        if (isOutsideMap()) {
            List<String> newRow = addNewNorthRow();
            newRow.add(columnRoverIndex, NORTH_SOUTH);
        }
        else {
            if(map.get(rowRoverIndex).get(columnRoverIndex).equals(BLANK))
                map.get(rowRoverIndex).set(columnRoverIndex, NORTH_SOUTH);
            else
                addCrossroad();
        }
        rowRoverIndex = Math.max(0, rowRoverIndex);
    }

    private List<String> addNewNorthRow() {
        List<String> newRow = new ArrayList<>();
        map.add(0, newRow);
        initNorthRow(newRow);
        return newRow;
    }

    private void initNorthRow(List<String> newRow) {
        for (int i = 0; i < map.get(1).size()-1; i++)
            newRow.add(0, BLANK);
    }

    public void moveSouth() {
        rowRoverIndex++;
        if (isOutsideMap()) {
            ArrayList<String> newRow = addNewSouthRow();
            newRow.add(NORTH_SOUTH);
        }
        else
            map.get(rowRoverIndex).set(columnRoverIndex, NORTH_SOUTH);

    }

    private ArrayList<String> addNewSouthRow() {
        ArrayList<String> newRow;
        newRow = new ArrayList<>();
        initSouthRow(newRow);
        map.add(newRow);
        return newRow;
    }

    private void initSouthRow(ArrayList<String> newRow) {
        for (int i = 0; i < map.get(map.size()-1).size()-1; i++)
            newRow.add(0, BLANK);
    }

    private boolean isOutsideMap() {
        return rowRoverIndex == -1 || rowRoverIndex == map.size();
    }

    public void addCrossroad() {
        if (canAddCrossroad())
            map.get(rowRoverIndex).set(columnRoverIndex, CROSSROAD);
    }

    private boolean canAddCrossroad() {
        return (columnRoverIndex > 0 || rowRoverIndex > 0) && !map.get(rowRoverIndex).get(columnRoverIndex).equals(SAMPLE);
    }

    public String draw() {
        StringBuilder sb = new StringBuilder();

        for (int j = 0; j < map.size(); j++) {
            for (int i = 0; i < map.get(j).size(); i++) {
                if(j == rowRoverIndex && i == columnRoverIndex)
                    sb.append(CURRENT);
                else
                    sb.append(map.get(j).get(i));
            }
            sb.append(LINE_SEPARATOR);
        }

        return sb.toString();
    }

    public void takeSample() {
        map.get(rowRoverIndex).set(columnRoverIndex, SAMPLE);
    }
}
