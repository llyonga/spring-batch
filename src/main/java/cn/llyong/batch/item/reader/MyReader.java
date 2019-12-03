package cn.llyong.batch.item.reader;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @description:
 * @author: lvyong
 * @date: 2019-12-03
 * @time: 2:22 下午
 * @version: 1.0
 */
public class MyReader implements ItemReader<String> {

    private Iterator<String> iterator;

    public MyReader(List<String> data) {
        this.iterator = data.iterator();
    }

    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        //一个数据一个数据的读取
        if (iterator.hasNext()) {
            String next = this.iterator.next();
            System.out.println("读取到一个数据：" + next);
            return next;
        } else {
            return null;
        }
    }
}
