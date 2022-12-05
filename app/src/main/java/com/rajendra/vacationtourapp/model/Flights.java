package com.rajendra.vacationtourapp.model;

public class Flights {
    String to;
    String from;
    String arrival;
    String departure;
    String price;
    String name;

    public Flights() {}

    public Flights(String to, String from, String arrival, String departure, String price, String name) {
        this.to = to;
        this.from = from;
        this.arrival = arrival;
        this.departure = departure;
        this.price = price;
        this.name = name;
    }

    public String getTo() { return to; }

    public void setTo(String to) { this.to = to; }

    public String getFrom() { return from; }

    public void setFrom(String from) { this.from = from; }

    public String getArrival() { return arrival; }

    public void setArrival(String arrival) { this.arrival = arrival; }

    public String getDeparture() { return departure; }

    public void setDeparture(String departure) { this.departure = departure; }

    public String getPrice() { return price; }

    public void setPrice(String price) { this.price = price; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }
}
