package com.shoppingapi.service;

import com.shoppingapi.client.ProductService;
import com.shoppingapi.client.UserService;
import com.shoppingapi.repository.ShopRepository;
import com.shoppingapi.dto.DTOConverter;
import com.shoppingapi.dto.ItemDTO;
import com.dto.ProductDTO;
import com.shoppingapi.dto.ShopDTO;
import com.shoppingapi.model.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShopService {

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    public List<ShopDTO> getAll() {
        return shopRepository.findAll()
                .stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }

    public List<ShopDTO> getByUser(String userIdentifier) {
        return shopRepository.findAllByUserIdentifier(userIdentifier)
                .stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }

    public List<ShopDTO> getByDate(ShopDTO shopDTO) {
        return shopRepository.findAllByDateGreaterThan(shopDTO.getDate())
                .stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }

    public List<ShopDTO> getByTotal(ShopDTO shopDTO) {
        return shopRepository.findAllByTotalGreaterThan(shopDTO.getTotal())
                .stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }

    public ShopDTO findById(Long shopId) {
        Optional<Shop> shop = shopRepository.findById(shopId);

        return shop.map(DTOConverter::convert).orElse(null);
    }

    public ShopDTO save(ShopDTO shopDTO, String key) {

        if (Objects.nonNull(userService.getUserByCpfAndKey(shopDTO.getUserIdentifier(), key)) && validateProducts(shopDTO.getItems())) {

            shopDTO.setTotal(shopDTO.getItems()
                    .stream()
                    .map(ItemDTO::getPrice)
                    .reduce((float) 0, Float::sum));

            shopDTO.setDate(new Date());

            return DTOConverter.convert(shopRepository.save(Shop.convert(shopDTO)));
        }

        return null;
    }

    private boolean validateProducts(List<ItemDTO> items) {
        for (ItemDTO item : items) {
            ProductDTO productDTO = productService.getProductByIdentifier(item.getProductIdentifier());

            if (Objects.isNull(productDTO)) {
                return false;
            }
            item.setPrice(productDTO.getPrice());
        }

        return true;
    }
}
