import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;

import javax.swing.Box;
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
import java.awt.Font;







public class randomGUi {
    public static void main(String[] args) {
        System.setProperty("sun.java2d.uiScale", "2");
        System.loadLibrary("sysinfo");
        homePage();
    }
    public static void homePage() {
        JFrame frame = new JFrame("Home Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        

        
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel Heading = new JLabel("System Information GUI");
        Heading.setBounds(185,30,300,40);
        Heading.setFont(new java.awt.Font("Serif", java.awt.Font.BOLD, 20));
        frame.add(Heading);

        JButton CPUbutton = new JButton("CPU Info");
        CPUbutton.setBounds(80,100,150,40);
        panel.add(CPUbutton);
        CPUbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                new showCpuInfo();

                frame.dispose();
            }
        });
        
        JButton diskButton = new JButton("Disk Info");
        diskButton.setBounds(240,100,150,40);
        panel.add(diskButton);
        diskButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                new showDiskInfo();

                frame.dispose();
            }
        });
        JButton memButton = new JButton("Memory Info");
        memButton.setBounds(400,100,150,40);
        panel.add(memButton);
        memButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                new showMemInfo();

                frame.dispose();
            }
        });
        JButton pciButton = new JButton("PCI Info");
        pciButton.setBounds(80,160,150,40);
        panel.add(pciButton);
        pciButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                new showPCIInfo();

                frame.dispose();
            }
        });
        JButton usbButton = new JButton("USB Info");
        usbButton.setBounds(240,160,150,40);
        panel.add(usbButton);
        usbButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                new showUSBInfo();

                frame.dispose();
            }
        });
        JButton gpuButton = new JButton("GPU Info");
        gpuButton.setBounds(80,220,150,40);
        panel.add(gpuButton);
        gpuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                new showGPUInfo();

                frame.dispose();
            }
        });
        JButton batteryButton = new JButton("Battery Info");
        batteryButton.setBounds(400,160,150,40);
        panel.add(batteryButton);
        batteryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                new showBatteryInfo();
                frame.dispose();
            }
        });
        JButton displayButton = new JButton("Display Info");
        displayButton.setBounds(240,220,150,40);
        panel.add(displayButton);
        displayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                new showDisplayInfo();
                frame.dispose();
            }
        });

        JButton motherboardButton = new JButton("Motherboard Info");
        motherboardButton.setBounds(400,220,150,40);
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
        cpuFrame.setSize(600, 400);
        JTextArea area = new JTextArea();
        area.setText(cpuInfo.getCpuSummary());
        JScrollPane scroll = new JScrollPane(area);
        

        JButton graphButton = new JButton("Show Live Processor Data Feed");
        graphButton.setBounds(300, 100, 220, 40);
        cpuFrame.add(graphButton, java.awt.BorderLayout.SOUTH);
        cpuFrame.add(scroll);
        graphButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                SwingUtilities.invokeLater(() -> {
                    LiveLineChart app = new LiveLineChart("JFreeChart Live CPU Data Feed");
                    JButton goHome = new JButton("Go To Home");
                    goHome.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e){
                            randomGUi.homePage();
                            app.dispose();
                        }
                    });

                    app.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    app.pack();
                    app.setLocationRelativeTo(null);

                    app.add(goHome, java.awt.BorderLayout.SOUTH);
                    app.setVisible(true);

            
                });
            }
        });

        JButton goHome = new JButton("Go To Home");
        goHome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                    randomGUi.homePage();
                    cpuFrame.dispose();
                }
            });
        cpuFrame.add(goHome, java.awt.BorderLayout.SOUTH);
        cpuFrame.setVisible(true);

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
        memFrame.setSize(600, 400);
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

        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));

        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> updateInfo());

        barPanel = new MemoryBarPanel();

        memFrame.add(infoPanel, BorderLayout.NORTH);
        memFrame.add(barPanel, BorderLayout.CENTER);
        memFrame.add(refreshButton, BorderLayout.SOUTH);

        
        JButton goHome = new JButton("Go To Home");
        goHome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                    randomGUi.homePage();
                    memFrame.dispose();
                }
            });
        
        buttons.add(refreshButton);
        buttons.add(goHome);
        memFrame.add(buttons, BorderLayout.SOUTH);
        memFrame.setVisible(true);

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
            totalLabel.setText("Total Memory: " + max + " KB");
            usedLabel.setText("Used Memory: " + used + " KB");
            freeLabel.setText("Free Memory: " + free + " KB");
            percentLabel.setText("Percent Used: " + String.format("%.2f%%", percent));
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
        pciFrame.setSize(600, 400);

        int pciCount = newPciInfo.getPCIBusCount();
        String pciDetails = newPciInfo.getFormattedPCIInfo();
        JTextArea pciDetailsText = new JTextArea(); 
        pciDetailsText.setFont(new Font("Monospaced", Font.PLAIN, 12));

        
        pciDetailsText.append("Device has: " + pciCount + " PCI buses\n");
        pciDetailsText.append(pciDetails);
        pciDetailsText.setEditable(false);


        JScrollPane scrollPane = new JScrollPane(pciDetailsText);
        pciFrame.add(scrollPane);

        JButton goHome = new JButton("Go To Home");
        goHome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                    randomGUi.homePage();
                    pciFrame.dispose();
                }
            });
        pciFrame.add(goHome, java.awt.BorderLayout.SOUTH);
        pciFrame.setVisible(true);

        pciFrame.setVisible(true);
        
    }
}

