package cn.yls.metaluccs.server.controller;

import cn.yls.metaluccs.server.pojo.entity.DataSet;
import cn.yls.metaluccs.server.pojo.vo.CountrySimpleVo;
import cn.yls.metaluccs.server.service.AsyncTest;
import cn.yls.metaluccs.server.service.TestCsvService;
import cn.yls.metaluccs.server.service.TestService;
import cn.yls.metaluccs.server.utils.GeoUtils;
import it.geosolutions.geoserver.rest.GeoServerRESTManager;
import it.geosolutions.geoserver.rest.GeoServerRESTPublisher;
import it.geosolutions.geoserver.rest.encoder.coverage.GSCoverageEncoder;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static it.geosolutions.geoserver.rest.encoder.GSResourceEncoder.ProjectionPolicy.REPROJECT_TO_DECLARED;


@RestController
public class TestController {

    private final static Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private TestService testService;

    @Autowired
    private AsyncTest asyncTest;

    @Autowired
    private TestCsvService testCsvServiceImpl;


    @GetMapping("/test")
    public CountrySimpleVo test(){
       // log.info("springboot test");
        CountrySimpleVo vo = new CountrySimpleVo();
        return vo;
    }
    @GetMapping("/test1")
    public String test1(){
        // log.info("springboot test");
        CountrySimpleVo vo = new CountrySimpleVo();
        return "vo";
    }

    @GetMapping("/import")
    public void insertTiffToPostGis(){


        String path = "/home/hanl/Data/yls/lulc2020/lc2020/";
        File rootDir = new File(path);
        int fileNum = 0;
        File[] subList = rootDir.listFiles();
        for (File file : subList) {
            if (file.isDirectory()) {
                scanFolder(file);
            } else {
                String suffix = file.getName().toLowerCase();
                if (suffix.endsWith(".tif") || suffix.endsWith(".tiff")) {
                    fileNum++;
                    String tifPath = file.getAbsolutePath();
                    String name = suffix;
                    double fileSize = file.length();
                    DataSet dataset = GeoUtils.importImage(tifPath);
                    dataset.setFileSize(fileSize);
                    dataset.setDataSetName(name);
                    int i = testService.insertDataSet(dataset);
                    logger.info(String.format("import number is  %d , The tif image name is %s ,It import successed", i, name));
                }
            }
        }
    }

    @GetMapping("/publish/data/tiff")
    public String publishDataTiff() throws FileNotFoundException, MalformedURLException {
        String url = "http://localhost:8080/geoserver";
        String userName = "admin";
        String passWord = "geoserver";

        String workSpace = "GOODLUCCs";     //创建的工作区名称
        String storeName = "test-tiff"; //创建的数据存储名称

        //与GeoServer连接，进行管理
        GeoServerRESTManager manager = new GeoServerRESTManager(new URL(url), userName, passWord);
        GeoServerRESTPublisher publisher = manager.getPublisher();

        //创建工作区
        boolean createWorkSpace = publisher.createWorkspace(workSpace);
        System.out.println("create ws " + createWorkSpace);

        //初始化和构造栅格数据储存
        String srs = "EPSG";//SRS空间参考系统 crs坐标参考系统
        GSCoverageEncoder gsCoverageEncoder = new GSCoverageEncoder();
        gsCoverageEncoder.setName("test1");
        gsCoverageEncoder.setTitle("test1");
        gsCoverageEncoder.setSRS(srs);
        gsCoverageEncoder.setNativeFormat("GeoTIFF");
        gsCoverageEncoder.addSupportedFormats("GEOTIFF");
        gsCoverageEncoder.addKeyword("geoTiff");
        gsCoverageEncoder.addKeyword("WCS");
        gsCoverageEncoder.setNativeCRS(srs);
        gsCoverageEncoder.setRequestSRS(srs);
        gsCoverageEncoder.setResponseSRS(srs);
        gsCoverageEncoder.setProjectionPolicy(REPROJECT_TO_DECLARED);
        gsCoverageEncoder.setLatLonBoundingBox(-180, -90, 180, 90, "EPSG:4326");
        //创建栅格数据存储
        boolean createStore = manager.getPublisher().createCoverage(workSpace,storeName,gsCoverageEncoder);
        System.out.println("Coverage store " + createStore);

        //待发布GeoTIFF数据路径
        String filePath = "/home/hanl/Data/yls/lulc2020/lc2020/01G_20200101-20210101.tif";
        //发布GeoTIFF（如果没有目标数据存储则会先自动创建该命名栅格数据存储再发布）
        boolean publish = manager.getPublisher().publishGeoTIFF(workSpace, storeName,new File(filePath));
        System.out.println("publish Coverage " + publish);

        //读取目标栅格数据存储
        //RESTCoverageStore restCoverageStore = manager.getReader().getCoverageStore(workSpace, storeName);
        return "OK";
    }

    @GetMapping("/tiff/create/geom")
    public Integer createGeom(){
        int i = testCsvServiceImpl.createGeom();
        return i;
    }
    @GetMapping("/scanFolder/tiff")
    public  void scanTifFolder() {

        String path = "/home/hanl/Data/yls/lulc2020/lc2020/";
        File rootDir = new File(path);

        scanFolder(rootDir);
    }
    @GetMapping("/tiff/down")
    public void downTiff(){
        asyncTest.downZip();
    }

    protected void scanFolder(File dir){

        int fileNum = 0;
        File[] subList = dir.listFiles();
        for (File file : subList) {
            if (file.isDirectory()) {
                scanFolder(file);
            } else {
                String suffix = file.getName().toLowerCase();
                if (suffix.endsWith(".tif") || suffix.endsWith(".tiff")) {
                    fileNum++;
                    String tifPath = file.getAbsolutePath();
                    logger.info(String.format("handling file %d %s", fileNum, tifPath));
                    if (GeoUtils.shiftImage(tifPath)) {
                        logger.info(String.format("shift file %d %s successed", fileNum, tifPath));
                    } else {
                        logger.error(String.format("shift file %d %s failed", fileNum, tifPath));
                    }
                }
            }
        }
    }




}
