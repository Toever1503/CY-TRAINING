package com.day_2022_03_17.day_2022_03_17.classes;

import java.util.Scanner;

public class Phone implements IACTION{
    private String PhoneName;
    private String Producer;
    private int Year;
    private int Price;

    public String getPhoneName() {
        return PhoneName;
    }

    public void setPhoneName(String phoneName) {
        PhoneName = phoneName;
    }

    public String getProducer() {
        return Producer;
    }

    public void setProducer(String producer) {
        Producer = producer;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        this.Year = year;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        this.Price = price;
    }

    @Override
    public void enterInformation(Scanner scan) {
        String txt;
        System.out.print("Enter phone name: ");
        this.PhoneName = scan.next();

        System.out.print("Enter producer: ");
        this.Producer = scan.next();

        System.out.print("Enter year: ");
        do{
            txt = scan.next();
            if(!txt.matches("[0-9]{4}")){
                System.out.println("Year invalid, enter again!");
                txt = null;
            }
        }
        while(txt == null);
        this.Year = Integer.parseInt(txt);

        System.out.print("Enter price: ");
        do{
            txt = scan.next();
            if(!txt.matches("[0-9]*")){
                System.out.println("Price invalid, enter again!");
                txt = null;
            }
        }
        while(txt == null);
        this.Price = Integer.parseInt(txt);
    }

    @Override
    public StringBuilder displayInformation() {
        StringBuilder sb = new StringBuilder();
        sb.append("Phone name: ").append(this.PhoneName);
        sb.append("\nProducer: ").append(this.Producer);
        sb.append("\nYear: ").append(this.Year);
        sb.append("\nPrice: ").append(this.Price);
        return sb;
    }
}
