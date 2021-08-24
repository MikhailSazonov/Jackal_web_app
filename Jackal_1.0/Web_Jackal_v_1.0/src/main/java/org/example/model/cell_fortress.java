package org.example.model;

public class cell_fortress {

    public static void fortress_event(Grid grid) {
        if (grid.dragging_coins) {
            grid.grid_[grid.starting_y][grid.starting_x] += '+';
            grid.dragging_coins = false;
        }
    }

}
