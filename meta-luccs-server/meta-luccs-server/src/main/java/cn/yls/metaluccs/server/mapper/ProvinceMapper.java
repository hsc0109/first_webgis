package cn.yls.metaluccs.server.mapper;

import cn.yls.metaluccs.server.pojo.entity.Province;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProvinceMapper {
    int deleteByPrimaryKey(String id);

    int insert(Province record);

    int insertSelective(Province record);

    Province selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Province record);

    int updateByPrimaryKey(Province record);

    List<Province> selectProvincesByCountryId(String countryId);
}