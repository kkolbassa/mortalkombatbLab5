package org.example.humanFabrics;

import org.example.players.Player;

/**
 * Интерфейс для создания персонажа игрока
 */
public interface IHumanFabric {
    Player create(int location);
}
