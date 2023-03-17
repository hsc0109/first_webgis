package cn.yls.metaluccs.server.service;

import cn.yls.metaluccs.server.pojo.vo.CitySimpleVo;
import cn.yls.metaluccs.server.pojo.vo.CountrySimpleVo;
import cn.yls.metaluccs.server.pojo.vo.ProvinceSimpleVo;

import java.util.List;

public interface AreaService {
    List<CountrySimpleVo> getCountries();

    List<ProvinceSimpleVo> getProvinces(String countryId);

    List<CitySimpleVo> getCities(String provinceId);
}
