package com.wework.base.service;

import com.wework.base.domain.po.CityStoreNumPO;
import com.wework.base.domain.po.StoreEvaluatePO;
import com.wework.base.domain.po.StorePO;
import com.wework.base.domain.vo.StoreDetailVO;
import com.wework.base.domain.vo.StoreVO;

import java.util.List;

public interface StoreService {
    /**
     * 查询门店
     * @param longitude
     * @param latitude
     * @param storeType
     */
    public List<StoreVO> findStoreList(String longitude, String latitude, String storeType);

    public long saveStoreEvaluate(StoreEvaluatePO po);

    public StoreDetailVO findStoreDetail(long storeId);

    public List<CityStoreNumPO> findCityStoreNum ();

    List<StoreVO> findCityStore(String city,String storeType);

    void saveStoreEvaluateImage(long evaluateId,List<String> listUrl);

    void saveStoreinfo(StorePO po);

    void saveStoreimage(Long storeId, List<String> list);

    void deleteStroe(Long storeId);
}
