package org.example.model;

public class cell_aircraft {

    public static void aircraft_event(int x, int y, Grid grid) {
        grid.pirate_x = x;
        grid.pirate_y = y;
        grid.BoardingOnAShip(grid.ship_coordinates[grid.current_player][0], grid.ship_coordinates[grid.current_player][1]);
    }

}
