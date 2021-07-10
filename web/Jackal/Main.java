package web.Jackal;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        game_master gm = new game_master();
        gm.field = new cell_[20][20];
        gm.field[0][0] = new cell_blocked();
        gm.field[0][12] = new cell_blocked();
        gm.field[12][0] = new cell_blocked();
        gm.field[12][12] = new cell_blocked();

        int players = 4;

        for (int i = 1; i <= 11; ++i) {
            gm.field[0][i] = new cell__water();
        }
        for (int i = 1; i <= 11; ++i) {
            gm.field[i][0] = new cell__water();
        }
        for (int i = 1; i <= 11; ++i) {
            gm.field[12][i] = new cell__water();
        }
        for (int i = 1; i <= 11; ++i) {
            gm.field[i][12] = new cell__water();
        }

        List<cell_> cells = new ArrayList<>();

        int k = 0;

        for (int i = 0; i < 3; ++i) {
            cells.add(new cell_arrow_right());
            ++k;
        }

        for (int i = 0; i < 3; ++i) {
            cells.add(new cell_arrow_right_diagonal());
            ++k;
        }

        for (int i = 0; i < 3; ++i) {
            cells.add(new cell_arrow_right_left());
            ++k;
        }

        for (int i = 0; i < 3; ++i) {
            cells.add(new cell_arrow_right_left_diagonal());
            ++k;
        }

        for (int i = 0; i < 3; ++i) {
            cells.add(new cell_arrow_up_down_left_right());
            ++k;
        }

        for (int i = 0; i < 3; ++i) {
            cells.add(new cell_arrow_up_down_left_right_diagonal());
            ++k;
        }

        for (int i = 0; i < 3; ++i) {
            cells.add(new cell_arrow_up_right_left_upper_diagonal());
            ++k;
        }

        for (int i = 0; i < 2; ++i) {
            cells.add(new cell_horse());
            ++k;
        }

        for (int i = 0; i < 6; ++i) {
            cells.add(new cell_ice());
            ++k;
        }

        for (int i = 0; i < 4; ++i) {
            cells.add(new cell_alligator());
            ++k;
        }

        cells.add(new cell_cannibal());
        ++k;

        for (int i = 0; i < 2; ++i) {
            cells.add(new cell_fortress());
            ++k;
        }

        for (int i = 0; i < 5; ++i) {
            cells.add(new cell_chest_I());
            ++k;
        }

        for (int i = 0; i < 5; ++i) {
            cells.add(new cell_chest_II());
            ++k;
        }

        for (int i = 0; i < 3; ++i) {
            cells.add(new cell_chest_III());
            ++k;
        }

        for (int i = 0; i < 2; ++i) {
            cells.add(new cell_chest_IV());
            ++k;
        }

        for (int i = 0; i < 1; ++i) {
            cells.add(new cell_chest_V());
            ++k;
        }

        cells.add(new cell_airplane());
        ++k;

        cells.add(new cell_aboriginal());
        ++k;

        for (int i = 0; i < 2; ++i) {
            cells.add(new cell_aircraft());
            ++k;
        }

        for (int i = 0; i < 2; ++i) {
            cells.add(new cell_cannon());
            ++k;
        }

        for (int i = 0; i < 4; ++i) {
            cells.add(new cell_rum_barrel());
            ++k;
        }

        for (int i = 0; i < 4; ++i) {
            cells.add(new cell_cave());
            ++k;
        }

        cells.add(new cell_earthquake());
        ++k;

        while (k < 121) {
            cells.add(new cell_empty());
            ++k;
        }

        java.util.Collections.shuffle(cells);

        k = 0;
        for (int i = 1; i <= 11; ++i) {
            for (int j = 1; j <= 11; ++j) {
                gm.field[i][j] = cells.get(k);
                if (cells.get(k).type.equals("cannon")) {
                    gm.field[i][j].dir = (int) (Math.random() * 4) + 1;
                    if (gm.field[i][j].dir == 1) {
                        gm.field[i][j].p_x = 0;
                        gm.field[i][j].p_y = j;
                    }
                    if (gm.field[i][j].dir == 2) {
                        gm.field[i][j].p_x = i;
                        gm.field[i][j].p_y = 12;
                    }
                    if (gm.field[i][j].dir == 3) {
                        gm.field[i][j].p_x = 12;
                        gm.field[i][j].p_y = j;
                    }
                    if (gm.field[i][j].dir == 4) {
                        gm.field[i][j].p_x = i;
                        gm.field[i][j].p_y = 0;
                    }
                }
                ++k;
            }
        }

        gm.field[0][6].ship_on_cell = true;
        gm.field[0][6].ship = new gameobj_ship();
        gm.field[0][6].ship.player.num = 0;

        gm.field[12][6].ship_on_cell = true;
        gm.field[12][6].ship = new gameobj_ship();
        gm.field[12][6].ship.player.num = 1;

        gm.field[6][0].ship_on_cell = true;
        gm.field[6][0].ship = new gameobj_ship();
        gm.field[6][0].ship.player.num = 2;

        gm.field[6][12].ship_on_cell = true;
        gm.field[6][12].ship = new gameobj_ship();
        gm.field[6][12].ship.player.num = 3;


        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 3; ++j) {
                gm.field[15+i][j].pirate_on_cell = true;
                gm.field[15+i][j].pirates.add(new gameobj_pirate(gm));
            }
        }


        gm.players_count = 4;
        gm.current_player_num = 0;


        while (gm.coins_on_field > 0) {
            int o_o = 0;
        }

        int max = 0;
        int winner = 0;
        int cnt = 0;
        for (int i = 0; i < 4; ++i) {

            if (gm.players.get(i).coins_count > max) {
                winner = i;
                cnt = 1;
            }

            else if (gm.players.get(i).coins_count == max) {
                ++cnt;
            }

        }

        if (cnt == 1)
            System.out.println("Player " + winner + " wins!");
        else
            System.out.println("Draw!");

    }

}
