package com.it1901.booking.Application.Event.Offer;

public class OfferBuilder {

    protected Integer offerID;
    protected Offer.offerState state;
    protected Integer bookerID;
    protected Integer managerID;

    public static OfferBuilder offer() {
        return new OfferBuilder();
    }

    public OfferBuilder withOfferID(Integer offerID) {
        this.offerID = offerID;
        return this;
    }

    public OfferBuilder withOfferSubject(Offer.offerState state) {
        this.state = state;
        return this;
    }

    public OfferBuilder withOfferBody(Integer bookerID) {
        this.bookerID = bookerID;
        return this;
    }

    public OfferBuilder withManagerID(Integer managerID) {
        this.managerID = managerID;
        return this;
    }

    public Offer build() {
        return new Offer(this);
    }
}
