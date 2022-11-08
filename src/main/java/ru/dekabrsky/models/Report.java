package ru.dekabrsky.models;

import java.util.ArrayList;
import java.util.List;

public class Report {
    private final ArrayList<Player> players;

    public Report(ArrayList<Player> players) {
        this.players = players;
    }

    public int getGoalsForPlayer(String name) {
        return players.stream()
                .filter(player -> player.getName().equals(name))
                .findFirst()
                .orElseThrow()
                .getAllGoalsCount();
    }

    public List<Player> getPlayersList() {
        return players;
    }
}
