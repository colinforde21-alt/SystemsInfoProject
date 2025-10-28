/*
 *  USB information class for JNI
 *
 *  Copyright (c) 2024 Mark Burkley (mark.burkley@ul.ie)
 */

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

    public String vendorType (int bus, int device){
        int vid = vendorID (bus, device);
        switch (vid){
            case 0x046D: return "Logitech Inc.";
            case 0x05AC: return "Apple, Inc.";
            case 0x0781: return "SanDisk Corp.";
            case 0x8087: return "Intel Corp.";  
            case 0x045E: return "Microsoft Corp.";
            case 0x18D1: return "Google, Inc.";
            case 0x0BDA: return "Realtek Semiconductor Corp.";
            case 0x054C: return "Sony Corp.";
            case 0x12D1: return "Huawei Technologies Co., Ltd";
            case 0x1A40: return "Terminus Technology Inc.";
            case 0x0E8D: return "MediaTek Inc.";
            case 0x1B3D: return "Razer Inc.";
            case 0x1E7D: return "DJI Innovations";
            case 0x2E8A: return "Bitmain Technologies Inc.";
            case 0x1F3A: return "TP-Link Technologies Co., Ltd.";
            case 0x2C7C: return "Samsung Electronics Co., Ltd.";
            case 0x13FE: return "Sony Interactive Entertainment";
            case 0x1532: return "Razer Inc.";
            case 0x0C45: return "Microdia";
            case 0x1D6B: return "Linux Foundation";
            case 0x0403: return "Future Technology Devices International, Ltd";
            case 0x10C4: return "Silicon Labs";
            case 0x1366: return "Cypress Semiconductor Corp.";
            default: return "Unknown Vendor";
        }
    }

    public String deviceType(int bus, int device) {
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
    }

    public String deviceType(int bus, int device) {
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
    }

    


}


        
