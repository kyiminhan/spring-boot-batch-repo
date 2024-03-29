package com.kyiminhan.mm.spring.task;

import java.util.Collection;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.kyiminhan.mm.spring.entity.Department;
import com.kyiminhan.mm.spring.entity.Employee;
import com.kyiminhan.mm.spring.service.DepartmentService;
import com.kyiminhan.mm.spring.service.EmployeeService;
import com.kyiminhan.mm.utils.CSVReader;
import com.kyiminhan.mm.utils.TableType;

import lombok.extern.log4j.Log4j2;

/**
 * The Class TaskOne.<BR>
 *
 * @author KYIMINHAN <BR>
 * @version 1.0 <BR>
 * @since 2019/06/20 <BR>
 *        spring-batch-002 system <BR>
 *        com.kyiminhan.mm.spring.task <BR>
 *        TaskOne.java <BR>
 */
@Log4j2
@Component
public class TaskOne implements Tasklet {

	/** The department service. */
	@Autowired
	@Qualifier(value = "departmentService")
	private DepartmentService departmentService;

	/** The employee service. */
	@Autowired
	@Qualifier(value = "employeeService")
	private EmployeeService employeeService;

	/**
	 * Execute.
	 *
	 * @param contribution the contribution
	 * @param chunkContext the chunk context
	 * @return RepeatStatus
	 * @throws Exception the exception
	 */
	@Override
	public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {

		TaskOne.log.info("**************************************************");
		TaskOne.log.info("MyTaskOne start..");

		final String departmentDataFilePath = "src/main/resources/csv/department.csv";
		final Collection<Object> departments = CSVReader.getInstance().readFile(departmentDataFilePath,
				TableType.DEPARTMENT);
		departments.stream().forEach(obj -> {
			TaskOne.log.info(obj);
			this.departmentService.save((Department) obj);
		});

		final String employeeDataFilePath = "src/main/resources/csv/employee.csv";
		final Collection<Object> employees = CSVReader.getInstance().readFile(employeeDataFilePath, TableType.EMPLOYEE);
		employees.stream().forEach(obj -> {
			TaskOne.log.info(obj);
			this.employeeService.save((Employee) obj);
		});

		// TODO adding Coding

		TaskOne.log.info("MyTaskOne done..");
		TaskOne.log.info("**************************************************");
		return RepeatStatus.FINISHED;
	}
}