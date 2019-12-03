package cn.llyong.batch.item.file;

import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @description:
 * @author: lvyong
 * @date: 2019-12-03
 * @time: 4:09 下午
 * @version: 1.0
 */
@Data
public class Customer {
    private int id;
    private String firstName;
    private String lastName;
    private Date birthday;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"firstName\":\"")
                .append(firstName).append('\"');
        sb.append(",\"lastName\":\"")
                .append(lastName).append('\"');
        sb.append(",\"birthday\":\"")
                .append(birthday).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
