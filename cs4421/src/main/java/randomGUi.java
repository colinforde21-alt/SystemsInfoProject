
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.BoxLayout;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;




public class randomGUi {
    public static void main(String[] args) {
        System.setProperty("sun.java2d.uiScale", "2");
        homePage();
    }
    public static void homePage() {
        JFrame frame = new JFrame("Home Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);


        int buttonWidth = 100;
        int buttonHeight = 40;
        int spacing = 20;

        int startX = (frame.getWidth() - (3 * buttonWidth + 2 * spacing)) / 2;
        int startY = (frame.getHeight() - (2 * buttonHeight + spacing)) / 2;
        JPanel panel = new JPanel();
        JButton CPUbutton = new JButton("CPU Info");
        CPUbutton.setBounds(startX, startY, buttonWidth, buttonHeight);
        panel.add(CPUbutton);
        CPUbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                new showCpuInfo();

                frame.dispose();
            }
        });
        
        JButton diskButton = new JButton("Disk Info");
        diskButton.setBounds(startX + buttonWidth + spacing, startY, buttonWidth, buttonHeight);
        panel.add(diskButton);
        diskButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                new showDiskInfo();

                frame.dispose();
            }
        });
        JButton memButton = new JButton("Memory Info");
        memButton.setBounds(startX + 2 * (buttonWidth + spacing), startY, buttonWidth, buttonHeight);
        panel.add(memButton);
        memButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                new showMemInfo();

                frame.dispose();
            }
        });
        JButton pciButton = new JButton("PCI Info");
        pciButton.setBounds(startX + (buttonWidth + spacing) / 2, startY + buttonHeight + spacing, buttonWidth, buttonHeight);
        panel.add(pciButton);
        pciButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                new showPCIInfo();

                frame.dispose();
            }
        });
        JButton usbButton = new JButton("USB Info");
        usbButton.setBounds(startX + (buttonWidth + spacing) / 2 + buttonWidth + spacing, startY + buttonHeight + spacing, buttonWidth, buttonHeight);
        panel.add(usbButton);
        usbButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                new showUSBInfo();

                frame.dispose();
            }
        });
        JButton gpuButton = new JButton("GPU Info");
        gpuButton.setBounds(startX + (buttonWidth + spacing) / 2 + buttonWidth + spacing, startY + buttonHeight + spacing, buttonWidth, buttonHeight);
        panel.add(gpuButton);
        gpuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                new showGPUInfo();

                frame.dispose();
            }
        });
        JButton batteryButton = new JButton("Battery Info");
        batteryButton.setBounds(startX + (buttonWidth + spacing) / 2 + buttonWidth + spacing, startY + buttonHeight + spacing, buttonWidth, buttonHeight);
        panel.add(batteryButton);
        batteryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                new showBatteryInfo();
                frame.dispose();
            }
        });
        JButton displayButton = new JButton("Display Info");
        displayButton.setBounds(startX + (buttonWidth + spacing) / 2 + buttonWidth + spacing, startY + buttonHeight + spacing, buttonWidth, buttonHeight);
        panel.add(displayButton);
        displayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                new showDisplayInfo();
                frame.dispose();
            }
        });

        JButton motherboardButton = new JButton("Motherboard Info");
        motherboardButton.setBounds(startX + (buttonWidth + spacing) / 2 + buttonWidth + spacing, startY + buttonHeight + spacing, buttonWidth, buttonHeight);
        panel.add(motherboardButton);
        motherboardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                new displayMotherboardInfo();
                frame.dispose();
            }
        });
        frame.add(panel);
        frame.setVisible(true);

    }
}

