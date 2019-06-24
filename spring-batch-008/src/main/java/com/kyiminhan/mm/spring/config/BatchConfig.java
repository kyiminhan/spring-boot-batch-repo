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

@Configuration
@EnableBatchProcessing
@ComponentScan(basePackages = "com.kyiminhan.mm")
public class BatchConfig {

	@Autowired
	private JobBuilderFactory jobs;

	@Autowired
	private StepBuilderFactory steps;

	@Autowired
	private TaskOne taskOne;

	@Autowired
	private TaskTwo taskTwo;

	@Bean
	public Step stepOne() {
		return this.steps.get("stepOne").tasklet(this.taskOne).build();
	}

	@Bean
	public Step stepTwo() {
		return this.steps.get("stepTwo").tasklet(this.taskTwo).build();
	}

	@Bean(name = "demoJobOne")
	public Job demoJobOne() {
		return this.jobs.get("demoJobOne").start(this.stepOne()).next(this.stepTwo()).build();
	}

	@Bean(name = "demoJobTwo")
	public Job demoJobTwo() {
		return this.jobs.get("demoJobTwo").flow(this.stepOne()).build().build();
	}
}