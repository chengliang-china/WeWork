package com.wework.base.service.serviceImpl;

import com.wework.base.config.BaseCode;
import com.wework.base.domain.base.BaseJSON;
import com.wework.base.domain.dto.CouponDetailDTO;
import com.wework.base.domain.dto.UserCouponDetailDTO;
import com.wework.base.domain.po.CouponPO;
import com.wework.base.domain.po.UserCouponPO;
import com.wework.base.domain.po.UserPO;
import com.wework.base.domain.vo.CouponDetailVO;
import com.wework.base.domain.vo.CouponVO;
import com.wework.base.domain.vo.UserCouponListVO;
import com.wework.base.mapper.CouponMapper;
import com.wework.base.mapper.UserCouponMapper;
import com.wework.base.service.CouponService;
import com.wework.base.service.RedisService;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    private CouponMapper couponMapper;

    @Autowired
    private UserCouponMapper userCouponMapper;

    @Autowired
    private RedisService redisService;

    @Override
    public BaseJSON addCoupon(String token, CouponVO couponVO) {

        BaseJSON baseJSON = new BaseJSON();

        try{
            CouponPO couponPO = new CouponPO();
            PropertyUtils.copyProperties(couponPO, couponVO);

            if(couponPO.getEndDate().getTime() > new Date().getTime()){
                baseJSON.setFail("优惠卷过期时间不得小于当前时间");
                return baseJSON;
            }
            couponPO.setCouponStatus(BaseCode.VALID);
            couponPO.setCreateTime(new Date());
            couponPO.setIsDel(BaseCode.UNDEL);

            couponMapper.addCoupon(couponPO);

        }catch (Exception e){
            e.printStackTrace();
            baseJSON.setFail("系统异常，请稍后再试！");
        }

        return baseJSON;
    }

    @Override
    public BaseJSON getAllCoupon() {
        return null;
    }

    @Override
    public BaseJSON findCouponAvailables(String token) {
        BaseJSON baseJSON = new BaseJSON();

        try{
            UserPO userPO = (UserPO) redisService.get(token);

            if(userPO == null){
                baseJSON.setFail("token 过期 请重新登陆！");
                baseJSON.setCode(110);
                return baseJSON;
            }
            baseJSON.setResult(this.getAllCoupon(userPO,token));

        }catch (Exception e){
            e.printStackTrace();
            baseJSON.setFail("系统异常，请稍后再试！");
        }
        return baseJSON;
    }

    @Override
    public BaseJSON receiveCoupon(String token, long couponId) {
        BaseJSON baseJSON = new BaseJSON();

        try{
            UserPO userPO = (UserPO) redisService.get(token);

            if(userPO == null){
                baseJSON.setFail("token 过期 请重新登陆！");
                baseJSON.setCode(110);
                return baseJSON;
            }
            // 检测优惠卷是否领取过
            List<UserCouponPO> userCouponPOS = userCouponMapper.receivedCoupon(userPO.getUserId(), couponId);

            if(userCouponPOS.size() > 0){
                baseJSON.setFail("优惠卷已经领取，不可重复领取！");
                return baseJSON;
            }

            UserCouponPO userCouponPO = new UserCouponPO();
            userCouponPO.setCouponId(couponId);
            userCouponPO.setUserId(userPO.getUserId());

            int i = userCouponMapper.receiveCoupon(userCouponPO);

            if(i == 1){
                baseJSON.setMessage("获取优惠卷成功！");
            }

        }catch (Exception e){
            e.printStackTrace();
            baseJSON.setFail("系统异常，请稍后再试！");
        }
        return baseJSON;
    }

    @Override
    public BaseJSON receiveAllCoupon(String token) {
        BaseJSON baseJSON = new BaseJSON();

        try{
            UserPO userPO = (UserPO) redisService.get(token);
            UserCouponPO userCouponPO = new UserCouponPO();

            if(userPO == null){
                baseJSON.setFail("token 过期 请重新登陆！");
                baseJSON.setCode(110);
                return baseJSON;
            }

            CouponDetailVO allCoupon = this.getAllCoupon(userPO, token);
            List<CouponDetailDTO> availableList = allCoupon.getAvailableList();

            for(int i=0;i<availableList.size();i++){
                this.receiveCouponPrivate(userPO,availableList.get(i).getCouponId()); // 领取
            }

        }catch (Exception e){
            e.printStackTrace();
            baseJSON.setFail("系统异常，请稍后再试！");
        }
        return baseJSON;
    }

    @Override
    public BaseJSON getUserCouponList(String token) {
        BaseJSON baseJSON = new BaseJSON();

        try{
            UserPO userPO = (UserPO) redisService.get(token);

            if(userPO == null){
                baseJSON.setFail("token 过期 请重新登陆！");
                baseJSON.setCode(110);
                return baseJSON;
            }

            List<UserCouponDetailDTO> unUsed = userCouponMapper.getUserCouponListForUnUsed(userPO.getUserId());
            List<UserCouponDetailDTO> Used = userCouponMapper.getUserCouponListForUsed(userPO.getUserId());

            UserCouponListVO UserCouponListVO = new UserCouponListVO();
            UserCouponListVO.setUnUsed(unUsed);
            UserCouponListVO.setUsedAndExpired(Used);

            baseJSON.setResult(UserCouponListVO);

        }catch (Exception e){
            e.printStackTrace();
            baseJSON.setFail("系统异常，请稍后再试！");
        }
        return baseJSON;
    }

    // 私有方法

    private CouponDetailVO getAllCoupon(UserPO userPO,String token){

        couponMapper.updateAll();

        List<CouponDetailDTO> couponDetails4Valid = couponMapper.findCouponDetails4Valid(); // 有效的优惠卷

        List<UserCouponPO> userCouponByUserId = userCouponMapper.findUserCouponByUserId(userPO.getUserId()); // 用户拥有的所有的优惠卷

        // 可以领取的优惠卷集合
        List<CouponDetailDTO> availableList = copyList(couponDetails4Valid);
        // 已经拥有的优惠卷集合
        List<CouponDetailDTO> notAvailableList = new ArrayList<>();

        CouponDetailVO couponDetailVO = new CouponDetailVO();

        for(int i=0;i<userCouponByUserId.size();i++){
            for(int j=0;j<couponDetails4Valid.size();j++){

                if(userCouponByUserId.get(i).getCouponId() - couponDetails4Valid.get(j).getCouponId() == 0 ) {
                    notAvailableList.add(couponDetails4Valid.get(j)); // 不可领取
                    availableList.remove(couponDetails4Valid.get(j));
                }
            }
        }


        if(userCouponByUserId.size() == 0){
            availableList = couponDetails4Valid;
        }

        couponDetailVO.setAvailableList(availableList);
        couponDetailVO.setNotAvailableList(notAvailableList);

        return couponDetailVO;
    }

    private int receiveCouponPrivate(UserPO userPO,long couponId){
        int i1 = couponMapper.updateAll();
        System.out.println("aaaaa:"+ i1);
        // 检测优惠卷是否领取过
        List<UserCouponPO> userCouponPOS = userCouponMapper.receivedCoupon(userPO.getUserId(), couponId);

        if(userCouponPOS.size() > 0){

            return -1;
        }

        UserCouponPO userCouponPO = new UserCouponPO();
        userCouponPO.setCouponId(couponId);
        userCouponPO.setUserId(userPO.getUserId());

        int i = userCouponMapper.receiveCoupon(userCouponPO);

        return i;
    }

    private List<CouponDetailDTO> copyList(List<CouponDetailDTO> source){
        List<CouponDetailDTO> dest = new ArrayList<>();
        for(CouponDetailDTO dto : source){
            dest.add(dto);
        }
        return dest;
    }
}
