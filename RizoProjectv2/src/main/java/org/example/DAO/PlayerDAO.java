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
            Player p3 = new Player();
            p3.Name = "Player 3";
            p3.Id = 3;
            players.add(p3);
            Player p4 = new Player();
            p4.Name = "Player 4";
            p4.Id = 4;
            players.add(p4);
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
