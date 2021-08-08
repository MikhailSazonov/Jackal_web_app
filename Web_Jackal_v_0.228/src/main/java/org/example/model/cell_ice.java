package org.example.model;

public class cell_ice {

    public static void ice_event(int old_x, int old_y, int x, int y, Grid grid, int[] pirates, String cell_type) {
        if (grid.Event.equals("") && !grid.airplane) {
            if (!(grid.ship_coordinates[grid.current_player][1] == 2 * x - old_x &&
                    grid.ship_coordinates[grid.current_player][0] == 2 * y - old_y ||
                    grid.ship_coordinates[(grid.current_player + 1) % 2][1] == 2 * x - old_x &&
                            grid.ship_coordinates[(grid.current_player + 1) % 2][0] == 2 * y - old_y)) {
                grid.Move(2 * x - old_x, 2 * y - old_y, pirates, grid);
                if (grid.parse_name(grid.grid[2 * y - old_y][2 * x - old_x]).equals("water") && grid.dragging_coins) {
                    grid.dragging_coins = false;
                    --grid.coins_on_field;
                }
            }
            else {
                grid.BoardingOnAShip(2 * y - old_y, 2 * x - old_x, true);
                grid.grid[y][x] = grid.grid[y][x].substring(0, grid.grid[y][x].length()-1);
            }
        }
    }

}
