package cn.llyong.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
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

/**
 * @description:
 * @author: llyong
 * @date: 2019/12/2
 * @time: 21:32
 * @version: 1.0
 */
@Configuration
@EnableBatchProcessing
public class JobDemoFlow {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step flowStep1() {
        return stepBuilderFactory.get("flowStep1").tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                System.out.println("execute flowStep1....");
                return RepeatStatus.FINISHED;
            }
       }).build();
    }

    @Bean
    public Step flowStep2() {
        return stepBuilderFactory.get("flowStep2").tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                System.out.println("execute flowStep2....");
                return RepeatStatus.FINISHED;
            }
        }).build();
    }

    @Bean
    public Step flowStep3() {
        return stepBuilderFactory.get("flowStep3").tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                System.out.println("execute flowStep3....");
                return RepeatStatus.FINISHED;
            }
        }).build();
    }

    /**
     * 指明flow对象包含哪些step
     * @return
     */
    @Bean
    public Flow flowDemo1() {
        return new FlowBuilder<Flow>("flowDemo1")
                .start(flowStep1()).next(flowStep2()).build();
    }

    @Bean
    public Job jobFlowDemo() {
        return jobBuilderFactory.get("jobFlowDemo")
                .start(flowDemo1()).next(flowStep3()).end()
                .build();
    }
}
