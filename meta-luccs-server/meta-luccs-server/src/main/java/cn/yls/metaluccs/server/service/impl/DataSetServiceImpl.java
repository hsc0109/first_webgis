package cn.yls.metaluccs.server.service.impl;

import cn.yls.metaluccs.server.mapper.DataSetMapper;
import cn.yls.metaluccs.server.pojo.entity.DataSet;
import cn.yls.metaluccs.server.pojo.param.AreaSimpleInfo;
import cn.yls.metaluccs.server.service.DataSetService;
import cn.yls.metaluccs.server.utils.ZipUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DataSetServiceImpl implements DataSetService {

    @Value("${server.zip.rootPath}")
    private String serverZipRootPath;
    @Autowired
    DataSetMapper dataSetMapper;

    @Override
    public List<DataSet> searchDatasetByCorrd(AreaSimpleInfo areaSimpleInfo) {
        String polygonStr = "MULTIPOLYGON (((";
        if(areaSimpleInfo.getCorrdinates()!=null && areaSimpleInfo.getCorrdinates().size()>0){
            for(String[] cn : areaSimpleInfo.getCorrdinates()){
                polygonStr += cn[0]+" "+cn[1]+",";
            }
        }
        polygonStr = polygonStr.substring(0,polygonStr.length()-1) +")))";
        List<DataSet> list  = dataSetMapper.searchDatasetByCorrd(polygonStr);
        return list;
    }

    @Override
    public String downloadDatasetZip(AreaSimpleInfo areaSimpleInfo) throws Exception {
        List<File> fileList = new ArrayList<>();
        List<DataSet> list = this.searchDatasetByCorrd(areaSimpleInfo);
        if(list!=null && list.size()>0){
            for(DataSet item :list){
                fileList.add(new File(item.getFilePath()));
            }
        }
        String fileName = new SimpleDateFormat("yyyyMMddHHmmssSSS") .format(new Date() );
        String filePath = this.serverZipRootPath+fileName+".zip";
        FileOutputStream fos2 = new FileOutputStream(new
                File(filePath));
        ZipUtils.toZip(fileList, fos2);
        return filePath;
    }
}
