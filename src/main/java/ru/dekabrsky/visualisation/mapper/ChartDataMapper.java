package ru.dekabrsky.visualisation.mapper;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import ru.dekabrsky.models.Player;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ChartDataMapper {
    public static PieDataset createPlayerByTeamsDataset(List<Player> players) {

        var playersCountByTeams = players.stream()
                .collect(
                        Collectors.groupingBy(
                                Player::getTeam,
                                HashMap::new,
                                Collectors.counting()
                        )
                );

        DefaultPieDataset dataset = new DefaultPieDataset();

        playersCountByTeams.forEach(dataset::setValue);

        return dataset;
    }

    public static CategoryDataset createFollowersDataset(List<Player> players) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        players.forEach(p -> dataset.setValue(p.getFollowersCount(), "followersCount", p.getName()));
        return dataset;
    }
}
