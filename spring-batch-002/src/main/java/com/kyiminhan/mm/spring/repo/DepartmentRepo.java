package com.kyiminhan.mm.spring.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kyiminhan.mm.spring.entity.Department;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Integer> {

}