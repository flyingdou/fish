package com.fish.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.fish.constants.Constants;
import com.fish.dao.ActiveMapper;
import com.fish.dao.FishingGroundMapper;
import com.fish.pojo.Active;
import com.fish.pojo.FishingGround;
import com.fish.service.ActiveService;
import com.fish.util.commentsUtil;

/**
 * 活动业务实现类
 * 
 * @author Administrator
 *
 */
@Service
@Transactional
public class ActiveServiceImpl implements ActiveService {

	@Autowired
	private ActiveMapper activeMapper;

	@Autowired
	private FishingGroundMapper fishGroundMapper;

	/**
	 * 发布活动
	 */
	public JSONObject release(JSONObject param) {
		JSONObject result = new JSONObject();
		// 创建活动对象, 并补全信息
		Active active = new Active();
		if (param.containsKey("memberId") && StringUtils.isNotEmpty(param.getString("memberId"))) {
			active.setCreator(param.getIntValue("memberId"));
		}
		if (param.containsKey("name") && StringUtils.isNotEmpty(param.getString("name"))) {
			active.setName(param.getString("name"));
		}
		if (param.containsKey("poster") && StringUtils.isNotEmpty(param.getString("poster"))) {
			active.setPoster(param.getString("poster"));
		}
		if (param.containsKey("startTime") && StringUtils.isNotEmpty(param.getString("startTime"))) {
			active.setStartTime(commentsUtil.formatStringToDate(param.getString("startTime")));
		}
		if (param.containsKey("endTime") && StringUtils.isNotEmpty(param.getString("endTime"))) {
			active.setEndTime(commentsUtil.formatStringToDate(param.getString("endTime")));
		}
		if (param.containsKey("address") && StringUtils.isNotEmpty(param.getString("address"))) {
			active.setAddress(param.getString("address"));
		}
		if (param.containsKey("fishingGround") && StringUtils.isNotEmpty(param.getString("fishingGround"))) {
			// 如果用户选择了钓场就把钓场id保存到活动数据中,并把钓场的地址覆盖活动的地址
			FishingGround fishGround = fishGroundMapper.selectByPrimaryKey(param.getIntValue("fishingGround"));
			active.setFishingGround(fishGround.getId());
			active.setAddress(fishGround.getAddress());
		}
		if (param.containsKey("telephone") && StringUtils.isNotEmpty(param.getString("telephone"))) {
			active.setTelephone(param.getString("telephone"));
		}
		if (param.containsKey("upperLimit") && StringUtils.isNotEmpty(param.getString("upperLimit"))) {
			active.setUpperLimit(param.getIntValue("upperLimit"));
		}
		if (param.containsKey("price") && StringUtils.isNotEmpty(param.getString("price"))) {
			active.setPrice(param.getIntValue("price"));
		}
		if (param.containsKey("city") && StringUtils.isNotEmpty(param.getString("city"))) {
			active.setCity(param.getString("city"));
		}
		if (param.containsKey("remark") && StringUtils.isNotEmpty(param.getString("remark"))) {
			active.setRemark(param.getString("remark"));
		}
		// 发布默认开启
		active.setIsOpen(Constants.OPEN_STATUS);
		// 发布默认审核通过
		active.setStatus(Constants.APPLY_STATUS_PASS);
		// 发布默认未推荐
		active.setRecommend(Constants.UN_RECOMMEND_STATUS);
		// 发布时间为当前时间
		active.setAutoDate(new Date());
		// 调用添加活动数据mapper
		activeMapper.insertSelective(active);
		// 添加成功后将活动详情数据转换成json格式返回给前端(日期格式化)
		result.fluentPut("success", true).fluentPut("active", active);
		return result;
	}

	/**
	 * 获取活动列表
	 */
	public JSONObject getActiveList(JSONObject param) {
		JSONObject result = new JSONObject();
		List<Map<String, Object>> activeList = new ArrayList<Map<String, Object>>();
		// 创建查询参数
		Map<String, Object> queryParam = new HashMap<String, Object>();
		queryParam.put("productType", Constants.PRODUCT_TYPE_ACTIVE);
		queryParam.put("isOpen", Constants.OPEN_STATUS);
		queryParam.put("status", Constants.APPLY_STATUS_PASS);
		queryParam.put("orderStatus", Constants.ORDER_STATUS_BEPAY);
		// 根据城市查询活动列表
		if (param.containsKey("city") && StringUtils.isNotEmpty(param.getString("city"))) {
			queryParam.put("city", param.get("city"));
			queryParam.put("recommend", Constants.UN_RECOMMEND_STATUS);
			activeList = activeMapper.getActiveListByCity(queryParam);
		}
		// 根据推荐查询活动列表
		if (param.containsKey("recommend") && StringUtils.isNotEmpty(param.getString("recommend"))) {
			queryParam.put("recommend", param.get("recommend"));
			activeList = activeMapper.getActiveListByRecommend(queryParam);
		}
		result.fluentPut("success", true).fluentPut("activeList", activeList);
		return result;
	}

	/**
	 * 根据id查询活动信息
	 */
	public JSONObject getActiveById(JSONObject param) {
		Map<String, Object> active = activeMapper.getActiveById(param.getString("activeId"));
		// json序列化并格式化日期
		JSONObject result = new JSONObject();
		result.fluentPut("success", true).fluentPut("active", active);
		return result;
	}
}
