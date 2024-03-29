package com.kyiminhan.mm.spring.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.kyiminhan.mm.spring.task.TaskOne;
import com.kyiminhan.mm.spring.task.TaskTwo;

/**
 * The Class BatchConfig.<BR>
 *
 * @author KYIMINHAN <BR>
 * @version 1.0 <BR>
 * @since 2019/06/24 <BR>
 *        spring-batch-008 system <BR>
 *        com.kyiminhan.mm.spring.config <BR>
 *        BatchConfig.java <BR>
 */
@Configuration
@EnableBatchProcessing
@ComponentScan(basePackages = "com.kyiminhan.mm")
public class BatchConfig {

	/** The jobs. */
	@Autowired
	private JobBuilderFactory jobs;

	/** The steps. */
	@Autowired
	private StepBuilderFactory steps;

	/** The task one. */
	@Autowired
	private TaskOne taskOne;

	/** The task two. */
	@Autowired
	private TaskTwo taskTwo;

	/**
	 * Step one.
	 *
	 * @return Step
	 */
	@Bean
	public Step stepOne() {
		return this.steps.get("stepOne").tasklet(this.taskOne).build();
	}

	/**
	 * Step two.
	 *
	 * @return Step
	 */
	@Bean
	public Step stepTwo() {
		return this.steps.get("stepTwo").tasklet(this.taskTwo).build();
	}

	/**
	 * Demo job one.
	 *
	 * @return Job
	 */
	@Bean(name = "demoJobOne")
	public Job demoJobOne() {
		return this.jobs.get("demoJobOne").start(this.stepOne()).next(this.stepTwo()).build();
	}

	/**
	 * Demo job two.
	 *
	 * @return Job
	 */
	@Bean(name = "demoJobTwo")
	public Job demoJobTwo() {
		return this.jobs.get("demoJobTwo").flow(this.stepOne()).build().build();
	}
}