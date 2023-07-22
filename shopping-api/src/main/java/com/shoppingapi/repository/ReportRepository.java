package com.shoppingapi.repository;

import com.shoppingapi.dto.ShopReportDTO;
import com.shoppingapi.model.Shop;

import java.util.Date;
import java.util.List;

public interface ReportRepository {

    public List<Shop> getShopByFilters(Date initialDate, Date finalDate, Float minValue);
    public ShopReportDTO getReportByDate(Date initialDate, Date finalDate);
}
