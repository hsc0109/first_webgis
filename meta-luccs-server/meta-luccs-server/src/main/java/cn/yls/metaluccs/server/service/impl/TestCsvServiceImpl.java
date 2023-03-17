package cn.yls.metaluccs.server.service.impl;

import cn.yls.metaluccs.server.mapper.TestCsvMapper;
import cn.yls.metaluccs.server.pojo.entity.TestCsv;
import cn.yls.metaluccs.server.service.TestCsvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestCsvServiceImpl implements TestCsvService {
    @Autowired
    TestCsvMapper testCsvMapper;
    @Override
    public int createGeom() {
        List<TestCsv> csves = testCsvMapper.selectAllCsv();
        if(csves!=null && csves.size()>0){
            for (TestCsv csv : csves){
                String csvesString = csv.getJwd().replace("(","").replace(")","");
                String[] array = csvesString.split(",");
                //String geomString = "MULTIPOLYGON ((("+array[0]+" "+array[1]+","+array[2]+" "+array[1]+","+array[2]+" "+array[3]+","+array[0]+" "+array[3]+","+array[0]+" "+array[1]+")))";
                String geomString = "MULTIPOLYGON ((("+array[1]+" "+array[0]+","+array[3]+" "+array[0]+","+array[3]+" "+array[2]+","+array[1]+" "+array[2]+","+array[1]+" "+array[0]+")))";
                System.out.println("----------->"+geomString);
                csv.setGeom(geomString);
                testCsvMapper.updateCsv(csv);
            }
        }
        return csves.size();
    }
}
