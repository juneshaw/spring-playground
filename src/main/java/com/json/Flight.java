package com.json;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import java.util.List;

class Flight {
    private static Date departs;
    //    private TicketList tickets;
    private List<Ticket> tickets;

    public Flight() {
    }

    public Flight(Date departs, List<Ticket> tickets) {
        this.departs = departs;
        this.tickets = tickets;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Date getDeparts() {
        return departs;
    }

    public void setDeparts(Date departs) {
        this.departs = departs;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

}