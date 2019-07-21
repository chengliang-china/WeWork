package com.wework.base.service.serviceImpl;

import com.wework.base.config.BaseCode;
import com.wework.base.domain.po.CarouselMapPO;
import com.wework.base.domain.po.CityStoreNumPO;
import com.wework.base.domain.po.StoreEvaluatePO;
import com.wework.base.domain.po.StorePO;
import com.wework.base.domain.vo.StoreDetailVO;
import com.wework.base.domain.vo.StoreVO;
import com.wework.base.mapper.OrderMapper;
import com.wework.base.mapper.StoreMapper;
import com.wework.base.service.StoreService;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreMapper storeMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<StoreVO> findStoreList() {

        List<StorePO> storePOlist = storeMapper.getStoreList();
        List<StoreVO> storeVOlist = new ArrayList<>();
        if(storePOlist.size() >0){
            for(StorePO storePo:storePOlist){
                StoreVO storeVO = new StoreVO();
                storeVO.setStoreId(storePo.getStoreId());
                storeVO.setStoreName(storePo.getStoreName());
                storeVO.setThumbnailUrl(storePo.getThumbnailUrl());
                storeVO.setLatitude(storePo.getLatitude());
                storeVO.setLongitude(storePo.getLongitude());
                storeVO.setOpenStartTime(storePo.getOpenStartTime());
                storeVO.setOpenEndTime(storePo.getOpenEndTime());
                storeVO.setOffStartTime(storePo.getOffStartTime());
                storeVO.setOffEndTime(storePo.getOffEndTime());
                storeVO.setApplyFee(storePo.getApplyFee());
                storeVO.setThumbnailUrl(storePo.getThumbnailUrl());
                storeVOlist.add(storeVO);
            }
        }
        return storeVOlist;
    }

    @Override
    public long saveStoreEvaluate(StoreEvaluatePO po) {
        return storeMapper.saveStoreEvaluate(po);
    }

    @Override
    public StoreDetailVO findStoreDetail(long storeId) {

        StoreDetailVO storeDetailVO = new StoreDetailVO();
        List<String> serviceList = storeMapper.findStoreService(storeId);
        List<String> imageList = storeMapper.findStoreImage(storeId);
        StorePO storePO = storeMapper.findStoreDetail(storeId);
        storeDetailVO.setServiceList(serviceList);
        storeDetailVO.setImageList(imageList);
        storeDetailVO.setStoreId(storePO.getStoreId());
        storeDetailVO.setStoreName(storePO.getStoreName());
        storeDetailVO.setStoreIntroduction(storePO.getStoreIntroduction());
        storeDetailVO.setApplyFee(storePO.getApplyFee());
        storeDetailVO.setArrivalWay(storePO.getArrivalWay());
        storeDetailVO.setLatitude(storePO.getLatitude());
        storeDetailVO.setLongitude(storePO.getLongitude());
        storeDetailVO.setOpenStartTime(storePO.getOpenStartTime());
        storeDetailVO.setOpenEndTime(storePO.getOpenEndTime());
        storeDetailVO.setOffStartTime(storePO.getOffStartTime());
        storeDetailVO.setOffEndTime(storePO.getOffEndTime());

        //获取最新评价
        StoreEvaluatePO storeEvaluatePO = storeMapper.findEvaluate(storeId);
        if(storeEvaluatePO != null){
            storeDetailVO.setScore(storeEvaluatePO.getScore());
            storeDetailVO.setDescription(storeEvaluatePO.getDescription());
            storeDetailVO.setEvaluateName(storeEvaluatePO.getEvaluateName());
            storeDetailVO.setEvaluateTime(storeEvaluatePO.getEvaluateTime());
        }
        return storeDetailVO;
    }

    @Override
    public List<CityStoreNumPO> findCityStoreNum() {
        return storeMapper.findCityStoreNum();
    }

    @Override
    public List<StoreVO> findCityStore(String city,String storeType) {
        List<StorePO> storePOlist = storeMapper.getStoreListByCity(city,storeType);
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
                storeVO.setOffStartTime(storePo.getOffStartTime());
                storeVO.setOffEndTime(storePo.getOffEndTime());
                storeVO.setApplyFee(storePo.getApplyFee());
                storeVO.setThumbnailUrl(storePo.getThumbnailUrl());

                //查看已开订单数量，订单和工位一一对应
                int num = orderMapper.findOrderNumByStatus(0, BaseCode.ORDER_OPENED);
                int seatNum = storePo.getSeatNum();
                if(seatNum - num < 10){//座位减去使用数量，等于未使用数量，查看座位是否充足
                    storeVO.setSeatIsEnough(0);
                }else{
                    storeVO.setSeatIsEnough(1);
                }
                storeVOlist.add(storeVO);
            }
        }
        return storeVOlist;
    }

    @Override
    public void saveStoreEvaluateImage(long evaluateId,List<String> listUrl) {
        for(String url:listUrl){
            storeMapper.saveStoreEvaluateUrl(evaluateId,url);
        }
    }

    @Override
    public void saveStoreinfo(StorePO po) {
        storeMapper.saveStoreinfo(po);
    }

    @Override
    public void saveStoreimage(Long storeId, String url) {
        storeMapper.saveStoreImage(storeId,url);
    }

    @Override
    public void deleteStroe(Long storeId) {
        storeMapper.deleteStroe(storeId);
        storeMapper.deleteStroeImage(storeId);
    }

    @Override
    public void updateStoreInfo(StorePO po) {
        storeMapper.updateStoreInfo(po);
    }

    @Override
    public void updateHomeImage(CarouselMapPO carouselMapPo) {
        storeMapper.updateHomeImage(carouselMapPo);
    }

    @Override
    public CarouselMapPO getHomeImage() {
        return storeMapper.getHomeImage();
    }

    @Override
    public List<Map<String, Object>> getStoreTypes() {
        return storeMapper.getStoreTypes();
    }

    @Override
    public void updateStoreTypes(String one, String two, String three, String four) {
        storeMapper.updateStoreTypes(1,one);
        storeMapper.updateStoreTypes(2,two);
        storeMapper.updateStoreTypes(3,three);
        storeMapper.updateStoreTypes(4,four);

    }
}
