package com.kyiminhan.mm.spring.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
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
import com.kyiminhan.mm.spring.processor.EmployeeValidationProcessor;
import com.kyiminhan.mm.spring.writer.ConsoleWriter;

/**
 * The Class BatchConfig.<BR>
 *
 * @author KYIMINHAN <BR>
 * @version 1.0 <BR>
 * @since 2019/06/20 <BR>
 *        spring-batch-004 system <BR>
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

	/** The input resource. */
	@Value("/input/employee.csv")
	private Resource inputResource;

	/** The employee validation processor. */
	@Autowired
	private EmployeeValidationProcessor employeeValidationProcessor;

	/** The console writer. */
	@Autowired
	private ConsoleWriter consoleWriter;

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
		return this.stepBuilderFactory.get("step1").<Employee, Employee>chunk(1).reader(this.reader())
				.processor(this.employeeValidationProcessor).writer(this.consoleWriter).build();
	}

	/**
	 * Reader.
	 *
	 * @return FlatFileItemReader
	 */
	@Bean
	public FlatFileItemReader<Employee> reader() {
		final FlatFileItemReader<Employee> itemReader = new FlatFileItemReader<>();
		itemReader.setLineMapper(this.lineMapper());
		itemReader.setLinesToSkip(1);
		itemReader.setResource(this.inputResource);
		return itemReader;
	}

	/**
	 * Line mapper.
	 *
	 * @return LineMapper
	 */
	@Bean
	public LineMapper<Employee> lineMapper() {
		final DefaultLineMapper<Employee> lineMapper = new DefaultLineMapper<>();
		final DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setNames(new String[] { "id", "address", "email", "name", "phone" });
		lineTokenizer.setIncludedFields(new int[] { 0, 1, 2, 3, 4 });
		final BeanWrapperFieldSetMapper<Employee> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(Employee.class);
		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(fieldSetMapper);
		return lineMapper;
	}
}