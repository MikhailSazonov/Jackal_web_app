package org.example.model;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

@Component
public class Grid {

    public String[][] grid;

    {

        grid = new String[13][13];

        ship_pirates_ = new int[]{3, 3};

        ship_coordinates = new int[][]{{6, 0}, {6, 12}};

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

        grid[6][0] = grid[6][0].substring(0, grid[6][0].length()-13);
        grid[6][12] = grid[6][12].substring(0, grid[6][12].length()-13);

        grid[6][0] += "3pirates_0pl_ship_";
        grid[6][12] += "3pirates_1pl_ship_";

        List<String> cells = new ArrayList<>();

        int k = 0;

        for (int i = 0; i < 3; ++i) {
            cells.add("arrow_0pirates_5pl_");
            ++k;
        }

        for (int i = 0; i < 2; ++i) {
            cells.add("horse_0pirates_5pl_");
            ++k;
        }

        for (int i = 0; i < 4; ++i) {
            cells.add("lab$II_0pirates_5pl_");
            ++k;
        }

        for (int i = 0; i < 3; ++i) {
            cells.add("lab$III_0pirates_5pl_");
            ++k;
        }

        for (int i = 0; i < 2; ++i) {
            cells.add("lab$IV_0pirates_5pl_");
            ++k;
        }

        for (int i = 0; i < 1; ++i) {
            cells.add("lab$V_0pirates_5pl_");
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

        for (int i = 0; i < 1; ++i) {
            cells.add("resurrect_0pirates_5pl_");
            ++k;
        }

        for (int i = 0; i < 3; ++i) {
            cells.add("trap_0pirates_5pl_");
            ++k;
        }

        for (int i = 0; i < 2; ++i) {
            cells.add("aircraft_0pirates_5pl_");
            ++k;
        }

        for (int i = 0; i < 1; ++i) {
            cells.add("airplane_0pirates_5pl_");
            ++k;
        }

        for (int i = 0; i < 2; ++i) {
            cells.add("cannon_0pirates_5pl_");
            ++k;
        }

        for (int i = 0; i < 5; ++i) {
            cells.add("chest$I_0pirates_5pl_");
            ++k;
        }

        for (int i = 0; i < 5; ++i) {
            cells.add("chest$II_0pirates_5pl_");
            ++k;
        }

        for (int i = 0; i < 3; ++i) {
            cells.add("chest$III_0pirates_5pl_");
            ++k;
        }

        for (int i = 0; i < 2; ++i) {
            cells.add("chest$IV_0pirates_5pl_");
            ++k;
        }

        for (int i = 0; i < 1; ++i) {
            cells.add("chest$V_0pirates_5pl_");
            ++k;
        }

        for (int i = 0; i < 4; ++i) {
            cells.add("rum$barrel_0pirates_5pl_");
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

        grid[6][1] = "x=1_y=6_chest$I_0pirates_5pl_";
        grid[5][1] = "x=1_y=5_horse_0pirates_5pl_";


    }

    String Event = "";

    public String[][] getGrid(){
        return grid;
    }

    int[] players_pirates = new int[]{3, 3};

    boolean ship_is_chosen = false;
    int ship_x;
    int ship_y;
    int ship_pirates;
    int ship_player;

    int[][] ship_coordinates;
    int[] ship_pirates_;

    boolean pirate_is_chosen = false;
    int pirate_x = -1;
    int pirate_y = -1;
    int starting_x;
    int starting_y;
    int cell_pirates;
    int pirate_player;


    int current_player = 0;

    boolean dragging_coins = false;
    public int[] players_coins = new int[]{0, 0};

    public int coins_on_field = 37;

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
        int j = 0;
        for (int k = cell_name.length()-1; cell_name.charAt(k) == '+'; --k) {
            ++j;
        }
        if (cell_name.substring(cell_name.length()-5, cell_name.length()-1).equals("ship")) {
            j += 5;
        }
        String pirates = cell_name.substring(cell_name.length()-13-j, cell_name.length()-j);
        int pir = Integer.parseInt(pirates.substring(0, 1));
        int ppl = Integer.parseInt(pirates.substring(9, 10));
        return new int[]{pir, ppl};
    }

    void NextTurn() {
        if (dragging_coins && !parse_name(grid[pirate_y][pirate_x]).equals("water")) {
            grid[pirate_y][pirate_x] += '+';
        }
        ship_is_chosen = false;
        pirate_is_chosen = false;
        dragging_coins = false;
        Event = "";
        pirate_x = -1;
        pirate_y = -1;
        current_player = (current_player + 1) % 2;
    }

    void BoardingOnAShip(int y, int x) {
        if (y == ship_coordinates[current_player][0] && x == ship_coordinates[current_player][1]) {
            grid[y][x] = grid[y][x].substring(0, grid[y][x].length() - 18);
            grid[y][x] += ship_pirates_[current_player] + 1 + "pirates_" + current_player + "pl_ship_";
            ++ship_pirates_[current_player];
            if (dragging_coins) {
                ++players_coins[current_player];
                --coins_on_field;
                dragging_coins = false;
            }
        } else {
            --players_pirates[current_player];
        }

        int j = 0;
        for (int k = grid[pirate_y][pirate_x].length()-1; grid[pirate_y][pirate_x].charAt(k) == '+'; --k) {
            ++j;
        }

        grid[pirate_y][pirate_x] = grid[pirate_y][pirate_x].substring(0, grid[pirate_y][pirate_x].length() - 13 - j);
        int ppl = (cell_pirates <= 1 ? 5 : current_player);
        int pir = Math.max(0, cell_pirates-1);
        grid[pirate_y][pirate_x] += pir + "pirates_" + ppl + "pl_";
        for (int i = 0; i < j; ++i) {
            grid[pirate_y][pirate_x] += '+';
        }

    }

    boolean Move(int x, int y, int[] pirates, Grid grid) {

        String cell_type = parse_name(grid.grid[y][x]);

        if (!cell_type.equals("blocked") && !cell_type.equals("water") ||
                cell_type.equals("water") && parse_name(grid.grid[pirate_y][pirate_x]).equals("water") ||
                grid.ship_coordinates[current_player][0] == y && grid.ship_coordinates[current_player][1] == x ||
                grid.ship_coordinates[(current_player + 1) % 2][0] == y && grid.ship_coordinates[(current_player + 1) % 2][1] == x) {

            if (grid.ship_coordinates[current_player][0] == y && grid.ship_coordinates[current_player][1] == x ||
                    grid.ship_coordinates[(current_player + 1) % 2][0] == y && grid.ship_coordinates[(current_player + 1) % 2][1] == x) {
                BoardingOnAShip(y, x);
                return true;
            }

            int[] new_pirates = grid.parse_pirates(grid.grid[y][x]);

            boolean attack = false;

            if (new_pirates[1] != grid.current_player && new_pirates[1] != 5 && !dragging_coins && !cell_type.equals("fortress")) {

                attack = true;

                grid.current_player = (grid.current_player + 1) % 2;
                int old_y = grid.pirate_y;
                int old_x = grid.pirate_x;
                grid.pirate_y = y;
                grid.pirate_x = x;
                while (grid.parse_pirates(grid.grid[y][x])[0] > 0) {
                    int old_cell_pirates = grid.cell_pirates;
                    grid.cell_pirates = grid.parse_pirates(grid.grid[y][x])[0];
                    if (!grid.parse_name(grid.grid[y][x]).equals("water"))
                        grid.BoardingOnAShip(grid.ship_coordinates[grid.current_player][0], grid.ship_coordinates[grid.current_player][1]);
                    else {
                        grid.grid[grid.pirate_y][grid.pirate_x] = grid.grid[grid.pirate_y][grid.pirate_x].substring(0, grid.grid[grid.pirate_y][grid.pirate_x].length() - 13);
                        int ppl = (grid.cell_pirates == 1 ? 5 : grid.current_player);
                        grid.grid[grid.pirate_y][grid.pirate_x] += grid.cell_pirates - 1 + "pirates_" + ppl + "pl_";
                        --players_pirates[current_player];
                    }
                    grid.cell_pirates = old_cell_pirates;
                }

                grid.current_player = (grid.current_player + 1) % 2;
                grid.pirate_y = old_y;
                grid.pirate_x = old_x;

            } else if (new_pirates[1] != grid.current_player && new_pirates[1] != 5 && (dragging_coins || cell_type.equals("fortress"))) {
                return false;
            }


            int j = 0;
            for (int k = grid.grid[pirate_y][pirate_x].length() - 1; grid.grid[pirate_y][pirate_x].charAt(k) == '+'; --k) {
                ++j;
            }
            boolean ship = false;
            if (grid.grid[pirate_y][pirate_x].substring(grid.grid[pirate_y][pirate_x].length()-5, grid.grid[pirate_y][pirate_x].length()-1).equals("ship")) {
                j += 5;
                ship = true;
            }
            grid.grid[grid.pirate_y][grid.pirate_x] = grid.grid[grid.pirate_y][grid.pirate_x].substring(0, grid.grid[grid.pirate_y][grid.pirate_x].length() - 13 - j);
            int ppl = (grid.cell_pirates == 1 ? 5 : grid.current_player);
            grid.grid[grid.pirate_y][grid.pirate_x] += grid.cell_pirates - 1 + "pirates_" + ppl + "pl_";


            int q = 0;
            for (int k = grid.grid[y][x].length() - 1; grid.grid[y][x].charAt(k) == '+'; --k) {
                ++q;
            }
            grid.grid[y][x] = grid.grid[y][x].substring(0, grid.grid[y][x].length() - 13 - q);
            if (!attack)
                grid.grid[y][x] += pirates[0] + 1 + "pirates_" + grid.current_player + "pl_";
            else
                grid.grid[y][x] += 1 + "pirates_" + grid.current_player + "pl_";
            if (ship) {
                grid.grid[grid.pirate_y][grid.pirate_x] += "ship_";
                j -= 5;
            }
            for (int i = 0; i < j; ++i) {
                grid.grid[grid.pirate_y][grid.pirate_x] += '+';
            }
            for (int i = 0; i < q; ++i) {
                grid.grid[y][x] += '+';
            }

            int old_x_ = pirate_x;
            int old_y_ = pirate_y;
            pirate_x = x;
            pirate_y = y;

            grid.cell_pirates = grid.parse_pirates(grid.grid[y][x])[0];
            ChooseCellType(cell_type, x, y, pirates, old_x_, old_y_);

            return true;
        }
        return false;
    }

    public void MakeEmpty(int y, int x) {
        String cell_coordinates = "";
        int i = 0;
        int j = 0;
        while (i < 2) {
            cell_coordinates += grid[y][x].charAt(j);
            ++j;
            if (grid[y][x].charAt(j) == '_')
                ++i;
        }
        while (i < 3) {
            ++j;
            if (grid[y][x].charAt(j) == '_')
                ++i;
        }
        grid[y][x] = cell_coordinates + "_empty" + grid[y][x].substring(j);

    }

    public void ChooseCellType(String cell_type, int x, int y, int[] pirates, int old_x, int old_y) {

        switch (cell_type) {
            case ("arrow") : {
                Event = "arrow";
                break;
            }

            case ("horse") : {
                Event = "horse";
                break;
            }

            case ("cannibal") : {
                cell_cannibal.cannibal_event(x, y, this);
                NextTurn();
                break;
            }

            case ("chest$I") : {
                cell_chest$I.chest_I_event(x, y, this);
                NextTurn();
                break;
            }

            case ("chest$II") : {
                cell_chest$II.chest_II_event(x, y, this);
                NextTurn();
                break;
            }

            case ("chest$III") : {
                cell_chest$III.chest_III_event(x, y, this);
                NextTurn();
                break;
            }

            case ("chest$IV") : {
                cell_chest$IV.chest_IV_event(x, y, this);
                NextTurn();
                break;
            }

            case ("chest$V") : {
                cell_chest$V.chest_V_event(x, y, this);
                NextTurn();
                break;
            }

            case ("fortress") : {
                cell_fortress.fortress_event(this);
                NextTurn();
                break;
            }

            case ("croc") : {
                cell_croc.croc_event(old_x, old_y, this, pirates, cell_type);
                NextTurn();
                break;
            }

            case ("aircraft") : {
                cell_aircraft.aircraft_event(x, y, this);
                NextTurn();
                break;
            }

            case ("ice") : {
                cell_ice.ice_event(old_x, old_y, x, y, this, pirates, cell_type);
                break;
            }

            default:
                NextTurn();

        }

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


        int[] pirates = parse_pirates(cell_name);

        if (i < cell_name.length())
            ++i;

        int x = Integer.parseInt(x_coord);
        int y = Integer.parseInt(y_coord);

        switch (Event) {
            case ("arrow") : {
                cell_arrow.arrow_event(x, y, pirates, this, cell_type);
                return;
            }

            case ("horse") : {
                cell_horse.horse_event(x, y, this, cell_type, pirates);
                return;
            }

        }

        String game_object = "";

        if (grid[y][x].substring(grid[y][x].length()-5, grid[y][x].length()-1).equals("ship")) {
            game_object = "ship";
        }

        if (ship_is_chosen || pirate_is_chosen) {
            if (ship_is_chosen) {

                if (parse_name(cell_name).equals("water") &&
                        ((Math.abs(x - ship_x) <= 1 && ship_y == y) ||
                        (Math.abs(y - ship_y) <= 1 && ship_x == x))
                        && !(x == ship_x && y == ship_y)) {
                    grid[ship_y][ship_x] = grid[ship_y][ship_x].substring(0, grid[ship_y][ship_x].length() - 18);
                    grid[ship_y][ship_x] += "0pirates_5pl_";
                    int[] new_pirates = parse_pirates(grid[y][x]);
                    if (new_pirates[0] > 0) {
                        int old_player = current_player;
                        current_player = new_pirates[1];
                        while (parse_pirates(grid[y][x])[0] > 0) {
                            pirate_y = y;
                            pirate_x = x;
                            BoardingOnAShip(ship_y, ship_x);
                        }
                        current_player = old_player;
                    }
                    grid[y][x] = grid[y][x].substring(0, grid[ship_y][ship_x].length()-13);
                    grid[y][x] += ship_pirates + "pirates_" + current_player + "pl_ship_";
                    ship_coordinates[current_player] = new int[]{y, x};

                    NextTurn();
                }

                else if (!parse_name(cell_name).equals("blocked") && Math.abs(x - ship_x) <= 1 && Math.abs(y - ship_y) <= 1 &&
                        !(x == ship_x && y == ship_y) && ship_pirates > 0) {

                    --ship_pirates_[current_player];

                    pirate_is_chosen = true;
                    pirate_x = ship_x;
                    pirate_y = ship_y;
                    Move(x, y, pirates, this);

//                    int[] new_pirates = parse_pirates(grid[y][x]);
//                    grid[y][x] = grid[y][x].substring(0, grid[y][x].length() - 13);
//                    ++new_pirates[0];
//                    grid[y][x] += new_pirates[0] + "pirates_" + current_player + "pl_";
//
//
//                    int old_x_ = pirate_x;
//                    int old_y_ = pirate_y;
//                    pirate_x = x;
//                    pirate_y = y;
//
//                    ChooseCellType(cell_type, x, y, pirates, old_x_, old_y_);


                }

                else {

                    ship_is_chosen = false;

                }

            } else {

                if (y == pirate_y && x == pirate_x && cell_name.charAt(cell_name.length()-1) == '+') {
                    dragging_coins = true;
                    grid[y][x] = grid[y][x].substring(0, grid[y][x].length()-1);
                    return;
                } else if (y == pirate_y && x == pirate_x && dragging_coins) {
                    dragging_coins = false;
                    grid[y][x] += "+";
                    return;
                } else if (y == pirate_y && x == pirate_x && cell_name.equals("resurrect") && players_pirates[current_player] < 3) {
                    grid[y][x] = grid[y][x].substring(0, grid[y][x].length() - 13);
                    grid[y][x] += grid[y][x] += pirates[0] + 1 + "pirates_" + current_player + "pl_";
                }


                if ((!parse_name(cell_name).equals("blocked") && !parse_name(cell_name).equals("water") ||
                        parse_name(cell_name).equals("water") && parse_name(grid[pirate_y][pirate_x]).equals("water") &&
                                game_object.equals("")) &&
                        Math.abs(x - pirate_x) <= 1 && Math.abs(y - pirate_y) <= 1
                        && !(x == pirate_x && y == pirate_y)) {

                    Move(x, y, pirates, this);

                }

                else if (parse_name(cell_name).equals("water") &&
                        Math.abs(x - pirate_x) <= 1 && Math.abs(y - pirate_y) <= 1
                        && !(x == pirate_x && y == pirate_y) && !game_object.equals("")) {

                    BoardingOnAShip(y, x);
                    NextTurn();

                }

                else {

                    pirate_is_chosen = false;
                    dragging_coins = false;

                }

            }

        } else if (game_object.equals("ship") && pirates[1] == current_player) {

            ship_is_chosen = true;
            ship_x = x;
            ship_y = y;
            ship_pirates = pirates[0];
            ship_player = pirates[1];
            cell_pirates = pirates[0];

        } else if (pirates[0] > 0 && pirates[1] == current_player) {

            pirate_is_chosen = true;
            pirate_x = x;
            pirate_y = y;
            starting_x = x;
            starting_y = y;
            cell_pirates = pirates[0];
            pirate_player = pirates[1];

        }

    }

}
