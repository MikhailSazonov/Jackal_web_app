package org.example.DAO;
import org.example.model.Player;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class PlayerDAO {
    private List<Player> players;
    {
            players = new ArrayList<>();

            Player p1 = new Player();
            p1.Name = "Player 1";
            p1.Id = 1;
            players.add(p1);
            Player p2 = new Player();
            p2.Name = "Player 2";
            p2.Id = 2;
            players.add(p2);
    }

    public List<Player> getPlayers(){
        return players;
    }

    public Player getId(int id){
        for(int i = 0; i < players.size(); i++){
            if(players.get(i).Id == id){
                return players.get(i);
            }
        }
        return null;
    }
}
