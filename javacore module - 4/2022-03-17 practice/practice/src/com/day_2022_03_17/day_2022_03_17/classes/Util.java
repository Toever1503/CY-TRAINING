package com.day_2022_03_17.day_2022_03_17.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public class Util {
    private List<Phone> phones;
    private Scanner scan;

    public Util() {
        this.phones = new ArrayList<Phone>();
        scan = new Scanner(System.in);
    }

    public List<Phone> getPhones() {
        return phones;
    }


    public void menu() {
        boolean run = true;
        while (run) {
            System.out.println("<---------------------Phone Management--------------------->");
            System.out.println("1. Enter phone information");
            System.out.println("2. Display phone information");
            System.out.println("3. Search phone");
            System.out.println("4. Display phone which has max price");
            System.out.println("5. Display phone which has min price");
            System.out.println("6. Exit");
            System.out.println("<---------------------------------------------------------->");
            System.out.print("You choose: ");
            String choice = null;
            do {
                choice = this.scan.next();
                if (choice == null) {
                    choice = null;
                    System.out.println("Choose again!");
                }
            } while (choice == null);

            switch (choice) {
                case "1":
                    enterPhoneInformation();
                    break;
                case "2":
                    displayPhoneInformation();
                    break;
                case "3":
                    searchPhone();
                    break;
                case "4":
                    displayPhoneHasMaxPrice();
                    break;
                case "5":
                    displayPhoneHasMinPrice();
                    break;
                case "6":
                    System.out.println("Exit successfully!");
                    run = false;
                    scan.close();
                    break;
                default:
                    System.out.println("Wrong choice, please choose again!");
                    break;
            }
        }
    }

    public void enterPhoneInformation() {
        System.out.println("Choose phone: ");
        System.out.println("1. Smartphone");
        System.out.println("2. Landline");
        System.out.println("3. Back");
        System.out.print("You choose: ");
        String check = null;
        do {
            check = this.scan.next();
        } while (check == null);

        switch (check) {
            case "1": {
                Smartphone sm = new Smartphone();
                sm.enterInformation(this.scan);
                this.phones.add(sm);
                System.out.println("Add successfully!");
                break;
            }
            case "2": {
                Landline sm = new Landline();
                sm.enterInformation(this.scan);
                this.phones.add(sm);
                System.out.println("Add successfully!");
                break;
            }
            case "3":
                break;
            default:
                System.out.println("Wrong choice, please choose again!");
                break;
        }
    }

    public void displayPhoneInformation() {
        System.out.println("List Phone: ");
        this.getPhones().forEach(phone -> {
            System.out.println(phone.displayInformation().toString());
            ;
            System.out.println("..................");
        });

    }

    public void searchPhone() {
        System.out.println("Search phone: ");
        System.out.print("Enter phone name to search: ");
        String search = this.scan.next();

        this.getPhones().forEach(phone -> {
            if (phone.getPhoneName().equalsIgnoreCase(search)) {
                System.out.println("Searched: ");
                phone.displayInformation();
            }
        });
    }

    public void displayPhoneHasMaxPrice() {
        AtomicReference<Phone> p = new AtomicReference<>(this.getPhones().get(0));

        this.getPhones().forEach(phone -> {
            if (phone.getPrice() > p.get().getPrice())
                p.set(phone);
        });

        System.out.println("Phone has max price is: ");
        p.get().displayInformation();
    }

    public void displayPhoneHasMinPrice() {
        AtomicReference<Phone> p = new AtomicReference<>(this.getPhones().get(0));

        this.getPhones().forEach(phone -> {
            if (phone.getPrice() < p.get().getPrice())
                p.set(phone);
        });

        System.out.println("Phone has max price is: ");
        p.get().displayInformation();
    }

    public static void main(String[] args) {
        new Util().menu();
    }

}
