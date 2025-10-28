import com.github.marandus.pciid.model.Device;
import com.github.marandus.pciid.model.Vendor;
import com.github.marandus.pciid.service.PciIdsDatabase;
import java.util.ArrayList;
import java.io.IOException;
import com.github.marandus.pciid.model.Vendor;
import com.github.marandus.pciid.model.Device;

public class newPciInfo {
    static pciInfo pci = new pciInfo();
    static PciIdsDatabase db = new PciIdsDatabase();

    static {
        System.loadLibrary("sysinfo");
        try {
            db.loadRemote();
        } catch (IOException e) {
            System.err.println("Failed to load PCI IDs: " + e.getMessage());
        }
        pci.read();
    }

    public static int getPCIBusCount() {
        return pci.busCount();
    }
    
    public static ArrayList<String> getPCIInfo() {
        ArrayList<String> pciDetails = new ArrayList<String>(); 
        for (int i = 0; i < pci.busCount(); i++) {
            //System.out.println("Bus "+i+" has "+pci.deviceCount(i)+" devices");

            // Iterate for up to 32 devices.  Not every device slot may be populated
            // so ensure at least one function before printing device information
            for (int j = 0; j < 32; j++) {
                if (pci.functionCount (i, j) > 0) {

                    // Iterate through up to 8 functions per device.
                    for (int k = 0; k < 8; k++) {
                        if (pci.vendorID(i, j, k) == 0 && pci.productID(i, j, k) == 0) {
                            continue;
                            // System.out.println("Bus " + i + " device " + j + " function " + k + " is empty");
                        }

                        String vendorIdHex = String.format("%04X", pci.vendorID(i, j, k));
                        String productIdHex = String.format("%04X", pci.productID(i, j, k));


                        Vendor vendor = db.findVendor(vendorIdHex);
                        Device device = db.findDevice(vendorIdHex, productIdHex);

                        String vendorName = (vendor != null) ? vendor.getName() : "Unknown Vendor: 0x" + vendorIdHex;
                        String deviceName = (device != null) ? device.getName() : "Unknown Device: 0x" + productIdHex;

                        pciDetails.add("Bus " + i + " device " + j + " function " + k + " has vendor " + vendorName + " and product " + deviceName);
                    }
                }
            }
        }
        return pciDetails;
    }
}