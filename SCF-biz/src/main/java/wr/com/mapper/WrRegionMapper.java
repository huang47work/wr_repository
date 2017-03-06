package wr.com.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import wr.com.mapper.base.BaseDao;

public interface WrRegionMapper<T> extends BaseDao<T> {
	public abstract String selectNameById(@Param("id") Long id);
	
	public abstract T getById(Long id);

	public abstract List<T> getSonsOfRegion(Long id);

	public abstract T queryByFullIdPath(@Param("fullIdPath") String fullIdPath);

	public abstract List<T> getRegionsByNameList(@Param("regionNameList") List<String> regionNameList);

	public abstract String getFullIdPathByName(@Param("regionName") String regionName);
}