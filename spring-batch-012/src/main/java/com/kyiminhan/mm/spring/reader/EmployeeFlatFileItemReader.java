package com.kyiminhan.mm.spring.reader;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.kyiminhan.mm.spring.entity.Employee;

/**
 * The Class EmployeeFlatFileItemReader.<BR>
 *
 * @author KYIMINHAN <BR>
 * @version 1.0 <BR>
 * @since 2019/06/24 <BR>
 *        spring-batch-012 system <BR>
 *        com.kyiminhan.mm.spring.reader <BR>
 *        EmployeeFlatFileItemReader.java <BR>
 */
@Component
@StepScope
public class EmployeeFlatFileItemReader extends FlatFileItemReader<Employee> {

	/** The resource. */
	@Value("input/employee.csv")
	private Resource resource;

	/**
	 * After properties set.
	 *
	 * @throws Exception the exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void afterPropertiesSet() throws Exception {
		this.setLinesToSkip(1);
		this.setResource(this.resource);
		this.setLineMapper(new DefaultLineMapper() {
			{
				this.setLineTokenizer(new DelimitedLineTokenizer() {
					{
						this.setNames(new String[] { "id", "address", "email", "name", "phone" });
						this.setIncludedFields(new int[] { 0, 1, 2, 3, 4 });
					}
				});
				this.setFieldSetMapper(new BeanWrapperFieldSetMapper() {
					{
						this.setTargetType(Employee.class);
					}
				});
			}
		});
		super.afterPropertiesSet();
	}
}