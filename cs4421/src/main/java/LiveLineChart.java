import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class LiveLineChart extends JFrame {

    private XYSeries series;
    private double lastX = 0;

    private static final String TITLE = "Live processor load Data Feed";
    private static final int DELAY_MS = 3000;

    public LiveLineChart(String title) {
        super(title);

        this.series = new XYSeries("Processor Load");
        XYSeriesCollection dataset = new XYSeriesCollection(this.series);

        JFreeChart chart = ChartFactory.createXYLineChart(
            TITLE,
            "Time (Seconds)",
            "cpu load %",
            dataset,
            PlotOrientation.VERTICAL,
            true,
            true,
            false
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));

        this.add(chartPanel, BorderLayout.CENTER);

        startDataFeed();
    }

    private void startDataFeed() {
        ActionListener task = event -> {
            lastX += 1.0;
            double yValue = CpuInfo.getProcessorLoad();
            series.add(lastX, yValue);
        };

        Timer timer = new Timer(DELAY_MS, task);
        timer.start();
    }
}


