package besysoft.tiendaRest.controller;

import besysoft.tiendaRest.exception.AtributeNotMeetRequirements;
import besysoft.tiendaRest.exception.EntityNotFoundException;
import besysoft.tiendaRest.model.Product;
import besysoft.tiendaRest.service.ProductService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;


    @PostMapping("/create")
    ResponseEntity<?> createProduct(@Valid @RequestBody Product product){
        productService.create(product);
        return ResponseEntity.ok("fue creado exitosamente");
    }

    @GetMapping("/getAll")
    ResponseEntity<?> getAllProduct(){
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("/getCodigo/{codigo}")
    ResponseEntity<?> getProductCodigo(@PathVariable Long codigo){
        return ResponseEntity.ok(productService.findByCodigo(codigo));

    }

    @GetMapping("/findByName/{name}")
    ResponseEntity<?> findByName(@PathVariable String name){
        return ResponseEntity.ok(productService.findByName(name));
    }

    @GetMapping("/findByPrice/{price}")
    ResponseEntity<?> findByPrice(@PathVariable Double price){
        return ResponseEntity.ok(productService.findByPrice(price));
    }

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
