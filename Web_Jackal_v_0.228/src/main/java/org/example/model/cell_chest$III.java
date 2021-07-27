package org.example.model;

public class cell_chest$III {

    public static void chest_III_event(int x, int y, Grid grid) {

        grid.MakeEmpty(y, x);

        grid.grid[y][x] += "+++";

    }

}
