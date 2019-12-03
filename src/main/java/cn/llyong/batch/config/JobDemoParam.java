package cn.llyong.batch.config;

import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @description:
 * @author: lvyong
 * @date: 2019-12-03
 * @time: 11:28 上午
 * @version: 1.0
 */
//@Configuration
public class JobDemoParam implements StepExecutionListener {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    private Map<String, JobParameter> params;

    @Bean
    public Job jobParamDemo() {
        return jobBuilderFactory.get("jobParamDemo")
                .start(paramStep())
                .build();
    }

    /**
     * 使用step级别的监听来传递数据
     * @return
     */
    @Bean
    public Step paramStep() {
        return stepBuilderFactory.get("paramStep")
                .listener(this)
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("execute paramStep....");
                        System.out.println("接收到的参数的值：" + params.toString());
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

    /**
     * 这里测试采用启动的时候传入
     * @param stepExecution
     */
    @Override
    public void beforeStep(StepExecution stepExecution) {
        params = stepExecution.getJobParameters().getParameters();
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return ExitStatus.COMPLETED;
    }
}
