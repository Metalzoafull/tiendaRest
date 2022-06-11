package besysoft.tiendaRest.service;


import besysoft.tiendaRest.exception.AtributeNotMeetRequirements;
import besysoft.tiendaRest.exception.EntityCodeException;
import besysoft.tiendaRest.exception.EntityNotFoundException;
import besysoft.tiendaRest.model.Product;
import besysoft.tiendaRest.model.Seller;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class SellerService {


    //Service creado de vendedores para cumplir todas las funciones necesarias del ejercicio
    private List<Seller> sellers = new ArrayList<>();

    @Autowired
    private ProductService productService;


    //crear el vendedor
    @SneakyThrows
    public Seller create(Seller seller){
        if(sellers == null){
            sellers = new ArrayList<>();
        }
        if(seller.getProductList() == null){
            seller.setProductList(new ArrayList<>());
        }
        Seller seller1 = sellers.stream()
                .filter(s -> Objects.equals(s.getCode(), seller.getCode()))
                .findFirst()
                .orElse(null);
        if (seller1 == null){
            sellers.add(seller);
        }else {
            throw new EntityCodeException("This Code Already exist, please choose another");
        }

        return seller;
    }




    //listar y devolver a los vendedores
    @SneakyThrows
    public List<Seller> getSellers(){
        if (!this.sellers.isEmpty()){
            return this.sellers;
        }else {
            throw new EntityNotFoundException("not exist sellers", "P-402", HttpStatus.NOT_FOUND);
        }
    }

    //buscar un vendedor por su codigo
    @SneakyThrows
    public Seller findSellerByCode(Long code){
        return sellers.stream()
                .filter(seller -> Objects.equals(seller.getCode(), code))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("not exist sellers","P-402", HttpStatus.NOT_FOUND));
    }

    //vendedor realizando una venta(es usado par aun solo producto)
    public Seller realizeSell(Long sellerCode, Long productcode){
        Seller seller = this.findSellerByCode(sellerCode);
        Product producto = productService.findByCodigo(productcode);
        seller.getProductList().add(producto);
        return seller;
    }

    //vendedor realizando muchas ventas(es solo para varios productos,
    // si vas a mandar uno solo tiene que ser una lista)
    public Seller realizeSales(Long sellerCode, List<Long> productList){
        for (Long i : productList) {
            this.realizeSell(sellerCode, i);
        }
        return this.findSellerByCode(sellerCode);
    }

    //devolver la comision que recibe un vendedor
    // (devolvera una exepcion si no tiene la cantidad de ventas necesaria)
    @SneakyThrows
    public Double makeCommission(Long sellerCode){
        Seller seller = this.findSellerByCode(sellerCode);
        List<Product> productList = seller.getProductList();
        double result;
        if (productList.size() > 1){
            result = productList.stream()
                    .map(Product::getPrice)
                    .reduce(Double::sum).orElse(0.0);
            if (productList.size() == 2){
                result = (5 * result)/100;
            }else{
                result = (10 * result)/100;
            }
        }else{
            throw new AtributeNotMeetRequirements("the seller does not have the necessary sales", "P-403",HttpStatus.BAD_REQUEST);
        }
        return result;

    }

    //cargar vendedores por defecto
    public void loadSellers(){
        this.sellers = new ArrayList<>();

        Seller seller = Seller.builder().code(1L).name("jorge").salary(50000.0).productList(new ArrayList<>()).build();
        Seller seller2 = Seller.builder().code(41L).name("juan").salary(48000.0).productList(new ArrayList<>()).build();
        Seller seller3 = Seller.builder().code(290L).name("nicolas").salary(75000.50).productList(new ArrayList<>()).build();
        Seller seller4 = Seller.builder().code(138L).name("alejandro").salary(100048.7).productList(new ArrayList<>()).build();

        sellers.add(seller);
        sellers.add(seller2);
        sellers.add(seller3);
        sellers.add(seller4);
    }

}
