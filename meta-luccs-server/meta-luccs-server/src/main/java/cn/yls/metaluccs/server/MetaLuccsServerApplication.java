package cn.yls.metaluccs.server;

import org.gdal.gdal.gdal;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableAsync
@MapperScan("cn.yls.metaluccs.server.mapper")
public class MetaLuccsServerApplication {

    public static void main(String[] args) {
        System.out.println("========+>"+System.getProperty("java.library.path"));
        gdal.AllRegister();
        SpringApplication.run(MetaLuccsServerApplication.class, args);
    }

}
