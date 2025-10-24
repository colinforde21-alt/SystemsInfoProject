package com.example; 

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import java.util.concurrent.TimeUnit;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;




public class CpuInfo  {

    static SystemInfo si = new SystemInfo();
    static CentralProcessor processor = si.getHardware().getProcessor();

    public static void main(String[] args) {
        System.out.println("--- CS4421 Hardware Reporter (OSHI Demonstration) ---");

        
        presentSystemDetails();
        
            
    
    }
    

    public static double getProcessorLoad()  {
        try {
            long[] prevTicks = processor.getSystemCpuLoadTicks();
            
            TimeUnit.SECONDS.sleep(1); 
            
            double cpuLoad = processor.getSystemCpuLoadBetweenTicks(prevTicks) * 100;
            return cpuLoad;
        } catch (InterruptedException e) {
            return 0.0;
        }
    }
    
 
    private static void presentSystemDetails()  {

        
        String model = si.getHardware().getComputerSystem().getModel();
        //CentralProcessor processer = si.getHardware().getProcessor();

        System.out.println("Model: " + model);
        System.out.println("OS: " + si.getOperatingSystem());
        
        System.out.println("\n--- CPU Cores ---");
        System.out.println("Processor Name: " + processor.getProcessorIdentifier().getName());
        System.out.println("Physical Cores: " + processor.getPhysicalProcessorCount());
        System.out.println("Logical Cores (Threads): " + processor.getLogicalProcessorCount());
        
        SwingUtilities.invokeLater(() -> {
            LiveLineChart app = new LiveLineChart("JFreeChart Live Data Example");
            app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            app.pack();
            app.setLocationRelativeTo(null); // Center on screen
            app.setVisible(true);
        });

        }

    }


class LiveLineChart  extends JFrame  {

    
    private XYSeries series;
    private double lastX = 0;
    
    private static final String TITLE = "Live processor load Data Feed";
    private static final int DELAY_MS = 3000; 
    private static final int MAX_DATA_POINTS = 500;

    public LiveLineChart(String title)  {
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

        // 4. Start the Data Feeding Timer
        startDataFeed();
    }

    private void startDataFeed()  {
        
        ActionListener task = event -> {
            
            lastX += 1.0; 
            double yValue = CpuInfo.getProcessorLoad(); 

            
            series.add(lastX, yValue);

            
        };

        Timer timer = new Timer(DELAY_MS, task);
        //timer.setInitialDelay(0); 
        timer.start();
    }
}

