package wr.com.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import wr.com.pojo.GoodsQuality;
@Repository
public interface GoodsQualityMapper {
   
	int deleteByPrimaryKey(String qid);

    int insert(GoodsQuality record);

    int insertSelective(GoodsQuality record);

    GoodsQuality selectByPrimaryKey(String qid);
    
    List<GoodsQuality> findAll();

    int updateByPrimaryKeySelective(GoodsQuality record);

    int updateByPrimaryKey(GoodsQuality record);
}