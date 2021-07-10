package web.Jackal;

// Планируется, что с помощью этого класса будет осуществляться взаимодействие игрока с игровыми объектами и будут
// происходить некоторые события, например, смерть пирата, его воскрешение, сход пирата с корабля

import java.util.List;

public class game_master {

    cell_[][] field;

    List<player> players;

    player cur_player;

    int current_player_num = 0;
    int players_count = 4;

    int coins_on_field = 37;

    String Event;

    gameobj_pirate current_pirate;
    boolean pirate_is_chosen = false;

    gameobj_ship current_ship;
    boolean ship_is_chosen = false;


    boolean cell_is_chosen = false;
    int x_cell = 0;
    int y_cell = 0;



    boolean movement = false;

    int turn = 0;


    void NextTurn() {

        if (current_pirate.carrying_coins) {
            if (current_pirate.in_fortress || !field[current_pirate.x][current_pirate.y].land) {
                current_pirate.carrying_coins = false;
            } else {
                --field[current_pirate.old_x][current_pirate.old_y].coins_on_cell;
            }
        }

        current_pirate.old_x = 0;
        current_pirate.old_y = 0;
        current_pirate.airplane = true;
        movement = false;
        pirate_is_chosen = false;
        ship_is_chosen = false;
        current_pirate.arrow = false;
        current_player_num = (current_player_num + 1) % players_count;
        cur_player = players.get(current_player_num);
        if (current_player_num == 0)
            ++turn;
    }


    void Revive(int x, int y) {
        if (field[15 + current_player_num][3 - cur_player.pirates_count].pirate_on_cell) {
            gameobj_pirate rev_pirate = field[15 + current_player_num][3 - cur_player.pirates_count].pirates.get(0);
            field[15 + current_player_num][3 - cur_player.pirates_count].pirate_on_cell = false;
            rev_pirate.x = x;
            rev_pirate.y = y;
            ++cur_player.pirates_count;
        }
    }



    void LeftMouseClick(int x, int y) {

        switch (Event) {
            case ("arrow_right") -> cell_arrow_right.Event(current_pirate.x, current_pirate.y, x, y, this);

            case ("arrow_right_diagonal") -> cell_arrow_right_diagonal.Event(current_pirate.x, current_pirate.y, x, y, this);

            case ("arrow_right_left") -> cell_arrow_right_left.Event(current_pirate.x, current_pirate.y, x, y, this);

            case ("arrow_right_left_diagonal") -> cell_arrow_right_left_diagonal.Event(current_pirate.x, current_pirate.y, x, y, this);

            case ("arrow_up_down_left_right") -> cell_arrow_up_down_left_right.Event(current_pirate.x, current_pirate.y, x, y, this);

            case ("arrow_up_down_left_right_diagonal") -> cell_arrow_up_down_left_right_diagonal.Event(current_pirate.x, current_pirate.y, x, y, this);

            case ("arrow_up_right_left_upper_diagonal") -> cell_arrow_up_right_left_upper_diagonal.Event(current_pirate.x, current_pirate.y, x, y, this);

            case ("cave") -> cell_cave.Event(x, y, this);

            case ("airplane") -> cell_airplane.Event(x, y, this);

            case ("earthquake") -> cell_earthquake.Event(x_cell, y_cell, x, y, this);

            case ("horse") -> cell_horse.Event(current_pirate.x, current_pirate.y, x, y, this);

            case ("ice") -> cell_ice.Event(current_pirate.x, current_pirate.y, x, y, this);
        }

        if (!ship_is_chosen && !pirate_is_chosen) {

            if (field[x][y].ship_on_cell &&
                    field[x][y].ship.player.num == current_player_num) {
                ship_is_chosen = true;
                current_ship = field[x][y].ship;

            } else if (field[x][y].pirate_on_cell &&
                    field[x][y].pirates.get(0).player.num == current_player_num &&
                    field[x][y].pirates.get(0).skip != turn) {
                pirate_is_chosen = true;
                current_pirate = field[x][y].pirates.get(0);
            }

        } else {

            if (pirate_is_chosen) {

                if (x == current_pirate.x && y == current_pirate.y) {
                    if (field[x][y].type.equals("aboriginal") && cur_player.pirates_count < 3) {
                        Revive(x, y);
                        NextTurn();
                    }
                    if (current_pirate.carrying_coins) {
                        ++field[x][y].coins_on_cell;
                        current_pirate.carrying_coins = false;
                        return;
                    } else if (field[x][y].coins_on_cell > 0) {
                        --field[x][y].coins_on_cell;
                        current_pirate.carrying_coins = true;
                        return;
                    }
                }

                boolean move = current_pirate.Move(x, y);
                if (move && Event.equals("")) {
                    NextTurn();
                }


            } else {
                boolean move = current_ship.Move(x, y);
                if (move)
                    NextTurn();
            }
        }


    }




    void RightMouseClick() {
        if (Event.equals("")) {
            if (pirate_is_chosen) {
                pirate_is_chosen = false;
            }
            if (ship_is_chosen) {
                ship_is_chosen = false;
            }
        } else if (Event.equals("airplane")) {
            Event = "";
            NextTurn();
        }
    }

}
