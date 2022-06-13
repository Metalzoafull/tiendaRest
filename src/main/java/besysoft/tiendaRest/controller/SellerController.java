package besysoft.tiendaRest.controller;

import besysoft.tiendaRest.dto.ErrorDTO;
import besysoft.tiendaRest.model.Seller;
import besysoft.tiendaRest.service.SellerService;
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
import java.util.List;

@RestController
@RequestMapping("/seller")// le aclaro la runa donde quiero que se llame
public class SellerController {

    @Autowired
    private SellerService sellerService;

    //peticion para crear vendedor
    @Operation(summary = "Created Seller(quitar el product list a la hora de crear al vendedor, el schema es solo un ejemplo de como quedaria la entidad completa)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Create Seller",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Seller.class)) }),
            @ApiResponse(responseCode = "400", description = "Los datos no cumplen con el minimo requerimiento(y un  mensaje diciendo que datos no cumplen)",
                    content = @Content),
            @ApiResponse(responseCode = "201", description = "This Code Already exist, please choose another",
                    content = @Content) })
    @PostMapping("/create")
    ResponseEntity<?> createSeller(@Valid @RequestBody Seller seller){
        return ResponseEntity.ok(sellerService.create(seller));
    }

    //peticion para agregar un producto al vendedor
    @Operation(summary = "Realice Sell")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Realice Sell",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Seller.class)) }),
            @ApiResponse(responseCode = "P-402", description = "not exist sellers or not exit product",
                    content = @Content)})
    @PostMapping("/addProductSeller/{sellerCode}/{productCode}")
    ResponseEntity<?> addProductSeller(@PathVariable Long sellerCode, @PathVariable Long productCode){
        return ResponseEntity.ok(sellerService.realizeSell(sellerCode, productCode));
    }

    //peticion para agregar varios productos al vendedor
    @Operation(summary = "Make a lot of sales")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Make a lot of sales",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Seller.class)) }),
            @ApiResponse(responseCode = "P-402", description = "not exist sellers or not exit product",
                    content = @Content)})
    @PostMapping("/addProductsSeller/{sellerCode}")
    ResponseEntity<?> addProductsSeller(@PathVariable Long sellerCode, @RequestBody List<Long> productList){
        return ResponseEntity.ok(sellerService.realizeSales(sellerCode, productList));
    }


    //peticion para devolver la comicion que recibira X vendedor
    @Operation(summary = "Get commission")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get commission",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Seller.class)) }),
            @ApiResponse(responseCode = "P-402", description = "not exist sellers or not exit product",
                    content = @Content),
            @ApiResponse(responseCode = "P-403", description = "the seller does not have the necessary sales",
                    content = @Content)})
    @GetMapping("/commision/{sellerCode}")
    ResponseEntity<?> makeCommission(@PathVariable Long sellerCode){
        return ResponseEntity.ok(sellerService.makeCommission(sellerCode));
    }

    //listar vendedores
    @Operation(summary = "Find All Sellers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get All Sellers",
                    content = { @Content( mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Seller.class))) }),
            @ApiResponse(responseCode = "P-402", description = "not exist sellers",
                    content = @Content)})
    @GetMapping("/getAll")
    ResponseEntity<?> getAllSellers(){
        return ResponseEntity.ok(sellerService.getSellers());
    }

    @Operation(summary = "Load Sellers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Load Sellers" )})
    @GetMapping("/loadSellers")
    ResponseEntity<?> loadSellers(){
        sellerService.loadSellers();
        return ResponseEntity.ok("vendedores cargados");
    }

    @Operation(summary = "Find Seller")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get Seller",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Seller.class)) }),
            @ApiResponse(responseCode = "P-402", description = "not exist sellers",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)))})
    @GetMapping("/findByCode/{sellerCode}")
    ResponseEntity<?> findSellerByCode(@PathVariable Long sellerCode){
        return ResponseEntity.ok(sellerService.findSellerByCode(sellerCode));
    }





}
