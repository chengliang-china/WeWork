package com.wework.base.service;

import com.wework.base.domain.po.CarouselMapPO;
import com.wework.base.domain.po.CityStoreNumPO;
import com.wework.base.domain.po.StoreEvaluatePO;
import com.wework.base.domain.po.StorePO;
import com.wework.base.domain.vo.StoreDetailVO;
import com.wework.base.domain.vo.StoreVO;

import java.util.List;
import java.util.Map;

public interface StoreService {

    public List<StoreVO> findStoreList();

    public long saveStoreEvaluate(StoreEvaluatePO po);

    public StoreDetailVO findStoreDetail(long storeId);

    public List<CityStoreNumPO> findCityStoreNum ();

    List<StoreVO> findCityStore(String city,String storeType);

    void saveStoreEvaluateImage(long evaluateId,List<String> listUrl);

    void saveStoreinfo(StorePO po);

    void saveStoreimage(Long storeId, String url);

    void deleteStroe(Long storeId);

    void updateStoreInfo(StorePO po);

    void updateHomeImage(CarouselMapPO carouselMapPo);

    CarouselMapPO getHomeImage();

    List<Map<String, Object>> getStoreTypes();

    void updateStoreTypes(String one, String two, String three, String four);
}
