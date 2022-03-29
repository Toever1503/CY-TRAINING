package com.day_2022_03_17.day_2022_03_17.classes;

import java.util.Scanner;

public class Smartphone extends Phone {
    private String Os;
    private float OsVersion;

    public String getOs() {
        return Os;
    }

    public void setOs(String os) {
        Os = os;
    }

    public float getOsVersion() {
        return OsVersion;
    }

    public void setOsVersion(float osVersion) {
        OsVersion = osVersion;
    }

    @Override
    public void enterInformation(Scanner scan) {
        super.enterInformation(scan);
        String txt;
        System.out.print("Enter os: ");
        if (!scan.hasNext())
            scan.next();
        this.Os = scan.next();

        System.out.print("Enter os version: ");
        do {
            txt = scan.next();
            if (!txt.matches("[0-9.]*")) {
                System.out.println("Version invalid, enter again!");
                txt = null;
            }
        }
        while (txt == null);
        this.OsVersion = Float.parseFloat(txt);
    }

    @Override
    public StringBuilder displayInformation() {
        StringBuilder sb = super.displayInformation();
        sb.append("\nOs: ").append(this.Os);
        sb.append("\nOs version: ").append(this.OsVersion);
        return sb;
    }
}
