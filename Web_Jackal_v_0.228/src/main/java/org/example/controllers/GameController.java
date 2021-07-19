package org.example.controllers;
import org.example.DAO.PlayerDAO;
import org.example.model.Grid;
import org.example.model.Move;
import org.example.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        return "jackal/gamefield";
    }

//    @GetMapping("/game")
//    public String NewGamePage() {
//        grid.grid[1][1] = "saddam";
//        return "jackal/gamefield";
//    }

    @PostMapping("/game")
    public String NewGamePage(@RequestParam Map<String, String> requestParams, Model model) {
        model.addAttribute("players", playerDAO.getPlayers());
        model.addAttribute("grid", grid.getGrid());
        String value = requestParams.get("cell");
        grid.GameUpdate(value);
        return "jackal/gamefield";
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
