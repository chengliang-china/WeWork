package com.wework.base.service.serviceImpl;

import com.wework.base.domain.po.StorePO;
import com.wework.base.domain.vo.StoreVO;
import com.wework.base.mapper.StoreMapper;
import com.wework.base.service.StoreService;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreMapper storeMapper;

    @Override
    public List<StoreVO> findStoreList(String longitude, String latitude, String storeType) {

        List<StorePO> storePOlist = storeMapper.getStoreList(longitude,latitude,storeType);
        List<StoreVO> storeVOlist = new ArrayList<>();
        if(storePOlist.size() >0){
            for(StorePO storePo:storePOlist){
                StoreVO storeVO = new StoreVO();
                storeVO.setStoreId(storePo.getStoreId());
                storeVO.setStoreName(storePo.getStoreName());
                storeVO.setLatitude(storePo.getLatitude());
                storeVO.setLongitude(storePo.getLongitude());
                storeVO.setOpenStartTime(storePo.getOpenStartTime());
                storeVO.setOpenEndTime(storePo.getOpenEndTime());
                storeVO.setApplyFee(storePo.getApplyFee());
                storeVO.setThumbnailUrl(storePo.getThumbnailUrl());
                storeVOlist.add(storeVO);
            }
        }
        return storeVOlist;
    }
}
