package org.example.model;

public class cell_chest$I {

    public static void chest_I_event(int x, int y, Grid grid) {

        grid.MakeEmpty(y, x);

        grid.grid[y][x] += "+";
    }

}
