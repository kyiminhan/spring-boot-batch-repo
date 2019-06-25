package com.kyiminhan.mm.spring.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kyiminhan.mm.spring.entity.WkEmployee;

/**
 * The Interface WkEmployeeRepo.<BR>
 *
 * @author KYIMINHAN <BR>
 * @version 1.0 <BR>
 * @since 2019/06/25 <BR>
 *        spring-batch-016 system <BR>
 *        com.kyiminhan.mm.spring.repo <BR>
 *        WkEmployeeRepo.java <BR>
 */
@Repository
public interface WkEmployeeRepo extends JpaRepository<WkEmployee, Integer> {
}