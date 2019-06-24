package com.kyiminhan.mm.spring.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.kyiminhan.mm.spring.entity.Employee;
import com.kyiminhan.mm.spring.reader.EmployeeFlatFileItemReader;
import com.kyiminhan.mm.spring.task.FileDeletingTasklet;
import com.kyiminhan.mm.spring.writer.ConsoleWriter;

/**
 * The Class BatchConfig.<BR>
 *
 * @author KYIMINHAN <BR>
 * @version 1.0 <BR>
 * @since 2019/06/24 <BR>
 *        spring-batch-011 system <BR>
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

	/** The console writer. */
	@Autowired
	private ConsoleWriter<Employee> consoleWriter;

	/** The employee flat file item reader. */
	@Autowired
	private EmployeeFlatFileItemReader employeeFlatFileItemReader;

	/** The input resources. */
	@Value("file:C:/Users/kyiminhan/Desktop/test/employee*.csv")
	private Resource[] inputResources;

	/**
	 * Multi resource item reader.
	 *
	 * @return MultiResourceItemReader
	 */
	@Bean
	@StepScope
	public MultiResourceItemReader<Employee> multiResourceItemReader() {
		final MultiResourceItemReader<Employee> resourceItemReader = new MultiResourceItemReader<>();
		resourceItemReader.setResources(this.inputResources);
		resourceItemReader.setDelegate(this.employeeFlatFileItemReader);
		return resourceItemReader;
	}

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
		return this.stepBuilderFactory.get("step1").<Employee, Employee>chunk(5).reader(this.multiResourceItemReader())
				.writer(this.consoleWriter).build();
	}

	/**
	 * Step 2.
	 *
	 * @return Step
	 */
	@Bean
	public Step step2() {
		final FileDeletingTasklet task = new FileDeletingTasklet();
		task.setResources(this.inputResources);
		return this.stepBuilderFactory.get("step2").tasklet(task).build();
	}
}