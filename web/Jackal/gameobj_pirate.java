package web.Jackal;

public class gameobj_pirate extends gameobj_ {

    game_master gm;

    boolean carrying_coins = false;
    boolean carrying_treasure = false;

    boolean in_fortress = false;

    boolean airplane = false;

    boolean arrow = false;

    boolean stuck = false;

    int point_if_stuck = 0;

    int old_x = 0;
    int old_y = 0;

    int skip = 0;

    gameobj_pirate(game_master gm_) {
        gm = gm_;
    }

    public gameobj_pirate(int num) {
        player.num = num;
    }

    boolean Move(int new_x, int new_y) {

        if (!gm.field[x][y].airplane && (Math.abs(new_x - x) >= 1 || Math.abs(new_y - y) >= 1))
            return false;

        if (gm.field[x][y].type.equals("Stuck") && point_if_stuck < gm.field[x][y].number_of_moves) {
            if (!carrying_coins)
                gm.field[new_x][new_y].Attack(player.num, gm.field[new_x][new_y].type, point_if_stuck);
            else {
                for (gameobj_pirate p : gm.field[x][y].pirates) {
                    if (p.player.num != player.num) {
                        return false;
                    }
                }
            }
            ++point_if_stuck;
            return true;
        }

        if (gm.field[new_x][new_y].equals(new cell_blocked())) {
            return false;
        }
        if (gm.turn == skip)
            return false;
        if (gm.field[new_x][new_y].land == gm.field[x][y].land || arrow) {
            if (carrying_coins && !gm.movement) {
                if (!gm.field[new_x][new_y].hidden &&
                        (gm.field[new_x][new_y].pirates.isEmpty() || gm.field[new_x][new_y].pirates.get(0).player == player)) {
                    if (gm.field[new_x][new_y].land != gm.field[x][y].land) {
                        --gm.coins_on_field;
                    }
                    old_x = x;
                    old_y = y;
                    x = new_x;
                    y = new_y;
                    gm.field[new_x][new_y].pirates.add(this);
                    ++gm.field[x][y].coins_on_cell;
                    if (gm.field[x][y].airplane) {
                        gm.field[x][y] = new cell_empty();
                        gm.current_pirate.airplane = true;
                    }
                    gm.movement = true;
                    gm.field[x][y].pirate_on_cell = true;
                    gm.field[x][y].pirates.add(gm.current_pirate);
                    gm.field[old_x][old_y].pirates.remove(0);
                    if (gm.field[old_x][old_y].pirates.isEmpty())
                        gm.field[old_x][old_y].pirate_on_cell = false;
                    gm.field[x][y].Trigger(new int[]{x - old_x, y - old_y});
                    point_if_stuck = 0;
                    return true;
                }
                return false;
            }
            old_x = x;
            old_y = y;
            x = new_x;
            y = new_y;
            gm.field[new_x][new_y].pirate_on_cell = true;
            gm.field[new_x][new_y].pirates.add(this);
            gm.field[new_x][new_y].Attack(player.num, gm.field[new_x][new_y].type, 0);
            if (gm.field[x][y].airplane) {
                gm.field[x][y] = new cell_empty();
                gm.current_pirate.airplane = true;
            }
            gm.movement = true;
            gm.field[x][y].pirate_on_cell = true;
            gm.field[x][y].pirates.add(gm.current_pirate);
            gm.field[old_x][old_y].pirates.remove(0);
            if (gm.field[old_x][old_y].pirates.isEmpty())
                gm.field[old_x][old_y].pirate_on_cell = false;
            gm.field[x][y].Trigger(new int[]{x - old_x, y - old_y});
            point_if_stuck = 0;
            return true;
        }
        return false;
    }

    void Death() {
        gm.field[x][y].pirates.remove(0);
        if (gm.field[x][y].pirates.isEmpty())
            gm.field[x][y].pirate_on_cell = false;
        x = 15 + player.num;
        y = 3 - player.pirates_count;
    }

}
