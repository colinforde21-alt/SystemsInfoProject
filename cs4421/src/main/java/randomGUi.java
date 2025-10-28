
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import java.util.ArrayList;


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

        int pciCount = newPciInfo.getPCIBusCount();
        ArrayList<String> pciDetails = newPciInfo.getPCIInfo();

        JTextArea pciCountText = new JTextArea("Number of PCI Buses: " + pciCount);
        JTextArea pciDetailsText = new JTextArea();
        StringBuilder detailsBuilder = new StringBuilder();
        for (String device : pciDetails) {
            detailsBuilder.append(device).append("\n");
        }
        pciDetailsText.setText(detailsBuilder.toString());
        pciDetailsText.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(pciDetailsText);


        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(pciCountText);
        panel.add(scrollPane);

        pciFrame.add(panel);
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
