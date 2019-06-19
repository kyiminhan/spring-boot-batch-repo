package com.kyiminhan.mm.spring.task;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

/**
 * The Class TaskOne.<BR>
 *
 * @author KYIMINHAN <BR>
 * @version 1.0 <BR>
 * @since 2019/06/19 <BR>
 * spring-batch-001 system <BR>
 * com.kyiminhan.mm.spring.task <BR>
 * TaskOne.java <BR>
 */
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

		System.out.println("**************************************************");
		System.out.println("MyTaskOne start..");

		// TODO adding Coding

		System.out.println("MyTaskOne done..");
		System.out.println("**************************************************");
		return RepeatStatus.FINISHED;
	}
}