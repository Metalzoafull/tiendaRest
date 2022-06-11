package besysoft.tiendaRest.model;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    //creando una clase producto con todas los valores que se pideron(codigo, nombre, precio, y categoria)
    //aparte de eso le agregamos anotaciones por si no se cumplen los requisitos necesarios, se mostraran por pantalla en las escepciones
    @NotNull(message= "code number value is required")
    @Min(value = 0, message = "numericField not be negative")
    private Long code;

    @NotBlank(message = "Product name cannot be blank")
    private String name;

    @NotNull(message= "numericField: positive number value is required")
    @Min(value = 0, message = "numericField not be negative")
    private Double price;

    @NotBlank(message = "Category cannot be empty")
    private String category;

}
