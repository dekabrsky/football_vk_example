package ru.dekabrsky;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import ru.dekabrsky.csvParser.CSVParser;
import ru.dekabrsky.db.DBRepository;
import ru.dekabrsky.db.DbOrmRepository;
import ru.dekabrsky.models.Report;
import ru.dekabrsky.vkApi.VkRepository;

import java.io.IOException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws Exception {
        // DBRepository.connect();

        Report report = CSVParser.getReportFromCSV("rawData/players.csv");
        var players = report.getPlayersList();
        VkRepository vk = new VkRepository();
        for (var player: players) {
            try {
                var group = vk.getMostPopularGroupByPlayer(player.getName());
                var membersCount = vk.getGroupMembersCount(group);
                player.setFollowersCount(membersCount);
                System.out.println("Количество подписчиков " + player.getName() + " " + membersCount);
            } catch (ApiException | ClientException e ) {
                e.printStackTrace();
            }
        }

//        DBRepository.createTablePlayers();
//        DBRepository.savePlayers(players);

        var dbOrm = new DbOrmRepository();
        dbOrm.connect();
        dbOrm.createTable();
        dbOrm.savePlayers(players);
        System.out.println(dbOrm.getPlayers());
        System.out.println(dbOrm.getPlayersByName("Karim Benzema"));
        dbOrm.close();
    }
}
