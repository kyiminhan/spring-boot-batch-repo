package com.kyiminhan.mm.spring.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The Class Department.<BR>
 *
 * @author KYIMINHAN <BR>
 * @version 1.0 <BR>
 * @since 2019/06/20 <BR>
 *        spring-batch-002 system <BR>
 *        com.kyiminhan.mm.spring.entity <BR>
 *        Department.java <BR>
 */
@Getter
@Setter
@Entity

/**
 * Builds the.
 *
 * @return Department
 */

/**
 * Builds the.
 *
 * @return Department
 */
@Builder
@ToString

/**
 * Instantiates a new department.
 */

/**
 * Instantiates a new department.
 */
@NoArgsConstructor

/**
 * Instantiates a new department.
 *
 * @param id the id
 * @param departmentName the department name
 */

/**
 * Instantiates a new department.
 *
 * @param id             the id
 * @param departmentName the department name
 */
@AllArgsConstructor
public class Department implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/** The department name. */
	@Column
	private String departmentName;
}