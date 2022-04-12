package mk.ukim.finki.library.Model.DTO;

import lombok.Data;
import mk.ukim.finki.library.Model.Enumerations.Category;

import javax.persistence.*;


@Data
public class BookDTO {


    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;

    private Long authorId;

    private Integer availableCopies;

    public BookDTO() {
    }

    public BookDTO(String name, Category category, Long authorId, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.authorId = authorId;
        this.availableCopies = availableCopies;
    }
}
