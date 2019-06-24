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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.kyiminhan.mm.spring.chunkListener.ItemCountListener;
import com.kyiminhan.mm.spring.entity.Employee;
import com.kyiminhan.mm.spring.itemSteam.ItemCountItemStream;
import com.kyiminhan.mm.spring.reader.EmployeeFlatFileItemReader;
import com.kyiminhan.mm.spring.writer.ConsoleWriter;

/**
 * The Class BatchConfig.<BR>
 *
 * @author KYIMINHAN <BR>
 * @version 1.0 <BR>
 * @since 2019/06/24 <BR>
 *        spring-batch-013 system <BR>
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

	/** The item count item stream. */
	@Autowired
	private ItemCountItemStream itemCountItemStream;

	/** The item count listener. */
	@Autowired
	private ItemCountListener itemCountListener;

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
				.reader(this.employeeFlatFileItemReader).writer(this.consoleWriter).listener(this.itemCountListener)
				.stream(this.itemCountItemStream).build();

	}
}