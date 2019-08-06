package com.ninjasul.springbatch.simeplejob.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;

import java.util.Random;

@Slf4j
public class OddDecider implements JobExecutionDecider {

    private static final Random random = new Random();

    @Override
    public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
        boolean randomBoolean = random.nextBoolean();
        log.info("랜덤값: {}", randomBoolean);

        return randomBoolean ? new FlowExecutionStatus("ODD") : new FlowExecutionStatus("EVEN");
    }
}