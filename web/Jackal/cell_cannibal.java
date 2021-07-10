package web.Jackal;

public class cell_cannibal extends cell_ {

    void Trigger(int[] directions) {
        gm.current_pirate.Death();
        --gm.coins_on_field;
        gm.NextTurn();
    }

}
