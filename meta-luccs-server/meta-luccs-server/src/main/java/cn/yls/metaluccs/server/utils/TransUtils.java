package cn.yls.metaluccs.server.utils;
import cn.yls.metaluccs.server.model.Point;
import org.gdal.osr.CoordinateTransformation;
import org.gdal.osr.SpatialReference;


public class TransUtils {

    private static final double AXIS = 6378245.0;

    private static final double OFFSET = 0.00669342162296594323;

    private TransUtils() {}

    public static Point geoTrans(SpatialReference inSr, SpatialReference outSr, Point in) {
        CoordinateTransformation ct = CoordinateTransformation.CreateCoordinateTransformation(inSr, outSr);
        double[] out = ct.TransformPoint(in.getX(), in.getY());
        return new Point(out[0], out[1]);
    }

    public static Point geoTrans(int inEpsg, int outEpsg, Point in) {
        SpatialReference inSr = new SpatialReference();
        inSr.ImportFromEPSG(inEpsg);
        SpatialReference outSr = new SpatialReference();
        outSr.ImportFromEPSG(outEpsg);
        return geoTrans(inSr, outSr, in);
    }

    private static double transformLat(double lng, double lat) {
        double ret = -100.0 + 2.0 * lng + 3.0 * lat + 0.2 * lat * lat + 0.1 * lng * lat + 0.2 * Math.sqrt(Math.abs(lng));
        ret += ((20.0 * Math.sin(6.0 * lng * Math.PI) + 20.0 * Math.sin(2.0 * lng * Math.PI)) * 2.0) / 3.0;
        ret += ((20.0 * Math.sin(lat * Math.PI) + 40.0 * Math.sin((lat / 3.0) * Math.PI)) * 2.0) / 3.0;
        ret += ((150.0 * Math.sin((lng / 12.0) * Math.PI) + 300.0 * Math.sin((lng / 30.0) * Math.PI)) * 2.0) / 3.0;
        return ret;
    }

    private static double transformLng(double lng, double lat) {
        double ret = 300.0 + lng + 2.0 * lat + 0.1 * lng * lng + 0.1 * lng * lat + 0.1 * Math.sqrt(Math.abs(lng));
        ret += ((20.0 * Math.sin(6.0 * lng * Math.PI) + 2.0 * Math.sin(2.0 * lng * Math.PI)) * 2.0) / 3.0;
        ret += ((20.0 * Math.sin(lng * Math.PI) + 40.0 * Math.sin((lng / 3.0) * Math.PI)) * 2.0) / 3.0;
        ret += ((150.0 * Math.sin((lng / 12.0) * Math.PI) + 300.0 * Math.sin((lng / 30.0) * Math.PI)) * 2.0) / 3.0;
        return ret;
    }

    public static Point deltaLngLat(double lng, double lat) {
        double dLat = transformLat(lng - 105.0, lat - 35.0);
        double dLng = transformLng(lng - 105.0, lat - 35.0);
        double radLat = (lat / 180.0) * Math.PI;
        double magic = Math.sin(radLat);
        magic = 1 - OFFSET * magic * magic;
        double sqrtMagic = Math.sqrt(magic);
        dLat = (dLat * 180.0) / (((AXIS * (1 - OFFSET)) / (magic * sqrtMagic)) * Math.PI);
        dLng = (dLng * 180.0) / ((AXIS / sqrtMagic) * Math.cos(radLat) * Math.PI);
        return new Point(dLng, dLat);
    }



}
