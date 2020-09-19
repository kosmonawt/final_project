package pl.sda.final_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.final_project.dto.ProductDto;
import pl.sda.final_project.model.product.ProductType;
import pl.sda.final_project.service.ProductCategoryService;
import pl.sda.final_project.service.ProductService;

@Controller
public class ProductDetailController {

    private final ProductService productService;
    private final ProductCategoryService productCategoryService;

    public ProductDetailController(ProductService productService, ProductCategoryService productCategoryService) {
        this.productService = productService;
        this.productCategoryService = productCategoryService;
    }


    @GetMapping("/product/{id}")
    public ModelAndView showProductDetails(@PathVariable Long id) {
        return new ModelAndView("productDetails", "product", productService.findProductById(id));
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {

        if (productService.deleteProductById(id)) {
            return "index";
        } else
            return "cantFindProduct";
    }

    @GetMapping("/update/{id}")
    public ModelAndView showUpdateProductForm(@PathVariable Long id) {
        ModelAndView model = new ModelAndView();
        model.addObject("productCategories", productCategoryService.findProductCategory());
        model.addObject("updateProduct", productService.findProductById(id));
        model.addObject("productTypes", ProductType.values());
        model.setViewName("updateProduct");
        return model;

    }

    @PostMapping("/update/{id}")
    public ModelAndView updateProduct(@PathVariable Long id, ProductDto product) {

        if (productService.updateProduct(id, product)) {
            return new ModelAndView("index");
        } else
            return new ModelAndView("updateProductError");
    }

}
