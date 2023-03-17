package cn.yls.metaluccs.server.service.impl;

import cn.yls.metaluccs.server.common.ReturnCodeEnum;
import cn.yls.metaluccs.server.converter.AreaConverter;
import cn.yls.metaluccs.server.mapper.CityMapper;
import cn.yls.metaluccs.server.mapper.CountryMapper;
import cn.yls.metaluccs.server.mapper.ProvinceMapper;
import cn.yls.metaluccs.server.pojo.entity.City;
import cn.yls.metaluccs.server.pojo.entity.Country;
import cn.yls.metaluccs.server.pojo.entity.Province;
import cn.yls.metaluccs.server.pojo.vo.CitySimpleVo;
import cn.yls.metaluccs.server.pojo.vo.CountrySimpleVo;
import cn.yls.metaluccs.server.pojo.vo.ProvinceSimpleVo;
import cn.yls.metaluccs.server.service.AreaService;
import org.checkerframework.checker.guieffect.qual.UI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaConverter areaConverter;
    @Autowired
    CountryMapper countryMapper;

    @Autowired
    ProvinceMapper provinceMapper;

    @Autowired
    CityMapper cityMapper;

    @Override
    public List<CountrySimpleVo> getCountries() {
        List<Country> list = countryMapper.selectCountries();
        return list.stream().map(areaConverter::countryToCountrySimpleVo).collect(Collectors.toList());
    }

    @Override
    public List<ProvinceSimpleVo> getProvinces(String countryId) {
        List<Province> list = provinceMapper.selectProvincesByCountryId(countryId);
        return list.stream().map(areaConverter::provinceToProvinceSimpleVo).collect(Collectors.toList());
    }

    @Override
    public List<CitySimpleVo> getCities(String provinceId) {
        List<City> list  = cityMapper.selectCitiesByProvinceId(provinceId);
        return list.stream().map(areaConverter::cityToCitySimpleVo).collect(Collectors.toList());
    }

    public static void main(String[] args){
        String str = ReturnCodeEnum.getStatusState(0).getMessage();
        System.out.println(str);
    }
}
