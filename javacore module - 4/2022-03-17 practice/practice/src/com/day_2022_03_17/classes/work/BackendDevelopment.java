package com.day_2022_03_17.classes.work;

public class BackendDevelopment extends Employee {
    private String FrameworkName;
    private String Language;
    private String ProgramingLanguage;

    public BackendDevelopment(int id, String name, String FrameworkName, String Language, String ProgramingLanguage) {
        super(id, name);
        this.FrameworkName = FrameworkName;
        this.Language = Language;
        this.ProgramingLanguage = ProgramingLanguage;
    }

    public String getFrameworkName() {
        return FrameworkName;
    }

    public void setFrameworkName(String FrameworkName) {
        this.FrameworkName = FrameworkName;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String Language) {
        this.Language = Language;
    }

    public String getProgramingLanguage() {
        return ProgramingLanguage;
    }

    public void setProgramingLanguage(String ProgramingLanguage) {
        this.ProgramingLanguage = ProgramingLanguage;
    }


    @Override
    public void DisplayEmployee() {
        super.DisplayEmployee();
        System.out.println("Framework Name: " + FrameworkName);
        System.out.println("Language: " + Language);
        System.out.println("Programing Language: " + ProgramingLanguage);
    }

}
