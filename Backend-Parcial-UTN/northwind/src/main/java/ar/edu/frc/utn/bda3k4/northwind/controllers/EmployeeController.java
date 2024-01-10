package ar.edu.frc.utn.bda3k4.northwind.controllers;

import ar.edu.frc.utn.bda3k4.northwind.entities.Employee;
import ar.edu.frc.utn.bda3k4.northwind.entities.request.EmployeeRequest;
import ar.edu.frc.utn.bda3k4.northwind.entities.response.EmployeeResponse;
import ar.edu.frc.utn.bda3k4.northwind.services.interfaces.EmployeeService;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<Object> findAll() {
        try {
            val employees = employeeService.findAll()
                    .stream()
                    .map(EmployeeResponse::from)
                    .toList();
            return ResponseEntity.ok(employees);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody EmployeeRequest aRequest) {
        try {
            Employee newEmployee = aRequest.toEmployee();
            if (aRequest.getReportsTo() != null){
                Employee reportsTo = employeeService.findById(aRequest.getReportsTo());
                newEmployee.setReportsTo(reportsTo);
            }
            newEmployee = employeeService.add(newEmployee);
            return ResponseEntity.ok(EmployeeResponse.from(newEmployee));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody EmployeeRequest aRequest) {
        try {
            Employee reportsTo = null;
            Employee modifiedEmployee = aRequest.toEmployee();
            if (aRequest.getReportsTo() != null){reportsTo = employeeService.findById(aRequest.getReportsTo());}
            modifiedEmployee.setReportsTo(reportsTo);
            modifiedEmployee = employeeService.update(id, modifiedEmployee);
            return ResponseEntity.accepted().body(EmployeeResponse.from(modifiedEmployee));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findOne(@PathVariable Integer id) {
        try {
            Employee employee = employeeService.findById(id);
            return ResponseEntity.ok(EmployeeResponse.from(employee));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        try {
            Employee employee = employeeService.delete(id);
            return ResponseEntity.ok(EmployeeResponse.from(employee));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
