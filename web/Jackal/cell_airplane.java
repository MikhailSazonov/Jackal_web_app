package web.Jackal;

public class cell_airplane extends cell_ {

    cell_airplane() {
        airplane = true;
        type = "airplane";
    }

    static void Event(int x, int y, game_master gm) {
        if (!(x == gm.current_pirate.x && y == gm.current_pirate.y)) {
            boolean move = gm.current_pirate.Move(x, y);
            if (move) {
                gm.Event = "";
                gm.NextTurn();
            }
        }
    }

    void Trigger(int[] directions) {
        gm.Event = "airplane";
    }

}