class showCpuInfo {
    public showCpuInfo() {
        JFrame cpuFrame = new JFrame("CPU Information");
        cpuFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        cpuFrame.setSize(500, 400);
        JTextArea area = new JTextArea();
        area.setText(cpuInfo.getCpuSummary());
        JScrollPane scroll = new JScrollPane(area);
        

        JButton graphButton = new JButton("Show Graph");
        graphButton.setBounds(100, 100, 100, 40);
        cpuFrame.add(graphButton, java.awt.BorderLayout.SOUTH);
        cpuFrame.add(scroll);
        graphButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                SwingUtilities.invokeLater(() -> {
                    LiveLineChart app = new LiveLineChart("JFreeChart Live Data Example");
                    app.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    app.pack();
                    app.setLocationRelativeTo(null);
                    app.setVisible(true);
            
                });
            }
        });
        cpuFrame.setVisible(true);
    }
}

 class showMemInfo {
    private memInfo mem;
    private JLabel totalLabel, usedLabel, freeLabel, percentLabel;
    private MemoryBarPanel barPanel;

    public showMemInfo() {
        mem = new memInfo();

        JFrame memFrame = new JFrame("Memory Information");
        memFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        memFrame.setSize(400, 300);
        memFrame.setLayout(new BorderLayout());

        JPanel infoPanel = new JPanel(new GridLayout(4, 1));
        totalLabel = new JLabel();
        usedLabel = new JLabel();
        freeLabel = new JLabel();
        percentLabel = new JLabel();

        infoPanel.add(totalLabel);
        infoPanel.add(usedLabel);
        infoPanel.add(freeLabel);
        infoPanel.add(percentLabel);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> updateInfo());

        barPanel = new MemoryBarPanel();

        memFrame.add(infoPanel, BorderLayout.NORTH);
        memFrame.add(barPanel, BorderLayout.CENTER);
        memFrame.add(refreshButton, BorderLayout.SOUTH);

        updateInfo();

        memFrame.setVisible(true);
    }

    private void updateInfo() {
        try {
            mem.read(); // Loads the memory info
            totalLabel.setText("Total Memory: " + mem.getTotal() + " KB");
            usedLabel.setText("Used Memory: " + mem.getUsed() + " KB");
            freeLabel.setText("Free Memory: " + mem.getFree() + " KB");
            percentLabel.setText("Percent Used: " + String.format("%.2f%%", mem.getPercentUsed()));
        } catch (UnsatisfiedLinkError e) {
            // Fallback to Runtime if native library fails
            Runtime runtime = Runtime.getRuntime();
            long total = runtime.totalMemory() / 1024; // KB
            long free = runtime.freeMemory() / 1024;   // KB
            long max = runtime.maxMemory() / 1024;     // KB
            long used = total - free;
            double percent = (double) used / max * 100.0;
            totalLabel.setText("Total Memory: " + max + " KB (Fallback)");
            usedLabel.setText("Used Memory: " + used + " KB (Fallback)");
            freeLabel.setText("Free Memory: " + free + " KB (Fallback)");
            percentLabel.setText("Percent Used: " + String.format("%.2f%% (Fallback)", percent));
        }
        barPanel.repaint();
    }

    // Inner class for the horizontal bar graph
    class MemoryBarPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int width = getWidth() - 60;
            int height = 40;
            int x = 30, y = 30;
            long total = 0;
            long used = 0;
            try {
                total = mem.getTotal();
                used = mem.getUsed();
            } catch (UnsatisfiedLinkError e) {
                // Fallback values
                Runtime runtime = Runtime.getRuntime();
                total = runtime.maxMemory() / 1024;
                long free = runtime.freeMemory() / 1024;
                used = (runtime.totalMemory() / 1024) - free;
            }
            if (total == 0) return;
            int usedBarWidth = (int)((double)used/total * width);
            int freeBarWidth = width - usedBarWidth;

            // Used (red)
            g.setColor(Color.RED);
            g.fillRect(x, y, usedBarWidth, height);

            // Free (green)
            g.setColor(Color.GREEN);
            g.fillRect(x + usedBarWidth, y, freeBarWidth, height);

            // Border
            g.setColor(Color.BLACK);
            g.drawRect(x, y, width, height);
        }
    }
}


class showPCIInfo {
    public showPCIInfo() {
        JFrame pciFrame = new JFrame("PCI Information");
        pciFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pciFrame.setSize(400, 300);

        int pciCount = newPciInfo.getPCIBusCount();
        ArrayList<String> pciDetails = newPciInfo.getPCIInfo();
        JTextArea pciDetailsText = new JTextArea(); 
        StringBuilder detailsBuilder = new StringBuilder();
        for (String device : pciDetails) {
            detailsBuilder.append(device).append("\n");
        }
        pciDetailsText.append("Device has: " + pciCount + " PCI buses\n");
        pciDetailsText.append(detailsBuilder.toString());
        pciDetailsText.setEditable(false);


        JScrollPane scrollPane = new JScrollPane(pciDetailsText);
        pciFrame.add(scrollPane);
        pciFrame.setVisible(true);
        
    }
}

