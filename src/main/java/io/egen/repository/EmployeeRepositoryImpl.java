package io.egen.repository;

import io.egen.entity.Employee;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    //Spring will take care of how to initialize entity manager and how many to create using spring orm
    @PersistenceContext
    private EntityManager em;

    public List<Employee> findAll() {
        TypedQuery<Employee> query = em.createNamedQuery("Employee.findAll",Employee.class);
        return query.getResultList();
    }

    public Employee findOne(String id) {
        return em.find(Employee.class, id);
    }

    public Employee findByEmail(String email) {
        TypedQuery<Employee> query = em.createNamedQuery("Employee.findByEmail",Employee.class);

        query.setParameter("paramEmail",email);
        List<Employee> resultList = query.getResultList();
        if(resultList != null && resultList.size() ==1){
            return resultList.get(0);
        }
        else{
            return null;
        }

    }

    @Transactional
    public Employee create(Employee emp) {
        em.persist(emp);
        return emp;
    }

    public Employee update(Employee emp) {
        System.out.println("In Employee Repository sending updated employee obj"+emp);
        Employee updated = em.merge(emp);
        System.out.println("In Employee Repository employee returned updated obj"+updated);
        return updated;
    }
    public void delete(Employee emp) {
        em.remove(emp);
    }
}
