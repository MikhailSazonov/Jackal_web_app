package org.example.controllers;
import com.google.gson.Gson;
import org.example.DAO.PlayerDAO;
import org.example.model.Grid;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@Controller
@RequestMapping("/jackal")
public class GameController {

    private final PlayerDAO playerDAO;

    private final Grid grid;

    public GameController(PlayerDAO playerDAO, Grid grid) {
        this.playerDAO = playerDAO;
        this.grid = grid;
    }

    @GetMapping("/game")
    public String gamePage(Model model) {
        model.addAttribute("players", playerDAO.getPlayers());
        model.addAttribute("grid", grid.getGrid());
        model.addAttribute("table", grid.getTable());
        return "gamef";
    }

//    @PostMapping("/game")
//    public String NewGamePage(@RequestParam Map<String, String> requestParams, Model model) {
//        model.addAttribute("players", playerDAO.getPlayers());
//        model.addAttribute("grid", grid.getGrid());
//        String value = requestParams.get("cell");
//        grid.GameUpdate(value);
//        for (int i = 0; i < 2; ++i) {
//            playerDAO.getPlayers().get(i).Money = grid.players_coins[i];
//        }
//        if (grid.coins_on_field == 0) {
//            if (playerDAO.getPlayers().get(0).Money > playerDAO.getPlayers().get(1).Money)
//                return "jackal/player1";
//            else
//                return "jackal/player2";
//        }
//        return "gamef";
//    }


    @PostMapping(value = "/game", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String NewGamePage_(
            @RequestBody String value,
            Model model, HttpServletResponse response) throws IOException {
        model.addAttribute("players", playerDAO.getPlayers());
        model.addAttribute("grid", grid.getGrid());
        response.setContentType("application/json");
        grid.GameUpdate(value);
        for (int i = 0; i < 2; ++i) {
            playerDAO.getPlayers().get(i).Money = grid.players_coins[i];
        }
        Gson g = new Gson();
        String str = g.toJson(grid.getTable());
        return str;
    }

    @GetMapping("/end")
    public String goodByePage(@RequestParam(value = "name", required = false) String name, Model model)
    {
        String Name = name;
        model.addAttribute("Name", name);
         System.out.println(Name);
        return "jackal/goodbye";
    }

    /*@PostMapping("/game")
    public String goodPage()
    {
        System.out.println("post");
        return "jackal/goodbye";
    }*/

    /*@PostMapping("/game")
    public String greetingSubmit(@ModelAttribute Move greeting, Model model) {
        model.addAttribute("greeting", greeting);
        return "result";
    }*/
}
