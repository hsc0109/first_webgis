package cn.yls.metaluccs.server.service;

import cn.yls.metaluccs.server.pojo.entity.DataSet;
import cn.yls.metaluccs.server.pojo.param.AreaSimpleInfo;

import java.util.List;

public interface DataSetService {
    List<DataSet> searchDatasetByCorrd(AreaSimpleInfo areaSimpleInfo);

    String downloadDatasetZip(AreaSimpleInfo areaSimpleInfo) throws Exception;
}
