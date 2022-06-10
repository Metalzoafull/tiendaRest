package besysoft.tiendaRest.service;


import besysoft.tiendaRest.exception.AtributeNotMeetRequirements;
import besysoft.tiendaRest.exception.EntityNotFoundException;
import besysoft.tiendaRest.model.Product;
import besysoft.tiendaRest.model.Seller;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class SellerService {

    private List<Seller> sellers;



    @Autowired
    private ProductService productService;


    public Seller create(Seller seller){
        if(sellers == null){
            sellers = new ArrayList<>();
        }
        if(seller.getProductList() == null){
            seller.setProductList(new ArrayList<>());
        }
        sellers.add(seller);
        return seller;
    }


    @SneakyThrows
    public List<Seller> getSellers(){
        if (!sellers.isEmpty()){
            return sellers;
        }else {
            throw new EntityNotFoundException("not exist sellers");
        }
    }

    @SneakyThrows
    public Seller findSellerByCode(Long code){
        return sellers.stream()
                .filter(seller -> Objects.equals(seller.getCode(), code))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("seller not found"));
    }

    public Seller realizeSell(Long sellerCode, Long productcode){
        Product producto = productService.findByCodigo(productcode);
        Seller seller = this.findSellerByCode(sellerCode);
        seller.getProductList().add(producto);
        return seller;
    }

    public Seller realizeSales(Long sellerCode, List<Long> productList){
        for (Long i : productList) {
            this.realizeSell(sellerCode, i);
        }
        return this.findSellerByCode(sellerCode);
    }

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
            throw new AtributeNotMeetRequirements("the seller does not have the necessary sales");
        }
        return result;

    }

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
