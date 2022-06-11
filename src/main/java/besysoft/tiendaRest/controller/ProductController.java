package besysoft.tiendaRest.controller;


import besysoft.tiendaRest.model.Product;
import besysoft.tiendaRest.model.Seller;
import besysoft.tiendaRest.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Created Product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Create Product",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Product.class)) }),
            @ApiResponse(responseCode = "400", description = "Los datos no cumplen con el minimo requerimiento(y un  mensaje diciendo que datos no cumplen)",
                    content = @Content),
            @ApiResponse(responseCode = "201", description = "This Code Already exist, please choose another",
                    content = @Content) })
    @PostMapping("/create")
    ResponseEntity<?> createProduct(@Valid @RequestBody Product product) {
        productService.create(product);
        return ResponseEntity.ok("fue creado exitosamente");
    }

    //peticion para listar productos
    @Operation(summary = "Find All Products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get All Product",
                    content = { @Content( mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Product.class))) }),
            @ApiResponse(responseCode = "P-402", description = "not exist products",
                    content = @Content)})
    @GetMapping("/getAll")
    ResponseEntity<?> getAllProduct() {
        return ResponseEntity.ok(productService.getAll());
    }

    //buscar producto por codigo
    @Operation(summary = "Find Product By Code")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get Product By Code",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Product.class)) }),
            @ApiResponse(responseCode = "P-402", description = "this code no exist",
                    content = @Content)})
    @GetMapping("/getCodigo/{codigo}")
    ResponseEntity<?> getProductCodigo(@PathVariable Long codigo) {
        return ResponseEntity.ok(productService.findByCodigo(codigo));

    }

    //buscar productos por nombre
    @GetMapping("/findByName/{name}")
    @Operation(summary = "Find Products By Name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get All Products By Name",
                    content = { @Content( mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Product.class))) }),
            @ApiResponse(responseCode = "P-402", description = "There are no products with that name",
                    content = @Content)})
    ResponseEntity<?> findByName(@PathVariable String name) {
        return ResponseEntity.ok(productService.findByName(name));
    }

    //buscar productos por precio
    @Operation(summary = "Find Products By Price")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get All Products By Price",
                    content = { @Content( mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Product.class))) }),
            @ApiResponse(responseCode = "P-402", description = "Not exist products with that price",
                    content = @Content)})
    @GetMapping("/findByPrice/{price}")
    ResponseEntity<?> findByPrice(@PathVariable Double price){
        return ResponseEntity.ok(productService.findByPrice(price));
    }

    //buscar productos por categoria
    @Operation(summary = "Find Products By Category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get All Products By Category",
                    content = { @Content( mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Product.class))) }),
            @ApiResponse(responseCode = "P-402", description = "Not exist products with that category",
                    content = @Content)})
    @GetMapping("/findByCategory/{category}")
    ResponseEntity<?> findByCategory(@PathVariable String category){
        return ResponseEntity.ok(productService.findByCategory(category));

    }

    @Operation(summary = "Load Products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Load Products",
                    content = { @Content( mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Product.class))) })})
    @GetMapping("/loadProducts")
    ResponseEntity<?> loadProducs(){

        productService.loadProduct();
        return ResponseEntity.ok("productos cargados");
    }


}
