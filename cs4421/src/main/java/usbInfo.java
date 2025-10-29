/*
 *  USB information class for JNI
 *
 *  Copyright (c) 2024 Mark Burkley (mark.burkley@ul.ie)
 */

import java.util.List;

import oshi.SystemInfo;
import oshi.hardware.UsbDevice;

public class usbInfo 
{
    // Refresh the current values and counters - call this before other methods
    public native void read ();

    // Return the number of USB buses
    public native int busCount ();

    // Return the number of devices on a USB bus
    public native int deviceCount (int bus);

    // Return the vendor ID of a USB device
    public native int vendorID (int bus, int device);

    // Return the product ID of a USB device
    public native int productID (int bus, int device);


    /*public String deviceType(int bus, int device) {
        String vendor = vendorType(bus, device).toLowerCase();

        if (vendor.contains("logitech")) {
            return "Peripheral (Keyboard/Mouse/Webcam)";
        } else if (vendor.contains("apple")) {
            return "Mobile / Peripheral / Accessory";
        } else if (vendor.contains("sandisk")) {
            return "Storage Device (USB Drive)";
        } else if (vendor.contains("intel")) {
            return "Chipset / Internal Controller";
        } else if (vendor.contains("microsoft")) {
            return "Peripheral / Accessory";
        } else if (vendor.contains("google")) {
            return "Mobile / Development Device";
        } else if (vendor.contains("realtek")) {
            return "Network Adapter / Audio / Internal Chip";
        } else if (vendor.contains("sony")) {
            return "Entertainment Device";
        } else if (vendor.contains("huawei")) {
            return "Mobile Modem / Network Device";
        } else if (vendor.contains("terminus")) {
            return "USB Hub Controller";
        } else if (vendor.contains("mediatek")) {
            return "Chipset / Network / Mobile Controller";
        } else if (vendor.contains("razer")) {
            return "Gaming Peripheral";
        } else if (vendor.contains("dji")) {
            return "Camera / Drone Device";
        } else if (vendor.contains("bitmain")) {
            return "Mining / Compute Hardware";
        } else if (vendor.contains("tp-link")) {
            return "Network Adapter / Router Interface";
        } else if (vendor.contains("samsung")) {
            return "Storage / Mobile / Peripheral Device";
        } else if (vendor.contains("microdia")) {
            return "Imaging Device (Webcam)";
        } else if (vendor.contains("linux foundation")) {
            return "System Virtual / Composite Device";
        } else if (vendor.contains("future technology devices")) {
            return "USB Serial Converter / Development Board";
        } else if (vendor.contains("silicon labs")) {
            return "Serial Interface / Microcontroller";
        } else if (vendor.contains("cypress")) {
            return "Microcontroller / Development Board";
        } else if (vendor.contains("broadcom")) {
            return "Wireless / Bluetooth Controller";
        } else if (vendor.contains("toshiba")) {
            return "Storage Device / Flash Memory";
        } else {
            return "Miscellaneous / Unknown Device";
        }
    }*/

    SystemInfo si = new SystemInfo();
    List<UsbDevice> usbDevices = si.getHardware().getUsbDevices(true); 

    public String displayUSBInfo() {
        StringBuilder usbInformation = new StringBuilder();
        int buses = busCount();

        usbInformation.append("Number of USB Buses: " + buses + "\n");
        for (int bus = 0; bus < buses; bus++) {
            int devices = deviceCount(bus);
            usbInformation.append("Bus " + bus + " - Number of Devices: " + devices + "\n");

            for (UsbDevice device : usbDevices) {
                String vendor = device.getVendor();
                String vendorId = device.getVendorId();
                String productId = device.getProductId();
                String product = device.getName();

                usbInformation.append("  Device: " + product + "\n");
                usbInformation.append("Vendor ID: 0x" + vendorId + " (" + vendor + ")\n");
                usbInformation.append("Product ID: 0x" + productId + "\n");


                //usbInformation.append("Device Type: " + deviceType(vendor) + "\n");
            }
        }
        return usbInformation.toString();
    }
}



        
