package org.example.model;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

@Component
public class Grid {

    List<int[]> ship_coordinates;

    public String[][] grid;

    {

        ship_coordinates = new ArrayList<>();

        ship_coordinates.add(new int[]{6, 0});
        ship_coordinates.add(new int[]{0, 6});
        ship_coordinates.add(new int[]{6, 12});
        ship_coordinates.add(new int[]{12, 6});

        grid = new String[13][13];

        grid[0][0] = "x=0_y=0_blocked";
        grid[0][12] = "x=12_y=0_blocked";
        grid[12][0] = "x=0_y=12_blocked";
        grid[12][12] = "x=12_y=12_blocked";

        for (int i = 1; i <= 11; ++i) {
            grid[0][i] = "x=" + i + "_y=0_water";
        }
        for (int i = 1; i <= 11; ++i) {
            grid[i][0] = "x=0_y=" + i + "_water";
        }
        for (int i = 1; i <= 11; ++i) {
            grid[12][i] = "x=" + i + "_y=12_water";
        }
        for (int i = 1; i <= 11; ++i) {
            grid[i][12] = "x=12_y=" + i + "_water";
        }

        for (int[] p : ship_coordinates) {
            grid[p[0]][p[1]] += "_ship";
        }

        List<String> cells = new ArrayList<>();

        int k = 0;

        for (int i = 0; i < 3; ++i) {
            cells.add("right");
            ++k;
        }

        for (int i = 0; i < 2; ++i) {
            cells.add("horse");
            ++k;
        }

        for (int i = 0; i < 6; ++i) {
            cells.add("ice");
            ++k;
        }

        for (int i = 0; i < 4; ++i) {
            cells.add("croc");
            ++k;
        }

        cells.add("cannibal");
        ++k;

        for (int i = 0; i < 2; ++i) {
            cells.add("fortress");
            ++k;
        }

        for (int i = 0; i < 5; ++i) {
            cells.add("chest_I");
            ++k;
        }

        for (int i = 0; i < 5; ++i) {
            cells.add("chest_II");
            ++k;
        }

        for (int i = 0; i < 3; ++i) {
            cells.add("chest_III");
            ++k;
        }

        for (int i = 0; i < 2; ++i) {
            cells.add("chest_IV");
            ++k;
        }

        for (int i = 0; i < 1; ++i) {
            cells.add("chest_V");
            ++k;
        }

        while (k < 121) {
            cells.add("empty");
            ++k;
        }

        java.util.Collections.shuffle(cells);

        k = 0;

        for (int i = 1; i <= 11; ++i) {
            for (int j = 1; j <= 11; ++j) {
                grid[i][j] = "x=" + j + "_y=" + i + "_" + cells.get(k);
                ++k;
            }
        }

    }

    public String[][] getGrid(){
        return grid;
    }

    boolean ship_is_chosen = false;

    public void GameUpdate(String cell_name) {
        String x_coord;
    }

}
