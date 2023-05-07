package org.example.humanFabrics;

import org.example.players.Player;

public class HumanFabric {
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
