package ru.dekabrsky.visualisation.drawer;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.ui.RectangleInsets;
import ru.dekabrsky.models.Player;
import ru.dekabrsky.visualisation.mapper.ChartDataMapper;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BarChartDrawer extends JFrame {
    public BarChartDrawer(String title, ArrayList<Player> playerList) {
        super(title);
        setContentPane(createPlayersByTeamsPanel(playerList));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(600, 300);
    }

    public static JPanel createPlayersByTeamsPanel(ArrayList<Player> playerList)
    {
        JFreeChart chart = createBarChart(ChartDataMapper.createFollowersDataset(playerList));
        chart.setPadding(new RectangleInsets(4, 8, 2, 2));
        return new ChartPanel(chart);
    }

    private static JFreeChart createBarChart(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart(
                "Players Followers",
                "Player Name",                   // x-axis label
                "Followers Count",                // y-axis label
                dataset,
                PlotOrientation.HORIZONTAL,
                false,
                false,
                false
        );

        chart.addSubtitle(new TextTitle("Data from VK"));
        chart.setBackgroundPaint(Color.white);

        return chart;
    }
}
