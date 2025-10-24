/*
 *  Disk information class for JNI
 *
 *  Copyright (c) 2024 Mark Burkley (mark.burkley@ul.ie)
 */

public class diskInfo 
{
    // Refresh the current values and counters - call this before other methods
    public native void read ();
    public native int diskCount ();
    public native String getName (int disk);
    public native long getTotal (int disk);
    public native long getUsed (int disk);
    public native long getAvailable (int disk);

    public double getUsedPercent (int disk){
        long total = getTotal(disk);
        long used = getUsed(disk);
        if (total == 0) {
            return 0.0;
        }
        double percent = ((double)used / (double)total) * 100.0;
        return percent;
    }

    public double getAvailablePercent (int disk){
        long total = getTotal(disk);
        long available = getAvailable(disk);
        if (total == 0) {
            return 0.0;
        }
        double percent = ((double)available / (double)total) * 100.0;
        return percent;
    }
    public double systemTotal(){
        double total = 0.0;
        int disks = diskCount();
        for (int i = 0; i < disks; i++){
            total += (double)getTotal(i);
        }
        double totalGB = total / (1024.0 * 1024.0 * 1024.0);
        return totalGB;
    }

    public double systemUsed(){
        double used = 0.0;
        int disks = diskCount();
        for (int i = 0; i < disks; i++){
            used += (double)getUsed(i);
        }
        double usedGB = used / (1024.0 * 1024.0 * 1024.0);
        return usedGB;
    }

    public double systemAvailable(){
        double available = 0.0;
        int disks = diskCount();
        for (int i = 0; i < disks; i++){
            available += (double)getAvailable(i);
        }
        double availableGB = available / (1024.0 * 1024.0 * 1024.0);
        return availableGB;
    }

    public String getDiskType (int disk){
        String name = getName(disk).toLowerCase();
        if (name.startsWith("sd") ){
            return "SSD";
        } else if (name.startsWith("vd")){
            return "Virtual Disk";
        } else if (name.startsWith("hd")){
            return "HDD";
        } else if (name.startsWith("nvme")){
            return "NVMe";
        } else if (name.startsWith("mmcblk")){
            return "eMMC";
        } else if (name.startsWith("loop")){
            return "Loopback";
        } else {
            return "Unknown";
        }
    }

}


