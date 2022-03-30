package com.domain;

public class People {
    private Long Id;
    private String Name;
    private Integer age;

    public People() {
    }

    public People(Long id, String name, Integer age) {
        Id = id;
        Name = name;
        this.age = age;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return this.Id.equals(((People) o).getId());
    }
}
