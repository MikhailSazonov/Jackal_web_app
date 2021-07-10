package web.Jackal;

public class cell_chest_I extends cell_ {
    void Trigger(int[] directions) {
        if (hidden)
            coins_on_cell += 1;
    }
}
