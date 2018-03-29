package com.github.javadojo;

import java.util.ArrayList;
import java.util.List;
import static com.github.javadojo.MarsRover.LINE_SEPARATOR;

public class RoverPath {

    private List<List<String>> map = new ArrayList<>();
    private int rowRoverIndex = 0;
    private int columnRoverIndex = 0;

    public RoverPath() {
        map.add(new ArrayList<String>());
        map.get(0).add("X");
    }

    public void moveWest() {
        columnRoverIndex--;
        map.get(map.size()-1).set(columnRoverIndex, "-");
    }

    public void moveEast() {
        map.get(map.size()-1).add("-");
        columnRoverIndex++;
    }

    public void moveNorth() {
        ArrayList<String> newRow = new ArrayList<>();
        rowRoverIndex--;
        if (rowRoverIndex == -1) {
            map.add(0, newRow);
            initNorthRow(newRow);
            newRow.add(columnRoverIndex, "|");
        }
        else {
            if(map.get(rowRoverIndex).get(columnRoverIndex).equals(" ")) {
                map.get(rowRoverIndex).set(columnRoverIndex, "|");
            }
            else {
                addCrossroad();
            }
        }
        rowRoverIndex = Math.max(0, rowRoverIndex);
    }

    private void initNorthRow(ArrayList<String> newRow) {
        for (int i = 0; i < map.get(1).size()-1; i++)
            newRow.add(0, " ");
    }

    private void initSouthRow(ArrayList<String> newRow) {
        for (int i = 0; i < map.get(map.size()-1).size()-1; i++)
            newRow.add(0, " ");
    }

    public void moveSouth() {
        ArrayList<String> row;
        rowRoverIndex++;
        if (rowRoverIndex == map.size()) {
            row = new ArrayList<>();
            initSouthRow(row);
            map.add(row);
            row.add("|");
        }
        else {
            map.get(rowRoverIndex).set(columnRoverIndex, "|");
        }

    }

    public String draw() {
        StringBuilder sb = new StringBuilder();

        for (int j = 0; j < map.size(); j++) {
            for (int i = 0; i < map.get(j).size(); i++) {
                String cell = map.get(j).get(i);
                if(j == rowRoverIndex && i == columnRoverIndex)
                    sb.append("*");
                else
                    sb.append(cell);
            }
            sb.append(LINE_SEPARATOR);
        }

        return sb.toString();
    }

    public void addCrossroad() {
        if (canAddCrossroad())
            map.get(rowRoverIndex).set(columnRoverIndex, "+");
    }

    private boolean canAddCrossroad() {
        return (columnRoverIndex > 0 || rowRoverIndex > 0) && !map.get(rowRoverIndex).get(columnRoverIndex).equals("S");
    }

    public void takeSample() {
        map.get(rowRoverIndex).set(columnRoverIndex, "S");
    }
}
