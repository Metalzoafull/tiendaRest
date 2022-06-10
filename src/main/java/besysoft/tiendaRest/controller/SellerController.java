package besysoft.tiendaRest.controller;

import besysoft.tiendaRest.model.Seller;
import besysoft.tiendaRest.service.SellerService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    private SellerService sellerService;


    @PostMapping("/create")
    ResponseEntity<?> createSeller(@Valid @RequestBody Seller seller){
        sellerService.create(seller);
        return ResponseEntity.ok("fue creado exitosamente");
    }

    @SneakyThrows
    @PostMapping("/addProductSeller/{sellerCode}/{productCode}")
    ResponseEntity<?> addProductSeller(@PathVariable Long sellerCode, @PathVariable Long productCode){
        return ResponseEntity.ok(sellerService.realizeSell(sellerCode, productCode));
    }

    @PostMapping("/addProductsSeller/{sellerCode}")
    ResponseEntity<?> addProductsSeller(@PathVariable Long sellerCode, @RequestBody List<Long> productList){
        return ResponseEntity.ok(sellerService.realizeSales(sellerCode, productList));
    }

    @SneakyThrows
    @GetMapping("/commision/{sellerCode}")
    ResponseEntity<?> makeCommission(@PathVariable Long sellerCode){
        return ResponseEntity.ok(sellerService.makeCommission(sellerCode));
    }

    @SneakyThrows
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
