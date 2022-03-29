package com.day_2022_03_21.thread;

import java.io.File;
import java.io.Serializable;

public class main2 implements Serializable  {

    public static void main(String[] args) {
        File file = new File("FILEa/asda/sadasd");
        System.out.println(file.mkdirs());


    }
}
