package pl.sda.final_project.model.product;

import pl.sda.final_project.model.user.BaseEntity;

import javax.persistence.Entity;

@Entity
public class ProductCategoryEntity extends BaseEntity {

    private String title;

    public String getTitle() {
        return title;
    }


    public ProductCategoryEntity(String title) {
        this.title = title;
    }

    public ProductCategoryEntity() {
    }


}
