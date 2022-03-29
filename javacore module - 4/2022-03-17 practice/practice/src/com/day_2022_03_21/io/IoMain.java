package com.day_2022_03_21.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IoMain {
    public static void main(String[] args) {
        List<Employee> listEmp = new ArrayList<Employee>();
        listEmp.add(new Employee(1, "employee 1"));
        listEmp.add(new Employee(2, "employee 2"));
        listEmp.add(new Employee(3, "employee 3"));
        listEmp.add(new Employee(4, "employee 4"));
        listEmp.add(new Employee(5, "employee 5"));
        listEmp.add(new Employee(6, "employee 6"));
        listEmp.add(new Employee(7, "employee 7"));

        String fileFolderPath = "data/employee";
        String filePath = fileFolderPath + "/employeeList.txt";
        String fileLogPath = fileFolderPath + "/employeeListLog.txt";

        File fileFolder = new File(fileFolderPath);
        File fileEmp = new File(filePath);
        File fileLogEmp = new File(fileLogPath);

        if (!fileFolder.exists()) {
            fileFolder.mkdirs();
        }
        if (!fileEmp.exists()) {
            try {
                fileEmp.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!fileLogEmp.exists()) {
            try {
                fileLogEmp.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//        writeFile(listEmp, fileEmp, fileLogEmp);

        readFile(fileEmp).forEach(emp->{
            System.out.println(emp.getId()+"-"+emp.getName());
        });
    }

    public static List<Employee> readFile(File fileEmp) {
        List<Employee> listEmp = new ArrayList<Employee>();
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(fileEmp));
            StringBuilder sb = new StringBuilder();

            while (bufferedInputStream.available() != 0) {
                int c = bufferedInputStream.read();
                if (c == 10) {
                    String[] empData = sb.toString().split("\t");
                    Employee emp = new Employee(Integer.valueOf(empData[0]), empData[1]);
                    listEmp.add(emp);
                    sb.setLength(0);
                } else {
                    sb.append((char) c);
                }
            }
            bufferedInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listEmp;
    }

    public static void writeFile(List<Employee> listEmp, File fileEmp, File fileLogEmp) {
        try {
            BufferedOutputStream fileWriter = new BufferedOutputStream(new FileOutputStream(fileEmp));
            BufferedOutputStream fileLogWriter = new BufferedOutputStream(new FileOutputStream(fileLogEmp));

            listEmp.forEach(emp -> {
                StringBuilder sb = new StringBuilder();
                sb.append(emp.getId()).append("\t").append(emp.getName()).append("\r\n");
                try {
                    fileWriter.write(sb.toString().getBytes());
                    fileWriter.flush();
                } catch (IOException e) {
                    StringBuilder logSb = new StringBuilder();
                    logSb.append("Employe-").append(emp.getId()).append(" got err writing\r\n");
                    e.printStackTrace();
                }
            });
            fileWriter.close();
            fileLogWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
