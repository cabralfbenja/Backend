package ar.edu.frc.utn.bda3k4.northwind;

import ar.edu.frc.utn.bda3k4.northwind.controllers.EmployeeController;
import ar.edu.frc.utn.bda3k4.northwind.entities.Employee;
import ar.edu.frc.utn.bda3k4.northwind.entities.request.EmployeeRequest;
import ar.edu.frc.utn.bda3k4.northwind.repositories.EmployeeRepository;
import ar.edu.frc.utn.bda3k4.northwind.services.implementations.EmployeeServiceImpl;
import ar.edu.frc.utn.bda3k4.northwind.support.LocalDateTimeAttributeConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

public class EmployeeControllerTest {

    private EmployeeController employeeController;
    private EmployeeRepository employeeRepository;
    private LocalDateTimeAttributeConverter localDateTimeAttributeConverter = new LocalDateTimeAttributeConverter();
    private final Employee EMPLOYEE = new Employee(
            1,
            "Davolio",
            "Nancy",
            "Sales Representative",
            "Ms.",
            localDateTimeAttributeConverter.convertToEntityAttribute("1948-12-08"),
            localDateTimeAttributeConverter.convertToEntityAttribute("1960-05-01"),
            "507 - 20th Ave. E. Apt. 2A",
            "Seattle",
            "WA",
            "98122",
            "USA",
            "(206) 555-9857",
            "5467",
            null,
            "Education includes a BA in psychology from Colorado State University in 1970.  She also completed \"The Art of the Cold Call.\"  Nancy is a member of Toastmasters International.",
            null,
            "http://accweb/emmployees/davolio.bmp",
            null
    );
    @BeforeEach
    void setUp() {
        employeeRepository = Mockito.mock(EmployeeRepository.class);
        EmployeeServiceImpl employeeService = new EmployeeServiceImpl(employeeRepository);
        employeeController = new EmployeeController(employeeService);
    }

    @Test
    void testFindAll(){
        List<Employee> employees = new ArrayList<>();
        employees.add(EMPLOYEE);
        Mockito.when(employeeRepository.findAll()).thenReturn(employees);
        Assertions.assertEquals(
                HttpStatus.OK,
                employeeController.findAll().getStatusCode()
        );
    }

    @Test
    void testFindAllEmpty(){
        Mockito.when(employeeRepository.findAll()).thenReturn(new ArrayList<>());
        Assertions.assertEquals(
                HttpStatus.NO_CONTENT,
                employeeController.findAll().getStatusCode()
        );
    }

    @Test
    void testFindById(){
        Mockito.when(employeeRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(EMPLOYEE));
        Assertions.assertEquals(
                HttpStatus.OK,
                employeeController.findOne(1).getStatusCode()
        );
    }

    @Test
    void testFindByIdNotFound(){
        Mockito.when(employeeRepository.findById(1)).thenReturn(java.util.Optional.empty());
        Assertions.assertEquals(
                HttpStatus.NOT_FOUND,
                employeeController.findOne(1).getStatusCode()
        );
    }

    @Test
    void testAdd(){
        Mockito.when(employeeRepository.save(any(Employee.class))).thenReturn(EMPLOYEE);
        Mockito.when(employeeRepository.saveAndFlush(any(Employee.class))).thenReturn(EMPLOYEE);


        Assertions.assertEquals(
                HttpStatus.OK,
                employeeController.add(new EmployeeRequest()).getStatusCode()
        );
    }

    @Test
    void testUpdate(){
        Mockito.when(employeeRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(EMPLOYEE));
        Mockito.when(employeeRepository.save(EMPLOYEE)).thenReturn(EMPLOYEE);
        Assertions.assertEquals(
                HttpStatus.ACCEPTED,
                employeeController.update(1, new EmployeeRequest()).getStatusCode()
        );
    }

    @Test
    void testUpdateNotFound(){
        Mockito.when(employeeRepository.findById(1)).thenReturn(java.util.Optional.empty());
        Assertions.assertEquals(
                HttpStatus.NOT_FOUND,
                employeeController.update(1, new EmployeeRequest()).getStatusCode()
        );
    }

    @Test
    void testDelete(){
        Mockito.when(employeeRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(EMPLOYEE));
        Assertions.assertEquals(
                HttpStatus.OK,
                employeeController.delete(1).getStatusCode()
        );
    }

    @Test
    void testDeleteNotFound(){
        Mockito.when(employeeRepository.findById(1)).thenReturn(java.util.Optional.empty());
        Assertions.assertEquals(
                HttpStatus.NOT_FOUND,
                employeeController.delete(1).getStatusCode()
        );
    }


}
