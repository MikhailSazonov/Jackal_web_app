package org.example.model;

import org.springframework.stereotype.Component;

public class Player {
    public String Name;
    public int Id;
    public int Money;

    public String getName(){
        return Name;
    }

    public int getId(){ return Id; }

    public int getMoney(){
        return Money;
    }

    }
