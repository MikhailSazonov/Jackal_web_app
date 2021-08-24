package org.example.model;

public class cell_chest$IV {

    public static void chest_IV_event(int x, int y, Grid grid) {

        grid.MakeEmpty(y, x);

        grid.grid_[y][x] += "++++";

    }

}
