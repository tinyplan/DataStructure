package com.hard.model;

/**
 * Book类
 * 特别说明:name属性中实际存储的是“value”
 */
public class Book{
    private String name;
    private int status;//1 : 在架；0 ： 借出

    public Book() {}

    public Book(String name){
        this(name,1);
    }

    public Book(String name, int status) {
        this.name = name;
        this.status = status;
    }

    @Override
    public String toString() {
        return "\t{" + "\"name\": " + name + ", \"status\": " + status + "}";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if(obj instanceof Book){
            if(this.name.equals(((Book) obj).name)){
                return true;
            }
        }
        return false;
    }
}
