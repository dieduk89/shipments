package com.test.shipments.model.response;

import com.test.shipments.model.StateHistoryEntity;

import java.time.LocalDateTime;

public class ShipmentDetail {
    private String source;
    private String destination;
    private String code;
    private String state;
    private LocalDateTime createDate;

    public ShipmentDetail(String source, String destination, String code, String state, LocalDateTime createDate) {
        this.source = source;
        this.destination = destination;
        this.code = code;
        this.state = state;
        this.createDate = createDate;
    }
}
