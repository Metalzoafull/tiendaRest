package besysoft.tiendaRest.controller;


import besysoft.tiendaRest.model.Product;
import besysoft.tiendaRest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/product")// le aclaro la runa donde quiero que se llame
public class ProductController {

    @Autowired
    private ProductService productService;


    //peticion para crear el producto
    @PostMapping("/create")
    ResponseEntity<?> createProduct(@Valid @RequestBody Product product) {
        productService.create(product);
        return ResponseEntity.ok("fue creado exitosamente");
    }

    //peticion para listar productos
    @GetMapping("/getAll")
    ResponseEntity<?> getAllProduct() {
        return ResponseEntity.ok(productService.getAll());
    }

    //buscar producto por codigo
    @GetMapping("/getCodigo/{codigo}")
    ResponseEntity<?> getProductCodigo(@PathVariable Long codigo) {
        return ResponseEntity.ok(productService.findByCodigo(codigo));

    }

    //buscar productos por nombre
    @GetMapping("/findByName/{name}")
    ResponseEntity<?> findByName(@PathVariable String name) {
        return ResponseEntity.ok(productService.findByName(name));
    }

    //buscar productos por precio
    @GetMapping("/findByPrice/{price}")
    ResponseEntity<?> findByPrice(@PathVariable Double price){
        return ResponseEntity.ok(productService.findByPrice(price));
    }

    //buscar productos por categoria
    @GetMapping("/findByCategory/{category}")
    ResponseEntity<?> findByCategory(@PathVariable String category){
        return ResponseEntity.ok(productService.findByCategory(category));

    }

    @GetMapping("/loadProducts")
    ResponseEntity<?> loadProducs(){

        productService.loadProduct();
        return ResponseEntity.ok("productos cargados");
    }


}
