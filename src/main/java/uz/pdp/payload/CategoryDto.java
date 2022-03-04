package uz.pdp.payload;

import lombok.Data;

@Data
public class CategoryDto {

    private String name;

    private Integer parentCategoryId;
}
