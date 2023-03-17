package cn.yls.metaluccs.server.mapper;

import cn.yls.metaluccs.server.pojo.entity.DataSet;

import java.util.List;

public interface DataSetMapper {
    int insert(DataSet record);

    int insertSelective(DataSet record);

    List<DataSet> searchDatasetByCorrd(String polygonStr);
}