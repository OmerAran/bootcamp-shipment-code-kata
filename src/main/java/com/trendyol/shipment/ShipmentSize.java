package com.trendyol.shipment;

public enum ShipmentSize {

    SMALL,
    MEDIUM,
    LARGE,
    X_LARGE;
    
    public ShipmentSize compare(ShipmentSize size) {
        if (this.ordinal() >= size.ordinal()) {
            return this;
        }
        else {
            return size;
        }
    }
}
