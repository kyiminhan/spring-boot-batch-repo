/*
 *
 */
package com.kyiminhan.mm.spring.config;

import javax.persistence.EntityManagerFactory;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JpaItemWriter;
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

import com.kyiminhan.mm.spring.entity.Employee;
import com.kyiminhan.mm.spring.repo.EmployeeRepo;

import lombok.extern.log4j.Log4j2;

/**
 * The Class BatchConfig.<BR>
 *
 * @author KYIMINHAN <BR>
 * @version 1.0 <BR>
 * @since 2019/06/25 <BR>
 *        spring-batch-015 system <BR>
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

	/** The emf. */
	@Autowired
	private EntityManagerFactory emf;

	/** The employee repo. */
	@Autowired
	protected EmployeeRepo employeeRepo;

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
		return this.stepBuilderFactory.get("step1").<Employee, Employee>chunk(50).reader(this.reader())
				.writer(this.jpaItemWriter()).build();

	}

	/**
	 * Reader.
	 *
	 * @return FlatFileItemReader
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public FlatFileItemReader<Employee> reader() {
		final FlatFileItemReader<Employee> reader = new FlatFileItemReader<>();
		reader.setLinesToSkip(1);
		reader.setResource(this.resource);
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
	 * Jpa item writer.
	 *
	 * @return JpaItemWriter
	 */
	@Bean
	public JpaItemWriter<Employee> jpaItemWriter() {
		final JpaItemWriter<Employee> writer = new JpaItemWriter<>();
		writer.setEntityManagerFactory(this.emf);
		return writer;
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
					BatchConfig.this.employeeRepo.findAll()
							.forEach(person -> BatchConfig.log.info("Found <" + person + "> in the database."));
				}
			}
		};
	}
}