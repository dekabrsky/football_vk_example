package ru.dekabrsky.visualisation.drawer;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleInsets;
import ru.dekabrsky.models.Player;
import ru.dekabrsky.visualisation.mapper.ChartDataMapper;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PieChartDrawer extends JFrame {

    public PieChartDrawer(String title, ArrayList<Player> playerList) {
        super(title);
        setContentPane(createPlayersByTeamsPanel(playerList));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(600, 300);
    }

    public static JPanel createPlayersByTeamsPanel(ArrayList<Player> playerList)
    {
        JFreeChart chart = createPieChart(ChartDataMapper.createPlayerByTeamsDataset(playerList));
        chart.setPadding(new RectangleInsets(4, 8, 2, 2));
        return new ChartPanel(chart);
    }

    private static JFreeChart createPieChart(PieDataset dataset) {
        JFreeChart chart = ChartFactory.createPieChart(
                "FIFA2022 players by clubs",  // chart title
                dataset,             // data
                false,               // no legend
                true,                // tooltips
                false                // no URL generation
        );

        chart.setBackgroundPaint(
                new GradientPaint(
                        new Point(0, 0),
                        new Color(20, 20, 20),
                        new Point(400, 200),
                        Color.DARK_GRAY
                )
        );

        TextTitle t = chart.getTitle();
        t.setHorizontalAlignment(HorizontalAlignment.LEFT);
        t.setPaint(new Color(240, 240, 240));
        t.setFont(new Font("Arial", Font.BOLD, 26));
        t.setText("FIFA2022 players by clubs");

        return chart;
    }

}
