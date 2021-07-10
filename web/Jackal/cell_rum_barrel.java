package web.Jackal;

public class cell_rum_barrel extends cell_ {

    void Trigger(int[] directions) {
        gm.current_pirate.skip = gm.turn + 1;
    }

}
