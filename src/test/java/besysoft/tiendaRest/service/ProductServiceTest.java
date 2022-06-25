package besysoft.tiendaRest.service;

import besysoft.tiendaRest.exception.EntityCodeException;
import besysoft.tiendaRest.exception.EntityNotFoundException;
import besysoft.tiendaRest.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Matchers.any;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    private Product product;
    private Product product2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
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

    }

    @Test
    void create() {
        //when(productService.create(any(Product.class))).thenReturn(product);
        //assertNotNull(productService.create(product));
        assertEquals(product, productService.create(product));
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
    }

    @Test
    void findByCode() {
    }

    @Test
    void findByName() {
    }

    @Test
    void findByPrice() {
    }

    @Test
    void findByCategory() {
    }
}