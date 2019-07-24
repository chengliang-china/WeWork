package com.wework.base.mapper;

import com.wework.base.domain.po.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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

	@Insert("insert into member(user_id,name,phone,wechat_no,mail,company,position,create_time,is_del)" +
				   "values(#{po.userId},#{po.name},#{po.phone},#{po.wechatNo},#{po.mail},#{po.company},#{po.position},now(),0)")
	int saveMemberInfo(@Param("po") MemberPO memberPO);

	@Select("select user_id,name,phone,wechat_no,mail,company,position from member where is_del = 0")
	List<MemberPO> findMemberList();
}
