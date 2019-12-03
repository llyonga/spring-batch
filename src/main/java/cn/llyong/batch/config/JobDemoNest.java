package cn.llyong.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.JobStepBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * Created with IntelliJ IDEA.
 *
 * @description: Job的嵌套
 * @author: lvyong
 * @date: 2019-12-03
 * @time: 10:32 上午
 * @version: 1.0
 */
//@Configuration
public class JobDemoNest {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private Job childJobOne;

    @Autowired
    private Job childJobTwo;

    @Autowired
    private JobLauncher jobLauncher;

    @Bean
    public Job parentJob(JobRepository repository, PlatformTransactionManager transactionManager) {
        return jobBuilderFactory.get("parentJob")
                .start(stepChildJob1(repository, transactionManager))
                .next(stepChildJob2(repository, transactionManager))
                .build();
    }

    public Step stepChildJob1(JobRepository repository, PlatformTransactionManager transactionManager) {
        return new JobStepBuilder(new StepBuilder("stepChildJob1"))
                .job(childJobOne)
                // 使用父job得启动对象启动
                .launcher(jobLauncher)
                .repository(repository)
                .transactionManager(transactionManager)
                .build();
    }

    public Step stepChildJob2(JobRepository repository, PlatformTransactionManager transactionManager) {
        return new JobStepBuilder(new StepBuilder("stepChildJob2"))
                .job(childJobTwo)
                // 使用父job得启动对象启动
                .launcher(jobLauncher)
                .repository(repository)
                .transactionManager(transactionManager)
                .build();
    }
}
