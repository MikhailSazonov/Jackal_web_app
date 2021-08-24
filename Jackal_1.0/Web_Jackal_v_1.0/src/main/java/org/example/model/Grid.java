package org.example.model;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class Grid {

    public String[][] grid_;

    public Cell[][] table;

    public Cell[][] getTable() {
        return table;
    }

    int[][] ship_coordinates;
    int[] ship_pirates_;

    public boolean[][] opened;

    {

        opened = new boolean[13][13];

        table = new Cell[13][13];

        for (int i = 0; i < 13; ++i) {
            for (int j = 0; j < 13; ++j) {
                table[i][j] = new Cell();
                opened[i][j] = true;
                table[i][j].Id = 13 * i + j;
                table[i][j].type = "water";
            }
        }

        grid_ = new String[13][13];

        ship_pirates_ = new int[]{3, 3};

        ship_coordinates = new int[][]{{6, 0}, {6, 12}};

        for (int i = 0; i <= 12; ++i) {
            grid_[0][i] = "x=" + i + "_y=0_water_0pirates_5pl_";
        }
        for (int i = 0; i <= 12; ++i) {
            grid_[i][0] = "x=0_y=" + i + "_water_0pirates_5pl_";
        }
        for (int i = 0; i <= 12; ++i) {
            grid_[12][i] = "x=" + i + "_y=12_water_0pirates_5pl_";
        }
        for (int i = 0; i <= 12; ++i) {
            grid_[i][12] = "x=12_y=" + i + "_water_0pirates_5pl_";
        }

        grid_[6][0] = grid_[6][0].substring(0, grid_[6][0].length()-13);
        grid_[6][12] = grid_[6][12].substring(0, grid_[6][12].length()-13);

        grid_[6][0] += "3pirates_0pl_ship_";
        grid_[6][12] += "3pirates_1pl_ship_";

        table[6][0].ship = true;
        table[6][0].ship_player = 0;
        table[6][0].pirates_count = 3;
        table[6][0].pirates_player = 0;

        table[6][12].ship = true;
        table[6][12].ship_player = 1;
        table[6][12].pirates_count = 3;
        table[6][12].pirates_player = 1;

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
            cells.add("lab$II_0pirates_5pl_$001_002$");
            ++k;
        }

        for (int i = 0; i < 3; ++i) {
            cells.add("lab$III_0pirates_5pl_$001_002_003$");
            ++k;
        }

        for (int i = 0; i < 2; ++i) {
            cells.add("lab$IV_0pirates_5pl_$001_002_003_004$");
            ++k;
        }

        for (int i = 0; i < 1; ++i) {
            cells.add("lab$V_0pirates_5pl_$001_002_003_004_005$");
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

        cells.add("leftcannon_0pirates_5pl_");
        ++k;

        cells.add("rightcannon_0pirates_5pl_");
        ++k;

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
                grid_[i][j] = "x=" + j + "_y=" + i + "_" + cells.get(k);
                opened[i][j] = false;
                table[i][j].type = "closed";
                ++k;
            }
        }


        for (int i = 0; i <= 12; ++i) {
            for (int j = 0; j <= 12; ++j) {
                table[i][j].Id = 13 * i + j;
            }
        }


    }

    public void TableUpdate() {
        for (int i = 0; i < 13; ++i) {
            for (int j = 0; j < 13; ++j) {

                String type = parse_name(grid_[i][j]);
                table[i][j].type = (opened[i][j] ? type : "closed");
                table[i][j].coins_left = coins_on_field;
                table[i][j].coins_1_player = players_coins[0];
                table[i][j].coins_2_player = players_coins[1];
                table[i][j].player_turn = current_player + 1;
                if (!type.startsWith("lab")) {
                    int[] pirates = parse_pirates(grid_[i][j]);
                    int k = 0;
                    for (int t = grid_[i][j].length() - 1; grid_[i][j].charAt(t) == '+'; --t) {
                        ++k;
                    }
                    table[i][j].pirates_count = pirates[0];
                    table[i][j].pirates_player = pirates[1];
                    table[i][j].coins = k;
                    if (type.startsWith("water")) {
                        if (grid_[i][j].endsWith("ship_")) {
                            table[i][j].ship = true;
                            table[i][j].ship_player = pirates[1];
                        } else {
                            table[i][j].ship = false;
                            table[i][j].ship_player = 5;
                        }
                    }
                } else {
                    List<String> parse_lab = ParseLab(grid_[i][j]);
                    if (parse_lab.size() >= 1) {
                        table[i][j].pirates_I_passage = parse_lab.get(0).charAt(0) - '0';
                        table[i][j].player_I_passage = parse_lab.get(0).charAt(1) - '0';
                        table[i][j].coins_I_passage = 0;
                        for (int k = 3; k < parse_lab.get(0).length(); ++k) {
                            ++table[i][j].coins_I_passage;
                        }
                    }
                    if (parse_lab.size() >= 2) {
                        table[i][j].pirates_II_passage = parse_lab.get(1).charAt(0) - '0';
                        table[i][j].player_II_passage = parse_lab.get(1).charAt(1) - '0';
                        table[i][j].coins_II_passage = 0;
                        for (int k = 3; k < parse_lab.get(1).length(); ++k) {
                            ++table[i][j].coins_II_passage;
                        }
                    }
                    if (parse_lab.size() >= 3) {
                        table[i][j].pirates_III_passage = parse_lab.get(2).charAt(0) - '0';
                        table[i][j].player_III_passage = parse_lab.get(2).charAt(1) - '0';
                        table[i][j].coins_III_passage = 0;
                        for (int k = 3; k < parse_lab.get(2).length(); ++k) {
                            ++table[i][j].coins_III_passage;
                        }
                    }
                    if (parse_lab.size() >= 4) {
                        table[i][j].pirates_IV_passage = parse_lab.get(3).charAt(0) - '0';
                        table[i][j].player_IV_passage = parse_lab.get(3).charAt(1) - '0';
                        table[i][j].coins_IV_passage = 0;
                        for (int k = 3; k < parse_lab.get(3).length(); ++k) {
                            ++table[i][j].coins_IV_passage;
                        }
                    }
                    if (parse_lab.size() >= 5) {
                        table[i][j].pirates_V_passage = parse_lab.get(4).charAt(0) - '0';
                        table[i][j].player_V_passage = parse_lab.get(4).charAt(1) - '0';
                        table[i][j].coins_V_passage = 0;
                        for (int k = 3; k < parse_lab.get(4).length(); ++k) {
                            ++table[i][j].coins_V_passage;
                        }
                    }
                }
            }
        }

    }

    String Event = "";

    public String[][] getGrid(){
        return grid_;
    }

    int[] players_pirates = new int[]{3, 3};

    boolean ship_is_chosen = false;
    int ship_x;
    int ship_y;
    int ship_pirates;
    int ship_player;

    boolean pirate_is_chosen = false;
    int pirate_x = -1;
    int pirate_y = -1;
    int starting_x;
    int starting_y;
    int cell_pirates;
    int pirate_player;


    boolean lab_is_chosen = false;
    int lab_chosen_cell = 0;
    int lab_x;
    int lab_y;
    List<String> parse_lab;
    String rest_lab_name;

    boolean airplane;
    boolean trapped;

    int turn = 1;
    int[][] forbidden = new int[13][13];

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
        if (dragging_coins && !parse_name(grid_[pirate_y][pirate_x]).equals("water") &&
                !parse_name(grid_[pirate_y][pirate_x]).startsWith("lab")) {
            grid_[pirate_y][pirate_x] += '+';
        }
        ship_is_chosen = false;
        pirate_is_chosen = false;
        dragging_coins = false;
        airplane = false;
        trapped = false;
        lab_is_chosen = false;
        Event = "";
        pirate_x = -1;
        pirate_y = -1;
        starting_x = -1;
        starting_y = -1;
        current_player = (current_player + 1) % 2;
        ++turn;
        lab_chosen_cell = 0;
        rest_lab_name = "";
    }

    void BoardingOnAShip(int y, int x, boolean ChangeCell) {
        if (y == ship_coordinates[current_player][0] && x == ship_coordinates[current_player][1]) {
            grid_[y][x] = grid_[y][x].substring(0, grid_[y][x].length() - 18);
            grid_[y][x] += ship_pirates_[current_player] + 1 + "pirates_" + current_player + "pl_ship_";
            ++ship_pirates_[current_player];
            if (dragging_coins) {
                ++players_coins[current_player];
                --coins_on_field;
                dragging_coins = false;
            }
        } else {
            --players_pirates[current_player];
        }

        if (ChangeCell) {

            int j = 0;
            for (int k = grid_[pirate_y][pirate_x].length() - 1; grid_[pirate_y][pirate_x].charAt(k) == '+'; --k) {
                ++j;
            }

            int[] pir = parse_pirates(grid_[pirate_y][pirate_x]);
            grid_[pirate_y][pirate_x] = grid_[pirate_y][pirate_x].substring(0, grid_[pirate_y][pirate_x].length() - 13 - j);
            int ppl = (pir[0] == 1 ? 5 : current_player);
            grid_[pirate_y][pirate_x] += pir[0] - 1 + "pirates_" + ppl + "pl_";
            for (int i = 0; i < j; ++i) {
                grid_[pirate_y][pirate_x] += '+';
            }
        }

    }

    boolean CheckAllies(int y, int x) {
        int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
        int[] dy = {1, 1, 0, -1, -1, -1, 0, 1};
        for (int i = 0; i < 8; ++i) {
            if (!parse_name(grid_[y+dy[i]][x+dx[i]]).equals("water")) {
                int[] pir = parse_pirates(grid_[y+dy[i]][x+dx[i]]);
                if (pir[0] > 0 && pir[1] == current_player) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean Move(int x, int y, int[] pirates, Grid grid) {


        String cell_type = parse_name(grid.grid_[y][x]);

        if (grid.dragging_coins && !opened[y][x]) {
            grid.pirate_is_chosen = false;
            grid.dragging_coins = false;
            grid.airplane = false;
            grid.trapped = false;
            grid.grid_[grid.pirate_y][grid.pirate_x] += '+';
            return false;
        }


        boolean gone_from_lab = false;
        boolean gone_to_lab = false;

        int[] new_pirates = new int[]{};

        int old__y_ = y;
        int old__x_ = x;


        if (!cell_type.startsWith("lab") && !parse_name(grid.grid_[pirate_y][pirate_x]).startsWith("lab")) {
            new_pirates = grid.parse_pirates(grid.grid_[y][x]);
        } else {
            if (!cell_type.startsWith("lab"))
                new_pirates = grid.parse_pirates(grid.grid_[y][x]);
            if (lab_is_chosen) {
                y = pirate_y;
                x = pirate_x;
            }
            parse_lab = ParseLab(grid.grid_[y][x]);
            rest_lab_name = "";
            int j = 0;
            while (grid.grid_[y][x].charAt(j) != '$') {
                rest_lab_name += grid.grid_[y][x].charAt(j);
                ++j;
            }
            rest_lab_name += '$';
            ++j;
            while (grid.grid_[y][x].charAt(j) != '$') {
                rest_lab_name += grid.grid_[y][x].charAt(j);
                ++j;
            }
            rest_lab_name += '$';
        }


        if (parse_name(grid.grid_[y][x]).startsWith("lab")) {
            if (lab_is_chosen && lab_chosen_cell < parse_lab.size()-1) {

                if (Integer.parseInt(Character.toString(parse_lab.get(lab_chosen_cell + 1).charAt(1))) != current_player &&
                        Integer.parseInt(Character.toString(parse_lab.get(lab_chosen_cell + 1).charAt(0))) > 0) {

                    if (!dragging_coins) {

                        int old_player = current_player;
                        current_player = (current_player + 1) % 2;
                        for (int i = 0; i < Integer.parseInt(Character.toString(parse_lab.get(lab_chosen_cell + 1).charAt(0))); ++i) {
                            BoardingOnAShip(ship_coordinates[current_player][0], ship_coordinates[current_player][1], false);
                        }
                        current_player = old_player;
                        parse_lab.set(lab_chosen_cell + 1, '1' + Integer.toString(current_player) +
                                (lab_chosen_cell + 2) + parse_lab.get(lab_chosen_cell + 1).substring(3));
                        int pir = Integer.parseInt(Character.toString(parse_lab.get(lab_chosen_cell).charAt(0)));
                        int pr = (pir > 1 ? current_player : 0);
                        parse_lab.set(lab_chosen_cell, Integer.toString(pir-1) + Integer.toString(pr) +
                                (lab_chosen_cell + 1) + parse_lab.get(lab_chosen_cell).substring(3));
                    } else {
                        return false;
                    }

                } else {
                    int pir = Integer.parseInt(Character.toString(parse_lab.get(lab_chosen_cell + 1).charAt(0)));
                    int pr = Integer.parseInt(Character.toString(parse_lab.get(lab_chosen_cell).charAt(0)));
                    int ppl = (pr == 1 ? 0 : current_player);
                    parse_lab.set(lab_chosen_cell + 1, (pir + 1) + Integer.toString(current_player) +
                            (lab_chosen_cell + 2) + parse_lab.get(lab_chosen_cell + 1).substring(3));
                    parse_lab.set(lab_chosen_cell, Integer.toString(pr - 1) + Integer.toString(ppl) +
                            Integer.toString(lab_chosen_cell + 1) + parse_lab.get(lab_chosen_cell).substring(3));
                }

                if (dragging_coins) {
                    parse_lab.set(lab_chosen_cell + 1, parse_lab.get(lab_chosen_cell + 1) + '+');
                }
                grid.grid_[y][x] = rest_lab_name;
                for (String i : parse_lab) {
                    grid.grid_[y][x] += i;
                    grid.grid_[y][x] += '_';
                }
                grid.grid_[y][x] = grid.grid_[y][x].substring(0, grid.grid_[y][x].length()-1);
                grid.grid_[y][x] += '$';
                NextTurn();
                return true;
            } else if (lab_is_chosen) {

                gone_from_lab = true;
                if (dragging_coins && parse_pirates(grid.grid_[old__y_][old__x_])[1] != current_player && parse_pirates(grid.grid_[old__y_][old__x_])[1] != 5) {
                    return false;
                }
                int pr = Integer.parseInt(Character.toString(parse_lab.get(lab_chosen_cell).charAt(0)));
                int ppl = (pr <= 1 ? 0 : current_player);
                parse_lab.set(lab_chosen_cell, Integer.toString(pr - 1) + Integer.toString(ppl) +
                        Integer.toString(lab_chosen_cell + 1) + parse_lab.get(lab_chosen_cell).substring(3));
                grid.grid_[y][x] = rest_lab_name;
                for (String i : parse_lab) {
                    grid.grid_[y][x] += i;
                    grid.grid_[y][x] += '_';
                }
                grid.grid_[y][x] = grid.grid_[y][x].substring(0, grid.grid_[y][x].length()-1);
                grid.grid_[y][x] += '$';

            }
            if ((!lab_is_chosen || lab_chosen_cell == parse_lab.size()-1) && parse_name(grid.grid_[old__y_][old__x_]).startsWith("lab")) {

                int pir = Integer.parseInt(Character.toString(parse_lab.get(lab_chosen_cell).charAt(0)));
                int pl = Integer.parseInt(Character.toString(parse_lab.get(lab_chosen_cell).charAt(1)));
                if (pl != current_player && pir > 0) {
                    if (dragging_coins)
                        return false;
                    int old_player = current_player;
                    current_player = (current_player + 1) % 2;
                    for (int k = 0; k < pir; ++k)
                        BoardingOnAShip(ship_coordinates[current_player][0], ship_coordinates[current_player][1], false);
                    current_player = old_player;
                    pir = 0;
                }
                parse_lab.set(0, (pir + 1) + Integer.toString(current_player) +
                        1 + parse_lab.get(lab_chosen_cell + 1).substring(3));
                gone_to_lab = true;
                grid.grid_[y][x] = rest_lab_name;
                if (dragging_coins) {
                    parse_lab.set(0, parse_lab.get(0) + '+');
                }
                for (String i : parse_lab) {
                    grid.grid_[y][x] += i;
                    grid.grid_[y][x] += '_';
                }
                grid.grid_[y][x] = grid.grid_[y][x].substring(0, grid.grid_[y][x].length()-1);
                grid.grid_[y][x] += '$';


            }
        }

        x = old__x_;
        y = old__y_;

        if (forbidden[pirate_y][pirate_x] == turn) {
            return false;
        }

        if (trapped && !CheckAllies(pirate_y, pirate_x)) {
            return false;
        }

        if (parse_name(grid.grid_[pirate_y][pirate_x]).equals("rightcannon") ||
                parse_name(grid.grid_[pirate_y][pirate_x]).equals("leftcannon") ||
                !cell_type.equals("blocked") && !cell_type.equals("water") ||
                cell_type.equals("water") && parse_name(grid.grid_[pirate_y][pirate_x]).equals("water") ||
                grid.ship_coordinates[current_player][0] == y && grid.ship_coordinates[current_player][1] == x ||
                grid.ship_coordinates[(current_player + 1) % 2][0] == y && grid.ship_coordinates[(current_player + 1) % 2][1] == x) {

            if (grid.ship_coordinates[current_player][0] == y && grid.ship_coordinates[current_player][1] == x ||
                    grid.ship_coordinates[(current_player + 1) % 2][0] == y && grid.ship_coordinates[(current_player + 1) % 2][1] == x) {
                BoardingOnAShip(y, x, true);
                opened[y][x] = true;
                if (parse_name(grid.grid_[pirate_y][pirate_x]).equals("croc")) {
                    NextTurn();
                }
                return true;
            }

            boolean attack = false;

            if (!cell_type.startsWith("lab") && new_pirates[1] != grid.current_player && new_pirates[1] != 5 && !dragging_coins && !cell_type.equals("fortress")) {

                attack = true;

                grid.current_player = (grid.current_player + 1) % 2;
                int old_y = grid.pirate_y;
                int old_x = grid.pirate_x;
                grid.pirate_y = y;
                grid.pirate_x = x;
                while (grid.parse_pirates(grid.grid_[y][x])[0] > 0) {
                    int old_cell_pirates = grid.cell_pirates;
                    grid.cell_pirates = grid.parse_pirates(grid.grid_[y][x])[0];
                    if (!grid.parse_name(grid.grid_[y][x]).equals("water"))
                        grid.BoardingOnAShip(grid.ship_coordinates[grid.current_player][0], grid.ship_coordinates[grid.current_player][1], true);
                    else {
                        grid.grid_[grid.pirate_y][grid.pirate_x] = grid.grid_[grid.pirate_y][grid.pirate_x].substring(0, grid.grid_[grid.pirate_y][grid.pirate_x].length() - 13);
                        int ppl = (grid.cell_pirates == 1 ? 5 : grid.current_player);
                        grid.grid_[grid.pirate_y][grid.pirate_x] += grid.cell_pirates - 1 + "pirates_" + ppl + "pl_";
                        --players_pirates[current_player];
                    }
                    grid.cell_pirates = old_cell_pirates;
                }

                grid.current_player = (grid.current_player + 1) % 2;
                grid.pirate_y = old_y;
                grid.pirate_x = old_x;

            } else if (!cell_type.startsWith("lab") && new_pirates[1] != grid.current_player && new_pirates[1] != 5 && (dragging_coins || cell_type.equals("fortress"))) {
                return false;
            }


            int j = 0;
            for (int k = grid.grid_[pirate_y][pirate_x].length() - 1; grid.grid_[pirate_y][pirate_x].charAt(k) == '+'; --k) {
                ++j;
            }
            boolean ship = false;
            if (grid.grid_[pirate_y][pirate_x].startsWith("ship", grid.grid_[pirate_y][pirate_x].length() - 5)) {
                j += 5;
                ship = true;
            }

            if (!gone_from_lab) {
                int ppr = parse_pirates(grid.grid_[pirate_y][pirate_x])[0];
                grid.grid_[pirate_y][pirate_x] = grid.grid_[pirate_y][pirate_x].substring(0, grid.grid_[pirate_y][pirate_x].length()-13-j);
                int ppl = (ppr <= 1 ? 5 : current_player);
                grid.grid_[pirate_y][pirate_x] += ppr - 1 + "pirates_" + ppl + "pl_";
            }


            int q = 0;
            if (!gone_to_lab) {
                for (int k = grid.grid_[y][x].length() - 1; grid.grid_[y][x].charAt(k) == '+'; --k) {
                    ++q;
                }
                grid.grid_[y][x] = grid.grid_[y][x].substring(0, grid.grid_[y][x].length() - 13 - q);
                if (!attack)
                    grid.grid_[y][x] += pirates[0] + 1 + "pirates_" + grid.current_player + "pl_";
                else
                    grid.grid_[y][x] += 1 + "pirates_" + grid.current_player + "pl_";
            }
            if (ship) {
                grid.grid_[grid.pirate_y][grid.pirate_x] += "ship_";
                j -= 5;
            }
            if (!gone_from_lab) {
                for (int i = 0; i < j; ++i) {
                    grid.grid_[grid.pirate_y][grid.pirate_x] += '+';
                }
            }

            if (!gone_to_lab) {
                for (int i = 0; i < q; ++i) {
                    grid.grid_[y][x] += '+';
                }
            }
            int old_x_ = pirate_x;
            int old_y_ = pirate_y;
            pirate_x = x;
            pirate_y = y;

            ChooseCellType(cell_type, x, y, pirates, old_x_, old_y_);
            opened[y][x] = true;
            return true;
        }
        return false;
    }

    public void MakeEmpty(int y, int x) {
        String cell_coordinates = "";
        int i = 0;
        int j = 0;
        while (i < 2) {
            cell_coordinates += grid_[y][x].charAt(j);
            ++j;
            if (grid_[y][x].charAt(j) == '_')
                ++i;
        }
        while (i < 3) {
            ++j;
            if (grid_[y][x].charAt(j) == '_')
                ++i;
        }
        grid_[y][x] = cell_coordinates + "_empty" + grid_[y][x].substring(j);

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

            case ("fortress") :

            case ("resurrect") : {
                cell_fortress.fortress_event(this);
                NextTurn();
                break;
            }

            case ("croc") : {
                cell_croc.croc_event(old_x, old_y, this, pirates, cell_type);
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

            case ("leftcannon") : {
                cell_leftcannon.leftcannon_event(x, y, this, pirates);
                break;
            }

            case ("rightcannon") : {
                cell_rightcannon.rightcannon_event(x, y, this, pirates);
                break;
            }

            case ("rum$barrel") : {
                cell_rum$barrel.rum$barrel_event(x, y, this);
            }

            default:
                NextTurn();

        }

    }

    List<String> ParseLab(String cell_name) {
        List<String> parse_lab = new ArrayList<>();
        int i = cell_name.length()-2;
        String new_ = "";
        while (cell_name.charAt(i) != '$') {
            new_ += cell_name.charAt(i);
            --i;
            if (cell_name.charAt(i) == '_') {
                String add = new StringBuffer(new_).reverse().toString();
                parse_lab.add(add);
                new_ = "";
                --i;
            }
        }
        String add = new StringBuffer(new_).reverse().toString();
        parse_lab.add(add);
        List<String> p_lab = new ArrayList<>();
        for (int j = parse_lab.size()-1; j >= 0; --j) {
            p_lab.add(parse_lab.get(j));
        }
        return p_lab;
    }


    void SetLabName(String cell_name, List<String> parse_) {
        int j = 0;
        int k = 0;
        while (k != 2) {
            ++j;
            if (cell_name.charAt(j) == '$')
                ++k;
        }
        ++j;
        grid_[lab_x][lab_y] = grid_[lab_x][lab_y].substring(0, j);
        for (String t : parse_) {
            grid_[lab_x][lab_y] += t + '_';
        }
        grid_[lab_x][lab_y] += '$';
    }

    boolean LabParsePirate(String lab_cell) {

        if (Integer.parseInt(Character.toString(lab_cell.charAt(0))) > 0 &&
                    Integer.parseInt(Character.toString(lab_cell.charAt(1))) == current_player) {
            return true;
        }

        return false;

    }


    public void GameUpdate(String cell_name) {

        if (cell_name.length() > 100) {

            int t = 9;

            String x_ = "";
            while (cell_name.charAt(t) >= '0' && cell_name.charAt(t) <= '9') {
                x_ += cell_name.charAt(t);
                ++t;
            }
            while (cell_name.charAt(t) != 'D') {
                ++t;
            }
            ++t;
            String y_ = "";
            while (cell_name.charAt(t) >= '0' && cell_name.charAt(t) <= '9') {
                y_ += cell_name.charAt(t);
                ++t;
            }

            int x__ = Integer.parseInt(x_);
            int y__ = Integer.parseInt(y_);

            cell_name = Integer.toString(y__ * 13 + x__);

        }

        if (cell_name.charAt(0) >= '0' && cell_name.charAt(0) <= '9') {
            int coord = Integer.parseInt(cell_name);
            cell_name = grid_[coord / 13][coord % 13];
        }

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

        int x = Integer.parseInt(x_coord);
        int y = Integer.parseInt(y_coord);


        int[] pirates = new int[0];
        if (!cell_type.startsWith("lab")) {
            pirates = parse_pirates(cell_name);
        }
        if (i < cell_name.length())
            ++i;

        switch (Event) {
            case ("arrow") : {
                cell_arrow.arrow_event(x, y, pirates, this, cell_type);
                TableUpdate();
                return;
            }

            case ("horse") : {
                cell_horse.horse_event(x, y, this, cell_type, pirates);
                TableUpdate();
                return;
            }

        }

        String game_object = "";

        if (grid_[y][x].substring(grid_[y][x].length()-5, grid_[y][x].length()-1).equals("ship")) {
            game_object = "ship";
        }

        if (ship_is_chosen || pirate_is_chosen || lab_is_chosen) {
            if (ship_is_chosen) {

                if (parse_name(cell_name).equals("water") &&
                        ((Math.abs(x - ship_x) <= 1 && ship_y == y) ||
                        (Math.abs(y - ship_y) <= 1 && ship_x == x))
                        && !(x == ship_x && y == ship_y) &&
                        !(x == 0 && y == 0) && !(x == 0 && y == 12) && !(x == 12 && y == 0) && !(x == 12 && y == 12)) {
                    grid_[ship_y][ship_x] = grid_[ship_y][ship_x].substring(0, grid_[ship_y][ship_x].length() - 18);
                    grid_[ship_y][ship_x] += "0pirates_5pl_";
                    int[] new_pirates = parse_pirates(grid_[y][x]);
                    if (new_pirates[0] > 0) {
                        int old_player = current_player;
                        current_player = new_pirates[1];
                        while (parse_pirates(grid_[y][x])[0] > 0) {
                            pirate_y = y;
                            pirate_x = x;
                            BoardingOnAShip(ship_y, ship_x, true);
                        }
                        current_player = old_player;
                    }
                    grid_[y][x] = grid_[y][x].substring(0, grid_[ship_y][ship_x].length()-13);
                    grid_[y][x] += ship_pirates + "pirates_" + current_player + "pl_ship_";
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


                }

                else {

                    ship_is_chosen = false;

                }

            } else if (pirate_is_chosen) {

                if (parse_name(grid_[pirate_y][pirate_x]).startsWith("lab")) {

                    if (y == pirate_y && x == pirate_x && parse_lab.get(lab_chosen_cell).length() > 3) {
                        dragging_coins = true;
                        String s = parse_lab.get(lab_chosen_cell);
                        parse_lab.set(lab_chosen_cell, s.substring(0, s.length() - 1));
                        rest_lab_name = "";
                        int j = 0;
                        while (grid_[y][x].charAt(j) != '$') {
                            rest_lab_name += grid_[y][x].charAt(j);
                            ++j;
                        }
                        rest_lab_name += '$';
                        ++j;
                        while (grid_[y][x].charAt(j) != '$') {
                            rest_lab_name += grid_[y][x].charAt(j);
                            ++j;
                        }
                        rest_lab_name += '$';
                        grid_[y][x] = rest_lab_name;
                        for (String e : parse_lab) {
                            grid_[y][x] += e;
                            grid_[y][x] += '_';
                        }
                        grid_[y][x] = grid_[y][x].substring(0, grid_[y][x].length()-1);
                        grid_[y][x] += '$';
                    }

                    else if (y == pirate_y && x == pirate_x && dragging_coins) {
                        dragging_coins = false;
                        String s = parse_lab.get(lab_chosen_cell);
                        parse_lab.set(lab_chosen_cell, s + '+');
                        rest_lab_name = "";
                        int j = 0;
                        while (grid_[y][x].charAt(j) != '$') {
                            rest_lab_name += grid_[y][x].charAt(j);
                            ++j;
                        }
                        rest_lab_name += '$';
                        ++j;
                        while (grid_[y][x].charAt(j) != '$') {
                            rest_lab_name += grid_[y][x].charAt(j);
                            ++j;
                        }
                        rest_lab_name += '$';
                        grid_[y][x] = rest_lab_name;
                        for (String e : parse_lab) {
                            grid_[y][x] += e;
                            grid_[y][x] += '_';
                        }
                        grid_[y][x] = grid_[y][x].substring(0, grid_[y][x].length()-1);
                        grid_[y][x] += '$';
                    }

                    else if (Math.abs(x - pirate_x) <= 1 && Math.abs(y - pirate_y) <= 1 && !(x == pirate_x && y == pirate_y)) {

                        Move(x, y, pirates, this);

                    } else {

                        pirate_is_chosen = false;
                        dragging_coins = false;

                    }

                }

                else {

                    if (y == pirate_y && x == pirate_x && cell_name.charAt(cell_name.length() - 1) == '+') {
                        dragging_coins = true;
                        grid_[y][x] = grid_[y][x].substring(0, grid_[y][x].length() - 1);
                        TableUpdate();
                        return;
                    } else if (y == pirate_y && x == pirate_x && dragging_coins) {
                        dragging_coins = false;
                        grid_[y][x] += "+";
                        TableUpdate();
                        return;
                    } else if (y == pirate_y && x == pirate_x && cell_type.equals("resurrect") && players_pirates[current_player] < 3) {
                        grid_[y][x] = grid_[y][x].substring(0, grid_[y][x].length() - 13);
                        grid_[y][x] += pirates[0] + 1 + "pirates_" + current_player + "pl_";
                        ++players_pirates[current_player];
                        NextTurn();
                        TableUpdate();
                        return;
                    }


                    if ((!cell_type.equals("blocked") && !cell_type.equals("water") ||
                            cell_type.equals("water") && parse_name(grid_[pirate_y][pirate_x]).equals("water") &&
                                    game_object.equals("")) &&
                            (Math.abs(x - pirate_x) <= 1 && Math.abs(y - pirate_y) <= 1 || airplane)
                            && !(x == pirate_x && y == pirate_y)) {

                        int p_x = pirate_x;
                        int p_y = pirate_y;
                        boolean move = Move(x, y, pirates, this);
                        if (move && parse_name(grid_[p_y][p_x]).equals("airplane")) {
                            MakeEmpty(p_y, p_x);
                        }

                    } else if (parse_name(cell_name).equals("water") &&
                            Math.abs(x - pirate_x) <= 1 && Math.abs(y - pirate_y) <= 1
                            && !(x == pirate_x && y == pirate_y) && !game_object.equals("")) {

                        BoardingOnAShip(y, x, true);
                        NextTurn();

                    } else {

                        pirate_is_chosen = false;
                        if (dragging_coins) {
                            grid_[pirate_y][pirate_x] += "+";
                        }
                        dragging_coins = false;
                        airplane = false;
                        trapped = false;

                    }
                }

            } else {

                if (Math.abs(x - lab_x) <= 1 && Math.abs(y - lab_y) <= 1 && !(x == lab_x && y == lab_y)) {
                    lab_chosen_cell = (lab_chosen_cell + 1) % parse_lab.size();
                } else if (x == lab_x && y == lab_y) {

                    if (LabParsePirate(parse_lab.get(lab_chosen_cell))) {
                        pirate_is_chosen = true;
                        pirate_x = x;
                        pirate_y = y;
                    }

                } else {

                    lab_is_chosen = false;

                }

            }

        } else if (game_object.equals("ship") && pirates[1] == current_player) {

            ship_is_chosen = true;
            ship_x = x;
            ship_y = y;
            ship_pirates = pirates[0];
            ship_player = pirates[1];
            cell_pirates = pirates[0];

        }  else if (cell_type.startsWith("lab")) {

            lab_is_chosen = true;
            lab_x = x;
            lab_y = y;
            parse_lab = ParseLab(cell_name);

        }  else if (!Arrays.equals(pirates, new int[]{}) && pirates[0] > 0 && pirates[1] == current_player) {

            pirate_is_chosen = true;
            pirate_x = x;
            pirate_y = y;
            starting_x = x;
            starting_y = y;
            cell_pirates = pirates[0];
            pirate_player = pirates[1];

            if (cell_type.equals("airplane")) {
                airplane = true;
            }

            if (cell_type.equals("trap")) {
                trapped = true;
            }

        }

        TableUpdate();

    }

}
