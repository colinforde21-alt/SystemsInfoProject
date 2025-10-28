import java.util.List;

import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.PowerSource;


public class batteryInfo 
{
    static SystemInfo si = new SystemInfo();
    static HardwareAbstractionLayer hal = si.getHardware();
    static List<PowerSource> powerSources = hal.getPowerSources();

    public static double getBatteryCapacity() 
    {

        if (!powerSources.isEmpty()) {
            // Assuming the first power source is the battery
            PowerSource battery = powerSources.get(0);
            return battery.getRemainingCapacityPercent() * 100; // Return as percentage
        } else {
            return -1; // Indicate no battery found
        }
    }

    public static String getBatteryTimeRemaining() {

        if (!powerSources.isEmpty()) {
            PowerSource battery = powerSources.get(0);
            double timeSeconds = battery.getTimeRemainingEstimated();

            // If OS doesn't provide an estimate, OSHI returns -1
            if (timeSeconds < 0) {
                return "Not available";
            }

            int totalMinutes = (int) Math.round(timeSeconds / 60.0);
            int hours = totalMinutes / 60;
            int minutes = totalMinutes % 60;

            String timeFormatted = String.format("%d hour%s %d minute%s",
                    hours, (hours != 1 ? "s" : ""),
                    minutes, (minutes != 1 ? "s" : ""));

            return timeFormatted;
        } else {
            return "";
        }
    }

    public static boolean isBatteryCharging() {


        if (!powerSources.isEmpty()) {
            PowerSource battery = powerSources.get(0);
            boolean charging = battery.isCharging();
            return charging;
        } else {
            return false;
        }
    }

    public static String getBatteryNameAndManufacturer() {
        if (!powerSources.isEmpty()) {
            PowerSource battery = powerSources.get(0);

            String name = battery.getName();
            String manufacturer = battery.getManufacturer();

            // Handle possible null or empty values
            if (manufacturer == null || manufacturer.isEmpty()) {
                manufacturer = "Unknown Manufacturer";
            }

            String info = String.format("Battery Name: %s, Manufacturer: %s", name, manufacturer);
            return info;
        } else {
            return "Battery Name: N/A, Manufacturer: N/A";
        }
    }
}