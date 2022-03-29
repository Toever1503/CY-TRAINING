package com.controller;

import com.dao.EmployeeDao;
import com.entity.Employee;

import java.util.List;
import java.util.Scanner;

public class MainProgram {
    private final Scanner Scan;
    private final StringBuffer Tmp;
    private final EmployeeDao EmpDao;

    public MainProgram() {
        this.Scan = new Scanner(System.in);
        this.Tmp = new StringBuffer();
        this.EmpDao = new EmployeeDao();
    }

    public void menu() {
        boolean run = true;

        while (run) {
            System.out.println("1. Add new employee");
            System.out.println("2. Update employee");
            System.out.println("3. Display all employee");
            System.out.println("4. Search employees");
            System.out.println("5. Sum total salary");
            System.out.println("0. Exit");
            do {
                System.out.print("You choose: ");
                this.Tmp.append(this.Scan.next());
                if (!this.Tmp.toString().matches("[0-5]")) {
                    this.Tmp.setLength(0);
                    System.out.println("Wrong option, please choose again!");
                    System.out.println();
                }
            } while (this.Tmp.length() == 0);

            switch (this.Tmp.toString()) {
                case "1":
                    this.AddEmployee();
                    break;
                case "2":
                    this.UpdateEmployee();
                    break;
                case "3":
                    this.DisplayAllEmployee();
                    break;
                case "4":
                    this.SeachEmp();
                    break;
                case "5":
                    this.SumTotalSalary();
                    break;
                default:
                    break;
            }
        }
    }

    public Employee EnterEmpInformation(Employee emp, String type) {
        this.Tmp.setLength(0);
        System.out.println();
//        if (!type.equalsIgnoreCase("update")) {
//            do {
//                System.out.print("Enter emp id: ");
//                this.Tmp.append(this.Scan.next());
//                if (!this.Tmp.toString().matches("[0-9]*")) {
//                    this.Tmp.setLength(0);
//                    System.out.println("Invalid id, please enter again!");
//                    System.out.println();
//                }
//            } while (this.Tmp.length() == 0);
//            emp.setId(Long.valueOf(this.Tmp.toString()));
//            this.Tmp.setLength(0);
//        }

        do {
            System.out.print("Enter emp name: ");
            this.Tmp.append(this.Scan.next());
            if (this.Tmp.toString().length() < 5) {
                System.out.println("Invalid name(too short), please enter again!");
                this.Tmp.setLength(0);
                System.out.println();
            }
        } while (this.Tmp.length() == 0);
        emp.setName(this.Tmp.toString());
        this.Tmp.setLength(0);

        do {
            System.out.print("Enter emp salary: ");
            this.Tmp.append(this.Scan.next());
            if (!this.Tmp.toString().matches("[0-9]*")) {
                this.Tmp.setLength(0);
                System.out.println("Invalid salary, please enter again!");
                System.out.println();
            }
        } while (this.Tmp.length() == 0);
        emp.setSalary(Double.valueOf(this.Tmp.toString()));
        this.Tmp.setLength(0);
        return emp;
    }


    public void AddEmployee() {
        this.Tmp.setLength(0);
        Employee emp = new Employee();
        emp = EnterEmpInformation(emp, "");

        if (this.EmpDao.save(emp) != null)
            System.out.println("Add new successfully!");
        else
            System.out.println("Add new failed!");
    }

    public void UpdateEmployee() {
        this.Tmp.setLength(0);
        do {
            System.out.print("You choose: ");
            this.Tmp.append(this.Scan.nextLine());
            if (this.Tmp.toString().matches("[0-9]*")) {
                this.Tmp.setLength(0);
                System.out.println("Invalid, please choose again!");
                System.out.println();
            }
        } while (this.Tmp.length() == 0);

        Employee empSearch = this.EmpDao.findById(Long.valueOf(this.Tmp.toString()));
        if (empSearch == null) {
            System.out.println();
            System.out.print("Employee not found!, do you want to continue?(y/n) ");
            if (this.Tmp.toString().equalsIgnoreCase("y")) {
                UpdateEmployee();
            }
        } else {
            Employee newInfor = EnterEmpInformation(empSearch, "update");
            if (this.EmpDao.save(newInfor) != null)
                System.out.println("Add new successfully!");
            else
                System.out.println("Add new failed!");
        }
    }

    public void DisplayAllEmployee() {
        System.out.println("------------List Employee---------------");
        List<Employee> list = this.EmpDao.findAll();
        list.forEach(System.out::println);
        if (list.isEmpty())
            System.out.println("List employee empty!");
    }

    public void SeachEmp() {
        this.Tmp.setLength(0);
        System.out.println("------------Search Employee---------------");
        System.out.print("Enter employee name to search: ");
        do {
            this.Tmp.append(this.Scan.nextLine());
        } while (this.Tmp.length() == 0);

        List<Employee> list = this.EmpDao.searchByName(this.Tmp.toString());
        if (list.isEmpty())
            System.out.println("Not found any!");
        list.forEach(System.out::println);
    }

    public void SumTotalSalary() {
        System.out.println("Total salary: " + this.EmpDao.sumTotalSalary());
    }

    public static void main(String[] args) {
        new MainProgram().menu();
    }
}
