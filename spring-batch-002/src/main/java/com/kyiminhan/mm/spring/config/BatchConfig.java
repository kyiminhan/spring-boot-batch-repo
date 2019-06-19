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
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.kyiminhan.mm.spring.task.TaskOne;
import com.kyiminhan.mm.spring.task.TaskTwo;

/**
 * The Class BatchConfig.<BR>
 *
 * @author KYIMINHAN <BR>
 * @version 1.0 <BR>
 * @since 2019/06/19 <BR>
 *        spring-batch-001 system <BR>
 *        com.kyiminhan.mm.spring.config <BR>
 *        BatchConfig.java <BR>
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.kyiminhan.mm")
@ComponentScan(basePackages = "com.kyiminhan.mm")
@EnableBatchProcessing
public class BatchConfig {

	/** The jobs. */
	@Autowired
	private JobBuilderFactory jobs;

	/** The steps. */
	@Autowired
	private StepBuilderFactory steps;

	/**
	 * Step one.
	 *
	 * @return Step
	 */
	@Bean
	public Step stepOne() {
		return this.steps.get("stepOne").tasklet(new TaskOne()).build();
	}

	/**
	 * Step two.
	 *
	 * @return Step
	 */
	@Bean
	public Step stepTwo() {
		return this.steps.get("stepTwo").tasklet(new TaskTwo()).build();
	}

	/**
	 * Demo job.
	 *
	 * @return Job
	 */
	@Bean
	public Job demoJob() {
		return this.jobs.get("demoJob").incrementer(new RunIdIncrementer()).start(this.stepOne()).next(this.stepTwo())
				.build();
	}
}