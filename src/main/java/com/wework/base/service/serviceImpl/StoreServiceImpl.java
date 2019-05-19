package com.wework.base.service.serviceImpl;

import com.wework.base.domain.base.po.StorePO;
import com.wework.base.domain.base.vo.StoreVO;
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
        if(storePOlist.size() >0){
            List<StoreVO> storeVOlist = new ArrayList<>();
            try {
                PropertyUtils.copyProperties(storeVOlist,storePOlist);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            return storeVOlist;
        }
        return null;
    }
}
