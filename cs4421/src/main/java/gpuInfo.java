import java.util.List;

import oshi.SystemInfo;
import oshi.hardware.GraphicsCard;
import oshi.hardware.HardwareAbstractionLayer;

public class gpuInfo {
    static SystemInfo si = new SystemInfo();
    static HardwareAbstractionLayer hal = si.getHardware();
    static List<GraphicsCard> graphicsCards = hal.getGraphicsCards();

    public static String getGPUName() {
        if (!graphicsCards.isEmpty()) {
            GraphicsCard gpu = graphicsCards.get(0);
            return gpu.getName();
        } else {
            return "No GPU found";
        }
    }

    public static String getGPUVendor() {
        if (!graphicsCards.isEmpty()) {
            GraphicsCard gpu = graphicsCards.get(0);
            return gpu.getVendor();
        } else {
            return "No GPU found";
        }
    }

    public static long getGPUMemory() {
        if (!graphicsCards.isEmpty()) {
            GraphicsCard gpu = graphicsCards.get(0);
            return gpu.getVRam()/ (1024 * 1024); // Convert bytes to MB
        } else {
            return -1;
        }
    }

    public static String getGPUDriverVersion() {
        if (!graphicsCards.isEmpty()) {
            GraphicsCard gpu = graphicsCards.get(0);
            return gpu.getVersionInfo();
        } else {
            return "No GPU found";
        }
    }
}