class showUSBInfo {
    public showUSBInfo() {
        JFrame usbFrame = new JFrame("USB Information");
        usbFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        usbFrame.setSize(600, 400);
        usbInfo usb = new usbInfo();
        usb.read();
        JTextArea usbInfoText = new JTextArea();
        usbInfoText.setText(usb.displayUSBInfo());
        usbInfoText.setEditable(false);
        JScrollPane scroll = new JScrollPane(usbInfoText);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        usbFrame.add(scroll);
        
        
        JButton goHome = new JButton("Go To Home");
        goHome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                    randomGUi.homePage();
                    usbFrame.dispose();
                }
            });
        usbFrame.add(goHome, java.awt.BorderLayout.SOUTH);
        
        usbFrame.setVisible(true);

    }
}

class showDiskInfo {
    public showDiskInfo() {
        diskInfo disk = new diskInfo();
        disk.read();
        JFrame diskFrame = new JFrame("Disk Information");
        diskFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        diskFrame.setSize(600, 400);
        JTextArea diskInfoText = new JTextArea();
        diskInfoText.setText(disk.displayDiskInfo());
        diskInfoText.setEditable(false);

        JScrollPane scroll  = new JScrollPane(diskInfoText);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        diskFrame.add(scroll);

        JButton goHome = new JButton("Go To Home");
        goHome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                    randomGUi.homePage();
                    diskFrame.dispose();
                }
            });

        JButton readwritegraph = new JButton("Show Disk Read/Write Graph");
        readwritegraph.setBounds(350, 250, 220, 40);
        JPanel buttons = new JPanel();
        buttons.add(goHome);
        buttons.add(readwritegraph);
        diskFrame.add(buttons, BorderLayout.SOUTH);
        readwritegraph.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                SwingUtilities.invokeLater(() -> {
                    diskChart diskChartApp = new diskChart();
                    diskChartApp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    JButton goHome = new JButton("Go To Home");
                    goHome.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e){
                            randomGUi.homePage();
                            diskChartApp.dispose();
                        }
                    });

            
            diskChartApp.setVisible(true);
                });
            }
        });
        diskFrame.setVisible(true);
        
    }
}


class showGPUInfo {
    public showGPUInfo() {
        JFrame gpuFrame = new JFrame("GPU Information");
        gpuFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gpuFrame.setSize(600, 400);
        
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

        JButton goHome = new JButton("Go To Home");
        goHome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                    randomGUi.homePage();
                    gpuFrame.dispose();
                }
            });
        gpuFrame.add(goHome, java.awt.BorderLayout.SOUTH);
        gpuFrame.setVisible(true);

        gpuFrame.setVisible(true);
    }
}

