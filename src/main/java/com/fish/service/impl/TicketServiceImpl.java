package com.fish.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fish.constants.Constants;
import com.fish.dao.FishingGroundMapper;
import com.fish.dao.FishingTicketMapper;
import com.fish.pojo.FishingTicket;
import com.fish.service.TicketService;
import com.fish.wechat.WeChatAPI;

/**
 * 卡券业务接口实现类
 * 
 * @author Administrator
 *
 */
@Service
@Transactional
public class TicketServiceImpl implements TicketService {

	@Autowired
	private FishingTicketMapper fishingTicketMapper;

	@Autowired
	private FishingGroundMapper fishingGroundMapper;

	/**
	 * 发布垂钓券
	 */
	public JSONObject release(JSONObject param) {
		// // 创建微信卡券
		// // 请求access_token
		JSONObject getAccessTokenResult = WeChatAPI.getAccessToken(Constants.APPID_TEST, Constants.APP_SECRET_TEST);
		String accessToken = getAccessTokenResult.getString("access_token");
		// 上传卡券logo
		String filePath = "d:java/picture/2018041080817.png";
		JSONObject uploadImageResult = WeChatAPI.uploadImageToWechatServer(accessToken, filePath);
		filePath = uploadImageResult.getString("url");
		// 调用创建微信卡券方法
		JSONObject createdWeChatTicketParam = new JSONObject();
		JSONObject weChatCard = new JSONObject();
		JSONObject cash = new JSONObject();
		JSONObject base_info = new JSONObject();
		JSONObject sku = new JSONObject();
		JSONObject date_info = new JSONObject();
		JSONArray location_id_list = new JSONArray();

		// sku
		sku.fluentPut("quantity", 10);

		// date_info
		date_info.fluentPut("type", "DATE_TYPE_FIX_TERM").fluentPut("fixed_term", param.getInteger("period"))
				.fluentPut("fixed_begin_term", 0);

		// location_id_list
		List<String> poi_id_list = fishingGroundMapper.getPoiIdListByIdList(param.getString("fitFishGround"));
		location_id_list.addAll(poi_id_list);

		// base_info
		base_info.fluentPut("logo_url", filePath).fluentPut("code_type", "CODE_TYPE_QRCODE")
				.fluentPut("brand_name", "鱼惑").fluentPut("title", param.get("name")).fluentPut("color", "Color010")
				.fluentPut("notice", "请出示二维码").fluentPut("description", "不可与其他优惠同享").fluentPut("sku", sku)
				.fluentPut("date_info", date_info).fluentPut("location_id_list", location_id_list);

		// cash
		cash.fluentPut("base_info", base_info);

		// weChatCard
		weChatCard.fluentPut("card_type", "CASH").fluentPut("cash", cash).fluentPut("least_cost", 0)
				.fluentPut("reduce_cost", param.get("price"));

		// createdWeChatTicket
		createdWeChatTicketParam.fluentPut("card", weChatCard);
		JSONObject createdWeChatTicketResult = WeChatAPI.createdWeChatTicket(accessToken, createdWeChatTicketParam);

		// 创建一条垂钓券数据
		FishingTicket fishingTicket = new FishingTicket();
		fishingTicket.setPoster(param.getString("poster"));
		fishingTicket.setName(param.getString("name"));
		fishingTicket.setPeriod(param.getInteger("period"));
		fishingTicket.setFitFishGround(param.getString("fitFishGround"));
		fishingTicket.setPrice(param.getBigDecimal("price"));
		fishingTicket.setCreator(param.getInteger("creator"));
		fishingTicket.setRemark(param.getString("remark"));
		fishingTicket.setCardId(createdWeChatTicketResult.getString("card_id"));
		fishingTicket.setIsOpen(Constants.OPEN_STATUS);
		fishingTicket.setStatus(Constants.APPLY_STATUS_AUDITING);
		fishingTicket.setAuto_date(new Date());

		// 保存到数据库
		fishingTicketMapper.insertSelective(fishingTicket);

		JSONObject result = new JSONObject();
		result.fluentPut("success", true).fluentPut("fishingTicket", fishingTicket);
		return result;
	}

	/**
	 * 查询垂钓券列表
	 */
	public JSONObject getTicketList() {
		Map<String, Object> queryParam = new HashMap<String, Object>();
		queryParam.put("productType", Constants.PRODUCT_TYPE_TICKET);
		queryParam.put("isOpen", Constants.OPEN_STATUS);
		queryParam.put("status", Constants.APPLY_STATUS_PASS);

		// 查询所有有效的优惠券列表
		List<Map<String, Object>> ticketList = fishingTicketMapper.getEffectiveTikcetList(queryParam);

		JSONObject result = new JSONObject();
		result.fluentPut("success", true).fluentPut("ticketList", ticketList);
		return result;
	}

	/**
	 * 通过id查询垂钓券
	 */
	public JSONObject getTicketById(JSONObject param) {
		Map<String, Object> queryParam = new HashMap<String, Object>();
		queryParam.put("productType", Constants.PRODUCT_TYPE_TICKET);
		queryParam.put("ticketId", param.get("ticketId"));
		Map<String, Object> fishingTicket = fishingTicketMapper.getTicketById(queryParam);
		String ids = String.valueOf(fishingTicket.get("fit_fish_ground"));
		List<Map<String, Object>> fishingGroundList = fishingGroundMapper.getFishingGroundListByIdList(ids);
		fishingTicket.put("fishingGroundList", fishingGroundList);
		// 返回json
		JSONObject result = new JSONObject();
		result.fluentPut("success", true).fluentPut("fishingTicket", fishingTicket);
		return result;
	}

}
