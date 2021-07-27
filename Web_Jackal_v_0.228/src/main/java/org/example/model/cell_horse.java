package org.example.model;

public class cell_horse {

    public static void horse_event(int x, int y, Grid grid, String cell_type, int[] pirates) {

        if (!grid.parse_name(grid.grid[y][x]).equals("blocked") &&
                (x - grid.pirate_x == 2 && y - grid.pirate_y == 1 ||
                        x - grid.pirate_x == 2 && y - grid.pirate_y == -1 ||
                        x - grid.pirate_x == -2 && y - grid.pirate_y == 1 ||
                        x - grid.pirate_x == -2 && y - grid.pirate_y == -1 ||
                        x - grid.pirate_x == 1 && y - grid.pirate_y == 2 ||
                        x - grid.pirate_x == 1 && y - grid.pirate_y == -2 ||
                        x - grid.pirate_x == -1 && y - grid.pirate_y == 2 ||
                        x - grid.pirate_x == -1 && y - grid.pirate_y == -2)) {

            grid.Move(x, y, pirates, grid);
            if (grid.parse_name(grid.grid[y][x]).equals("water") && grid.dragging_coins) {
                grid.dragging_coins = false;
                --grid.coins_on_field;
            }

        }
    }
}
