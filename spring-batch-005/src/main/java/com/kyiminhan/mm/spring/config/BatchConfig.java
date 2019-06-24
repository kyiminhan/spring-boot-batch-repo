package com.kyiminhan.mm.spring.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.kyiminhan.mm.spring.entity.Department;
import com.kyiminhan.mm.spring.entity.Employee;
import com.kyiminhan.mm.spring.processor.DepartmentValidationProcessor;
import com.kyiminhan.mm.spring.processor.EmployeeValidationProcessor;
import com.kyiminhan.mm.spring.writer.ConsoleDepartmentWriter;
import com.kyiminhan.mm.spring.writer.ConsoleEmployeeWriter;

/**
 * The Class BatchConfig.<BR>
 *
 * @author KYIMINHAN <BR>
 * @version 1.0 <BR>
 * @since 2019/06/20 <BR>
 *        spring-batch-005 system <BR>
 *        com.kyiminhan.mm.spring.config <BR>
 *        BatchConfig.java <BR>
 */
@Configuration
@EnableBatchProcessing
@ComponentScan(basePackages = "com.kyiminhan.mm")
public class BatchConfig {

	/** The job builder factory. */
	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	/** The step builder factory. */
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	/** The emp input resource. */
	@Value("/input/employee.csv")
	private Resource empInputResource;

	/** The dep input resource. */
	@Value("/input/department.csv")
	private Resource depInputResource;

	/** The employee validation processor. */
	@Autowired
	private EmployeeValidationProcessor employeeValidationProcessor;

	/** The department validation processor. */
	@Autowired
	private DepartmentValidationProcessor departmentValidationProcessor;

	/** The console employee writer. */
	@Autowired
	private ConsoleEmployeeWriter consoleEmployeeWriter;

	/** The console department writer. */
	@Autowired
	private ConsoleDepartmentWriter consoleDepartmentWriter;

	/**
	 * Read CSV files job.
	 *
	 * @return Job
	 */
	@Bean
	public Job readCSVFilesJob() {
		return this.jobBuilderFactory.get("readCSVFilesJob").incrementer(new RunIdIncrementer()).start(this.step1())
				.next(this.step2()).build();
	}

	/**
	 * Step 1.
	 *
	 * @return Step
	 */
	@Bean
	public Step step1() {
		return this.stepBuilderFactory.get("step1").<Employee, Employee>chunk(1).reader(this.empReader())
				.processor(this.employeeValidationProcessor).writer(this.consoleEmployeeWriter).build();
	}

	/**
	 * Step 2.
	 *
	 * @return Step
	 */
	@Bean
	public Step step2() {
		return this.stepBuilderFactory.get("step2").<Department, Department>chunk(1).reader(this.depReader())
				.processor(this.departmentValidationProcessor).writer(this.consoleDepartmentWriter).build();
	}

	/**
	 * Emp reader.
	 *
	 * @return FlatFileItemReader
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public FlatFileItemReader<Employee> empReader() {
		final FlatFileItemReader<Employee> reader = new FlatFileItemReader<>();
		reader.setLinesToSkip(1);
		reader.setResource(this.empInputResource);
		reader.setLineMapper(new DefaultLineMapper() {
			{
				this.setLineTokenizer(new DelimitedLineTokenizer() {
					{
						this.setNames(new String[] { "id", "address", "email", "name", "phone" });
						this.setIncludedFields(new int[] { 0, 1, 2, 3, 4 });
					}
				});
				this.setFieldSetMapper(new BeanWrapperFieldSetMapper() {
					{
						this.setTargetType(Employee.class);
					}
				});
			}
		});
		return reader;
	}

	/**
	 * Dep reader.
	 *
	 * @return FlatFileItemReader
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public FlatFileItemReader<Department> depReader() {
		final FlatFileItemReader<Department> reader = new FlatFileItemReader<>();
		reader.setLinesToSkip(1);
		reader.setResource(this.empInputResource);
		reader.setLineMapper(new DefaultLineMapper() {
			{
				this.setLineTokenizer(new DelimitedLineTokenizer() {
					{
						this.setNames(new String[] { "id", "departmentName" });
						this.setIncludedFields(new int[] { 0, 1, });
					}
				});
				this.setFieldSetMapper(new BeanWrapperFieldSetMapper() {
					{
						this.setTargetType(Department.class);
					}
				});
			}
		});
		return reader;
	}
}