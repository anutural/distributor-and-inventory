package com.am.reaprich.reaprichbackend.data.entities.inventory.inventoryprovider;

public enum ItemState {
    IN_COMPANY(0),
    IN_COMPANY_BOOKED(1),
    IN_WAREHOUSE(2),
    IN_WAREHOUSE_BOOKED(3),
    SOLD(4),
    REMOVED(5);

    private int value;
    private ItemState(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
