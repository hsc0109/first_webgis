package cn.yls.metaluccs.server.mapper;

import cn.yls.metaluccs.server.pojo.entity.Country;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CountryMapper {
    int deleteByPrimaryKey(String id);

    int insert(Country record);

    int insertSelective(Country record);

    Country selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Country record);

    int updateByPrimaryKey(Country record);

    List<Country> selectCountries();

}