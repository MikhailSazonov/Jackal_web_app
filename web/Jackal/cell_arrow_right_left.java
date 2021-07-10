package web.Jackal;

public class cell_arrow_right_left extends cell_ {

    void Trigger(int[] directions) {
        gm.Event = "arrow_right_left";
    }

    public static void Event(int old_x, int old_y, int x, int y, game_master gm) {
        if (x - old_x == 1 && y - old_y == 0 || x - old_x == -1 && y - old_y == 0) {
            gm.current_pirate.arrow = true;
            gm.LeftMouseClick(x, y);
            gm.Event = "";
            gm.NextTurn();
        }
    }

}
