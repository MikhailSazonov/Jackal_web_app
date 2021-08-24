package org.example.model;

public class cell_croc {

    public static void croc_event(int x, int y, Grid grid, int[] pirates, String cell_type) {

        if (x == -1) {
            grid.BoardingOnAShip(grid.ship_coordinates[grid.current_player][0], grid.ship_coordinates[grid.current_player][1], true);
            return;
        }

            grid.Move(x, y, pirates, grid);
    }

}
