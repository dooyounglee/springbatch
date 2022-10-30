package com.doo.batch.test;

import java.util.Map;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class TestController {

	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	private ApplicationContext context;
	
	@RequestMapping(value = "/batch")
	public ExitStatus test(@RequestParam Map<String, Object> param)
			throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		log.info("param : {}", param);
		Job job = this.context.getBean(param.get("jobName").toString(), Job.class);
		
		return this.jobLauncher.run(job, new JobParametersBuilder().addString("param1", "value1").toJobParameters()).getExitStatus();
	}
}
