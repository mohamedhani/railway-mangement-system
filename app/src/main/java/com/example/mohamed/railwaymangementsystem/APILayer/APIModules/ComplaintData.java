package com.example.mohamed.railwaymangementsystem.APILayer.APIModules;

/**
 * Created by mohamed on 7/4/2019.
 */

public class ComplaintData {
    private String complaintMessage , trainId  , ticketNumber , jwt ;

    public ComplaintData(String complaintMessage, String trainId, String ticketNumber ,String jwt) {
        this.complaintMessage = complaintMessage;
        this.trainId = trainId;
        this.ticketNumber = ticketNumber;
        this.jwt=jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getComplaintMessage() {
        return complaintMessage;
    }

    public void setComplaintMessage(String complaintMessage) {
        this.complaintMessage = complaintMessage;
    }

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }
}
