
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;


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
        cpuFrame.setSize(500, 400);
        JTextArea area = new JTextArea();
        area.setText(CpuInfo.getCpuSummary());
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
    public showMemInfo() {
        JFrame memFrame = new JFrame("Memory Information");
        memFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        memFrame.setSize(400, 300);
        // Add components to display Memory information here
        memFrame.setVisible(true);
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
