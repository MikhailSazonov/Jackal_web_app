package web.Jackal;

public class cell_arrow_up_down_left_right_diagonal extends cell_ {

    void Trigger(int[] directions) {
        gm.Event = "arrow_up_down_left_right_diagonal";
    }

    public static void Event(int old_x, int old_y, int x, int y, game_master gm) {
        if (x - old_x == 1 && y - old_y == 1 || x - old_x == -1 && y - old_y == 1 ||
                x - old_x == 1 && y - old_y == -1 || x - old_x == -1 && y - old_y == -1) {
            gm.current_pirate.arrow = true;
            gm.LeftMouseClick(x, y);
            gm.Event = "";
            gm.NextTurn();
        }
    }

}
