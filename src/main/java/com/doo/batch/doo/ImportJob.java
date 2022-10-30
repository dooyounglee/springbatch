package com.doo.batch.doo;

import java.util.Collections;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.batch.item.data.builder.RepositoryItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;

@Configuration
public class ImportJob {

	@Autowired private JobBuilderFactory jobBuilderFactory;
	@Autowired private StepBuilderFactory stepBuilderFactory;
	
	@Bean(name = "jobjobjob")
	public Job jobjob() throws Exception {
		return this.jobBuilderFactory.get("importJob")
				.start(importStep01())
				//.incrementer(new RunIdIncrementer())
				.build();
	}
	
	@Bean
	public Step importStep01() throws Exception {
		return this.stepBuilderFactory.get("importStep01")
				.<Book, Book>chunk(100)
				.reader(importReader01(null))
				.processor(importProcessor01())
				.writer(importWriter01(null))
				.allowStartIfComplete(true)
				.build();
	}
	
	@Bean
	@StepScope
	public RepositoryItemReader<Book> importReader01(BookRepository bookRepository) {
		
		return new RepositoryItemReaderBuilder<Book>()
				//.name("importReader01")
				.methodName("findAll")
				.repository(bookRepository)
				.sorts(Collections.singletonMap("id", Sort.Direction.ASC))
				.build();
	}
	
	@Bean
	@StepScope
	public ImportItemProcessor importProcessor01() {
		return new ImportItemProcessor();
	}
	
	@Bean
	public RepositoryItemWriter<Book> importWriter01(BookRepository bookRepository) {
		
		return new RepositoryItemWriterBuilder<Book>()
				.repository(bookRepository)
				.methodName("save")
				.build();
	}
//	@Bean
//	public ItemWriter<? super Object> itemWriter() {
//		return (items) -> items.forEach(System.out::println);
//	}
}

class ImportItemProcessor implements org.springframework.batch.item.ItemProcessor<Book, Book> {

	@Override
	public Book process(Book item) throws Exception {
		System.out.println(item);
		item.setPrice(item.getPrice() + 1);
		System.out.println(item);
		return item;
	}
	
}