/*
 *
 */
package com.kyiminhan.mm.spring.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Sort.Direction;

import com.kyiminhan.mm.spring.entity.Employee;
import com.kyiminhan.mm.spring.entity.WkEmployee;
import com.kyiminhan.mm.spring.repo.EmployeeRepo;
import com.kyiminhan.mm.spring.repo.WkEmployeeRepo;

import lombok.extern.log4j.Log4j2;

/**
 * The Class BatchConfig.<BR>
 *
 * @author KYIMINHAN <BR>
 * @version 1.0 <BR>
 * @since 2019/06/25 <BR>
 *        spring-batch-016 system <BR>
 *        com.kyiminhan.mm.spring.config <BR>
 *        BatchConfig.java <BR>
 */
@Configuration
@EnableBatchProcessing
@ComponentScan(basePackages = "com.kyiminhan.mm")

/** The Constant log. */

/** The Constant log. */
@Log4j2
public class BatchConfig {

	/** The job builder factory. */
	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	/** The step builder factory. */
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	/** The resource. */
	@Value("input/employee.csv")
	private Resource resource;

	/** The employee repo. */
	@Autowired
	protected EmployeeRepo employeeRepo;

	/** The wk employee repo. */
	@Autowired
	protected WkEmployeeRepo wkEmployeeRepo;

	/**
	 * Read CSV files job.
	 *
	 * @return Job
	 */
	@Bean
	public Job readCSVFilesJob() {
		return this.jobBuilderFactory.get("readCSVFilesJob").incrementer(new RunIdIncrementer()).start(this.step1())
				.listener(this.listener()).build();
	}

	/**
	 * Step 1.
	 *
	 * @return Step
	 */
	@Bean
	public Step step1() {
		return this.stepBuilderFactory.get("step1").<Employee, WkEmployee>chunk(50).reader(this.itemReader())
				.processor(this.itemProcessor()).writer(this.itemWriter()).build();

	}

	/**
	 * Item reader.
	 *
	 * @return RepositoryItemReader
	 */
	@Bean
	public RepositoryItemReader<Employee> itemReader() {
		final RepositoryItemReader<Employee> reader = new RepositoryItemReader<>();
		reader.setRepository(this.employeeRepo);
		reader.setMethodName("findAll");
		final Map<String, Direction> sort = new HashMap<>();
		sort.put("id", Direction.ASC);
		reader.setSort(sort);
		return reader;
	}

	/**
	 * Item processor.
	 *
	 * @return ItemProcessor
	 */
	@Bean
	public ItemProcessor<Employee, WkEmployee> itemProcessor() {
		return emp -> WkEmployee.builder().name(new StringBuilder(emp.getName()).append("-Tokyo").toString())
				.email(emp.getEmail()).address(emp.getAddress()).phone(emp.getPhone()).build();
	}

	/**
	 * Item writer.
	 *
	 * @return RepositoryItemWriter
	 */
	@Bean
	public RepositoryItemWriter<WkEmployee> itemWriter() {
		final RepositoryItemWriter<WkEmployee> itemWriter = new RepositoryItemWriter<>();
		itemWriter.setRepository(this.wkEmployeeRepo);
		itemWriter.setMethodName("save");
		return itemWriter;
	}

	/**
	 * Listener.
	 *
	 * @return JobExecutionListener
	 */
	@Bean
	public JobExecutionListener listener() {
		return new JobExecutionListener() {
			@Override
			public void beforeJob(final JobExecution jobExecution) {
			}

			@Override
			public void afterJob(final JobExecution jobExecution) {
				if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
					BatchConfig.log.info("!!! JOB FINISHED! Time to verify the results");
					BatchConfig.this.wkEmployeeRepo.findAll()
							.forEach(person -> BatchConfig.log.info("Found <" + person + "> in the database."));
				}
			}
		};
	}
}