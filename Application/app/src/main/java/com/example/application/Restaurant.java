package com.example.application;

public class Restaurant {
    private String rId;
    private String name;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private double stars;
    private String categories;
    private String hours;

    public Restaurant(String id, String name, String address, String city, String state,
                      String zipCode, double stars, String categories, String hours) {
        this.rId = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.stars = stars;
        this.categories = categories;
        this.hours = hours;
    }

    public String getrId() {
        return rId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public double getStars() {
        return stars;
    }

    public String getCategories() {
        return categories;
    }

    public String getHours() {
        return hours;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setStars(double stars) {
        this.stars = stars;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }
}
