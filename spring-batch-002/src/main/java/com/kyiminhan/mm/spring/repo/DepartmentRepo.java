package com.kyiminhan.mm.spring.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kyiminhan.mm.spring.entity.Department;

/**
 * The Interface DepartmentRepo.<BR>
 *
 * @author KYIMINHAN <BR>
 * @version 1.0 <BR>
 * @since 2019/06/20 <BR>
 *        spring-batch-002 system <BR>
 *        com.kyiminhan.mm.spring.repo <BR>
 *        DepartmentRepo.java <BR>
 */
@Repository
public interface DepartmentRepo extends JpaRepository<Department, Integer> {

}