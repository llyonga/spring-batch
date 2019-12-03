package cn.llyong.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 *
 * @description:  决策器得使用
 * @author: lvyong
 * @date: 2019-12-03
 * @time: 10:14 上午
 * @version: 1.0
 */
//@Configuration
public class JobDemoDecider {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step deciderStep1() {
        return stepBuilderFactory.get("deciderStep1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("execute deciderStep1...");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

    @Bean
    public Step deciderStep2() {
        return stepBuilderFactory.get("deciderStep2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("execute deciderStep2...");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

    @Bean
    public Step deciderStep3() {
        return stepBuilderFactory.get("deciderStep3")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("execute deciderStep3...");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

    /**
     * 决策器
     * @return
     */
    @Bean
    public JobExecutionDecider myDecider() {
        return new MyDecider();
    }

    @Bean
    public Job jobDeciderDemo() {
        return jobBuilderFactory.get("jobDeciderDemo")
                .start(deciderStep1())
                .next(myDecider())
                .from(myDecider()).on("even").to(deciderStep2())
                .from(myDecider()).on("odd").to(deciderStep3())
                // * 表示匹配所有
                .from(deciderStep3()).on("*").to(myDecider())
                .end()
                .build();
    }
}
