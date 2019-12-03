package cn.llyong.batch.config;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;

/**
 * Created with IntelliJ IDEA.
 *
 * @description:
 * @author: lvyong
 * @date: 2019-12-03
 * @time: 10:18 上午
 * @version: 1.0
 */
public class MyDecider implements JobExecutionDecider {

    private int count = 0;

    @Override
    public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
        count ++;

        if (count % 2 == 0) {
            return new FlowExecutionStatus("even");
        } else {
            return new FlowExecutionStatus("odd");
        }
    }
}
