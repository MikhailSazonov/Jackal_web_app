package web.Jackal;

public class cell_fortress extends cell_ {

    String type = "Fortress";

    void Trigger(int[] directions) {
        if (gm.current_pirate.carrying_coins || this.pirates.get(0).player != gm.cur_player) {
            if (gm.current_pirate.carrying_coins) {
                ++gm.field[x - directions[0]][y - directions[1]].coins_on_cell;
                gm.current_pirate.carrying_coins = false;
                gm.current_pirate.in_fortress = true;
            }
            if (this.pirates.get(0).player.num != gm.cur_player.num) {
                if (!(gm.current_pirate.arrow || gm.current_pirate.airplane)) {
                    gm.LeftMouseClick(gm.current_pirate.x - directions[0], gm.current_pirate.y - directions[1]);
                } else {
                    gm.current_pirate.Death();
                    gm.NextTurn();
                }
            }
        } else {
            gm.current_pirate.in_fortress = true;
        }
    }

}
