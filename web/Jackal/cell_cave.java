package web.Jackal;

public class cell_cave extends cell_ {

    void Trigger(int[] directions) {
        if (hidden) {
            global_vars.cave_directions.add(new int[]{x, y});
        }
        if (!global_vars.pirates_in_cave.empty() && global_vars.pirates_in_cave.get(0).player.num != gm.cur_player.num) {
            gm.LeftMouseClick(gm.current_pirate.x - directions[0], gm.current_pirate.y - directions[1]);
        } else {
            for (int[] d : global_vars.cave_directions) {
                if (d[0] != x || d[1] != y && !gm.field[d[0]][d[1]].pirate_on_cell ||
                        (gm.field[d[0]][d[1]].pirate_on_cell && gm.field[d[0]][d[1]].pirates.get(0).player.num == gm.current_player_num)) {
                    gm.Event = "cave";
                    return;
                }
            }
            global_vars.pirates_in_cave.add(gm.current_pirate);
            gm.current_pirate.Death();
        }
    }

    static void Event(int x, int y, game_master gm) {
        for (int[] d : global_vars.cave_directions) {
            if (d[0] != x || d[1] != y && !gm.field[d[0]][d[1]].pirate_on_cell ||
                    (gm.field[d[0]][d[1]].pirate_on_cell && gm.field[d[0]][d[1]].pirates.get(0).player.num == gm.current_player_num)) {
                gm.LeftMouseClick(d[0], d[1]);
                gm.Event = "";
            }
        }
    }
}
