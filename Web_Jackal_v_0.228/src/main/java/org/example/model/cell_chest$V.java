package org.example.model;

public class cell_chest$V {

    public static void chest_V_event(int x, int y, Grid grid) {

        grid.MakeEmpty(y, x);

        grid.grid[y][x] += "+++++";

    }

}
