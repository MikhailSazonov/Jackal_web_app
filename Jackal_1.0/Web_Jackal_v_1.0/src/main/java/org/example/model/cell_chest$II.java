package org.example.model;

public class cell_chest$II {

    public static void chest_II_event(int x, int y, Grid grid) {

        grid.MakeEmpty(y, x);

        grid.grid_[y][x] += "++";

    }

}
