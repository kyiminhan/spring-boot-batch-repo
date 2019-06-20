package com.kyiminhan.mm.spring.task;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

/**
 * The Class TaskTwo.<BR>
 *
 * @author KYIMINHAN <BR>
 * @version 1.0 <BR>
 * @since 2019/06/20 <BR>
 *        spring-batch-003 system <BR>
 *        com.kyiminhan.mm.spring.task <BR>
 *        TaskTwo.java <BR>
 */
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

		System.out.println("**************************************************");
		System.out.println("MyTaskTwo start..");

		// TODO adding Coding

		System.out.println("MyTaskTwo done..");
		System.out.println("**************************************************");
		return RepeatStatus.FINISHED;
	}
}