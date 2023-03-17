package cn.yls.metaluccs.server.controller;

import cn.yls.metaluccs.server.converter.AreaConverter;
import cn.yls.metaluccs.server.pojo.entity.DataSet;
import cn.yls.metaluccs.server.pojo.param.AreaSimpleInfo;
import cn.yls.metaluccs.server.pojo.vo.CitySimpleVo;
import cn.yls.metaluccs.server.pojo.vo.CountrySimpleVo;
import cn.yls.metaluccs.server.pojo.vo.ProvinceSimpleVo;
import cn.yls.metaluccs.server.service.AreaService;
import cn.yls.metaluccs.server.service.DataSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/area")
public class AreaController {


    @Autowired
    private AreaService areaServiceImpl;

    @Autowired
    private DataSetService dataSetServiceImpl;

    @GetMapping("/countries")
    public List<CountrySimpleVo> countries(){
        List<CountrySimpleVo> list = areaServiceImpl.getCountries();
        return list ;
    }

    @GetMapping("/provinces")
    public List<ProvinceSimpleVo> provinces(HttpServletRequest request){
        String countryId = request.getParameter("countryId");
        if(!StringUtils.hasText(countryId)){
            return null;
        }
        List<ProvinceSimpleVo> list = areaServiceImpl.getProvinces(countryId);
        return list ;
    }

    @GetMapping("/cities")
    public List<CitySimpleVo> cities(HttpServletRequest request){
        String provinceId = request.getParameter("provinceId");
        if(!StringUtils.hasText(provinceId)){
            return null;
        }
        List<CitySimpleVo> list = areaServiceImpl.getCities(provinceId);
        return list ;
    }

    @PostMapping("/searchDataset")
    public List<DataSet> searchDataset(@RequestBody AreaSimpleInfo areaSimpleInfo){
        List<DataSet> list = dataSetServiceImpl.searchDatasetByCorrd(areaSimpleInfo);
        return list;
    }

}
