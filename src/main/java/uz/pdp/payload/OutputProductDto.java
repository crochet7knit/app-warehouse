package uz.pdp.payload;

import lombok.Data;
import uz.pdp.entity.Output;
import uz.pdp.entity.Product;

@Data
public class OutputProductDto {

    private Integer productId;

    private Double amount;

    private Double price;

    private Integer outputId;
}
