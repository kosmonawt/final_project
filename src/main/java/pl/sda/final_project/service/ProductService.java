package pl.sda.final_project.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.sda.final_project.dto.ProductDto;
import pl.sda.final_project.model.product.ProductCategoryEntity;
import pl.sda.final_project.model.product.ProductEntity;
import pl.sda.final_project.model.product.ProductType;
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
        ProductCategoryEntity category = productCategoryService.findCategoryById(productDto.getProductCategory().getProductCategoryId())
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


    public ProductDto findProductById(Long id) {
        return productRepo.findById(id)
                .map(ProductDto::apply)
                .orElseThrow(() -> new RuntimeException("Blow up in product service"));
    }

    public boolean deleteProductById(Long id) {

        if (productRepo.findById(id).isPresent()) {
            productRepo.deleteById(id);
            return true;
        } else
            return false;
    }

    public boolean updateProduct(Long id, ProductDto product) {

        if (productRepo.findById(id).isPresent()) {
            ProductEntity productToSave = productRepo.findById(id).get();
            Long productCategoryId = product.getProductCategory().getProductCategoryId();
            productToSave.setTitle(product.getProductTitle());
            productToSave.setDescription(product.getProductDescription());
            productToSave.setImageUrl(product.getProductImageUrl());
            productToSave.setPrice(product.getProductPrice());
            productToSave.setCategory(productCategoryService.findCategoryById(productCategoryId).orElseThrow(() -> new RuntimeException("Category not found")));
            productToSave.setProductType(ProductType.valueOf(product.getProductType()));
            productRepo.save(productToSave);
            return true;
        } else return false;
    }
}
