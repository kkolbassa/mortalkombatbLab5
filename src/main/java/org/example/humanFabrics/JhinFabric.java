package org.example.humanFabrics;

import org.example.players.Human;
import org.example.players.Player;

/**
 * Класс для создания определенного персонажа игрока
 */
public class JhinFabric implements IHumanFabric{
    @Override
    public Player create(int location) {
        return new Human(0,60,18,1, location, "Jhin");
    }
}
