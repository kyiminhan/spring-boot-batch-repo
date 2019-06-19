package com.kyiminhan.mm.spring.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kyiminhan.mm.spring.entity.Employee;

/**
 * The Interface EmployeeRepo.<BR>
 *
 * @author KYIMINHAN <BR>
 * @version 1.0 <BR>
 * @since 2019/06/19 <BR>
 * spring-batch-002 system <BR>
 * com.kyiminhan.mm.spring.repo <BR>
 * EmployeeRepo.java <BR>
 */
@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

}