package ar.edu.frc.utn.bda3k4.northwind;

import ar.edu.frc.utn.bda3k4.northwind.controllers.OrderDetailController;
import ar.edu.frc.utn.bda3k4.northwind.entities.Order;
import ar.edu.frc.utn.bda3k4.northwind.entities.OrderDetail;
import ar.edu.frc.utn.bda3k4.northwind.entities.Product;
import ar.edu.frc.utn.bda3k4.northwind.entities.request.create.OrderDetailCreateRequest;
import ar.edu.frc.utn.bda3k4.northwind.entities.request.update.OrderDetailUpdateRequest;
import ar.edu.frc.utn.bda3k4.northwind.repositories.*;
import ar.edu.frc.utn.bda3k4.northwind.services.implementations.*;
import ar.edu.frc.utn.bda3k4.northwind.support.OrderDetailPK;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class OrderDetailControllerTest {
    private OrderDetailController orderDetailController;
    private OrderDetailRepository orderDetailRepository;
    private ProductRepository productRepository;
    private OrderRepository orderRepository;
    private CategoryRepository categoryRepository;
    private SupplierRepository supplierRepository;
    private final OrderDetail ORDERDETAIL = new OrderDetail(1, 1, 1.0, 1, 1.0);
    private final Product PRODUCT = new Product(1, "", "", 1.0,
            1, 1, 1, false);
    private final Order ORDER = new Order(1, LocalDate.now(), LocalDate.now(),
            LocalDate.now(), 1.0, "", "", "", "",
            "", "");
    private final OrderDetailCreateRequest createRequest =
            new OrderDetailCreateRequest(1, 1, 1.0, 1, 1.0);

    private final OrderDetailUpdateRequest updateRequest =
            new OrderDetailUpdateRequest(1.0, 1, 1.0);

    @BeforeEach
    public void setup(){
        orderDetailRepository = Mockito.mock(OrderDetailRepository.class);
        productRepository = Mockito.mock(ProductRepository.class);
        orderRepository = Mockito.mock(OrderRepository.class);
        categoryRepository = Mockito.mock(CategoryRepository.class);
        supplierRepository = Mockito.mock(SupplierRepository.class);
        CategoryServiceImpl categoryService = new CategoryServiceImpl(categoryRepository);
        SupplierServiceImpl supplierService = new SupplierServiceImpl(supplierRepository);
        OrderServiceImpl orderService = new OrderServiceImpl(orderRepository);
        ProductServiceImpl productService = new ProductServiceImpl(productRepository, categoryService, supplierService);
        OrderDetailServiceImpl service = new OrderDetailServiceImpl(orderDetailRepository, orderService, productService);
        orderDetailController = new OrderDetailController(service);
    }

    @Test
    void testFindAll(){
        List<OrderDetail> orderDetailList = new ArrayList<>();
        orderDetailList.add(ORDERDETAIL);
        Mockito.when(orderDetailRepository.findAll()).thenReturn(orderDetailList);
        Assertions.assertEquals(
                HttpStatus.OK,
                orderDetailController.findAll().getStatusCode()
        );
    }

    @Test
    void testFindAllEmpty(){
        Mockito.when(orderDetailRepository.findAll()).thenReturn(new ArrayList<>());
        Assertions.assertEquals(
                HttpStatus.NO_CONTENT,
                orderDetailController.findAll().getStatusCode()
        );
    }

    @Test
    void testFindById(){
        Mockito.when(orderDetailRepository.findById(new OrderDetailPK(1,1)))
                .thenReturn(Optional.of(ORDERDETAIL));
        Assertions.assertEquals(
                HttpStatus.OK,
                orderDetailController.findOne(1,1).getStatusCode()
        );
    }

    @Test
    void testFindByIdNotFound(){
        Mockito.when(orderDetailRepository.findById(new OrderDetailPK(1,1)))
                .thenReturn(Optional.empty());
        Assertions.assertEquals(
                HttpStatus.NOT_FOUND,
                orderDetailController.findOne(1,1).getStatusCode()
        );
    }

    @Test
    void testAdd(){
        Mockito.when(orderRepository.findById(1)).thenReturn(Optional.of(ORDER));
        Mockito.when(productRepository.findById(1)).thenReturn(Optional.of(PRODUCT));
        Mockito.when(orderDetailRepository.save(any(OrderDetail.class))).thenReturn(ORDERDETAIL);
        Assertions.assertEquals(
                HttpStatus.OK,
                orderDetailController.add(this.createRequest).getStatusCode()
        );
    }

    @Test
    void testUpdate(){
        Mockito.when(orderDetailRepository.findById(new OrderDetailPK(1,1)))
                .thenReturn(Optional.of(ORDERDETAIL));
        Mockito.when(orderDetailRepository.save(ORDERDETAIL)).thenReturn(ORDERDETAIL);
        Assertions.assertEquals(
                HttpStatus.OK,
                orderDetailController.update(1,1, this.updateRequest).getStatusCode()
        );
    }

    @Test
    void testUpdateNotFound(){
        Mockito.when(orderDetailRepository.findById(new OrderDetailPK(1,1)))
                .thenReturn(Optional.empty());
        Assertions.assertEquals(
                HttpStatus.NOT_FOUND,
                orderDetailController.update(1,1, this.updateRequest).getStatusCode());
    }

    @Test
    void testDelete(){
        Mockito.when(orderDetailRepository.findById(new OrderDetailPK(1,1)))
                .thenReturn(Optional.of(ORDERDETAIL));
        Assertions.assertEquals(
                HttpStatus.OK,
                orderDetailController.delete(1,1).getStatusCode()
        );
    }

    @Test
    void testDeleteNotFound(){
        Mockito.when(orderDetailRepository.findById(new OrderDetailPK(1,1)))
                .thenReturn(Optional.empty());
        Assertions.assertEquals(
                HttpStatus.NOT_FOUND,
                orderDetailController.delete(1,1).getStatusCode()
        );
    }
}
