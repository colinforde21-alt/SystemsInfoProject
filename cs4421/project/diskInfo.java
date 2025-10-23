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
    
    
}
//Percentage Used and Free of Disk
class diskPercentage
{
    public double getUsagePercentage(int disk, diskInfo di)
    {
        long total = di.getTotal(disk);
        long used = di.getUsed(disk);
        if (total == 0) return 0.0;
        return ((double)used / (double)total) * 100.0;
    }
    public double getFreePercentage(int disk, diskInfo di)
    {
        long total = di.getTotal(disk);
        long available = di.getAvailable(disk);
        if (total == 0) return 0.0;
        return ((double)available / (double)total) * 100.0;
    }
}
