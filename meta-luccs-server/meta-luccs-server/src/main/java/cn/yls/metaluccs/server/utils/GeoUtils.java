package cn.yls.metaluccs.server.utils;

import cn.yls.metaluccs.server.model.Point;
import cn.yls.metaluccs.server.pojo.entity.DataSet;
import org.gdal.gdal.Dataset;
import org.gdal.gdal.gdal;
import org.gdal.gdalconst.gdalconstConstants;
import org.gdal.osr.SpatialReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;


public class GeoUtils {

    private final static Logger logger = LoggerFactory.getLogger(GeoUtils.class);

    private GeoUtils() {}

    public static DataSet importImage(String path){
        Dataset dataset = gdal.Open(path, gdalconstConstants.GA_ReadOnly);
        if (dataset == null) {
            logger.error(String.format("open %s failed", path));
            return null;
        }
        double[] geoTrans = dataset.GetGeoTransform();
        int xSize = dataset.GetRasterXSize();
        int ySize = dataset.GetRasterYSize();
        double left = geoTrans[0];
        double right = geoTrans[0] + geoTrans[1] * xSize;
        double top = geoTrans[3];
        double bottom = geoTrans[3] + geoTrans[5] * ySize;
        DataSet ds = new DataSet();
        ds.setId(UUID.randomUUID().toString());
        ds.setLeft(left);
        ds.setRight(right);
        ds.setTop(top);
        ds.setBottom(bottom);
        ds.setFilePath(path);
        return  ds;
    }
    public static boolean shiftImage(String path) {
        Dataset dataset = gdal.Open(path, gdalconstConstants.GA_ReadOnly);
        if (dataset == null) {
            logger.error(String.format("open %s failed", path));
            return false;
        }

        double[] geoTrans = dataset.GetGeoTransform();
        int xSize = dataset.GetRasterXSize();
        int ySize = dataset.GetRasterYSize();
        double left = geoTrans[0];
        double right = geoTrans[0] + geoTrans[1] * xSize;
        double top = geoTrans[3];
        double bottom = geoTrans[3] + geoTrans[5] * ySize;
        Point center = new Point((left + right) / 2, (top + bottom) / 2);
        logger.info(String.format("center: %f, %f", center.getX(), center.getY()));

        Point delta = new Point(0, 0);
        SpatialReference sr = dataset.GetSpatialRef();
        if (sr.IsGeographic() == 0) {
            SpatialReference srGeo = sr.CloneGeogCS();
            Point lngLat = TransUtils.geoTrans(sr, srGeo, center);
            Point deltaLngLat = TransUtils.deltaLngLat(lngLat.getX(), lngLat.getY());
            Point shiftLngLat = new Point(lngLat.getX() + deltaLngLat.getX(), lngLat.getY() + deltaLngLat.getY());
            Point shift = TransUtils.geoTrans(srGeo, sr, shiftLngLat);
            delta = new Point(shift.getX() - center.getX(), shift.getY() - center.getY());
        } else {
            delta = TransUtils.deltaLngLat(center.getX(), center.getY());
        }
        double deltaX = delta.getX();
        double deltaY = delta.getY();
        logger.info(String.format("shift: %f, %f", deltaX, deltaY));
        geoTrans[0] = geoTrans[0] + deltaX;
        geoTrans[3] = geoTrans[3] + deltaY;
        dataset.SetGeoTransform(geoTrans);
        dataset.FlushCache();

        return true;
    }

}
