package com.json;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


@RestController
public class JsonController {

    @GetMapping("/flights/flight")
    public Flight getFlight() {
        Date departs = new Date(Date.parse("April 21 2017 02:34:00 PM UTC"));
        Passenger passenger = new Passenger("Some name", "Some other name");
        Integer price = 200;
        Ticket ticket = new Ticket(passenger, price);
        List<Ticket> tickets = Arrays.asList(ticket);
        Flight flight = new Flight(departs, tickets);
        return flight;
    }

    @GetMapping("/flights")
    public Flight getFlights() {
        Date departs = new Date(Date.parse("April 21 2017 02:34:00 PM UTC"));
        Passenger passenger1 = new Passenger("Some name", "Some other name");
        Integer price1 = 200;
        Ticket ticket1 = new Ticket(passenger1, price1);
        Passenger passenger2 = new Passenger("Some other name", null);
        Integer price2 = 400;
        Ticket ticket2 = new Ticket(passenger2, price2);
        List<Ticket> tickets = Arrays.asList(ticket1, ticket2);
        Flight flight = new Flight(departs, tickets);
        return flight;
    }

    @PostMapping("/flights/tickets/total")
    public TicketTotal getTotal(@RequestBody List<Ticket> tickets) {
        Integer sum = 0;
        Iterator<Ticket> ticketIterator = tickets.iterator();
        while (ticketIterator.hasNext()) {
            sum += ticketIterator.next().getPrice();
        }
        TicketTotal total = new TicketTotal(sum);
        return(total);
    }

    @PostMapping("/some/path")
    public Person getPerson(@RequestBody Person person) {
        return(person);
    }

    @PostMapping("/some/path/gsonSerialize")
    public Person getPersonGsonSerialize(@RequestBody Person person) {
        return(person);
    }

    @PostMapping("/jr/string-example")
    public Pets getPetsGsonFile(@RequestBody Pets pets) {
        return (pets);
    }
}

