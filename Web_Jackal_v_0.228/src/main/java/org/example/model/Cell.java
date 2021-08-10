package org.example.model;

public class Cell {


    public int Id = 0;

    public int pirates_count = 0;
    public int pirates_player = 5;
    public String type = "";
    public boolean ship = false;
    public int ship_player = 5;
    public int coins = 0;

    public int pirates_I_passage = 0;
    public int player_I_passage = 0;
    public int coins_I_passage = 0;

    public int pirates_II_passage = 0;
    public int player_II_passage = 0;
    public int coins_II_passage = 0;

    public int pirates_III_passage = 0;
    public int player_III_passage = 0;
    public int coins_III_passage = 0;

    public int pirates_IV_passage = 0;
    public int player_IV_passage = 0;
    public int coins_IV_passage = 0;

    public int pirates_V_passage = 0;
    public int player_V_passage = 0;
    public int coins_V_passage = 0;

    public void setId(int id){
        Id = id;
    }

    public int getId(){
        return Id;
    }


    public void setType(String s){
        type = s;
    }

    public String getType() {
        return type;
    }

    public Cell(int id, String Type){
        Id = id;
        type = Type;
    }

    Cell() {
        Id = 0;
        type = "";
    }

}
