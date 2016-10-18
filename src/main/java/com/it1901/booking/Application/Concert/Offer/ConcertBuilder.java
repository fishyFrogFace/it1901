package com.it1901.booking.Application.Concert.Offer;

import java.time.LocalDate;

public class ConcertBuilder {

    protected Integer eventID;
    protected LocalDate startDate;
    protected Integer duration;
    protected Integer ticketPrice;
    protected Integer ticketsSold;
    protected Integer artistID;
    protected Integer offerID;
    protected Integer stageID;

    public static ConcertBuilder event() {
        return new ConcertBuilder();
    }

    public ConcertBuilder withEventID(Integer eventID) {
        this.eventID = eventID;
        return this;
    }

    public ConcertBuilder withStartDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public ConcertBuilder withDuration(Integer duration) {
        this.duration = duration;
        return this;
    }

    public ConcertBuilder withTicketPrice(Integer ticketPrice) {
        this.ticketPrice = ticketPrice;
        return this;
    }

    public ConcertBuilder withTicketsSold(Integer ticketsSold) {
        this.ticketsSold = ticketsSold;
        return this;
    }

    public ConcertBuilder withArtistID(Integer artistID) {
        this.artistID = artistID;
        return this;
    }

    public ConcertBuilder withOfferID(Integer offerID) {
        this.offerID = offerID;
        return this;
    }

    public ConcertBuilder withStageID(Integer stageID) {
        this.stageID = stageID;
        return this;
    }

    public Concert build() {
        return new Concert(this);
    }
}
