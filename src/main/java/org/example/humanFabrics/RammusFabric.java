package org.example.humanFabrics;

import org.example.players.Human;
import org.example.players.Player;

/**
 * Класс для создания определенного персонажа игрока
 */
public class RammusFabric implements IHumanFabric{
    @Override
    public Player create(int location) {
       return new Human(0,120,10,1, location, "Rammus");
    }
}
