package com.json;

public class Ticket {
    private  Passenger passenger;
    private  Integer price;

    public Ticket() {}

    public Ticket(Passenger passenger, Integer price) {

        this.passenger = passenger;
        this.price = price;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

}