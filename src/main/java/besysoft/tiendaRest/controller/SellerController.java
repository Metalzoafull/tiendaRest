package besysoft.tiendaRest.controller;

import besysoft.tiendaRest.model.Seller;
import besysoft.tiendaRest.service.SellerService;
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
    @PostMapping("/create")
    ResponseEntity<?> createSeller(@Valid @RequestBody Seller seller){
        sellerService.create(seller);
        return ResponseEntity.ok("fue creado exitosamente");
    }

    //peticion para agregar un producto al vendedor
    @PostMapping("/addProductSeller/{sellerCode}/{productCode}")
    ResponseEntity<?> addProductSeller(@PathVariable Long sellerCode, @PathVariable Long productCode){
        return ResponseEntity.ok(sellerService.realizeSell(sellerCode, productCode));
    }

    //peticion para agregar varios productos al vendedor
    @PostMapping("/addProductsSeller/{sellerCode}")
    ResponseEntity<?> addProductsSeller(@PathVariable Long sellerCode, @RequestBody List<Long> productList){
        return ResponseEntity.ok(sellerService.realizeSales(sellerCode, productList));
    }


    //peticion para devolver la comicion que recibira X vendedor
    @GetMapping("/commision/{sellerCode}")
    ResponseEntity<?> makeCommission(@PathVariable Long sellerCode){
        return ResponseEntity.ok(sellerService.makeCommission(sellerCode));
    }

    //listar vendedores
    @GetMapping("/getAll")
    ResponseEntity<?> getAllSellers(){
        return ResponseEntity.ok(sellerService.getSellers());
    }

    @GetMapping("/loadSellers")
    ResponseEntity<?> loadSellers(){
        sellerService.loadSellers();
        return ResponseEntity.ok("vendedores cargados");
    }






}
