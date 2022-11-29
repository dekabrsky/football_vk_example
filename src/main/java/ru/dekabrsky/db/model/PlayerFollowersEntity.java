package ru.dekabrsky.db.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "playersFollowers")
public class PlayerFollowersEntity {
    public static final String NAME_COLUMN = "name";

    @DatabaseField(generatedId = true)
    private long playerId;

    @DatabaseField(canBeNull = false)
    private String name;

    @DatabaseField()
    private int followersCount;

    public PlayerFollowersEntity() { }

    public PlayerFollowersEntity(String name, int followersCount) {
        this.followersCount = followersCount;
        this.name = name;
    }

    @Override
    public String toString() {
        return "PlayerFollowersEntity{" +
                "playerId=" + playerId +
                ", name='" + name + '\'' +
                ", followersCount=" + followersCount +
                '}';
    }
}
