package besysoft.tiendaRest.controller;

import besysoft.tiendaRest.exception.EntityNotFoundException;
import besysoft.tiendaRest.model.Product;
import besysoft.tiendaRest.service.ProductService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.AttributeNotFoundException;
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

    @SneakyThrows
    @GetMapping("/getCodigo/{codigo}")
    ResponseEntity<?> getProductCodigo(@PathVariable Long codigo){
        if (productService.findAllByCodigo(codigo) == null){
            throw new EntityNotFoundException("el producto no existe");
        }
        return ResponseEntity.ok(productService.findAllByCodigo(codigo));

    }
}
