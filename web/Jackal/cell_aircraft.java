package web.Jackal;

public class cell_aircraft extends cell_ {

    void Trigger(int[] directions) {
        gameobj_pirate NewPirateOnBoard = gm.current_pirate;
        if (NewPirateOnBoard.carrying_coins)
            ++gm.current_pirate.player.coins_count;
        ++gm.current_pirate.player.ship.pirates_amount;
        NewPirateOnBoard.Death();
    }

}
