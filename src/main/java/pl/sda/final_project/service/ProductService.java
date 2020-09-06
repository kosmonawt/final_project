package pl.sda.final_project.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.sda.final_project.dto.ProductDto;
import pl.sda.final_project.model.product.ProductCategoryEntity;
import pl.sda.final_project.model.product.ProductEntity;
import pl.sda.final_project.repo.ProductRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private ProductRepo productRepo;
    private ProductCategoryService productCategoryService;

    public ProductService(ProductRepo productRepo, ProductCategoryService productCategoryService) {
        this.productRepo = productRepo;
        this.productCategoryService = productCategoryService;
    }

    public void saveProduct(ProductDto productDto) {
        ProductEntity productEntityToSave = ProductEntity.apply(productDto);
        Long productCategoryId = productDto.getProductCategory().getProductCategoryId();
        ProductCategoryEntity category = productCategoryService.findCategoryById(productCategoryId)
                .orElseThrow(() -> new RuntimeException("Can't find category"));
        productEntityToSave.setCategory(category);
        productRepo.save(productEntityToSave);
    }

    public Page<ProductDto> findProducts(Pageable pageable) {

        Page<ProductEntity> page = productRepo.findAll(pageable);
        List<ProductDto> collect = page.stream()
                .map(ProductDto::apply)
                .collect(Collectors.toList());
        return new PageImpl<>(collect, pageable, page.getTotalElements());
    }


}
