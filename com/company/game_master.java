package com.company;

// Планируется, что с помощью этого класса будет осуществляться взаимодействие игрока с игровыми объектами и будут
// происходить некоторые события, например, смерть пирата, его воскрешение, сход пирата с корабля

public class game_master {

    cell_[][] field;

    int current_player = 1;

    void Death(gameobj_pirate pirate) {
        pirate.x = 12 + pirate.player.num;
        pirate.y = 0;
    }

    void Revive(player current_player, int x, int y) {

    }
}
