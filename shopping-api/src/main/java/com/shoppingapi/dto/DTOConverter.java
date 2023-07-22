package com.shoppingapi.dto;

import com.shoppingapi.model.Item;
import com.shoppingapi.model.Shop;

import java.util.stream.Collectors;

public class DTOConverter {

    public static ShopDTO convert(Shop shop){
        return new ShopDTO()
                .setUserIdentifier(shop.getUserIdentifier())
                .setTotal(shop.getTotal())
                .setDate(shop.getDate())
                .setItems(shop.getItems()
                        .stream()
                        .map(DTOConverter::convert)
                        .collect(Collectors.toList()));
    }

    public static ItemDTO convert(Item item){
        return new ItemDTO()
                .setProductIdentifier(item.getProductIdentifier())
                .setPrice(item.getPrice());
    }


}
