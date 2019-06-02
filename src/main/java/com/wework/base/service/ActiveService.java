package com.wework.base.service;

import com.wework.base.domain.po.StoreEvaluatePO;
import com.wework.base.domain.vo.ActiveVO;
import com.wework.base.domain.vo.StoreDetailVO;
import com.wework.base.domain.vo.StoreVO;

import java.util.List;

public interface ActiveService {

    public List<ActiveVO> findActiveList();

    public int saveActiveInfo(ActiveVO vo);
}
