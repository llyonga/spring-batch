package cn.llyong;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created with IntelliJ IDEA.
 *
 * @description:
 * @author:
 * @date: 2019/12/2
 * @time: 5:41 下午
 * @version: 1.0
 */
@SpringBootApplication
@EnableBatchProcessing
public class SpringBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatchApplication.class, args);
    }

}
