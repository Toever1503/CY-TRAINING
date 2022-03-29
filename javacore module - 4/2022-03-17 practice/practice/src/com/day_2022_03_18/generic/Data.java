package com.day_2022_03_18.generic;

public class Data<T> {
    private T Data;

    public void setDate(T data){
        this.Data = data;
    }

    public T getData(){
        return this.Data;
    }
}
