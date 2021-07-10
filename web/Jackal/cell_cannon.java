package web.Jackal;

public class cell_cannon extends cell_ {

    cell_cannon() {
        type = "cannon";
    }

    void Trigger(int[] directions) {
        gm.current_pirate.Move(p_x, p_y);
        gm.field[p_x][p_y].Trigger(new int[]{0, 0});
        gm.NextTurn();
    }

}
