package ru.dekabrsky.db;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import ru.dekabrsky.db.model.PlayerEntity;
import ru.dekabrsky.models.Player;

import java.sql.SQLException;
import java.util.List;

public class DbOrmRepository {
    private final String URL = "jdbc:sqlite:C:/sqlite/db/test.db";

    private ConnectionSource connectionSource = null;

    private Dao<PlayerEntity, String> playerDao = null;

    public void connect() throws SQLException {
        connectionSource = new JdbcConnectionSource(URL);

        playerDao = DaoManager.createDao(connectionSource, PlayerEntity.class);
    }

    public void createTable() throws SQLException {
        TableUtils.createTable(connectionSource, PlayerEntity.class);
    }

    public void savePlayers(List<Player> players) throws SQLException {
        for (var player: players) {
            playerDao.create(new PlayerEntity(player.getName(), player.getFollowersCount(), player.getTeam()));
        }
    }

    public List<PlayerEntity> getPlayers() throws SQLException {
        return playerDao.queryForAll();
    }

    public List<PlayerEntity> getPlayersByName(String name) throws SQLException {
        return playerDao.queryBuilder()
                .where()
                .eq(PlayerEntity.NAME_COLUMN, name)
                .query();
    }

    public void close() throws Exception {
        connectionSource.close();
    }
}
