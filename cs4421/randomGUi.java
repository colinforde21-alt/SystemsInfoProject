
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class randomGUi {
    public static void main(String[] args) {
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
                new showCpuInfo();

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
        frame.add(panel);
        frame.setVisible(true);
    }
}

class showCpuInfo {
    public showCpuInfo() {
        JFrame cpuFrame = new JFrame("CPU Information");
        cpuFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        cpuFrame.setSize(400, 300);
        // Add components to display CPU information here
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
        mem.read(); // Loads the memory info
        totalLabel.setText("Total Memory: " + mem.getTotal() + " KB");
        usedLabel.setText("Used Memory: " + mem.getUsed() + " KB");
        freeLabel.setText("Free Memory: " + mem.getFree() + " KB");
        percentLabel.setText("Percent Used: " + String.format("%.2f%%", mem.getPercentUsed()));
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
            int total = mem.getTotal();
            int used = mem.getUsed();
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
        // Add components to display PCI information here
        pciFrame.setVisible(true);
    }
}

class showUSBInfo {
    public showUSBInfo() {
        JFrame usbFrame = new JFrame("USB Information");
        usbFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        usbFrame.setSize(400, 300);
        // Add components to display USB information here
        usbFrame.setVisible(true);
    }
}

class showDiskInfo {
    public showDiskInfo() {
        JFrame diskFrame = new JFrame("Disk Information");
        diskFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        diskFrame.setSize(400, 300);
        // Add components to display Disk information here
        diskFrame.setVisible(true);
    }
}
