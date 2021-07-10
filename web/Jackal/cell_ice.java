package web.Jackal;

public class cell_ice extends cell_ {

    void Trigger(int[] directions) {
        gm.Event = "ice";
    }

    public static void Event(int old_x, int old_y, int x, int y, game_master gm) {
        if (gm.field[old_x][old_y].equals(new cell_arrow_right()) ||
                gm.field[old_x][old_y].equals(new cell_arrow_right_diagonal()) ||
                gm.field[old_x][old_y].equals(new cell_arrow_right_left()) ||
                gm.field[old_x][old_y].equals(new cell_arrow_right_left_diagonal()) ||
                gm.field[old_x][old_y].equals(new cell_arrow_up_down_left_right()) ||
                gm.field[old_x][old_y].equals(new cell_arrow_up_down_left_right_diagonal()) ||
                gm.field[old_x][old_y].equals(new cell_arrow_up_right_left_upper_diagonal()) ||
                gm.field[old_x][old_y].equals(new cell_airplane()) ||
                gm.field[old_x][old_y].equals(new cell_horse())) {
            gm.field[old_x][old_y].Trigger(new int[]{x - old_x, y - old_y});
        } else {
            gm.LeftMouseClick(x + (x - old_x), y + (y - old_y));
        }
    }

}
