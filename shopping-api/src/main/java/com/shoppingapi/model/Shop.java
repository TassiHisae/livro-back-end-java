package com.shoppingapi.model;

import com.shoppingapi.dto.ShopDTO;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "shop")
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userIdentifier;
    private Date date;
    private Float total;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "item", joinColumns = @JoinColumn(name = "shop_id"))
    private List<Item> items;

    public static Shop convert(ShopDTO shopDTO) {
        return new Shop()
                .setUserIdentifier(shopDTO.getUserIdentifier())
                .setTotal(shopDTO.getTotal())
                .setDate(shopDTO.getDate())
                .setItems(shopDTO.getItems()
                        .stream()
                        .map(Item::convert)
                        .collect(Collectors.toList()));
    }

    public Long getId() {
        return id;
    }

    public Shop setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUserIdentifier() {
        return userIdentifier;
    }

    public Shop setUserIdentifier(String userIdentifier) {
        this.userIdentifier = userIdentifier;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public Shop setDate(Date date) {
        this.date = date;
        return this;
    }

    public Float getTotal() {
        return total;
    }

    public Shop setTotal(Float total) {
        this.total = total;
        return this;
    }

    public List<Item> getItems() {
        return items;
    }

    public Shop setItems(List<Item> items) {
        this.items = items;
        return this;
    }
}
