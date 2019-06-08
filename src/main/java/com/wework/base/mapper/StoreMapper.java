package com.wework.base.mapper;

import com.wework.base.domain.po.CityStoreNumPO;
import com.wework.base.domain.po.StoreEvaluatePO;
import com.wework.base.domain.po.StorePO;
import com.wework.base.domain.po.StoreServicePO;
import com.wework.base.domain.vo.StoreDetailVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 门店mapper
 */
@Mapper
@Repository(value="StoreMapper")
public interface StoreMapper {
	/**
	 *
	 * @param longitude
	 * @return
	 */
	public List<StorePO> getStoreList(@Param("longitude") String longitude, @Param("latitude") String latitude, @Param("storeType") String storeType);

	public int saveStoreEvaluate(@Param("po")StoreEvaluatePO po);

	public StoreEvaluatePO findEvaluate(@Param("storeId") long storeId);

	public StorePO findStoreDetail(@Param("storeId") long storeId);

	public List<String> findStoreService(@Param("storeId") long storeId);

	public List<String> findStoreImage(@Param("storeId") long storeId);

	public List<CityStoreNumPO> findCityStoreNum();

	public List<StorePO> getStoreListByCity(@Param("city") String city,@Param("storeType") String storeType);
}
