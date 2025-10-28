import java.util.ArrayList;
import java.util.List;

import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.Display;
import oshi.util.EdidUtil;


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
    public static String getDisplayInfo() {
        for (Display display : hal.getDisplays()) {
            byte[] edid = display.getEdid();
            if (edid != null) {
                String manufacturerId = EdidUtil.getManufacturerID(edid);
                String productId = EdidUtil.getProductID(edid);
                int widthCm = EdidUtil.getHcm(edid);
                int heightCm = EdidUtil.getVcm(edid);
                int year = EdidUtil.getYear(edid);
                String prefRes = EdidUtil.getPreferredResolution(edid);

                return "Display Manufacturer ID: " + manufacturerId + "\nProduct ID: " + productId +"\nYear: " + year + "\nSize: " + widthCm + "cm x " + heightCm + "cm" + "\nPreferred Resolution: " + prefRes;
            } else {
                return "EDID data not available for this display.";
            }
        }
        return "No displays found.";
    }
}
