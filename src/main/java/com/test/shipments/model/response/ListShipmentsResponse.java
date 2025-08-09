package com.test.shipments.model.response;
import java.util.List;

public class ListShipmentsResponse {
    private List<ShipmentDetail> list;
    private int totalElements;
    private int totalPages;

    public ListShipmentsResponse(List<ShipmentDetail> list, int totalElements, int totalPages) {
        this.list = list;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    public List<ShipmentDetail> getList() {
        return list;
    }

    public void setList(List<ShipmentDetail> list) {
        this.list = list;
    }
}
