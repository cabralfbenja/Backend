package ar.edu.frc.utn.bda3k4.northwind.services.implementations;

import ar.edu.frc.utn.bda3k4.northwind.entities.Employee;
import ar.edu.frc.utn.bda3k4.northwind.repositories.EmployeeRepository;
import ar.edu.frc.utn.bda3k4.northwind.services.interfaces.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee add(Employee entity) {
        return this.employeeRepository.save(entity);
    }

    @Override
    public Employee update(Integer id, Employee entity) {
        Employee employee = this.employeeRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Employee not found"));
        employee.update(entity);
        return this.employeeRepository.save(employee);
    }

    @Override
    public Employee delete(Integer id) {
        Employee employee = this.employeeRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Employee not found"));
        this.employeeRepository.delete(employee);
        return employee;
    }

    @Override
    public Employee findById(Integer id) {
        return this.employeeRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("Employee not found"));
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employees = this.employeeRepository.findAll();
        if(employees.isEmpty()){throw new IllegalArgumentException("No employees found");}
        return employees;
    }
}
