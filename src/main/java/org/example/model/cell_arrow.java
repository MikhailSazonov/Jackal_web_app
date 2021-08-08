package org.example.model;

public class cell_arrow {

    public static void arrow_event(int x, int y, int[] pirates, Grid grid, String cell_type) {

        if (!grid.parse_name(grid.grid[y][x]).equals("blocked") &&
                Math.abs(x - grid.pirate_x) <= 1 && Math.abs(y - grid.pirate_y) == 0
                && !(x == grid.pirate_x && y == grid.pirate_y)) {

            grid.Move(x, y, pirates, grid);

            if (grid.parse_name(grid.grid[y][x]).equals("water") && grid.dragging_coins) {
                grid.dragging_coins = false;
                --grid.coins_on_field;
            }

            grid.NextTurn();
        }

    }
}
