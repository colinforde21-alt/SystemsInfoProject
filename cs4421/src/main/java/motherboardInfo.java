import oshi.SystemInfo;
import oshi.hardware.*;

public class motherboardInfo {
    SystemInfo si = new SystemInfo();
    HardwareAbstractionLayer hal = si.getHardware();
    ComputerSystem computerSystem = hal.getComputerSystem();
    Baseboard motherboard = computerSystem.getBaseboard();
    public String displayMotherboardInfo() {
        StringBuilder motherboardInformation = new StringBuilder();
        motherboardInformation.append("Motherboard Information:\n");
        motherboardInformation.append("Manufacturer: " + motherboard.getManufacturer() + "\n");
        motherboardInformation.append("Model: " + motherboard.getModel() + "\n");
        motherboardInformation.append("Version: " + motherboard.getVersion() + "\n");
        motherboardInformation.append("Serial Number: " + motherboard.getSerialNumber() + "\n");     
        return motherboardInformation.toString();
    }
}
