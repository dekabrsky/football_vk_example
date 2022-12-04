package ru.dekabrsky.db.mapper;

import ru.dekabrsky.db.model.PlayerEntity;
import ru.dekabrsky.models.Player;

public class PlayerFromDbMapper {
    public static Player map(PlayerEntity player) {
        return new Player(player.getName(), player.getTeam(), player.getFollowersCount());
    }
}