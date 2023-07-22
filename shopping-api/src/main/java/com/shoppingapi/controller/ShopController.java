package com.shoppingapi.controller;

import com.shoppingapi.service.ShopService;
import com.shoppingapi.dto.ShopDTO;
import com.shoppingapi.dto.ShopReportDTO;
import com.shoppingapi.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
public class ShopController {

    @Autowired
    private ShopService shopService;

    @Autowired
    private ReportService reportService;

    @GetMapping("/shopping")
    public List<ShopDTO> getShops() {
        return shopService.getAll();
    }

    @GetMapping("/shopping/shopByUser/{userIdentifier}")
    public List<ShopDTO> getShops(@PathVariable("userIdentifier") String userIdentifier) {
        return shopService.getByUser(userIdentifier);
    }

    @GetMapping("/shopping/shopByDate")
    public List<ShopDTO> getShops(@RequestBody ShopDTO shopDTO) {
        return shopService.getByDate(shopDTO);
    }
//
//    @GetMapping("/shopping/shopByTotal")
//    public List<ShopDTO> getShops(@RequestBody ShopDTO shopDTO){
//        return shopService.getByTotal(shopDTO);
//    }

    @GetMapping("/shopping/{id}")
    public ShopDTO findById(@PathVariable("id") Long id) {
        return shopService.findById(id);
    }

    @PostMapping("/shopping")
    public ShopDTO newShop(@RequestHeader(name = "key", required = true) String key, @Valid @RequestBody ShopDTO shopDTO) {
        return shopService.save(shopDTO, key);
    }

    @GetMapping("/shopping/search")
    public List<ShopDTO> getShopsByFilter(@RequestParam(name = "initialDate", required = true)
                                          @DateTimeFormat(pattern = "dd/MM/yyyy") Date initialDate,
                                          @RequestParam(name = "finalDate", required = false)
                                          @DateTimeFormat(pattern = "dd/MM/yyyy") Date finalDate,
                                          @RequestParam(name = "minValue", required = false) Float minValue) {
        return reportService.getShopByFilters(initialDate, finalDate, minValue);
    }

    @GetMapping("/shopping/report")
    public ShopReportDTO getShopByFilters(@RequestParam(name = "initialDate", required = true)
                                          @DateTimeFormat(pattern = "dd/MM/yyyy") Date initialDate,
                                          @RequestParam(name = "finalDate", required = true)
                                          @DateTimeFormat(pattern = "dd/MM/yyyy") Date finalDate) {
        return reportService.getReportByDate(initialDate, finalDate);
    }
}
