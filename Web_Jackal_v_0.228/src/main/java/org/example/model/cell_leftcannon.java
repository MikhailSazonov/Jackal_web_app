package org.example.model;

public class cell_leftcannon {

    public static void leftcannon_event(int x, int y, Grid grid, int[] pirates) {
        grid.Move(0, y, pirates, grid);
    }

}
