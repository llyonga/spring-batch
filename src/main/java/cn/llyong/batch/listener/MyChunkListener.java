package cn.llyong.batch.listener;

import org.springframework.batch.core.annotation.AfterChunk;
import org.springframework.batch.core.annotation.BeforeChunk;
import org.springframework.batch.core.scope.context.ChunkContext;

/**
 * Created with IntelliJ IDEA.
 *
 * @description:
 * @author: lvyong
 * @date: 2019-12-03
 * @time: 11:08 上午
 * @version: 1.0
 */
public class MyChunkListener {

    @BeforeChunk
    public void beforeChunk(ChunkContext context) {
        System.out.println("execute before chunk..., chunk info : " + context.getStepContext().getStepName());
    }

    @AfterChunk
    public void afterChunk(ChunkContext context) {
        System.out.println("execute after chunk..., chunk info : " + context.getStepContext().getStepName());
    }
}
