package org.example;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ProduitsStocks {
    private SimpleStringProperty productStocksCode;
    private SimpleStringProperty productStocksName;
    private SimpleStringProperty productStocksUnitMeasure;
    private SimpleIntegerProperty productStocksQuantity;

    public ProduitsStocks(String productStocksCode, String productStocksName, String productStocksUnitMeasure, int productStocksQuantity) {
        this.productStocksCode = new SimpleStringProperty(productStocksCode);
        this.productStocksName = new SimpleStringProperty(productStocksName);
        this.productStocksUnitMeasure = new SimpleStringProperty(productStocksUnitMeasure);
        this.productStocksQuantity = new SimpleIntegerProperty(productStocksQuantity);
    }

    public String getProductStocksCode() {
        return productStocksCode.get();
    }

    public void setProductStocksCode(String productStocksCode) {
        this.productStocksCode = new SimpleStringProperty(productStocksCode);
    }
    public String getProductStocksName() {
        return productStocksName.get();
    }

    public void setProductStocksName(String productStocksName) {
        this.productStocksName = new SimpleStringProperty(productStocksName);
    }

    public String getProductStocksUnitMeasure() {
        return productStocksUnitMeasure.get();
    }

    public void setProductStocksUnitMeasure(String productStocksUnitMeasure) {
        this.productStocksUnitMeasure = new SimpleStringProperty(productStocksUnitMeasure);
    }

    public int getProductStocksQuantity() {
        return productStocksQuantity.get();
    }

    public void setProductStocksQuantity(int productStocksQuantity) {
        this.productStocksQuantity = new SimpleIntegerProperty(productStocksQuantity);
    }
}
