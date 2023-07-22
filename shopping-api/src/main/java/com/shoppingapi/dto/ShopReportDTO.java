package com.shoppingapi.dto;

public class ShopReportDTO {

    private Integer count;
    private Double total;
    private Double mean;

    public Integer getCount() {
        return count;
    }

    public ShopReportDTO setCount(Integer count) {
        this.count = count;
        return this;
    }

    public Double getTotal() {
        return total;
    }

    public ShopReportDTO setTotal(Double total) {
        this.total = total;
        return this;
    }

    public Double getMean() {
        return mean;
    }

    public ShopReportDTO setMean(Double mean) {
        this.mean = mean;
        return this;
    }
}
