package com.kyiminhan.mm.spring.task;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

/**
 * The Class TaskTwo.<BR>
 *
 * @author KYIMINHAN <BR>
 * @version 1.0 <BR>
 * @since 2019/06/24 <BR>
 *        spring-batch-008 system <BR>
 *        com.kyiminhan.mm.spring.task <BR>
 *        TaskTwo.java <BR>
 */

/** The Constant log. */
@Log4j2
@Component
public class TaskTwo implements Tasklet {

	/**
	 * Execute.
	 *
	 * @param contribution the contribution
	 * @param chunkContext the chunk context
	 * @return RepeatStatus
	 * @throws Exception the exception
	 */
	@Override
	public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {

		TaskTwo.log.info("**************************************************");
		TaskTwo.log.info("MyTaskTwo start..");

		// TODO adding Coding

		TaskTwo.log.info("MyTaskTwo done..");
		TaskTwo.log.info("**************************************************");
		return RepeatStatus.FINISHED;
	}
}