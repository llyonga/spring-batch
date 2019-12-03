package cn.llyong.batch.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

/**
 * Created with IntelliJ IDEA.
 *
 * @description:
 * @author: lvyong
 * @date: 2019-12-03
 * @time: 11:06 上午
 * @version: 1.0
 */
public class MyJobListener implements JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("execute job before..., job info : " + jobExecution.getJobInstance().getJobName());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println("execute job after..., job info : " + jobExecution.getJobInstance().getJobName());
    }
}
