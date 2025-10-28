import java.util.ArrayList;
import java.util.List;

import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.Display;


public class displayInfo {
    static SystemInfo si = new SystemInfo();
    static HardwareAbstractionLayer hal = si.getHardware();

    public static int getDisplayCount() {
        List<Display> displays = hal.getDisplays();
        if (displays != null) {
            return displays.size();
        } else {
            return 0;
        }
    }

}
