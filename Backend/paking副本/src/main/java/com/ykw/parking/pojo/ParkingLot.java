package com.ykw.parking.pojo;

public class ParkingLot {
    private int id;
    private String pid;
    private int identify;//认筹
    private int open;//开盘
    private String number;//编号
    private double price;//价格
    private String explain;//说明
    private int sale;//销售情况
    public ParkingLot(int id, String pid, int identify, int open, String number, double price, String explain, int sale) {
        this.id = id;
        this.pid = pid;
        this.identify = identify;
        this.open = open;
        this.number = number;
        this.price = price;
        this.explain = explain;
        this.sale = sale;
    }

    public ParkingLot() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public void setIdentify(int identify) {
        this.identify = identify;
    }

    public void setOpen(int open) {
        this.open = open;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }

    public int getId() {
        return id;
    }

    public String getPid() {
        return pid;
    }

    public int getIdentify() {
        return identify;
    }

    public int getOpen() {
        return open;
    }

    public String getNumber() {
        return number;
    }

    public double getPrice() {
        return price;
    }

    public String getExplain() {
        return explain;
    }

    public int getSale() {
        return sale;
    }

    @Override
    public String toString() {
        return "ParkingLot{" +
                "id='" + id + '\'' +
                ", pid='" + pid + '\'' +
                ", identify=" + identify +
                ", open=" + open +
                ", number='" + number + '\'' +
                ", price=" + price +
                ", explain='" + explain + '\'' +
                ", sale=" + sale +
                '}';
    }
}
