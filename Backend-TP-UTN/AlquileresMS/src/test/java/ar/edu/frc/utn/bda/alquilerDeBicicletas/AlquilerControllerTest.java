package ar.edu.frc.utn.bda.alquilerDeBicicletas;


import ar.edu.frc.utn.bda.alquilerDeBicicletas.controllers.AlquileresController;
import ar.edu.frc.utn.bda.alquilerDeBicicletas.entities.Alquiler;
import ar.edu.frc.utn.bda.alquilerDeBicicletas.entities.Tarifa;
import ar.edu.frc.utn.bda.alquilerDeBicicletas.repositories.AlquilerRepository;
import ar.edu.frc.utn.bda.alquilerDeBicicletas.repositories.TarifaRepository;
import ar.edu.frc.utn.bda.alquilerDeBicicletas.services.implementations.AlquilerServiceImpl;
import ar.edu.frc.utn.bda.alquilerDeBicicletas.services.implementations.EstacionServiceImpl;
import ar.edu.frc.utn.bda.alquilerDeBicicletas.services.implementations.MonedaServiceImpl;
import ar.edu.frc.utn.bda.alquilerDeBicicletas.services.implementations.TarifaServiceImpl;
import ar.edu.frc.utn.bda.alquilerDeBicicletas.services.interfaces.EstacionService;
import ar.edu.frc.utn.bda.alquilerDeBicicletas.services.interfaces.MonedaService;

import org.apache.el.stream.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

public class AlquilerControllerTest {

    private AlquileresController alquilerController;
    private AlquilerRepository alquilerRepository;
    private TarifaRepository tarifaRepository;
    private EstacionService estacionService;
    private MonedaService monedaService;

    private final Alquiler ALQUILER_INICIADO = new Alquiler(
            1,
            "1",
            1,
            LocalDateTime.now(),
            null,
            null,
            1,
            null,
            new Tarifa(1,1,'S',1,null,null,null,300.0,6.0, 80.0,240.0));

    private final Alquiler ALQUILER_FINALIZADO = new Alquiler(
            1,
            "1",
            2,
            LocalDateTime.now(),
            LocalDateTime.now(),
            1.0,
            1,
            5,
            new Tarifa(1,1,'S',1,null,null,null,300.0,6.0, 80.0,240.0));

    @BeforeEach
    public void setUp() {
        alquilerRepository = Mockito.mock(AlquilerRepository.class);
        tarifaRepository = Mockito.mock(TarifaRepository.class);
        TarifaServiceImpl tarifaService = new TarifaServiceImpl(tarifaRepository);
        AlquilerServiceImpl alquilerService = new AlquilerServiceImpl(alquilerRepository, tarifaService);
        monedaService = Mockito.mock(MonedaService.class);
        estacionService = Mockito.mock(EstacionService.class);
        alquilerController = new AlquileresController(alquilerService, monedaService, estacionService);
    }

    @Test
    public void testGetAlquileresEnPeriodo() {
        List<Alquiler> alquilerList = new ArrayList<>();
        alquilerList.add(ALQUILER_INICIADO);
        System.out.println(alquilerList);
        Mockito.when(alquilerRepository.findAllEnPerido(any(LocalDateTime.class), any(LocalDateTime.class))).thenReturn(alquilerList);
        Assertions.assertEquals(
                HttpStatus.OK,
                alquilerController.getAlquileresPeriodo("2021-01-01 00:00:00", "2024-01-01 00:00:00").getStatusCode()
        );

    }
    @Test
    public void testGetAlquileresEnPeriodoEmpty() {
        Mockito.when(alquilerRepository.findAllEnPerido(any(LocalDateTime.class), any(LocalDateTime.class))).thenReturn(new ArrayList<>());
        Assertions.assertEquals(
                HttpStatus.NO_CONTENT,
                alquilerController.getAlquileresPeriodo("2021-01-01 00:00:00", "2024-01-01 00:00:00").getStatusCode()
        );
    }

