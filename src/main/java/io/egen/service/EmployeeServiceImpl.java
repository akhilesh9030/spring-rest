package io.egen.service;

import io.egen.entity.Employee;
import io.egen.exceptions.BadRequestException;
import io.egen.exceptions.ResourceNotFoundException;
import io.egen.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Transactional(readOnly = true)
    public List<Employee> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Employee findOne(String id) {
        Employee existing = repository.findOne(id);
        if(existing == null){
            //exception handling 404
            throw new ResourceNotFoundException("Employee with id "+id+" does not exist");
        }
        return existing;
    }

    @Transactional
    public Employee create(Employee emp) {
        Employee existing = repository.findByEmail(emp.getEmail());
        if(existing != null){
            //exception handling, 400 Bad request
            throw new BadRequestException("Employee with email "+emp.getEmail()+" already exist");
        }
        return repository.create(emp);
    }

    @Transactional
    public Employee update(String id, Employee emp) {
        Employee existing =  repository.findOne(id);
        System.out.println("In Employee Service Existing employee obj"+existing);
        System.out.println("In Employee Service new employee"+emp);
        if(existing == null){
            //exception handling 404
            throw new ResourceNotFoundException("Employee with id "+id+" does not exist");
        }
        return repository.update(emp);
    }

    @Transactional
    public void delete(String id) {
        Employee existing =  repository.findOne(id);
        if(existing == null){
            //exception handling 404
            throw new ResourceNotFoundException("Employee with id "+id+" does not exist");
        }
        repository.delete(existing);
    }
}
