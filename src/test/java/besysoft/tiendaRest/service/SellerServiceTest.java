package besysoft.tiendaRest.service;

import besysoft.tiendaRest.exception.EntityCodeException;
import besysoft.tiendaRest.exception.EntityNotFoundException;
import besysoft.tiendaRest.exception.NotRequirements;
import besysoft.tiendaRest.model.Product;
import besysoft.tiendaRest.model.Seller;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SellerServiceTest {

    @Autowired
    private SellerService sellerService;

    @Autowired
    private ProductService productService;

    private Seller seller;
    private Seller seller2;
    private Seller seller3;
    private Seller seller4;
    private Product product;
    private Product product3;
    private Product product4;
    private List<Seller> sellerList = new ArrayList<>();

    @BeforeAll
    void setUp() {

        //seller = Seller.builder().code(1L).name("jorge").salary(50000.0).productList(new ArrayList<>()).build();
        seller = Seller.builder()
                .code(1L)
                .name("jorge")
                .salary(50000.0)
                .build();
        seller2 = Seller.builder()
                .code(1L)
                .name("jorge")
                .salary(50000.0)
                .productList(new ArrayList<>())
                .build();
        seller3 = Seller.builder()
                .code(41L)
                .name("juan")
                .salary(48000.0)
                .productList(new ArrayList<>())
                .build();
        seller4 = Seller.builder()
                .code(138L)
                .name("alejandro")
                .salary(100048.7)
                .productList(new ArrayList<>())
                .build();

        product = Product.builder()
                .code(74L)
                .name("manteca")
                .price(9.50)
                .category("alimento")
                .build();

        product3 = Product.builder()
                .code(98L)
                .name("guantes")
                .price(10.50)
                .category("utensilio")
                .build();

        product4 = Product.builder()
                .code(245L)
                .name("mouse")
                .price(2500.90)
                .category("periferico")
                .build();

        sellerList.add(seller);
        sellerList.add(seller3);
        sellerList.add(seller4);
        productService.create(product);
        productService.create(product3);
        productService.create(product4);
    }

    @Test
    void create() {
        /*System.out.println(seller);
        System.out.println(seller3);
        System.out.println(seller4);*/
        assertEquals(seller, sellerService.create(seller));
        assertEquals(seller3, sellerService.create(seller3));
        assertEquals(seller4, sellerService.create(seller4));

        assertEquals(3, sellerService.getSellers().size());
    }

    @Test
    void createException(){
        EntityCodeException thrown = Assertions.assertThrows(
                EntityCodeException.class,
                () -> sellerService.create(seller2),
                "This Code Already exist, please choose another"
        );
        assertTrue(thrown.getMessage().contains("This Code Already exist, please choose another"));

    }


    @Test
    void getSellers() {
        assertTrue(sellerList.size() == sellerService.getSellers().size() && sellerList.containsAll(sellerService.getSellers()) &&
                sellerService.getSellers().containsAll(sellerList));

    }

    /*@Test
    void getSellersException() {

    }

     */


    @Test
    void findSellerByCode() {
        assertEquals(seller, sellerService.findSellerByCode(1L));
        assertEquals(seller4, sellerService.findSellerByCode(138L));

    }

    @Test
    void findSellerByCodeException() {
        EntityNotFoundException thrown = Assertions.assertThrows(
                EntityNotFoundException.class,
                () -> sellerService.findSellerByCode(50L),
                "not exist sellers"
        );
        //assertTrue(thrown.getMessage().contains("This Code Already exist, please choose another"));
        assertTrue(thrown.getMessage().contains("not exist sellers"));

    }


    @Test
    void realizeSell() {
        assertEquals(seller, sellerService.realizeSell(1L,74L));
    }


    @Test
    void realizeSales() {
        assertEquals(seller3, sellerService.realizeSales(41L, List.of(98L,245L)));
    }


    @Test
    void makeCommission() {
        assertEquals(125.57, sellerService.makeCommission(41L),0.1);
        //assertEquals(3, sellerService.getSellers().size());
        //System.out.println(sellerService.getSellers());

    }

    /*@Test
    void makeCommissionException() {
        System.out.println(sellerService.getSellers());
        NotRequirements thrown = Assertions.assertThrows(
                NotRequirements.class,
                () -> sellerService.makeCommission(138L),
                "the seller does not have the necessary sales"
        );
        //assertTrue(thrown.getMessage().contains("This Code Already exist, please choose another"));
        assertTrue(thrown.getMessage().contains("the seller does not have the necessary sales"));
    }*/

}