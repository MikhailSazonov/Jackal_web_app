package org.example.model;

public class Cell {
    public int Id;

    public void setId(int id){
        Id = id;
    }

    public int getId(){
        return Id;
    }

    String Type;

    public void setType(String s){
        Type = s;
    }

    public String getType() {
        return Type;
    }

    public Cell(int id, String type){
        Id = id;
        Type = type;
    }

    Cell() {
        Id = 0;
        Type = "";
    }

}
