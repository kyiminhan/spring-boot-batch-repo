package com.kyiminhan.mm.spring.task;

import java.io.File;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.UnexpectedJobExecutionException;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;

import lombok.extern.log4j.Log4j2;

/** The Constant log. */
@Log4j2
public class FileDeletingTasklet implements Tasklet, InitializingBean {

	/** The resources. */
	private Resource[] resources;

	public void setResources(final Resource[] resources) {
		this.resources = resources;
	}

	/**
	 * After properties set.
	 *
	 * @throws Exception the exception
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(this.resources, "directory must be set");
	}

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
		for (final Resource r : this.resources) {
			final File file = r.getFile();
			final boolean deleted = file.delete();
			if (!deleted) {
				throw new UnexpectedJobExecutionException("Could not delete file " + file.getPath());
			} else {
				FileDeletingTasklet.log.info("deleted the file " + file.getPath());
			}
		}
		return RepeatStatus.FINISHED;
	}
}