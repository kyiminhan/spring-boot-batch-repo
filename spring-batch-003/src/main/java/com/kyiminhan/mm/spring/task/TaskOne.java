package com.kyiminhan.mm.spring.task;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

/**
 * The Class TaskOne.<BR>
 *
 * @author KYIMINHAN <BR>
 * @version 1.0 <BR>
 * @since 2019/06/20 <BR>
 *        spring-batch-003 system <BR>
 *        com.kyiminhan.mm.spring.task <BR>
 *        TaskOne.java <BR>
 */
@Log4j2
@Component
public class TaskOne implements Tasklet {

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

		TaskOne.log.info("**************************************************");
		TaskOne.log.info("MyTaskOne start..");

		// TODO adding Coding

		TaskOne.log.info("MyTaskOne done..");
		TaskOne.log.info("**************************************************");
		return RepeatStatus.FINISHED;
	}
}