package pl.sda.final_project.service;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import pl.sda.final_project.dto.BasketItemDto;
import pl.sda.final_project.dto.ProductDto;

import java.util.HashSet;
import java.util.Set;

@Service
@SessionScope
public class BasketService {

    private final Set<BasketItemDto> productList = new HashSet<>();

    public void addProductToBasket(ProductDto productDto) {
        BasketItemDto basketItemDto = new BasketItemDto(1, productDto);
        productList.add(basketItemDto);
    }

    public Set<BasketItemDto> getProductList() {
        return productList;
    }
}
