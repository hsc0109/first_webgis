package cn.yls.metaluccs.server.converter;
import cn.yls.metaluccs.server.pojo.entity.City;
import cn.yls.metaluccs.server.pojo.entity.Country;
import cn.yls.metaluccs.server.pojo.entity.Province;
import cn.yls.metaluccs.server.pojo.vo.CitySimpleVo;
import cn.yls.metaluccs.server.pojo.vo.CountrySimpleVo;
import cn.yls.metaluccs.server.pojo.vo.ProvinceSimpleVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AreaConverter {
    AreaConverter INSTANCE = Mappers.getMapper(AreaConverter.class);

    @Mappings({
            @Mapping(target = "countryId", source = "id"),
            @Mapping(target = "countryName", source = "countryName"),
    })
    CountrySimpleVo countryToCountrySimpleVo(Country Country);

    @Mappings({
            @Mapping(target = "countryId", source = "countryId"),
            @Mapping(target = "provinceId", source = "id"),
            @Mapping(target = "provinceName", source = "provinceName"),
    })
    ProvinceSimpleVo provinceToProvinceSimpleVo(Province province);

    @Mappings({
            @Mapping(target = "countryId", source = "countryId"),
            @Mapping(target = "provinceId", source = "provinceId"),
            @Mapping(target = "cityId", source = "id"),
            @Mapping(target = "cityName", source = "cityName"),
    })
    CitySimpleVo cityToCitySimpleVo(City city);
}
