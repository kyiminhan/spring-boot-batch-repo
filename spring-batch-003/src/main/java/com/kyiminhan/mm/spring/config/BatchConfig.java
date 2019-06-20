package com.kyiminhan.mm.spring.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kyiminhan.mm.spring.listener.JobResultListener;
import com.kyiminhan.mm.spring.listener.StepItemProcessListener;
import com.kyiminhan.mm.spring.listener.StepItemReadListener;
import com.kyiminhan.mm.spring.listener.StepItemWriteListener;
import com.kyiminhan.mm.spring.listener.StepResultListener;
import com.kyiminhan.mm.spring.listener.StepSkipListener;
import com.kyiminhan.mm.spring.task.TaskOne;
import com.kyiminhan.mm.spring.task.TaskTwo;

import lombok.Setter;

/**
 * The Class BatchConfig.<BR>
 *
 * @author KYIMINHAN <BR>
 * @version 1.0 <BR>
 * @since 2019/06/20 <BR>
 *        spring-batch-003 system <BR>
 *        com.kyiminhan.mm.spring.config <BR>
 *        BatchConfig.java <BR>
 */
@Configuration
@EnableBatchProcessing
@Setter(onMethod = @__(@Autowired))
@SuppressWarnings("unused")
public class BatchConfig {

	/** The jobs. */
	private JobBuilderFactory jobs;

	/** The steps. */
	private StepBuilderFactory steps;

	/** The job result listener. */
	private JobResultListener jobResultListener;

	/** The step result listener. */
	private StepResultListener stepResultListener;

	/** The step item read listener. */
	private StepItemReadListener stepItemReadListener;

	/** The step item process listener. */
	private StepItemProcessListener stepItemProcessListener;

	/** The step item write listener. */
	private StepItemWriteListener stepItemWriteListener;

	/** The step skip listener. */
	private StepSkipListener stepSkipListener;

	/** The task one. */
	private TaskOne taskOne;

	/** The task two. */
	private TaskTwo taskTwo;

	/**
	 * Step one.
	 *
	 * @return Step
	 */
	@Bean
	public Step stepOne() {
		final Step step = this.steps.get("stepOne").tasklet(this.taskOne).listener(this.stepResultListener).build();
		// final Step step =
		// this.steps.get("stepOne").tasklet(this.taskOne).listener(this.stepItemReadListener).build();
		// final Step step =
		// this.steps.get("stepOne").tasklet(this.taskOne).listener(this.stepItemProcessListener).build();
		// final Step step =
		// this.steps.get("stepOne").tasklet(this.taskOne).listener(this.stepItemWriteListener).build();
		// final Step step =
		// this.steps.get("stepOne").tasklet(this.taskOne).listener(this.stepSkipListener).build();
		return step;
	}

	/**
	 * Step two.
	 *
	 * @return Step
	 */
	@Bean
	public Step stepTwo() {
		final Step step = this.steps.get("stepTwo").tasklet(this.taskTwo).listener(this.stepResultListener).build();
		// final Step step =
		// this.steps.get("stepTwo").tasklet(this.taskTwo).listener(this.stepItemReadListener).build();
		// final Step step =
		// this.steps.get("stepTwo").tasklet(this.taskTwo).listener(this.stepItemProcessListener).build();
		// final Step step =
		// this.steps.get("stepTwo").tasklet(this.taskTwo).listener(this.stepItemWriteListener).build();
		// final Step step =
		// this.steps.get("stepTwo").tasklet(this.taskTwo).listener(this.stepSkipListener).build();
		return step;
	}

	/**
	 * Demo job.
	 *
	 * @return Job
	 */
	@Bean
	public Job demoJob() {
		return this.jobs.get("demoJob").incrementer(new RunIdIncrementer()).listener(this.jobResultListener)
				.start(this.stepOne()).next(this.stepTwo()).build();
	}
}