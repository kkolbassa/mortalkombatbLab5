/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.enemyFabrics;

import org.example.players.Player;
import org.example.players.ShaoKahn;

/**
 *
 * @author Мария
 */
public class ShaoKahnFabric implements EnemyFabricInterface {
    
    @Override
    public Player create(int i) {
        Player enemy;
        if(i==0){
            enemy = new ShaoKahn(3, 100, 30, 1);
        }
        else{
           enemy = new ShaoKahn(3, 145, 44, 1); 
        }
        return enemy;
    }
}
