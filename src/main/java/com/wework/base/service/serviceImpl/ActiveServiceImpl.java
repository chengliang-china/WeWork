package com.wework.base.service.serviceImpl;

import com.wework.base.config.BaseCode;
import com.wework.base.domain.po.*;
import com.wework.base.domain.vo.ActiveUserVO;
import com.wework.base.domain.vo.ActiveVO;
import com.wework.base.domain.vo.OrderDetailVO;
import com.wework.base.domain.vo.OrderVO;
import com.wework.base.mapper.ActiveMapper;
import com.wework.base.mapper.OrderMapper;
import com.wework.base.mapper.StoreMapper;
import com.wework.base.service.ActiveService;
import com.wework.base.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ActiveServiceImpl implements ActiveService {

    @Autowired
    private ActiveMapper activeMapper;

    @Override
    public List<ActiveVO> findActiveList() {
        List<ActivePO> poList = activeMapper.findActiveList();
        if(poList.size() < 0){
            return null;
        }
        List<ActiveVO> voList = new ArrayList<ActiveVO>();
        for(ActivePO po:poList){
            ActiveVO vo = new ActiveVO();
            BeanUtils.copyProperties(po, vo);
            voList.add(vo);
        }
        return voList;
    }

    @Override
    public int saveActiveInfo(ActiveUserVO vo) {
        ActiveUserPO activePo = new ActiveUserPO();
        BeanUtils.copyProperties(vo,activePo);
        return activeMapper.saveActiveInfo(activePo);
    }

    @Override
    public List<ActiveVO> findActiveByStatus(long userId, int activeStatus) {
        List<ActivePO> listPo = activeMapper.findActiveByStatus(activeStatus);
        if(listPo.size()==0){
            return null;
        }
        List<ActiveVO> listVo = new ArrayList<>();
        for (ActivePO po :listPo){
            ActiveVO activeVo = new ActiveVO();
            activeVo.setId(po.getId());
            activeVo.setActiveName(po.getActiveName());
            activeVo.setActiveStatus(po.getActiveStatus());
            activeVo.setActiveAddr(po.getActiveAddr());
            activeVo.setActiveIntroduction(po.getActiveIntroduction());
            activeVo.setActiveTime(po.getActiveTime());
            activeVo.setActiveUrl(po.getActiveUrl());
            int a = activeMapper.findUserIsJoinActive(userId,po.getId());
            if(a>0){
                activeVo.setIsSignUp(1);
            }else{
                activeVo.setIsSignUp(0);
            }
            listVo.add(activeVo);
        }
        return listVo;
    }

    @Override
    public int saveActive(ActivePO activePo) {
        //step 将所有进行中的活动置为已结束
        activeMapper.updateActive();
        return activeMapper.saveActive(activePo);
    }

    @Override
    public void saveMemberInfo(MemberPO memberPO) {
        activeMapper.saveMemberInfo(memberPO);
    }

    @Override
    public List<MemberPO> findMemberList() {
        return activeMapper.findMemberList();
    }
}