    @Test
    public void testAdd() {
        Mockito.when(estacionService.existeEstacion(any(Integer.class))).thenReturn(true);
        Mockito.when(alquilerRepository.findActivoByIdCliente(any(String.class))).thenReturn(null);
        Mockito.when(alquilerRepository.save(any(Alquiler.class))).thenReturn(ALQUILER_INICIADO);
        Assertions.assertEquals(
                HttpStatus.OK,
                alquilerController.add("1", 1).getStatusCode()
        );
    }

    @Test
    public void testAddAlquilerExiste(){
        Mockito.when(alquilerRepository.findActivoByIdCliente(any(String.class))).thenReturn(ALQUILER_INICIADO);
        Mockito.when(estacionService.existeEstacion(any(Integer.class))).thenReturn(true);
        Mockito.when(alquilerRepository.save(any(Alquiler.class))).thenReturn(ALQUILER_INICIADO);
        Assertions.assertEquals(
                HttpStatus.BAD_REQUEST,
                alquilerController.add("1", 1).getStatusCode()
        );
    }
    @Test
    public void testAddEstacionNotFound(){
        Mockito.when(alquilerRepository.findActivoByIdCliente(any(String.class))).thenReturn(null);
        Mockito.when(estacionService.existeEstacion(any(Integer.class))).thenReturn(false);
        Mockito.when(alquilerRepository.save(any(Alquiler.class))).thenReturn(ALQUILER_INICIADO);
        Assertions.assertEquals(
                HttpStatus.BAD_REQUEST,
                alquilerController.add("1", 1).getStatusCode()
        );
    }


    @Test
    public void testFinalizar() {
        Mockito.when(alquilerRepository.findActivoByIdCliente(any(String.class))).thenReturn(ALQUILER_INICIADO);
        Mockito.when(estacionService.existeEstacion(any(Integer.class))).thenReturn(true);
        Mockito.when(alquilerRepository.save(any(Alquiler.class))).thenReturn(ALQUILER_FINALIZADO);
        Assertions.assertEquals(
                HttpStatus.OK,
                alquilerController.finalizar("1", 5,"ARS").getStatusCode()
        );
    }

    @Test
    public void testFinalizarSinAlquilerActivo(){
        Mockito.when(alquilerRepository.findActivoByIdCliente(any(String.class))).thenReturn(null);
        Mockito.when(estacionService.existeEstacion(any(Integer.class))).thenReturn(true);
        Mockito.when(alquilerRepository.save(any(Alquiler.class))).thenReturn(ALQUILER_FINALIZADO);
        Assertions.assertEquals(
                HttpStatus.BAD_REQUEST,
                alquilerController.finalizar("1", 5,"ARS").getStatusCode()
        );
    }

    // Chequear porque no se corrobora el if(alquiler.getEstacionRetiroId() != estacionId)
    @Test
    public void testFinalizarEstacionRetiroIgualAEstacionDevolucion(){
        Mockito.when(alquilerRepository.findActivoByIdCliente(any(String.class))).thenReturn(ALQUILER_INICIADO);
        Mockito.when(estacionService.existeEstacion(any(Integer.class))).thenReturn(false);
        Mockito.when(alquilerRepository.save(any(Alquiler.class))).thenReturn(ALQUILER_FINALIZADO);
        Assertions.assertEquals(
                HttpStatus.BAD_REQUEST,
                alquilerController.finalizar("1", 1,"ARS").getStatusCode()
        );


    }

    @Test
    public void testFinalizarEstacionNotFound(){
        Mockito.when(alquilerRepository.findActivoByIdCliente(any(String.class))).thenReturn(ALQUILER_FINALIZADO);
        Mockito.when(estacionService.existeEstacion(any(Integer.class))).thenReturn(false);
        Mockito.when(alquilerRepository.save(any(Alquiler.class))).thenReturn(ALQUILER_FINALIZADO);
        Assertions.assertEquals(
                HttpStatus.BAD_REQUEST,
                alquilerController.finalizar("1", 5,"ARS").getStatusCode()
        );


    }




}
