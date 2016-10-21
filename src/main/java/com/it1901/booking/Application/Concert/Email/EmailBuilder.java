package com.it1901.booking.Application.Concert.Email;

public class EmailBuilder {

    protected Integer emailID;
    protected String emailSubject;
    protected String emailBody;
    protected Integer offerID;

    public static EmailBuilder email() {
        return new EmailBuilder();
    }

    public EmailBuilder withEmailID(int emailID) {
        this.emailID = emailID;
        return this;
    }

    public EmailBuilder withEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
        return this;
    }

    private String parseBody(String emailBody) {
        String htmlBody = emailBody.replaceAll("(\r\n|\n)", "<br />");
        return htmlBody;
    }

    public EmailBuilder withEmailBody(String emailBody) {
        this.emailBody = parseBody(emailBody);
        return this;
    }

    public EmailBuilder withOfferID(int offerID) {
        this.offerID = offerID;
        return this;
    }

    public Email build() {
        return new Email(this);
    }
}
