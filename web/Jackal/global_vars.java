package web.Jackal;

import java.util.List;
import java.util.Stack;

public class global_vars {
    static int[][] pirate_moves = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

    static int[][] ship_moves = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

    static Stack<gameobj_pirate> pirates_in_cave;

    static List<int[]> cave_directions;

}
