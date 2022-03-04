package uz.pdp.payload;

import lombok.Data;
import uz.pdp.entity.Client;
import uz.pdp.entity.Currency;
import uz.pdp.entity.Warehouse;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Data
public class OutputDto {

    private Timestamp date;

    private Integer warehouseId;

    private Integer clientId;

    private Integer currencyId;

    private String factureNumber;

    private String code;
}
