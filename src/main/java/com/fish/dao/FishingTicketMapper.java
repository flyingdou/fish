package com.fish.dao;

import java.util.List;
import java.util.Map;

import com.fish.pojo.FishingTicket;

public interface FishingTicketMapper {

	int deleteByPrimaryKey(Integer id);

	int insertSelective(FishingTicket record);

	FishingTicket selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(FishingTicket record);

	/**
	 * 通过创建者查询卡券列表
	 * 
	 * @param queryParam
	 * @return
	 */
	List<Map<String, Object>> getTicketListByCreator(Map<String, Object> queryParam);

	/**
	 * 查询有效的卡券列表(审核通过并且是开启状态)
	 * 
	 * @param queryParam
	 * @return
	 */
	List<Map<String, Object>> getEffectiveTikcetList(Map<String, Object> queryParam);

	/**
	 * 通过id查询卡券详细信息
	 * 
	 * @param ticketId
	 * @return
	 */
	Map<String, Object> getTicketById(Map<String, Object> queryParam);
}