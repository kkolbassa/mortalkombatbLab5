/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.players;

import java.util.ArrayList;

/**
 * Класс для описания игрока
 * @author Мария
 */
public class Player {
    
    private int level;
    private int health;
    private int maxHealth;
    private int damage;
    private int attack;
    private ArrayList<Integer> strategy;
    
    public Player(int level, int health, int damage, int attack){
        this.level=level;
        this.health=health;
        this.damage=damage;
        this.attack=attack;
        this.maxHealth =health;
        this.strategy = new ArrayList<>();
    }
   
    public void setLevel(){
        this.level++;
    }
    public void setHealth(int h){
        this.health+=h;
    }
    public void setNewHealth(int h){
        this.health=h;
    }
    public void setDamage(int d){
        this.damage+=d;
    }
    public void setAttack(int a){
        this.attack=a;
    }
    public void addStrategy(int a){
        strategy.add(a);
    }
    public void setNullStrategy(){
        strategy.clear();
    }

    public ArrayList<Integer> getStrategy() {
        return strategy;
    }

    public void setMaxHealth(int h){
        this.maxHealth +=h;
    }
    
    public int getLevel(){
        return this.level;
    }
    public int getHealth(){
        return this.health;
    }
    public int getDamage(){
        return this.damage;
    }
    public int getAttack(){
        return this.attack;
    }
    public int getMaxHealth(){
        return this.maxHealth;
    }
    
    public String getName(){
        return "";
    }
    
}
