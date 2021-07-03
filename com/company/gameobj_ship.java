package com.company;

public class gameobj_ship extends gameobj_{

    int x, y;
    player player;
    int pirates_amount = 3;

    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};

    void GetOnBoard(gameobj_pirate NewPirateOnBoard) {
        if (NewPirateOnBoard.player.num == this.player.num)
            ++pirates_amount;
        NewPirateOnBoard.Death();
    }

    void GetOffBoard() {
        if (pirates_amount > 0) {
            --pirates_amount;
            int i = 0;
            while (!gm.field[x+dx[i]][y+dy[i]].land) {
                ++i;
            }
            gm.Revive(player, x+dx[i], y+dy[i]);
        }
    }
}
