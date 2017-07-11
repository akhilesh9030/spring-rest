package io.egen.service;

import io.egen.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by bolla on 6/22/2017.
 */

public interface EmployeeService {
    List<Employee> findAll();

    Employee findOne(String id);

    Employee create(Employee emp);

    Employee update(String id, Employee emp);

    void delete(String id);


}
