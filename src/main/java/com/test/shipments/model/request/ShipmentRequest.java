package com.test.shipments.model.request;

public class ShipmentRequest {
    private String source;
    private String destination;
    private Double weight;
    private String description;

    public ShipmentRequest(String source, String destination, Double weight, String description) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
        this.description = description;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
