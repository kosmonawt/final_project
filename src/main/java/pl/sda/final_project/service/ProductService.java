package pl.sda.final_project.service;

import org.springframework.stereotype.Service;
import pl.sda.final_project.dto.ProductDto;
import pl.sda.final_project.model.product.ProductEntity;
import pl.sda.final_project.repo.ProductRepo;

@Service
public class ProductService {

    private ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public void saveProduct(ProductDto productDto) {
        ProductEntity productEntityToSave = ProductEntity.apply(productDto);
        //TODO product category
        productRepo.save(productEntityToSave);
    }

}
