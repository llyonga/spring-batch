package cn.llyong.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

/**
 * Created with IntelliJ IDEA.
 *
 * @description:
 * @author: lvyong
 * @date: 2019-12-03
 * @time: 10:00 上午
 * @version: 1.0
 */
//@Configuration
public class JobDemoSplit {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step splitStep1() {
        return stepBuilderFactory.get("splitStep1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("execute splitStep1...");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

    @Bean
    public Step splitStep2() {
        return stepBuilderFactory.get("splitStep2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("execute splitStep2...");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

    @Bean
    public Step splitStep3() {
        return stepBuilderFactory.get("splitStep3")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("execute splitStep3...");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

    @Bean
    public Flow splitFlowDemo1() {
        return new FlowBuilder<Flow>("splitFlowDemo1")
                .start(splitStep1())
                .build();
    }

    @Bean
    public Flow splitFlowDemo2() {
        return new FlowBuilder<Flow>("splitFlowDemo2")
                .start(splitStep2())
                .next(splitStep3())
                .build();
    }

    @Bean
    public Job joSplitDemo() {
        return jobBuilderFactory.get("joSplitDemo")
                .start(splitFlowDemo1())
                .split(new SimpleAsyncTaskExecutor())
                .add(splitFlowDemo2())
                .end()
                .build();
    }
}
