package org.example.model;

public class cell_rum$barrel {

    public static void rum$barrel_event(int x, int y, Grid grid) {
        grid.forbidden[y][x] = grid.turn + 2;
    }

}
