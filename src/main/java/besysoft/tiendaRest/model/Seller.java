package besysoft.tiendaRest.model;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Seller {

    //creando una clase vendedor con todas los valores que se pideron(codigo, nombre, salario, y para guardar una relacion con producto le
    // agregamos una lista de productos)
    @NotNull(message= "code number value is required")
    @Min(value = 0, message = "numericField not be negative")
    private Long code;

    @NotBlank(message = "Seller name cannot be blank")
    private String name;

    @NotNull(message= "salary: positive number value is required")
    @Min(value = 0, message = "salary not be negative")
    private Double salary;

    private List<Product> productList;

}
