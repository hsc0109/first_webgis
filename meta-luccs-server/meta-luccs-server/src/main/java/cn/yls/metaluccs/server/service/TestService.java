package cn.yls.metaluccs.server.service;

import cn.yls.metaluccs.server.mapper.DataSetMapper;
import cn.yls.metaluccs.server.pojo.entity.DataSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TestService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DataSetMapper dataSetMapper;

    @Async("asyncThreadPoolTaskExecutor")
    public void asyncMethod() {
        int i =0;
        while(true){
            i++;
            logger.info( i + " 查看接口是否有数据 ---- 异步方法内部线程名称：{}", Thread.currentThread().getName());
            if(i>10){
                logger.info("{} 接口访问完成", Thread.currentThread().getName());
                break;
            }
            sleep();
        }
    }
    public int insertDataSet(DataSet dataSet){
        return dataSetMapper.insert(dataSet);
    }
    public void syncMethod() {
        sleep();
    }

    private void sleep() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
