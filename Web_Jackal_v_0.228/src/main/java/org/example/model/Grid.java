package org.example.model;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

@Component
public class Grid {

    public String[][] grid;

    {

//        ship_coordinates = new ArrayList<>();
//
//        ship_coordinates.add(new int[]{6, 0});
//        ship_coordinates.add(new int[]{0, 6});
//        ship_coordinates.add(new int[]{6, 12});
//        ship_coordinates.add(new int[]{12, 6});

        grid = new String[13][13];

        grid[0][0] = "x=0_y=0_blocked_0pirates_5pl_";
        grid[0][12] = "x=12_y=0_blocked_0pirates_5pl_";
        grid[12][0] = "x=0_y=12_blocked_0pirates_5pl_";
        grid[12][12] = "x=12_y=12_blocked_0pirates_5pl_";

        for (int i = 1; i <= 11; ++i) {
            grid[0][i] = "x=" + i + "_y=0_water_0pirates_5pl_";
        }
        for (int i = 1; i <= 11; ++i) {
            grid[i][0] = "x=0_y=" + i + "_water_0pirates_5pl_";
        }
        for (int i = 1; i <= 11; ++i) {
            grid[12][i] = "x=" + i + "_y=12_water_0pirates_5pl_";
        }
        for (int i = 1; i <= 11; ++i) {
            grid[i][12] = "x=12_y=" + i + "_water_0pirates_5pl_";
        }

        grid[6][0] += "ship_3pirates_0pl_";
        grid[6][12] += "ship_3pirates_1pl_";

        List<String> cells = new ArrayList<>();

        int k = 0;

        for (int i = 0; i < 3; ++i) {
            cells.add("right_0pirates_5pl_");
            ++k;
        }

        for (int i = 0; i < 2; ++i) {
            cells.add("horse_0pirates_5pl_");
            ++k;
        }

        for (int i = 0; i < 6; ++i) {
            cells.add("ice_0pirates_5pl_");
            ++k;
        }

        for (int i = 0; i < 4; ++i) {
            cells.add("croc_0pirates_5pl_");
            ++k;
        }

        cells.add("cannibal_0pirates_5pl_");
        ++k;

        for (int i = 0; i < 2; ++i) {
            cells.add("fortress_0pirates_5pl_");
            ++k;
        }

        for (int i = 0; i < 5; ++i) {
            cells.add("chest_I_0pirates_5pl_");
            ++k;
        }

        for (int i = 0; i < 5; ++i) {
            cells.add("chest_II_0pirates_5pl_");
            ++k;
        }

        for (int i = 0; i < 3; ++i) {
            cells.add("chest_III_0pirates_5pl_");
            ++k;
        }

        for (int i = 0; i < 2; ++i) {
            cells.add("chest_IV_0pirates_5pl_");
            ++k;
        }

        for (int i = 0; i < 1; ++i) {
            cells.add("chest_V_0pirates_5pl_");
            ++k;
        }

        while (k < 121) {
            cells.add("empty_0pirates_5pl_");
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
    int ship_x;
    int ship_y;
    int ship_pirates;


    boolean pirate_is_chosen = false;
    int pirate_x;
    int pirate_y;
    int cell_pirates;

    int current_player = 0;

    String parse_name(String cell_name) {
        int i = 0;
        int j = 0;
        while (i < 2) {
            ++j;
            if (cell_name.charAt(j) == '_')
                ++i;
        }
        ++j;
        String cell_ = "";
        while (cell_name.charAt(j) != '_') {
            cell_ += cell_name.charAt(j);
            ++j;
        }
        return cell_;
    }

    int[] parse_pirates(String cell_name) {
        String pirates = cell_name.substring(cell_name.length()-13);
        int pir = Integer.parseInt(pirates.substring(0, 1));
        int ppl = Integer.parseInt(pirates.substring(9, 10));
        return new int[]{pir, ppl};
    }

    void NextTurn() {
        ship_is_chosen = false;
        pirate_is_chosen = false;
        current_player = (current_player + 1) % 2;
    }

    public void GameUpdate(String cell_name) {
        String x_coord = "";
        String y_coord = "";
        String cell_type = "";
        int i = 2;
        for (; cell_name.charAt(i) != '_'; ++i) {
            x_coord += cell_name.charAt(i);
        }
        i += 3;
        for (; cell_name.charAt(i) != '_'; ++i) {
            y_coord += cell_name.charAt(i);
        }
        ++i;
        for (; cell_name.charAt(i) != '_'; ++i) {
            cell_type += cell_name.charAt(i);
        }
        ++i;

        String game_object = "";

        i += 13;

        for (; i < cell_name.length() && cell_name.charAt(i) != '_'; ++i) {
            game_object += cell_name.charAt(i);
        }

        if (i < cell_name.length())
            ++i;

        ++i;

        int[] pirates = parse_pirates(cell_name);

        if (i < cell_name.length())
            ++i;

        int x = Integer.parseInt(x_coord);
        int y = Integer.parseInt(y_coord);

        if (ship_is_chosen || pirate_is_chosen) {
            if (ship_is_chosen) {

                if (parse_name(cell_name).equals("water") &&
                        ((Math.abs(x - ship_x) <= 1 && ship_y == y) ||
                        (Math.abs(y - ship_y) <= 1 && ship_x == x))
                        && !(x == ship_x && y == ship_y)) {
                    grid[ship_y][ship_x] = grid[ship_y][ship_x].substring(0, grid[ship_y][ship_x].length() - 18);
                    grid[y][x] += "ship_" + ship_pirates + "pirates_" + current_player + "pl_";
                    NextTurn();
                }

                else if (!parse_name(cell_name).equals("blocked") && Math.abs(x - ship_x) <= 1 && Math.abs(y - ship_y) <= 1 &&
                        !(x == ship_x && y == ship_y) && ship_pirates > 0) {

                    grid[ship_y][ship_x] = grid[ship_y][ship_x].substring(0, grid[ship_y][ship_x].length() - 13);
                    grid[ship_y][ship_x] += ship_pirates - 1 + "pirates_" + current_player + "pl_";
                    int[] new_pirates = parse_pirates(grid[y][x]);
                    grid[y][x] = grid[y][x].substring(0, grid[y][x].length() - 13);
                    ++new_pirates[0];
                    grid[y][x] += new_pirates[0] + "pirates_" + current_player + "pl_";
                    NextTurn();

                }

                else {

                    ship_is_chosen = false;

                }

            } else {


                if (!parse_name(cell_name).equals("blocked") && !parse_name(cell_name).equals("water") &&
                        Math.abs(x - pirate_x) <= 1 && Math.abs(y - pirate_y) <= 1
                        && !(x == pirate_x && y == pirate_y)) {

                    grid[pirate_y][pirate_x] = grid[pirate_y][pirate_x].substring(0, grid[pirate_y][pirate_x].length() - 13);
                    int ppl = (cell_pirates == 1 ? 5 : current_player);
                    grid[pirate_y][pirate_x] += cell_pirates - 1 + "pirates_" + ppl + "pl_";
                    grid[y][x] = grid[y][x].substring(0, grid[y][x].length() - 13);
                    grid[y][x] += pirates[0] + 1 + "pirates_" + current_player + "pl_";
                    NextTurn();
                }

                else {

                    pirate_is_chosen = false;

                }

            }
        } else if (game_object.equals("ship") && pirates[1] == current_player) {

            ship_is_chosen = true;
            ship_x = x;
            ship_y = y;
            ship_pirates = pirates[0];

        } else if (pirates[0] > 0 && pirates[1] == current_player) {

            pirate_is_chosen = true;
            pirate_x = x;
            pirate_y = y;
            cell_pirates = pirates[0];

        }

    }

}
