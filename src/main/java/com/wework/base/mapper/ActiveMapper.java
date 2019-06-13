package com.wework.base.mapper;

import com.wework.base.domain.po.ActivePO;
import com.wework.base.domain.po.ActiveUserPO;
import com.wework.base.domain.po.StoreEvaluatePO;
import com.wework.base.domain.po.StorePO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 门店mapper
 */
@Mapper
@Repository(value="ActiveMapper")
public interface ActiveMapper {

	public List<ActivePO> findActiveList();

	public List<ActivePO> findActiveByStatus(@Param("activeStatus") int activeStatus);

	public int saveActiveInfo(@Param("po") ActiveUserPO activeUserPo);

	public int findUserIsJoinActive(@Param("userId")long userId, @Param("activeId")long activeId);

	public int saveActive(@Param("po") ActivePO activePo);

	public void updateActive();
}
