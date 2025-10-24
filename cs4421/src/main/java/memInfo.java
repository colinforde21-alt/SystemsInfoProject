/*
 * CS4421 memInfo.java
 * - Designed for integration with template.java and with reference to W1L4/W3L8 lecture content
 * - Exposes both raw hardware info and computer architecture concepts (cells, address lines, memory type)
 * Uday Bhattacharya, University of Limerick
 */
public class memInfo
{
    // Native JNI library, required for hardware queries
    static {
        try {
            System.loadLibrary("sysinfo");
        } catch (UnsatisfiedLinkError e) {
            System.err.println("Native library failed to load: " + e);
        }
    }

    // Load native values from hardware: Call before ALL queries!
    public native void read();

    // Get total installed main memory (RAM), in KB (1024 bytes per KB)
    public native int getTotal();

    // Get memory currently in use, in KB
    public native int getUsed();

    // Returns free (unused) main memory, in KB (total - used)
    public int getFree() {
        return getTotal() - getUsed();
    }

    // Returns percent of memory in use, as a double (0-100%)
    public double getPercentUsed() {
        int total = getTotal();
        if (total == 0) return 0.0;
        return 100.0 * getUsed() / total;
    }

    // Returns the required number of memory address lines for this RAM size
    // (Slides: # of address lines determines maximum addressable memory)
    public int getAddressLines() {
        int bytes = getTotal() * 1024; // convert KB to bytes
        int lines = 0;
        while ((1L << lines) < bytes) lines++;
        return lines;
    }

    // Returns a formatted string reporting all main architectural properties
    public String getEducationalSummary() {
        int totalKB = getTotal();
        int usedKB = getUsed();
        int freeKB = getFree();
        double percent = getPercentUsed();
        int totalMB = totalKB / 1024;
        int usedMB = usedKB / 1024;
        int freeMB = freeKB / 1024;
        int addressLines = getAddressLines();
        long cells = (long)totalKB * 1024; // bytes = cells
        return String.format(
            "Physical RAM: %d KB (%.2f MB)\n" +
            "Used: %d KB (%.2f MB)\n" +
            "Free: %d KB (%.2f MB)\n" +
            "Usage: %.2f%%\n" +
            "Estimated address lines (bits): %d (can address up to %.2f MB)\n" +
            "Memory cells (bytes): %d (1 cell = 1 byte)\n" +
            "Main memory type: DDRx SDRAM (see slides for details on data, address lines and error detection)\n",
            totalKB, (double)totalKB/1024.0,
            usedKB, (double)usedKB/1024.0,
            freeKB, (double)freeKB/1024.0,
            percent,
            addressLines, (double)(1L<<addressLines)/(1024.0*1024.0),
            cells
        );
    }
}
