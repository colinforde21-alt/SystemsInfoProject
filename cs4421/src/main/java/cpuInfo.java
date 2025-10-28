import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.CentralProcessor.ProcessorCache;

import java.util.concurrent.TimeUnit;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;
import java.lang.StringBuilder;

public class cpuInfo {

    static SystemInfo si = new SystemInfo();
    static CentralProcessor processor = si.getHardware().getProcessor();

    public static void main(String[] args) {
        System.out.println("--- CS4421 Hardware Reporter (OSHI Demonstration) ---");
        presentSystemDetails();
    }

    public static double getProcessorLoad() {
        try {
            long[] prevTicks = processor.getSystemCpuLoadTicks();
            TimeUnit.SECONDS.sleep(1);
            double cpuLoad = processor.getSystemCpuLoadBetweenTicks(prevTicks) * 100;
            return cpuLoad;
        } catch (InterruptedException e) {
            return 0.0;
        }
    }

    public static void cacheInfo() {
        List<ProcessorCache> caches = processor.getProcessorCaches();
        for (ProcessorCache cache : caches) {
            long sizeKiB = cache.getCacheSize();
            System.out.println(String.format(
                "Cache L%d %s: %d KiB (Line Size: %d bytes)",
                (int) cache.getLevel(),
                cache.getType().toString(),
                sizeKiB,
                (int) cache.getLineSize()
            ));
        }
    }

    public static String getCpuSummary() {
        StringBuilder sb = new StringBuilder();
        String model = si.getHardware().getComputerSystem().getModel();
        sb.append("Model: ").append(model).append('\n');
        sb.append("OS: ").append(si.getOperatingSystem()).append('\n');
        sb.append('\n').append("--- CPU Cores ---").append('\n');
        sb.append("Number of sockets: ").append(processor.getPhysicalPackageCount()).append('\n');
        sb.append("Processor Name: ").append(processor.getProcessorIdentifier().getName()).append('\n');
        sb.append("Physical Cores: ").append(processor.getPhysicalProcessorCount()).append('\n');
        sb.append("Logical Cores (Threads): ").append(processor.getLogicalProcessorCount()).append('\n');

        List<ProcessorCache> caches = processor.getProcessorCaches();
        for (ProcessorCache cache : caches) {
            long sizeKiB = cache.getCacheSize();
            sb.append(String.format(
                "Cache L%d %s: %d KiB (Line Size: %d bytes)\n",
                (int) cache.getLevel(),
                cache.getType().toString(),
                sizeKiB,
                (int) cache.getLineSize()
            ));
        }

        return sb.toString();
    }

    private static void presentSystemDetails() {
        /*String model = si.getHardware().getComputerSystem().getModel();
        System.out.println("Model: " + model);
        System.out.println("OS: " + si.getOperatingSystem());

        System.out.println("\n--- CPU Cores ---");
        System.out.println("Number of sockets: " + processor.getPhysicalPackageCount());
        System.out.println("Processor Name: " + processor.getProcessorIdentifier().getName());
        System.out.println("Physical Cores: " + processor.getPhysicalProcessorCount());
        System.out.println("Logical Cores (Threads): " + processor.getLogicalProcessorCount());
        cacheInfo();*/

        SwingUtilities.invokeLater(() -> {
            LiveLineChart app = new LiveLineChart("JFreeChart Live Data Example");
            app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            app.pack();
            app.setLocationRelativeTo(null);
            app.setVisible(true);
        });
    }
}

 