package ru.dekabrsky.db;

import ru.dekabrsky.models.Player;

import java.sql.*;
import java.util.HashMap;
import java.util.List;

public class DBRepository {
    private static Connection conn = null;
    private static final String URL = "jdbc:sqlite:C:/sqlite/db/test.db";

    public static void connect() {
        try {
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static void createTablePlayers() {

        String sql = "CREATE TABLE IF NOT EXISTS players (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	name text NOT NULL,\n"
                + "	followersCount real\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table 'players' created.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void savePlayers(List<Player> players) {
        String sql = "INSERT INTO players(name,followersCount) VALUES(?,?)";

        for (var player : players) {
            try (Connection conn = DriverManager.getConnection(URL);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, player.getName());
                pstmt.setDouble(2, player.getFollowersCount());
                pstmt.executeUpdate();
                System.out.println("Player " + player.getName() + " added.");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static HashMap<String, Integer> getPlayers() {
        String sql = "SELECT name, followersCount FROM players";

        var res = new HashMap<String, Integer>();

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                res.put(rs.getString("name"), rs.getInt("followersCount"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return res;
    }
}
