package pl.sda.final_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.final_project.dto.ProductDto;
import pl.sda.final_project.model.product.ProductType;
import pl.sda.final_project.service.ProductService;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    private List<ProductDto> getAllProduct() {

        ProductDto productDto = new ProductDto();
        productDto.setAuthorName("JHSdcjhs");
        productDto.setAuthorSurname("asdasas");
        productDto.setProductPrice(BigDecimal.valueOf(211432553));
        productDto.setProductDescription("sdgdsdscvdsv");
        productDto.setProductTitle("product1");
        productDto.setProductCategoryTitle("Category");

        ProductDto productDto2 = new ProductDto();
        productDto2.setAuthorName("JHSdcjhs");
        productDto2.setAuthorSurname("asdasas");
        productDto2.setProductPrice(BigDecimal.valueOf(211432553));
        productDto2.setProductDescription("sdgdsdscvdsv");
        productDto2.setProductTitle("product2");
        productDto2.setProductCategoryTitle("Category");

        return List.of(productDto, productDto2);
    }

    @GetMapping("/all")
    public ModelAndView getProducts() {

        ModelAndView modelAndView = new ModelAndView("productList");
        modelAndView.addObject("products", getAllProduct());

        return modelAndView;

    }

    @GetMapping("/add")
    public ModelAndView saveProductForm() {
        ModelAndView modelAndView = new ModelAndView("productAdd");
        ProductDto productDto = new ProductDto();
        modelAndView.addObject("productToSave", productDto);
        modelAndView.addObject("productTypes", ProductType.values());
        return modelAndView;
    }

    @PostMapping("/add")
    public String saveProduct(ProductDto productDto) {
        productService.saveProduct(productDto);

        return "redirect:/product/all";
    }

}
