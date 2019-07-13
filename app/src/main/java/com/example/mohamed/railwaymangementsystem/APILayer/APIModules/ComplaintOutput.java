package com.example.mohamed.railwaymangementsystem.APILayer.APIModules;

/**
 * Created by mohamed on 7/6/2019.
 */

public class ComplaintOutput {
    String ticketId , message , trainNumber , email ;

    public ComplaintOutput(String ticketId, String message, String trainNumber, String email) {
        this.ticketId = ticketId;
        this.message = message;
        this.trainNumber = trainNumber;
        this.email = email;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
