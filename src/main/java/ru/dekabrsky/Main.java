package ru.dekabrsky;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import ru.dekabrsky.csvParser.CSVParser;
import ru.dekabrsky.db.DBRepository;
import ru.dekabrsky.db.DbOrmRepository;
import ru.dekabrsky.db.mapper.PlayerFromDbMapper;
import ru.dekabrsky.models.Player;
import ru.dekabrsky.models.Report;
import ru.dekabrsky.visualisation.drawer.BarChartDrawer;
import ru.dekabrsky.visualisation.drawer.PieChartDrawer;
import ru.dekabrsky.vkApi.VkRepository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Report report = getReport();
        var players = report.getPlayersList();
        downloadFromVk(players);

        var dbOrm = new DbOrmRepository();
        var playersFromDb = new ArrayList<Player>();
        try {
            dbOrm.connect();
            saveToDb(players, dbOrm);
            playersFromDb = dbOrm.getPlayers()
                    .stream()
                    .map(PlayerFromDbMapper::map)
                    .collect(Collectors.toCollection(ArrayList::new));
            dbOrm.close();
        } catch (Exception throwable) {
            throwable.printStackTrace();
        }

        new PieChartDrawer("Players By Teams", playersFromDb).setVisible(true);
        new BarChartDrawer("Players Followers", playersFromDb).setVisible(true);
    }

    private static Report getReport() {
        Report report = null;
        try {
            report = CSVParser.getReportFromCSV("rawData/players.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return report;
    }

    private static void downloadFromVk(List<Player> players) {
        VkRepository vk = new VkRepository();
        for (var player: players) {
            try {
                var group = vk.getMostPopularGroupByPlayer(player.getName());
                Thread.sleep(500);
                var membersCount = vk.getGroupMembersCount(group);
                Thread.sleep(500);
                player.setFollowersCount(membersCount);
                System.out.println("Количество подписчиков " + player.getName() + " " + membersCount);
            } catch (ApiException | ClientException | InterruptedException e ) {
                e.printStackTrace();
            }
        }
    }

    private static void saveToDb(List<Player> players, DbOrmRepository dbOrm) throws SQLException {
        dbOrm.createTable();
        dbOrm.savePlayers(players);
    }
}
