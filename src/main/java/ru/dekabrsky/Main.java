package ru.dekabrsky;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import ru.dekabrsky.csvParser.CSVParser;
import ru.dekabrsky.db.DBRepository;
import ru.dekabrsky.models.Report;
import ru.dekabrsky.vkApi.VkRepository;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        DBRepository.connect();

        // Загрузка данных в БД

        Report report = CSVParser.getReportFromCSV("rawData/players.csv");
        var players = report.getPlayersList();
        VkRepository vk = new VkRepository();
        for (var player: players) {
            try {
                var group = vk.getMostPopularGroupByPlayer(player.getName());
                var membersCount = vk.getGroupMembersCount(group);
                player.setFollowersCount(membersCount);
                System.out.println("Количество подписчиков " + player.getName() + " " + membersCount);
            } catch (ClientException | ApiException e) {
                e.printStackTrace();
            }
        }

        DBRepository.createTablePlayers();
        DBRepository.savePlayers(players);

        // Выгрузка данных из БД

        System.out.println(DBRepository.getPlayers());
    }
}
