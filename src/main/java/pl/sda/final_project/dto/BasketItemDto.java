package pl.sda.final_project.dto;


public class BasketItemDto {

    private Integer amount;
    private ProductDto productDto;

    public BasketItemDto(Integer amount, ProductDto productDto) {
        this.amount = amount;
        this.productDto = productDto;
    }
}
