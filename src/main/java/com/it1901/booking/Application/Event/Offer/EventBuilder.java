package com.it1901.booking.Application.Event.Offer;

import java.time.LocalDate;

public class EventBuilder {

    protected Integer eventID;
    protected LocalDate startDate;
    protected Integer duration;
    protected Integer ticketPrice;
    protected Integer ticketsSold;
    protected Integer artistID;
    protected Integer offerID;
    protected Integer stageID;

    public static EventBuilder event() {
        return new EventBuilder();
    }

    public EventBuilder withEventID(Integer eventID) {
        this.eventID = eventID;
        return this;
    }

    public EventBuilder withStartDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public EventBuilder withDuration(Integer duration) {
        this.duration = duration;
        return this;
    }

    public EventBuilder withTicketPrice(Integer ticketPrice) {
        this.ticketPrice = ticketPrice;
        return this;
    }

    public EventBuilder withTicketsSold(Integer ticketsSold) {
        this.ticketsSold = ticketsSold;
        return this;
    }

    public EventBuilder withArtistID(Integer artistID) {
        this.artistID = artistID;
        return this;
    }

    public EventBuilder withOfferID(Integer offerID) {
        this.offerID = offerID;
        return this;
    }

    public EventBuilder withStageID(Integer stageID) {
        this.stageID = stageID;
        return this;
    }

    public Event build() {
        return new Event(this);
    }
}
