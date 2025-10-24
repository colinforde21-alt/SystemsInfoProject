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

    // Percentage calculation methods removed - now handled in template.java

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
}


