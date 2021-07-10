package web.Jackal;

public class cell__water extends cell_ {

    boolean ship_on_cell = false;
    gameobj_ship ship;

    cell__water() {
        land = false;
        type = "water";
    }

    void Trigger(int[] directions) {
        if (ship_on_cell && pirate_on_cell) {
            while (pirates.size() > 0) {
                ship.GetOnBoard(gm.field[x][y].pirates.get(0));
            }
            pirate_on_cell = false;
        }
    }
}
