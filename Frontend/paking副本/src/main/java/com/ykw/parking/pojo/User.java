package com.ykw.parking.pojo;

public class User {
    private String id;
    private String name;
    private String phone_number;
    private String password;
    private String email;
    private int state;

    public User(String id, String name, String phone_number, String password, String email, int state) {
        this.id = id;
        this.name = name;
        this.phone_number = phone_number;
        this.password = password;
        this.email = email;
        this.state = state;
    }

    public User() {
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
