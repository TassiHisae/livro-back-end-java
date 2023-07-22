package com.shoppingapi.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public class ShopDTO {

    @NotBlank
    private String userIdentifier;

    private Date date;

    private Float total;

    @NotNull
    private List<ItemDTO> items;

    public String getUserIdentifier() {
        return userIdentifier;
    }

    public ShopDTO setUserIdentifier(String userIdentifier) {
        this.userIdentifier = userIdentifier;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public ShopDTO setDate(Date date) {
        this.date = date;
        return this;
    }

    public Float getTotal() {
        return total;
    }

    public ShopDTO setTotal(Float total) {
        this.total = total;
        return this;
    }

    public List<ItemDTO> getItems() {
        return items;
    }

    public ShopDTO setItems(List<ItemDTO> items) {
        this.items = items;
        return this;
    }
}
