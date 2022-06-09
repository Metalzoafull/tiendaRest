package besysoft.tiendaRest.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.NonNull;

import java.util.List;

public class Seller {

    private Long id;

    @NonNull
    private String name;

    @NonNull
    private Double salary;

    private List<Product> productList;

}
