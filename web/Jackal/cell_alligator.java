package web.Jackal;

public class cell_alligator extends cell_ {

    void Trigger(int[] directions) {
        gm.LeftMouseClick(gm.current_pirate.x - directions[0], gm.current_pirate.y - directions[1]);
        gm.NextTurn();
    }

}
