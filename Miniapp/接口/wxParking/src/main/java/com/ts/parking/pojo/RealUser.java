package com.ts.parking.pojo;

public class RealUser {
    private String id;
    private String name;
    private String phone_number;
    private String email;
    private String rname;
    private String id_number;

    public RealUser(String id, String name, String phone_number, String email, String rname, String id_number) {
        this.id = id;
        this.name = name;
        this.phone_number = phone_number;
        this.email = email;
        this.rname = rname;
        this.id_number = id_number;
    }

    public RealUser() {
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

    public String getEmail() {
        return email;
    }

    public String getRname() {
        return rname;
    }

    public String getId_number() {
        return id_number;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public void setId_number(String id_number) {
        this.id_number = id_number;
    }

    @Override
    public String toString() {
        return "RealUser{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", email='" + email + '\'' +
                ", rname='" + rname + '\'' +
                ", id_number='" + id_number + '\'' +
                '}';
    }
}
