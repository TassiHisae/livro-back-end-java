package com.shoppingapi.service;

import com.shoppingapi.dto.DTOConverter;
import com.shoppingapi.dto.ShopDTO;
import com.shoppingapi.dto.ShopReportDTO;
import com.shoppingapi.repository.ReportRepository;
import com.shoppingapi.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportService {

    @Autowired
    private ShopRepository shopRepository;

    public List<ShopDTO> getShopByFilters(Date initialDate, Date finalDate, Float minValue) {
        return shopRepository.getShopByFilters(initialDate, finalDate, minValue)
                .stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }

    public ShopReportDTO getReportByDate(Date initialDate, Date finalDate) {
        return shopRepository.getReportByDate(initialDate, finalDate);
    }
}
