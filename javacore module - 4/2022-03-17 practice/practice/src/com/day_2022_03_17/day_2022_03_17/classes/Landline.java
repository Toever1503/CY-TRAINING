package com.day_2022_03_17.day_2022_03_17.classes;

import java.util.Scanner;

public class Landline extends Phone {
    private Boolean hasWire;

    public Boolean getHasWire() {
        return hasWire;
    }

    public void setHasWire(Boolean hasWire) {
        this.hasWire = hasWire;
    }

    @Override
    public void enterInformation(Scanner scan) {
        super.enterInformation(scan);

        String txt = null;
        System.out.print("Has Wire? y or n: ");
        do {
            txt = scan.next();
            if (!txt.matches("[yn]{1}")) {
                System.out.println("Please enter again!");
                txt = null;
            }
        }
        while (txt == null);
        if (txt.equalsIgnoreCase("y"))
            this.hasWire = true;
        else
            this.hasWire = false;
    }

    @Override
    public StringBuilder displayInformation() {
        StringBuilder sb = super.displayInformation();
        sb.append("\nPhone has wire: ").append(this.hasWire == true ? "yes" : "no");
        return sb;
    }
}
