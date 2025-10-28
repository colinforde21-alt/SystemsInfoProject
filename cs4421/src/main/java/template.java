/*
 *  Example class containing methods to read and display CPU, PCI and USB information
 *
 *  Copyright (c) 2024 Mark Burkley (mark.burkley@ul.ie)
 */
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import com.github.marandus.pciid.service.PciIdsDatabase;

import java.util.ArrayList;
import java.util.List;

import java.io.IOException;

import com.github.marandus.pciid.model.Vendor;
import com.github.marandus.pciid.model.Device;


public class template 
{
    /*static PciIdsDatabase db = new PciIdsDatabase();
    public static void showPCI()
    {
        
        pciInfo pci = new pciInfo();
        pci.read();
        
        System.out.println("\nThis machine has "+
            pci.busCount()+" PCI buses ");

        // Iterate through each bus
        for (int i = 0; i < pci.busCount(); i++) {
            System.out.println("Bus "+i+" has "+
                pci.deviceCount(i)+" devices");

            // Iterate for up to 32 devices.  Not every device slot may be populated
            // so ensure at least one function before printing device information
            for (int j = 0; j < 32; j++) {
                if (pci.functionCount (i, j) > 0) {
                    System.out.println("Bus "+i+" device "+j+" has "+
                        pci.functionCount(i, j)+" functions");

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

                        System.out.println("Bus " + i + " device " + j + " function " + k + " has vendor " + vendorName + " and product " + deviceName);
                    }
                }
            }
        }
    }*/

    public static void showUSB()
    {
        usbInfo usb = new usbInfo();
        usb.read();
        System.out.println("\nThis machine has "+
            usb.busCount()+" USB buses ");

        // Iterate through all of the USB buses
        for (int i = 1; i <= usb.busCount(); i++) {
            System.out.println("Bus "+i+" has "+
                usb.deviceCount(i)+" devices");

            // Iterate through all of the USB devices on the bus
            for (int j = 1; j <= usb.deviceCount(i); j++) {
                System.out.println("Bus "+i+" device "+j+
                    " has vendor "+String.format("0x%04X", usb.vendorID(i,j))+
                    " and product "+String.format("0x%04X", usb.productID(i,j)));
            }
        }
    }

    public static void showCPU()
    {
        cpuInfo myCpu = new cpuInfo();
        myCpu.read(0);

        // Show CPU model, CPU sockets and cores per socket
        System.out.println("CPU " + myCpu.getModel() + " has "+
            myCpu.socketCount() + " sockets each with "+
            myCpu.coresPerSocket() + " cores");

        // Show sizes of L1,L2 and L3 cache
        System.out.println("l1d="+myCpu.l1dCacheSize()+
            ", l1i="+myCpu.l1iCacheSize()+
            ", l2="+myCpu.l2CacheSize()+
            ", l3="+myCpu.l3CacheSize());

        // Sleep for 1 second and display the idle time percentage for
        // core 1.  This assumes 10Hz so in one second we have 100
        myCpu.read(1);
        System.out.println("core 1 idle="+myCpu.getIdleTime(1)+"%");
    }

    public static void showDisk()
    {
        diskInfo disk = new diskInfo();
        disk.read();

        // Iterate through all of the disks
        for (int i = 0; i < disk.diskCount(); i++) {
            System.out.println ("disk "+disk.getName(i)+" has "+
                disk.getTotal(i)+" blocks, of which "+
                disk.getUsed(i)+" are used");
        }
    }

    public static void showMem(){
        memInfo mem = new memInfo();
        mem.read();
        System.out.println(mem.getEducationalSummary());
    }

    public static void showBattery() {
        double capacity = batteryInfo.getBatteryCapacity();
        if (capacity != -1) {
            String batInfo = batteryInfo.getBatteryNameAndManufacturer();
            System.out.println(batInfo);

            System.out.println("Battery capacity: " + capacity + "%");

            String timeRemaining = batteryInfo.getBatteryTimeRemaining();
            System.out.println("Time remaining: " + timeRemaining);

            boolean charging = batteryInfo.isBatteryCharging();
            if (charging) {
                System.out.println("Battery is charging.");
            } else {
                System.out.println("Battery is not charging.");
            }
        }
        else {
            System.out.println("No battery found.");
        }   
    }

    public static void showGPU() {
        String gpuName = gpuInfo.getGPUName();
        if (gpuName != "No GPU found") {
            String gpuVendor = gpuInfo.getGPUVendor();
            long gpuMemory = gpuInfo.getGPUMemory();
            String driverVersion = gpuInfo.getGPUDriverVersion();

            System.out.println("GPU Vendor: " + gpuVendor);
            System.out.println("GPU Name: " + gpuName);
            System.out.println("GPU Memory: " + gpuMemory + " MB");
            System.out.println("GPU Driver Version: " + driverVersion);
            
        } else {
            System.out.println(gpuName);
        }
    }

    public static void showDisplay() {
        int displayCount = displayInfo.getDisplayCount();
        System.out.println("Number of displays: " + displayCount);

    }

//     public static void main(String[] args)
//     {
        
//         /*try {
//             db.loadRemote();
//         } catch (IOException e) {
//             System.err.println("Failed to load PCI IDs: " + e.getMessage());
//         }*/
//         System.loadLibrary("sysinfo");
//         sysInfo info = new sysInfo();
//         cpuInfo myCpu = new cpuInfo();
//         myCpu.read(0);
        
//         showCPU();
//         //showPCI();
//         showUSB();
//         showDisk();
//         showMem();
//         showBattery();
//         showGPU();
//         showDisplay();
//         System.out.println("Device has: " + newPciInfo.getPCIBusCount() + " PCI buses");
//         ArrayList<String> myPciInfo = newPciInfo.getPCIInfo();
//         for (String pciDevice : myPciInfo) {
//             System.out.println(pciDevice);
//         }
        

//         // SystemInfo si = new SystemInfo();
//         // CentralProcessor cpu = si.getHardware().getProcessor();
//         // System.out.println("CPU: " + cpu.getProcessorIdentifier().getName());
//     }
}


