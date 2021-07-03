package com.company;

public class gameobj_pirate extends gameobj_ {

    game_master gm;
    player player;

    gameobj_pirate(game_master gm_) {
        gm = gm_;
    }

    void Move(int new_x, int new_y) {
        x = new_x;
        y = new_y;
    }

    void Death() {
        gm.Death(this);
    }

}
