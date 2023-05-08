/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.players;

import java.util.ArrayList;

/**
 *
 * @author Мария
 */
public class Human extends Player {
    

    private int points;
    private int experience;
    private int win;
    private int localRound;
    private int nextExperience;
    private  int location;
    private String name;


    public Human(int level, int health, int  damage, int attack, int location, String name){
        super (level, health, damage, attack);
        this.points=0;
        this.experience=0;
        this.nextExperience =40;
        this.win=0;
        this.localRound = 0;
        this.location = location;
        this.name = name;
    }

    public int getLocation() {
        return location;
    }
    public void setLocation(int location) {
        this.location = location;
    }

    public int getPoints(){
        return this.points;
    }
    public int getExperience(){
        return this.experience;
    }
    public int getNextExperience(){
        return this.nextExperience;
    }
    public int getWin(){
        return this.win;
    }
    public int getLocalRound(){
        return this.localRound;
    }

    public void setPoints(int p){
        this.points+=p;
    }
    public void setExperience(int e){
        this.experience+=e;
    }
    public void setNextExperience(int e){
        this.nextExperience =e;
    }
    public void setWin(){
        this.win++;
    }
    public void setLocalRound(){
        this.localRound++;
    }
    public void setLocalRoundNull(){
        this.localRound = 0;
    }

    @Override
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    
}
