/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.enemyFabrics;

import org.example.players.Player;
import org.example.players.ShaoKahn;

/**
 * Класс для создания  определенного вражеского персонажа (босса)
 * @author Мария
 */
public class ShaoKahnFabric implements EnemyFabricInterface {
    
    @Override
    public Player create(int i) {
        Player enemy = new ShaoKahn(2, 100, 30, 1);
        return enemy;
    }
}
