package org.example.order;

import org.example.courier.Courier;

import java.lang.reflect.Array;

public class Credentials {
    private String firstName;
    private String lastName;
    private String address;
    private String metroStation;
    private String phone;
    private Number rentTime;
    private String deliveryDate;
    private String comment;
    private String[] color;

    public Credentials() {
    }
    public Credentials (String firstName, String lastName, String address, String metroStation, String phone, Number rentTime, String deliveryDate, String comment, String[] color)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }
    public static Credentials from (Order order) {
        return new Credentials(order.getFirstName(), order.getLastName(), order.getAddress(), order.getMetroStation(), order.getPhone(), order.getRentTime(), order.getDeliveryDate(), order.getComment(), order.getColor());
    }

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getAddress() {
        return address;
    }
    public String getMetroStation() {
        return metroStation;
    }
    public String getPhone() {
        return phone;
    }
    public Number getRentTime() {
        return rentTime;
    }
    public String getDeliveryDate() {
        return deliveryDate;
    }
    public String getComment() {
        return comment;
    }
    public String[] getColor() {
        return color;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setMetroStation(String metroStation) {
        this.metroStation = metroStation;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setRentTime(Number rentTime) {
        this.rentTime = rentTime;
    }
    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public void setColor(String[] color) {
        this.color = color;
    }

}
