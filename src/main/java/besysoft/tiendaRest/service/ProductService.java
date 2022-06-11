package besysoft.tiendaRest.service;

import besysoft.tiendaRest.exception.EntityCodeException;
import besysoft.tiendaRest.exception.EntityNotFoundException;
import besysoft.tiendaRest.model.Product;
import besysoft.tiendaRest.model.Seller;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ProductService {


    //Service creado de productos para cumplir todas las funciones necesarias del ejercicio

    private List<Product> products;

    //crear producto
    @SneakyThrows
    public Product create(Product product){
        products.stream()
                .filter(p -> p.getCode() == product.getCode())
                .findFirst()
                .orElseThrow(() -> new EntityCodeException(
                        "This Code Already exist, please choose another"));
        if (products == null){
            products = new ArrayList<>();
        }
        products.add(product);
        return product;
    }

    //listar todos los productos
    public List<Product> getAll() {
        return products;
    }

    //buscar un producto por codigo
    @SneakyThrows
    public Product findByCodigo(Long codigo){
        return products.stream()
                .filter(p -> Objects.equals(p.getCode(), codigo))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("el producto no existe"));
    }

    //buscar productos por nombre
    @SneakyThrows
    public List<Product> findByName(String name){
        List<Product> productList = products.stream()
                .filter(p -> Objects.equals(p.getName(), name))
                .toList();
        if (productList.isEmpty()){
            throw new EntityNotFoundException("product name not exist");
        }
        return productList;
    }

    //buscar productos por precuio
    @SneakyThrows
    public List<Product> findByPrice(Double price){
        List<Product> productList = products.stream()
                .filter(p -> Objects.equals(p.getPrice(), price))
                .toList();
        if (productList.isEmpty()){
            throw new EntityNotFoundException("product price not exist");
        }
        return productList;
    }

    //buscar productos por categoria
    @SneakyThrows
    public List<Product> findByCategory(String category){
        List<Product> productList = products.stream()
                .filter(p -> Objects.equals(p.getCategory(), category))
                .toList();
        if (productList.isEmpty()){
            throw new EntityNotFoundException("product category not exist");
        }
        return productList;

    }


    //metodo para crear algunos productos por defecto
    public void loadProduct(){
        this.products = new ArrayList<>();

        Product product1 = Product.builder().code(48L).name("fideos").price(7.50).category("alimento").build();
        Product product2 = Product.builder().code(54L).name("cereales").price(3.75).category("alimento").build();
        Product product3 = Product.builder().code(98L).name("guantes").price(10.50).category("utensilio").build();
        Product product4 = Product.builder().code(245L).name("mouse").price(2500.90).category("periferico").build();
        Product product5 = Product.builder().code(785L).name("motoG5").price(43500.76).category("celular").build();
        Product product6 = Product.builder().code(4739L).name("monitor").price(37800.00).category("periferico").build();

        this.products.add(product1);
        this.products.add(product2);
        this.products.add(product3);
        this.products.add(product4);
        this.products.add(product5);
        this.products.add(product6);


    }




}
