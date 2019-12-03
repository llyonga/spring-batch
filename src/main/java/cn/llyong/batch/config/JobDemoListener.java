package cn.llyong.batch.config;

import cn.llyong.batch.listener.MyChunkListener;
import cn.llyong.batch.listener.MyJobListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @description:
 * @author: lvyong
 * @date: 2019-12-03
 * @time: 11:11 上午
 * @version: 1.0
 */
//@Configuration
public class JobDemoListener {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job jobListenerDemo() {
        return jobBuilderFactory.get("jobListenerDemo")
                .start(listenerJobStep1())
                .listener(new MyJobListener())
                .build();
    }

    @Bean
    public Step listenerJobStep1() {
        return stepBuilderFactory.get("listenerJobStep1")
                // 表示完整的读取size个数据，就进行处理  read process write
                .<String, String>chunk(2)
                .faultTolerant()
                .listener(new MyChunkListener())
                .reader(read())
                .writer(writer())
                .build();

    }

    @Bean
    public ItemWriter<String> writer() {
        return new ItemWriter<String>() {
            @Override
            public void write(List<? extends String> list) throws Exception {
                for (String s : list) {
                    System.out.println(s);
                }
            }
        };
    }

    @Bean
    public ItemReader<String> read() {
        return new ListItemReader<String>(Arrays.asList("zhangsan", "lisi", "wangwu"));
    }
}
