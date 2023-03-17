package cn.yls.metaluccs.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AsyncTest {

    @Autowired
    TestService testService;

    public void downZip(){
        testService.asyncMethod();
    }
}
