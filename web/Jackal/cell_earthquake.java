package web.Jackal;

public class cell_earthquake extends cell_ {

    void Trigger(int[] directions) {
        if (hidden) {
            gm.Event = "earthquake";
        }
    }

    static void Event(int x_cell, int y_cell, int x, int y, game_master gm) {
        if (gm.cell_is_chosen && !gm.field[x][y].pirate_on_cell && gm.field[x][y].coins_on_cell == 0) {
            gm.field[20][0] = new cell_();
        }
    }

}
