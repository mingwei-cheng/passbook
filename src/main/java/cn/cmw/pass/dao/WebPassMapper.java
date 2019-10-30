package cn.cmw.pass.dao;

import cn.cmw.pass.entity.WebPass;
import cn.cmw.pass.entity.WebPassExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface WebPassMapper {
    long countByExample(WebPassExample example);

    int deleteByExample(WebPassExample example);

    int deleteByPrimaryKey(String id);

    int insert(WebPass record);

    int insertSelective(WebPass record);

    List<WebPass> selectByExample(WebPassExample example);

    WebPass selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") WebPass record, @Param("example") WebPassExample example);

    int updateByExample(@Param("record") WebPass record, @Param("example") WebPassExample example);

    int updateByPrimaryKeySelective(WebPass record);

    int updateByPrimaryKey(WebPass record);
}