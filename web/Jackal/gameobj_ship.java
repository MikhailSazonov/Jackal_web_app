package web.Jackal;

public class gameobj_ship extends gameobj_{

    int x, y;
    player player;
    int pirates_amount = 3;

    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};

    void GetOnBoard(gameobj_pirate NewPirateOnBoard) {
        if (NewPirateOnBoard.player.num == this.player.num) {
            if (NewPirateOnBoard.carrying_coins) {
                ++this.player.coins_count;
                --gm.coins_on_field;
            }
            ++pirates_amount;
        }
        NewPirateOnBoard.Death();
    }

    void GetOffBoard() {
        if (pirates_amount > 0) {
            --pirates_amount;
            int i = 0;
            while (!gm.field[x+dx[i]][y+dy[i]].land) {
                ++i;
            }
            gm.Revive(x+dx[i], y+dy[i]);
        }
    }

    boolean Move(int new_x, int new_y) {
        if (!gm.field[new_x][new_y].land && Math.abs(new_x - x) == 0 || Math.abs(new_y - y) == 0 &&
                Math.abs(new_x - x) <= 1 && Math.abs(new_y - y) <= 1) {
            x = new_x;
            y = new_y;
            gm.field[x][y].Trigger(new int[]{x, y});
            return true;
        }
        return false;
    }

}
