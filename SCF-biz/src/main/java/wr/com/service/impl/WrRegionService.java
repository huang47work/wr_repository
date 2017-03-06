package wr.com.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.base.service.BaseService;

import wr.com.mapper.WrRegionMapper;
import wr.com.pojo.WrRegion;

/**
 * 地区
 * 
 * @author 郭杰
 * @since Dec 19,2016
 * @version 1.0.1
 * @param <T>
 *
 */
@Service("WrRegionService")
public class WrRegionService<T> extends BaseService<T> {

	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	WrRegionMapper<T> wrRegionMapper;

	@Override
	public WrRegionMapper<T> getDao() {
		return this.wrRegionMapper;
	}

	/**
	 * 查找下级元素
	 * 
	 * @param id
	 * @return
	 */
	@Cacheable(value = "userCache")
	public List<T> getSonRegionList(Long id) {
		if (id == null) {
			return null;
		}
		// String sortKey = "id";
		// String sortType = "asc";
		List<T> list = getDao().getSonsOfRegion(id);
		return list;
	}

	/**
	 * 根据城市名查找全路径
	 * 
	 * @param regionName
	 * @return
	 */
	public String getFullIdPathByName(String regionName) {
		return getDao().getFullIdPathByName(regionName);
	}

	/**
	 * 查找名字列表
	 * 
	 * @param regionNameList
	 * @return
	 */
	public List<T> getRegionsByNameList(List<String> regionNameList) {
		return getDao().getRegionsByNameList(regionNameList);
	}

	/**
	 * 翻译stationValue到省市
	 * 
	 * @param stationValue
	 * @return
	 */
	public String queryStationValue(String stationValue) {

		if (StringUtils.isBlank(stationValue)) {
			return "";
		}

		stationValue = stationValue.replaceAll("，", ",").replaceAll(" ", "").replaceAll("／", "/");
		String[] stationValueArr = stationValue.split(",");
		StringBuffer stationValuePos = new StringBuffer();

		for (String value : stationValueArr) {
			if (StringUtils.isBlank(value)) {
				continue;
			}
			if (value.equals("/1")) {
				stationValuePos.append("全国,");
			} else if (value.startsWith("/")) {
				// 判断省
				WrRegion region = (WrRegion) getDao().queryByFullIdPath(value);
				if (region != null) {
					stationValuePos.append(region.getRegionName() + "省,");
				}
			} else {
				try {
					Long regionId = Long.valueOf(value);
					WrRegion region = (WrRegion) getDao().getById(regionId);
					if (region != null && 2 == region.getRegionType()) {
						stationValuePos.append(region.getRegionName() + "市,");
					}
				} catch (Exception e) {
					logger.error("queryStationValue:error station value[" + value + "]");
				}
			}

		}
		String resultValue = null;
		if (stationValuePos.toString().endsWith(",")) {
			resultValue = stationValuePos.substring(0, stationValuePos.length() - 1);
		}
		return resultValue;
	}
}
