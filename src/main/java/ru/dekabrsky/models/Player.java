package ru.dekabrsky.models;

import java.util.HashMap;

public class Player {
    private final String name;
    private String team;
    private double cost;
    private HashMap<String, SeasonScore> scores = new HashMap<>();

    public Player(String name, String team, double cost) {
        this.name = name;
        this.team = team;
        this.cost = cost;
    }

    //region getters & setters
    public String getName() {
        return name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public HashMap<String, SeasonScore> getScores() {
        return scores;
    }

    //endregion getters & setters

    public void addScore(String season, SeasonScore score) {
        scores.put(season, score);
    }

    public int getAllGoalsCount() {
        return scores.values().stream().mapToInt(SeasonScore::getGoals).sum();
    }

    @Override
    public String toString() {
        return this.name + " " + this.team + " " + this.cost + "млн евро";
    }
}
