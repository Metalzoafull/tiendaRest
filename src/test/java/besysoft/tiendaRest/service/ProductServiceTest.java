package besysoft.tiendaRest.service;

import besysoft.tiendaRest.exception.EntityCodeException;
import besysoft.tiendaRest.exception.EntityNotFoundException;
import besysoft.tiendaRest.model.Product;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.event.annotation.BeforeTestExecution;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.BeforeTransaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Matchers.any;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
//@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@ContextConfiguration(classes = { SpringTestConfiguration.class })
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    private Product product;
    private Product product2;
    private Product product3;
    private List<Product> products = new ArrayList<>();

    @BeforeAll
    void setUp() {
        //MockitoAnnotations.initMocks(this);
        //        Product product1 = Product.builder()
        //        .code(48L)
        //        .name("fideos")
        //        .price(7.50)
        //        .category("alimento")
        //        .build();
        /*product = Product.builder()
                .code(48L)
                .name("fideos")
                .price(7.50)
                .category("alimento").build();*/

        product3 = Product.builder()
                .code(20L)
                .name("computadora")
                .category("electrodomestico")
                .build();

        product = new Product();
        product.setCode(48L);
        product.setName("fideos");
        product.setPrice(7.50);
        product.setCategory("alimento");

        product2 = new Product();
        product2.setCode(40L);
        product2.setName("fideos");
        product2.setPrice(7.50);
        product2.setCategory("alimento");
        productService.create(product2);



        products.add(product);
        products.add(product2);
        products.add(product3);

    }

    @Test
    void create() {
        //when(productService.create(any(Product.class))).thenReturn(product);
        //assertNotNull(productService.create(product));
        assertEquals(product, productService.create(product));
        assertEquals(product3, productService.create(product3));
    }

    @Test
    void createException(){
        EntityCodeException thrown = Assertions.assertThrows(
                EntityCodeException.class,
                () -> productService.create(product2),
                "This Code Already exist, please choose another"
        );
        assertTrue(thrown.getMessage().contains("This Code Already exist, please choose another"));
        //Assertions.assertEquals(thrown.getMessage(),"This Code Already exist, please choose another"); ambas opciones funcionan
    }

    @Test
    void getAll() {
        //assertNotSame(products, productService.getAll());
        assertTrue(products.size() == productService.getAll().size() && products.containsAll(productService.getAll())
                && productService.getAll().containsAll(products));

    }

    /*@Test
    void getAllException() {
    }

    */

    @Test
    void findByCode() {

        assertEquals(product2, productService.findByCode(product2.getCode()));
    }

    @Test
    void findByCodeException() {
        EntityNotFoundException thrown = Assertions.assertThrows(
                EntityNotFoundException.class,
                () -> productService.findByCode(50L),
                "this code no exist"
        );
        //assertTrue(thrown.getMessage().contains("This Code Already exist, please choose another"));
        assertTrue(thrown.getMessage().contains("this code no exist"));
    }

    @Test
    void findByName() {
        var listaFideos = List.of(product,product2);
        assertTrue(listaFideos.size() == productService.findByName("fideos").size() &&
                listaFideos.containsAll(productService.findByName("fideos")) &&
                productService.findByName("fideos").containsAll(listaFideos));
        //assertTrue(products.size() == productService.getAll().size() && products.containsAll(productService.getAll())
        //                && productService.getAll().containsAll(products));
    }

    @Test
    void findByNameException() {
        EntityNotFoundException thrown = Assertions.assertThrows(
                EntityNotFoundException.class,
                () -> productService.findByName("jorge"),
                "There are no products with that name"
        );
        assertTrue(thrown.getMessage().contains("There are no products with that name"));
    }

    @Test
    void findByPrice() {
        var listPrice = List.of(product,product2);
        var listPrice2 = productService.findByPrice(7.50);
        assertTrue(listPrice.size() == listPrice2.size() &&
                listPrice.containsAll(listPrice2) && listPrice2.containsAll(listPrice));
    }

    @Test
    void findByPriceException() {
        EntityNotFoundException thrown = Assertions.assertThrows(
                EntityNotFoundException.class,
                () -> productService.findByPrice(10.0),
                "Not exist products with that price"
        );
        assertTrue(thrown.getMessage().contains("Not exist products with that price"));

    }

    @Test
    void findByCategory() {
        var listCategory = List.of(product, product2);
        var listCategory2 = productService.findByCategory("alimento");
        assertTrue(listCategory.size() == listCategory2.size() &&
                listCategory.containsAll(listCategory2) && listCategory2.containsAll(listCategory));
    }

    @Test
    void findByCategoryException() {
        EntityNotFoundException thrown = Assertions.assertThrows(
                EntityNotFoundException.class,
                () -> productService.findByCategory("hola"),
                "Not exist products with that category"
        );
        assertTrue(thrown.getMessage().contains("Not exist products with that category"));
    }
}