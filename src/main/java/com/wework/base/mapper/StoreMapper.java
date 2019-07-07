package com.wework.base.mapper;

import com.wework.base.domain.po.CityStoreNumPO;
import com.wework.base.domain.po.StoreEvaluatePO;
import com.wework.base.domain.po.StorePO;
import com.wework.base.domain.po.StoreServicePO;
import com.wework.base.domain.vo.StoreDetailVO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 门店mapper
 */
@Mapper
@Repository(value="StoreMapper")
public interface StoreMapper {

	public List<StorePO> getStoreList();

	public long saveStoreEvaluate(@Param("po")StoreEvaluatePO po);

	public StoreEvaluatePO findEvaluate(@Param("storeId") long storeId);

	public StorePO findStoreDetail(@Param("storeId") long storeId);

	public List<String> findStoreService(@Param("storeId") long storeId);

	public List<String> findStoreImage(@Param("storeId") long storeId);

	public List<CityStoreNumPO> findCityStoreNum();

	public List<StorePO> getStoreListByCity(@Param("city") String city,@Param("storeType") String storeType);

	@Insert("insert into store_evaluate_image (evaluate_id,image_url,create_time,is_del) values(#{evaluateId},#{url},now(),0)")
	void saveStoreEvaluateUrl(@Param("evaluateId")long evaluateId, @Param("url")String url);

	@Insert("insert into store(store_name,store_type,apply_fee,city,store_introduction,arrival_way,open_start_time,open_end_time,longitude,latitude,thumbnail_url,seat_num,create_time,is_del) " +
			"values(#{storeName},#{storeType},#{applyFee},#{city},#{storeIntroduction},#{arrivalWay},#{openStartTime},#{openEndTime},#{longitude},#{latitude},#{thumbnailUrl},#{seatNum},now(),0)")
    void saveStoreinfo(StorePO po);

	@Insert("insert into store_image (store_id,image_url,create_time,is_del) values(#{storeId},#{url},now(),0)")
	void saveStoreImage(@Param("storeId")Long storeId, @Param("url")String url);

	@Update("update store set is_del = 1 where store_id = #{storeId}")
	void deleteStroe(Long storeId);

	@Delete("delete from  store_image where store_id = #{storeId}")
	void deleteStroeImage(Long storeId);

	void updateStoreInfo(@Param("po")StorePO po);
}
