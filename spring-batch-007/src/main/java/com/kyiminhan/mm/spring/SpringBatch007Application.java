package com.kyiminhan.mm.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBatch007Application {

//	/** The job launcher. */
//	@Autowired
//	private JobLauncher jobLauncher;
//
//	/** The job. */
//	@Autowired
//	private Job job;

	public static void main(final String... args) {
		SpringApplication.run(SpringBatch007Application.class, args);
	}

//	@Override
//	public void run(final String... args) throws Exception {
//
//		final JobParameters params = new JobParametersBuilder()
//				.addString("JobID", String.valueOf(System.currentTimeMillis())).toJobParameters();
//		this.jobLauncher.run(this.job, params);
//	}

}