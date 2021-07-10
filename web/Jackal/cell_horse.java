package web.Jackal;

public class cell_horse extends cell_ {

    void Trigger(int[] directions) {
        gm.Event = "horse";
    }

    public static void Event(int old_x, int old_y, int x, int y, game_master gm) {
        if (x - old_x == 2 && y - old_y == 1 ||
                x - old_x == 2 && y - old_y == -1 ||
                x - old_x == 1 && y - old_y == 1 ||
                x - old_x == 1 && y - old_y == -1 ||
                x - old_x == -2 && y - old_y == 1 ||
                x - old_x == -2 && y - old_y == -1 ||
                x - old_x == -1 && y - old_y == 1 ||
                x - old_x == -1 && y - old_y == -1) {
            gm.current_pirate.arrow = true;
            gm.Event = "";
            gm.LeftMouseClick(x, y);
        }
    }

}
