package ru.dekabrsky.csvParser;

import ru.dekabrsky.models.Player;
import ru.dekabrsky.models.Report;
import ru.dekabrsky.models.SeasonScore;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class CSVParser {

    public static Report getReportFromCSV(String path) throws IOException {
        Scanner sc = getScanner(path);
        var headers = sc.nextLine().split(";");
        ArrayList<String> seasons = mapSeasons(headers);
        sc.nextLine();
        var players = new ArrayList<Player>();
        while (sc.hasNextLine()) {
            var playerParts = sc.nextLine().split(";");
            players.add(mapPlayer(seasons, playerParts));
        }
        return new Report(players);
    }

    private static Scanner getScanner(String path) throws IOException {
        String content = Files.readString(Path.of(path), StandardCharsets.UTF_8);
        return new Scanner(content);
    }

    private static ArrayList<String> mapSeasons(String[] headers) {
        var seasons = new ArrayList<String>();
        for (int i = 3; i < headers.length; i += 2) {
            seasons.add(headers[i]);
        }
        return seasons;
    }

    private static Player mapPlayer(ArrayList<String> seasons, String[] playerParts) {
        var name = playerParts[0];
        var team = playerParts[1];
        var cost = Double.parseDouble(playerParts[2]);
        Player player = new Player(name, team, cost);
        for (int i = 3; i < playerParts.length; i += 2) {
            player.addScore(seasons.get((i - 3) / 2), mapScore(playerParts, i));
        }
        return player;
    }

    private static SeasonScore mapScore(String[] playerParts, int i) {
        var goals = Integer.parseInt(playerParts[i]);
        var passes = Integer.parseInt(playerParts[i + 1]);
        return new SeasonScore(goals, passes);
    }

}
