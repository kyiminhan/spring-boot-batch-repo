/*
 *
 */
package com.kyiminhan.mm.spring.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttribute;

import com.kyiminhan.mm.spring.entity.Employee;
import com.kyiminhan.mm.spring.processor.EmployeeProcessor;
import com.kyiminhan.mm.spring.reader.EmployeeFlatFileItemReader;
import com.kyiminhan.mm.spring.writer.ConsoleWriter;

/**
 * The Class BatchConfig.<BR>
 *
 * @author KYIMINHAN <BR>
 * @version 1.0 <BR>
 * @since 2019/06/25 <BR>
 *        spring-batch-014 system <BR>
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

	/** The employee flat file item reader. */
	@Autowired
	private EmployeeFlatFileItemReader employeeFlatFileItemReader;

	/** The employee processor. */
	@Autowired
	private EmployeeProcessor employeeProcessor;

	/** The console writer. */
	@Autowired
	private ConsoleWriter<Employee> consoleWriter;

	/**
	 * Read CSV files job.
	 *
	 * @return Job
	 */
	@Bean
	public Job readCSVFilesJob() {
		return this.jobBuilderFactory.get("readCSVFilesJob").incrementer(new RunIdIncrementer()).start(this.step1())
				.build();
	}

	/**
	 * Step 1.
	 *
	 * @return Step
	 */
	@Bean
	public Step step1() {
		// chunk(1)
		// chunk(5)
		// chunk(10)
		// chunk(20)
		// chunk(50)
		return this.stepBuilderFactory.get("step1").<Employee, Employee>chunk(50)
				.reader(this.employeeFlatFileItemReader).processor(this.employeeProcessor).writer(this.consoleWriter)
				.transactionAttribute(this.transactionAttribute()).build();

	}

	/**
	 * Transaction attrib.
	 *
	 * @return TransactionAttribute
	 */
	@Bean
	public TransactionAttribute transactionAttribute() {
		final DefaultTransactionAttribute attribute = new DefaultTransactionAttribute();
		attribute.setIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);
		attribute.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		attribute.setTimeout(30);
		return null;
	}
}