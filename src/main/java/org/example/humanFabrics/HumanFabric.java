package org.example.humanFabrics;

import org.example.players.Player;

/**
 * Класс для создания персонажа игрока
 */
public class HumanFabric {
    /**
     * Класс для создания персонажа игрока
     * @param name имя создаваемого персонажа
     * @param location кол-во локаций
     * @return персонаж
     */
    public Player create(String name, int location) {
        IHumanFabric fabric = null;

        switch (name) {
            case "Jhin":
                fabric = new JhinFabric();
                break;
            case "Karina":
                fabric = new KarinaFabric();
                break;
            case "Rammus":
                fabric = new RammusFabric();
                break;
        }

        return fabric.create(location);
    }
}
