package com.wework.base.mapper;

import com.wework.base.domain.base.po.StorePO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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
}
