package org.example.humanFabrics;

import org.example.players.Human;
import org.example.players.Player;

public class KarinaFabric implements IHumanFabric{
    @Override
    public Player create(int location) {
        return new Human(0,80,16,1, location, "Karina");
    }
}
