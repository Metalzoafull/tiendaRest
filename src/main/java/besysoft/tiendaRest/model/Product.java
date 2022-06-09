package besysoft.tiendaRest.model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class Product {

    private Long codigo;

    @NotBlank(message = "Product name cannot be blank")
    private String name;

    @NotNull(message= "numericField: positive number value is required")
    @Min(value = 0, message = "numericField not be negative")
    private Double price;

    @NotBlank(message = "Category cannot be empty")
    private String category;

}
