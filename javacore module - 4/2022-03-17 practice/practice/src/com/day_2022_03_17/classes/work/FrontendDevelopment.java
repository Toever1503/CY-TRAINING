package com.day_2022_03_17.classes.work;

public class FrontendDevelopment extends Employee {
    private String FrameworkName;
    private String[] Cores;

    public FrontendDevelopment(int id, String name, String frameworkName, String[] cores) {
        super(id, name);
        this.FrameworkName = frameworkName;
        this.Cores = cores;
    }

    public String getFrameworkName() {
        return this.FrameworkName;
    }

    public void setFrameworkName(String frameworkName) {
        this.FrameworkName = frameworkName;
    }

    public String[] getCores() {
        return this.Cores;
    }

    public void setCores(String[] cores) {
        this.Cores = cores;
    }

    @Override
    public void DisplayEmployee() {
        super.DisplayEmployee();
        System.out.println("Framework Name: " + this.FrameworkName);
        System.out.println("Cores: ");
        for (String core : this.Cores){
            System.out.print(core.concat(" "));
        }
    }
}
