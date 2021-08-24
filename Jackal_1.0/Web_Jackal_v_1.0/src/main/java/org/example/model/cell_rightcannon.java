package org.example.model;

public class cell_rightcannon {

    public static void rightcannon_event(int x, int y, Grid grid, int[] pirates) {
        grid.Move(12, y, pirates, grid);
    }

}
