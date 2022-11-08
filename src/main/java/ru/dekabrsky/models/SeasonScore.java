package ru.dekabrsky.models;

public class SeasonScore {
    private final int goals;
    private final int passes;

    public SeasonScore(int goals, int passes) {
        this.goals = goals;
        this.passes = passes;
    }

    public int getGoals() {
        return goals;
    }

    public int getPasses() {
        return passes;
    }
}