class showUSBInfo {
    public showUSBInfo() {
        JFrame usbFrame = new JFrame("USB Information");
        usbFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        usbFrame.setSize(400, 300);
        usbInfo usb = new usbInfo();
        usb.read();
        JTextArea usbInfoText = new JTextArea();
        usbInfoText.setText(usb.displayUSBInfo());
        usbInfoText.setEditable(false);
        
        usbFrame.setVisible(true);
    }
}

class showDiskInfo {
    public showDiskInfo() {
        diskInfo disk = new diskInfo();
        disk.read();
        JFrame diskFrame = new JFrame("Disk Information");
        diskFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        diskFrame.setSize(400, 300);
        JTextArea diskInfoText = new JTextArea();
        diskInfoText.setText(disk.displayDiskInfo());
        diskInfoText.setEditable(false);
        
        diskFrame.setVisible(true);
    }
}

class showGPUInfo {
    public showGPUInfo() {
        JFrame gpuFrame = new JFrame("GPU Information");
        gpuFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gpuFrame.setSize(400, 300);
        
        JTextArea gpuInfoText = new JTextArea();
        String gpuName = gpuInfo.getGPUName();
        if (gpuName != "No GPU found") {
            String gpuVendor = gpuInfo.getGPUVendor();
            long gpuMemory = gpuInfo.getGPUMemory();
            String driverVersion = gpuInfo.getGPUDriverVersion();

            gpuInfoText.append("GPU Vendor: " + gpuVendor + "\n");
            gpuInfoText.append("GPU Name: " + gpuName + "\n");
            gpuInfoText.append("GPU Memory: " + gpuMemory + " MB\n");
            gpuInfoText.append("GPU Driver Version: " + driverVersion + "\n");
            gpuInfoText.setEditable(false);
            
        } else {
            gpuInfoText.append(gpuName);
        }

        JScrollPane scrollPane = new JScrollPane(gpuInfoText);
        gpuFrame.add(scrollPane);

        gpuFrame.setVisible(true);
    }
}

class showBatteryInfo {
    public showBatteryInfo() {
        JFrame batteryFrame = new JFrame("Battery Information");
        batteryFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        batteryFrame.setSize(400, 300);

        JTextArea batteryInfoText = new JTextArea();
        double capacity = batteryInfo.getBatteryCapacity();
        if (capacity != -1) {
            String batInfo = batteryInfo.getBatteryNameAndManufacturer();
            batteryInfoText.append(batInfo + "\n");

            batteryInfoText.append("Battery capacity: " + capacity + "%\n");

            String timeRemaining = batteryInfo.getBatteryTimeRemaining();
            batteryInfoText.append("Time remaining: " + timeRemaining + "\n");

            boolean charging = batteryInfo.isBatteryCharging();
            if (charging) {
                batteryInfoText.append("Battery is charging.\n");
            } else {
                batteryInfoText.append("Battery is not charging.\n");
            }
        }
        else {
            batteryInfoText.append("No battery found.\n");
        }   

        batteryInfoText.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(batteryInfoText);
        batteryFrame.add(scrollPane);

        batteryFrame.setVisible(true);
    }
}

class showDisplayInfo {
    public showDisplayInfo() {
        JFrame displayFrame = new JFrame("Display Information");
        displayFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        displayFrame.setSize(400, 300);

        int displayCount = displayInfo.getDisplayCount();
        String displayDetails = displayInfo.getDisplayInfo();

        JTextArea displayInfoText = new JTextArea();
        displayInfoText.append("Number of displays: " + displayCount + "\n\n");
        displayInfoText.append(displayDetails + "\n");
        displayInfoText.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(displayInfoText);
        displayFrame.add(scrollPane);

        displayFrame.setVisible(true);
    }
}

class displayMotherboardInfo {
    public displayMotherboardInfo() {
        JFrame mbFrame = new JFrame("Motherboard Information");
        mbFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mbFrame.setSize(400, 300);

        motherboardInfo motherboard = new motherboardInfo();
        JTextArea mbInfoText = new JTextArea();
        mbInfoText.setText(motherboard.displayMotherboardInfo());
        mbInfoText.setEditable(false);
        

        JScrollPane scrollPane = new JScrollPane(mbInfoText);
        mbFrame.add(scrollPane);

        mbFrame.setVisible(true);
    }
}