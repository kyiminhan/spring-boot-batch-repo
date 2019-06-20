package com.kyiminhan.mm.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.kyiminhan.mm.spring.entity.Department;
import com.kyiminhan.mm.spring.entity.Employee;

/**
 * The Class CSVReader.<BR>
 *
 * @author KYIMINHAN <BR>
 * @version 1.0 <BR>
 * @since 2019/06/20 <BR>
 *        spring-batch-002 system <BR>
 *        com.kyiminhan.mm.utils <BR>
 *        CSVReader.java <BR>
 */
public class CSVReader {

	/** The instance. */
	private static volatile CSVReader instance = null;

	/**
	 * Instantiates a new CSV reader.
	 */
	private CSVReader() {

	}

	/**
	 * Gets the instance.
	 *
	 * @return the instance
	 */
	public static CSVReader getInstance() {
		if (null == CSVReader.instance) {
			synchronized (CSVReader.class) {
				CSVReader.instance = new CSVReader();
			}
		}
		return CSVReader.instance;
	}

	/**
	 * Read file.
	 *
	 * @param fileName the file name
	 * @param type     the type
	 * @return List
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public List<Object> readFile(final String fileName, final TableType type) throws IOException {
		final List<Object> collections = new ArrayList<>();
		final BufferedReader bufferReader = new BufferedReader(
				new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8));
		String line = null; // Read first line.
		final String cvsSplitBy = ",";
		// String cvsSplitBy = "\t";
		int iteration = 0;
		while ((line = bufferReader.readLine()) != null) {
			if (iteration == 0) {
				iteration++;
				continue;
			}
			final String[] dataArray = line.split(cvsSplitBy);

			if (type.equals(TableType.DEPARTMENT)) {
				collections.add(this.readDept(dataArray));
			} else if (type.equals(TableType.EMPLOYEE)) {
				collections.add(this.readEmp(dataArray));
			}
		}
		bufferReader.close();
		return collections;
	}

	/**
	 * Read dept.
	 *
	 * @param str the str
	 * @return Department
	 */
	private Department readDept(final String... str) {
		final Department dept = new Department();
		dept.setId(this.getIntValue(str[0]));
		dept.setDepartmentName(str[1]);
		return dept;
	}

	/**
	 * Read emp.
	 *
	 * @param str the str
	 * @return Employee
	 */
	private Employee readEmp(final String... str) {
		final Employee emp = new Employee();
		emp.setId(this.getIntValue(str[0]));
		emp.setAddress(str[1]);
		emp.setEmail(str[2]);
		emp.setName(str[3]);
		emp.setPhone(str[4]);
		return emp;
	}

	/**
	 * Gets the int value.
	 *
	 * @param str the str
	 * @return the int value
	 */
	private Integer getIntValue(final String str) {
		return StringUtils.isNumeric(str) ? Integer.valueOf(str) : 0;
	}
}
