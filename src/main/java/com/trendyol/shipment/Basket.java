package com.trendyol.shipment;

import java.util.List;
import java.util.stream.Collectors;

public class Basket {

    private final static Integer THRESHOLD_VALUE = 3;
    private List<Product> products;

    public ShipmentSize getShipmentSize() {
        if (hasThreeOrMoreProducts()) {
            for (ShipmentSize size : ShipmentSize.values()) {
                if (hasThreeOrMoreSameShipmentSize(size)) {
                    return getOneBiggerSize(size);
                }
            }
        }
        return getBiggestShipmentSize();
    }

    private Boolean hasThreeOrMoreSameShipmentSize(ShipmentSize shipmentSize) {
        List<ShipmentSize> shipmentSizes = products.stream().map(Product::getSize).collect(Collectors.toList());
        return shipmentSizes.stream().filter(x -> x == shipmentSize).count() >= THRESHOLD_VALUE;
    }

    private Boolean hasThreeOrMoreProducts() {
        return products.size() >= THRESHOLD_VALUE;
    }

    private ShipmentSize getBiggestShipmentSize() {
        ShipmentSize biggestShipmentSize = ShipmentSize.SMALL;
        for (Product product : products) {
            biggestShipmentSize = product.getSize().compare(biggestShipmentSize);
        }
        return biggestShipmentSize;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    private ShipmentSize getOneBiggerSize(ShipmentSize size) {
        switch (size) {
            case SMALL:
                return ShipmentSize.MEDIUM;
            case MEDIUM:
                return ShipmentSize.LARGE;
            default:
                return ShipmentSize.X_LARGE;
        }
    }
}
