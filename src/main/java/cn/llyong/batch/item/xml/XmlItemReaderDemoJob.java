package cn.llyong.batch.item.xml;

import cn.llyong.batch.item.file.Customer;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

/**
 * Created with IntelliJ IDEA.
 *
 * @description:
 * @author: lvyong
 * @date: 2019-12-03
 * @time: 4:38 下午
 * @version: 1.0
 */
public class XmlItemReaderDemoJob {

    /**
     * 通过StaxEventItemReader读取
     */

    @Bean
    @StepScope
    public StaxEventItemReader<Customer> xmlFileReader() {
        StaxEventItemReader<Customer> reader = new StaxEventItemReader<>();
        reader.setResource(new ClassPathResource("customer.xml"));

        //指定需要处理的根标签（当前这个数据实体对象的根标签）
        reader.setFragmentRootElementName("customer");
        //把xml转对象

//        reader.setUnmarshaller();
        return reader;
    }
}
