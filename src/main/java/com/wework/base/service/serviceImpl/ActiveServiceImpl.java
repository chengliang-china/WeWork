package com.wework.base.service.serviceImpl;

import com.wework.base.config.BaseCode;
import com.wework.base.domain.po.ActivePO;
import com.wework.base.domain.po.OrderTablePO;
import com.wework.base.domain.po.StorePO;
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
    public int saveActiveInfo(ActiveVO vo) {
        ActivePO activePo = new ActivePO();
        BeanUtils.copyProperties(vo,activePo);
        return activeMapper.saveActiveInfo(activePo);
    }
}