class showBatteryInfo {
    public showBatteryInfo() {
        JFrame batteryFrame = new JFrame("Battery Information");
        batteryFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        batteryFrame.setSize(600, 400);
        batteryFrame.setLayout(new BorderLayout());

        // --- 1. Text Area and Scroll Pane (CENTER region) ---
        JTextArea batteryInfoText = new JTextArea();
        
        // Populate Text Area (Simulation/Placeholder for original logic)
        // NOTE: This relies on the global 'batteryInfo' object and its methods.
        double capacity = batteryInfo.getBatteryCapacity();
        if (capacity >= 0) {
            String batInfo = batteryInfo.getBatteryNameAndManufacturer();
            batteryInfoText.append(batInfo + "\n");
            batteryInfoText.append("Battery capacity: " + capacity + "%\n");
            String timeRemaining = batteryInfo.getBatteryTimeRemaining();
            batteryInfoText.append("Time remaining: " + timeRemaining + "\n");
            boolean charging = batteryInfo.isBatteryCharging();
            batteryInfoText.append("Battery is " + (charging ? "charging." : "not charging.") + "\n");
        } else {
            batteryInfoText.append("No battery found or error reading data.\n");
        } 
        
        batteryInfoText.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(batteryInfoText);
        batteryFrame.add(scrollPane, BorderLayout.CENTER);

        // --- 2. Bar Panel and Home Button (SOUTH region) ---
        
        // Panel to stack the bar and button vertically
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS)); 
        
        BatteryBarPanel barPanel = new BatteryBarPanel();

        JButton goHome = new JButton("Go To Home");
        goHome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                // Assuming randomGUi.homePage() is correctly defined elsewhere
                // randomGUi.homePage(); 
                batteryFrame.dispose();
            }
        });

        // Center components horizontally in the BoxLayout
        barPanel.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        goHome.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        
        // Add components to the south panel with vertical spacing
        southPanel.add(Box.createVerticalStrut(10));
        southPanel.add(barPanel);
        southPanel.add(Box.createVerticalStrut(10)); 
        southPanel.add(goHome);
        southPanel.add(Box.createVerticalStrut(10)); 

        // Add the entire stack to the JFrame's SOUTH
        batteryFrame.add(southPanel, BorderLayout.SOUTH);
        
        batteryFrame.setVisible(true);
    }
    
    class BatteryBarPanel extends JPanel {
        
        private static final int BAR_HEIGHT = 40;
        private static final int PANEL_HEIGHT = BAR_HEIGHT + 20; // Fixed height for panel

        public BatteryBarPanel() {
            // CRUCIAL: Set preferred and max height to prevent vertical stretching
            setPreferredSize(new Dimension(600, PANEL_HEIGHT)); 
            setMaximumSize(new Dimension(Integer.MAX_VALUE, PANEL_HEIGHT));
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            int width = getWidth() - 60; // Bar width (with padding)
            int height = BAR_HEIGHT;
            
            // Draw starting from a small offset from the top of *this* panel
            int x = 30, y = 10; 
            
            // NOTE: This relies on the global 'batteryInfo' object and its methods.
            double capacity = batteryInfo.getBatteryCapacity();
            
            if (capacity < 0) { 
                g.setColor(Color.RED);
                g.drawString("No battery found or error reading data.", x, y + height/2 + 5);
                return;
            } 
            
            // Draw the bar based on the percentage
            int percentageLeftBarWidth = (int)((capacity/100) * width);

            // Remaining Charge (Green)
            g.setColor(Color.GREEN);
            g.fillRect(x, y, percentageLeftBarWidth, height);

            // Used Space (White)
            g.setColor(Color.WHITE);
            g.fillRect(x + percentageLeftBarWidth, y, width - percentageLeftBarWidth, height);

            // Border
            g.setColor(Color.BLACK);
            g.drawRect(x, y, width, height);
        }
    }
}

class showDisplayInfo {
    public showDisplayInfo() {
        JFrame displayFrame = new JFrame("Display Information");
        displayFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        displayFrame.setSize(600, 400);

        int displayCount = displayInfo.getDisplayCount();
        String displayDetails = displayInfo.getDisplayInfo();

        JTextArea displayInfoText = new JTextArea();
        displayInfoText.append("Number of displays: " + displayCount + "\n\n");
        displayInfoText.append(displayDetails + "\n");
        displayInfoText.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(displayInfoText);
        displayFrame.add(scrollPane);

        JButton goHome = new JButton("Go To Home");
        goHome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                    randomGUi.homePage();
                    displayFrame.dispose();
                }
            });
        displayFrame.add(goHome, java.awt.BorderLayout.SOUTH);
        displayFrame.setVisible(true);

        displayFrame.setVisible(true);
    }
}

class displayMotherboardInfo {
    public displayMotherboardInfo() {
        JFrame mbFrame = new JFrame("Motherboard Information");
        mbFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mbFrame.setSize(600, 400);

        motherboardInfo motherboard = new motherboardInfo();
        JTextArea mbInfoText = new JTextArea();
        mbInfoText.setText(motherboard.displayMotherboardInfo());
        mbInfoText.setEditable(false);
        

        JScrollPane scrollPane = new JScrollPane(mbInfoText);
        mbFrame.add(scrollPane);

        JButton goHome = new JButton("Go To Home");
        goHome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                    randomGUi.homePage();
                    mbFrame.dispose();
                }
            });
        mbFrame.add(goHome, java.awt.BorderLayout.SOUTH);
        mbFrame.setVisible(true);
    }
}
