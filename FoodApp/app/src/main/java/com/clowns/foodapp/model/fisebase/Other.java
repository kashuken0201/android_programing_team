package com.clowns.foodapp.model.fisebase;

public class Other {
    private String otherId;
    private String otherName;
    private double price;
    private String url;

    public Other() {
    }

    public Other(String otherName, double otherPrice, String otherUrl) {
        this.otherName = otherName;
        this.price = otherPrice;
        this.url = otherUrl;
    }

    public String getOtherId() {
        return otherId;
    }

    public void setOtherId(String otherId) {
        this.otherId = otherId;
    }

    public String getOtherName() {
        return otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
