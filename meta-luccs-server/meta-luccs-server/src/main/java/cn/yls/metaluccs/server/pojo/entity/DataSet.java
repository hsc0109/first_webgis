package cn.yls.metaluccs.server.pojo.entity;

import lombok.Data;

@Data
public class DataSet {
    private String id;

    private String dataSetName;

    private double fileSize;

    private String filePath;

    private double left;

    private double right;

    private double top;

    private double bottom;

    private Object geom;

    private String delFlag;

}