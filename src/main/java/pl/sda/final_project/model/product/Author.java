package pl.sda.final_project.model.product;

import pl.sda.final_project.dto.ProductDto;

import javax.persistence.Embeddable;

@Embeddable
public class Author {

    private String authorName;
    private String authorSurname;

    public static Author apply(ProductDto productDto) {
        Author authorToSave = new Author();
        authorToSave.authorName = productDto.getAuthorName();
        authorToSave.authorSurname = productDto.getAuthorSurname();
        return authorToSave;
    }

}
