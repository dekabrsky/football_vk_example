package ru.dekabrsky.db.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "playersFollowers")
public class PlayerEntity {
    public static final String NAME_COLUMN = "name";

    @DatabaseField(generatedId = true)
    private long playerId;

    @DatabaseField(canBeNull = false)
    private String name;

    @DatabaseField()
    private int followersCount;

    @DatabaseField()
    private String team;

    public PlayerEntity() { }

    public PlayerEntity(String name, int followersCount, String team) {
        this.followersCount = followersCount;
        this.name = name;
        this.team = team;
    }

    public String getName() {
        return name;
    }

    public String getTeam() {
        return team;
    }

    public int getFollowersCount() {
        return followersCount;
    }

    @Override
    public String toString() {
        return "PlayerFollowersEntity{" +
                "playerId=" + playerId +
                ", name='" + name + '\'' +
                ", followersCount=" + followersCount +
                ", team='" + team + '\'' +
                '}';
    }
}
