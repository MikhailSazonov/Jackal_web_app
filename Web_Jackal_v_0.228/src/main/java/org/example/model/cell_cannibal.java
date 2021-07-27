package org.example.model;

public class cell_cannibal {

    public static void cannibal_event(int x, int y, Grid grid) {

        grid.grid[y][x] = "x=" + x + "_y=" + y + "_cannibal_0pirates_5pl_";
        --grid.players_pirates[grid.current_player];

        if (grid.dragging_coins) {
            grid.dragging_coins = false;
            --grid.coins_on_field;
        }

    }

}
