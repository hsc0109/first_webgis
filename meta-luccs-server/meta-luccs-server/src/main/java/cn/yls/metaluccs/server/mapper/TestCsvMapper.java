package cn.yls.metaluccs.server.mapper;

import cn.yls.metaluccs.server.pojo.entity.TestCsv;

import java.util.List;

public interface TestCsvMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TestCsv record);

    int insertSelective(TestCsv record);

    TestCsv selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TestCsv record);

    int updateByPrimaryKey(TestCsv record);

    List<TestCsv> selectAllCsv();

    int  updateCsv(TestCsv csv);
}