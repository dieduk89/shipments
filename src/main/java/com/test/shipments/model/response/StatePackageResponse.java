package com.test.shipments.model.response;

public class StatePackageResponse {
    private String packageCode;
    private String state;

    public StatePackageResponse(String packageCode, String state) {
        this.packageCode = packageCode;
        this.state = state;
    }

    public String getPackageCode() {
        return packageCode;
    }

    public void setPackageCode(String packageCode) {
        this.packageCode = packageCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
