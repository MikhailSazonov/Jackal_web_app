package web.Jackal;

import java.util.List;

public class cell_ {

    game_master gm;

    String type;

    int x;
    int y;

//  Далее следуют переменные, которые требуются для описания уже конкретных клеток (например, p_x, p_y - координаты точки,
//  куда пират попадёт после выстрела из пушки), но они описаны в родительском классе, чтобы было проще обращаться к ним
//  из функции Move() у gameobj_pirate


    int p_x;
    int p_y;
    int dir;

    boolean land = true;

    boolean airplane = false;

    boolean pirate_on_cell = false;
    List<gameobj_pirate> pirates;

    boolean ship_on_cell = false;
    gameobj_ship ship;

    int coins_on_cell = 0;

    boolean hidden = true;

    int number_of_moves;

    void Trigger(int[] last_movement) {}

    void Attack(int player_color, String cell_type, int point_if_stuck_) {
        for (gameobj_pirate p : pirates) {
            if (p.player.num != player_color && !cell_type.equals("Stuck") ||
                    cell_type.equals("Stuck") && p.point_if_stuck == point_if_stuck_) {
                if (!cell_type.equals("Water")) {
                    ++p.player.ship.pirates_amount;
                    if (p.carrying_coins)
                        ++coins_on_cell;
                }
                p.Death();
            }
        }
    }

}
