import org.jfree.chart.*;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.*;
import oshi.SystemInfo;
import oshi.hardware.HWDiskStore;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class diskChart extends JFrame {

    TimeSeries readSeries = new TimeSeries("Total Read (MB/s)");
    TimeSeries writeSeries = new TimeSeries("Total Write (MB/s)");

    long lastRead = 0;
    long lastWrite = 0;
    boolean firstRun = true;

    public diskChart() {
        setTitle("Disk Read/Write Chart");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(readSeries);
        dataset.addSeries(writeSeries);

        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "Total Disk Read/Write", 
                "Time", 
                "MB/s", 
                dataset
        );

        XYPlot plot = chart.getXYPlot();
        plot.getRenderer().setSeriesPaint(0, Color.RED);
        plot.getRenderer().setSeriesPaint(1, Color.BLUE);

        setContentPane(new ChartPanel(chart));

        updatingInfo();
    }

    void updatingInfo() {
        SystemInfo si = new SystemInfo();
        java.util.List<HWDiskStore> disks = si.getHardware().getDiskStores();

        new Timer().scheduleAtFixedRate(new TimerTask() {
            public void run(){
            long currentRead = 0, currentWrite = 0;

                for (HWDiskStore d : disks) {
                    d.updateAttributes();
                    currentRead += d.getReadBytes();
                    currentWrite += d.getWriteBytes();
                }

                if (firstRun) {
                    lastRead = currentRead;
                    lastWrite = currentWrite;
                    firstRun = false;
                } else {
                    double readMB = (currentRead - lastRead) / 1024.0 / 1024.0;
                    double writeMB = (currentWrite - lastWrite) / 1024.0 / 1024.0;

                    readSeries.addOrUpdate(new Millisecond(), readMB);
                    writeSeries.addOrUpdate(new Millisecond(), writeMB);

                    lastRead = currentRead;
                    lastWrite = currentWrite;
                }
            }
        }, 1000, 1000);
    }
}
