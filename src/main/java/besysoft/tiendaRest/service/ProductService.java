package besysoft.tiendaRest.service;

import besysoft.tiendaRest.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private List<Product> products;

    public Product create(Product product){
        if (products == null){
            products = new ArrayList<>();
        }
        products.add(product);
        return product;
    }

    public List<Product> getAll() {
        return products;
    }

    public Product findAllByCodigo(Long codigo){
        return products.stream().filter(p -> p.getCodigo() == codigo).findFirst().orElse(null);
    }


}
