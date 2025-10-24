/*
 *  Example class containing methods to read and display CPU, PCI and USB information
 *
 *  Copyright (c) 2024 Mark Burkley (mark.burkley@ul.ie)
 */
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;


public class template 
{
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
                        if (pci.functionPresent (i, j, k) > 0) {
                            System.out.println("Bus "+i+" device "+j+" function "+k+
                                " has vendor "+String.format("0x%04X", pci.vendorID(i,j,k))+
                                " and product "+String.format("0x%04X", pci.productID(i,j,k)));
                        }
                    }
                }
            }
        }
    }

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


    public static void main(String[] args)
    {
        System.loadLibrary("sysinfo");
        sysInfo info = new sysInfo();
        cpuInfo myCpu = new cpuInfo();
        myCpu.read(0);
        
        showCPU();
        showPCI();
        showUSB();
        showDisk();
        showMem();

        SystemInfo si = new SystemInfo();
        CentralProcessor cpu = si.getHardware().getProcessor();
        System.out.println("CPU: " + cpu.getProcessorIdentifier().getName());
    }
}


