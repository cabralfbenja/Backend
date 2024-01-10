package ar.edu.frc.utn.bda3k4.northwind;

import ar.edu.frc.utn.bda3k4.northwind.controllers.ShipperController;
import ar.edu.frc.utn.bda3k4.northwind.entities.Shipper;
import ar.edu.frc.utn.bda3k4.northwind.entities.request.ShipperRequest;
import ar.edu.frc.utn.bda3k4.northwind.repositories.ShipperRepository;
import ar.edu.frc.utn.bda3k4.northwind.services.implementations.ShipperServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

public class ShipperControllerTest {
    private ShipperController shipperController;
    private ShipperRepository shipperRepository;

    private final Shipper shipper = new Shipper(1, "Speedy Express", "(503) 555-9831", null);

    @BeforeEach
    public void setup(){
        shipperRepository = Mockito.mock(ShipperRepository.class);
        ShipperServiceImpl shipperService = new ShipperServiceImpl(shipperRepository);
        shipperController = new ShipperController(shipperService);
    }

    @Test
    void testFindAll(){
        List<Shipper> shippers = new ArrayList<>();
        shippers.add(shipper);
        Mockito.when(shipperRepository.findAll()).thenReturn(shippers);
        Assertions.assertEquals(
                HttpStatus.OK,
                shipperController.findAll().getStatusCode()
        );
    }

    @Test
    void testFindAllEmpty(){
        Mockito.when(shipperRepository.findAll()).thenReturn(new ArrayList<>());
        Assertions.assertEquals(
                HttpStatus.NO_CONTENT,
                shipperController.findAll().getStatusCode()
        );
    }

    @Test
    void testFindById(){
        Mockito.when(shipperRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(shipper));
        Assertions.assertEquals(
                HttpStatus.OK,
                shipperController.findOne(1).getStatusCode()
        );
    }

    @Test
    void testFindByIdNotFound(){
        Mockito.when(shipperRepository.findById(1)).thenReturn(java.util.Optional.empty());
        Assertions.assertEquals(
                HttpStatus.NOT_FOUND,
                shipperController.findOne(1).getStatusCode()
        );
    }

    @Test
    void testAdd(){
        Mockito.when(shipperRepository.save(any(Shipper.class))).thenReturn(shipper);
        Mockito.when(shipperRepository.saveAndFlush(any(Shipper.class))).thenReturn(shipper);
        Assertions.assertEquals(
                HttpStatus.CREATED,
                shipperController.add(new ShipperRequest()).getStatusCode()
        );
    }

    @Test
    void testUpdate(){
        Mockito.when(shipperRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(shipper));
        Mockito.when(shipperRepository.save(shipper)).thenReturn(shipper);
        Assertions.assertEquals(
                HttpStatus.ACCEPTED,
                shipperController.update(1, new ShipperRequest()).getStatusCode()
        );
    }

    @Test
    void testUpdateNotFound(){
        Mockito.when(shipperRepository.findById(1)).thenReturn(java.util.Optional.empty());
        Assertions.assertEquals(
                HttpStatus.NOT_FOUND,
                shipperController.update(1, new ShipperRequest()).getStatusCode()
        );
    }

    @Test
    void testDelete(){
        Mockito.when(shipperRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(shipper));
        Assertions.assertEquals(
                HttpStatus.ACCEPTED,
                shipperController.delete(1).getStatusCode()
        );
    }

    @Test
    void testDeleteNotFound(){
        Mockito.when(shipperRepository.findById(1)).thenReturn(java.util.Optional.empty());
        Assertions.assertEquals(
                HttpStatus.NOT_FOUND,
                shipperController.delete(1).getStatusCode()
        );
    }


}
