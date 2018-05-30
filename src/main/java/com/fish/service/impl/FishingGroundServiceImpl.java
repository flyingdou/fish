package com.fish.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.fish.constants.Constants;
import com.fish.dao.FishingGroundMapper;
import com.fish.pojo.FishingGround;
import com.fish.service.FishingGroundService;
import com.fish.wechat.WeChatAPI;

/**
 * 钓场业务接口实现类
 * 
 * @author Administrator
 *
 */
@Service
@Transactional
public class FishingGroundServiceImpl implements FishingGroundService {

	@Autowired
	private FishingGroundMapper fishingGroundMapper;

	/**
	 * 发布钓场
	 */
	public JSONObject release(JSONObject param) {
		// 首先在微信中创建一个门店
		// 请求access_token
		JSONObject getAccessTokenResult = WeChatAPI.getAccessToken(Constants.APPID_EVENT, Constants.APPID_SECRET_EVENT);
		String accessToken = getAccessTokenResult.getString("access_token");
		// 上传门店logo
		String filePath = Constants.PICTURE_UPLOAD_PATH + "/" + "20180312250528.png";
		JSONObject uploadImageResult = WeChatAPI.uploadImageToWechatServer(accessToken, filePath);
		filePath = uploadImageResult.getString("url");
		// 收集参数, 调用创建微信门店方法
		JSONObject business = new JSONObject();
		JSONObject base_info = new JSONObject();

		// base_info
		base_info.fluentPut("business_name", "鱼惑钓场").fluentPut("branch_name", param.get("name"))
				.fluentPut("province", param.get("province")).fluentPut("city", param.get("city"))
				.fluentPut("district", param.get("district")).fluentPut("address", param.get("address"))
				.fluentPut("telephone", param.get("telephone")).fluentPut("categories", "旅游攻略")
				.fluentPut("offset_type", 1).fluentPut("longitude", param.get("longitude"))
				.fluentPut("latitude", param.get("latitude"));

		// business
		business.fluentPut("base_info", base_info);

		// 创建微信门店
		JSONObject createdWeChatStoreResult = WeChatAPI.createdWeChatStore(accessToken, business);

		// 在本地生成一条钓场数据
		FishingGround fishingGround = new FishingGround();
		fishingGround.setPoster(param.getString("poster"));
		fishingGround.setName(param.getString("name"));
		fishingGround.setType(param.getString("type"));
		fishingGround.setFeeType(param.getInteger("feeType"));
		fishingGround.setFee(param.getInteger("fee"));
		fishingGround.setWaterArea(param.getInteger("waterArea"));
		fishingGround.setWaterDeep(param.getInteger("waterDeep"));
		fishingGround.setAddress(param.getString("address"));
		fishingGround.setLongitude(param.getBigDecimal("longitude"));
		fishingGround.setLatitude(param.getBigDecimal("latitude"));
		fishingGround.setTelephone(param.getString("telephone"));
		fishingGround.setRemark(param.getString("remark"));
		fishingGround.setCreator(param.getInteger("memberId"));
		fishingGround.setCity(param.getString("city"));
		fishingGround.setPoi_id(createdWeChatStoreResult.getString("poi_id"));
		fishingGround.setWechat_audit(Constants.APPLY_STATUS_AUDITING);
		fishingGround.setAutoDate(new Date());

		// 保存到数据库
		fishingGroundMapper.insertSelective(fishingGround);

		JSONObject result = new JSONObject();
		result.fluentPut("success", true).fluentPut("fishingGround", fishingGround);
		return result;
	}

	/**
	 * 查询钓场列表
	 */
	@SuppressWarnings("unchecked")
	public JSONObject getFishingGroundList(JSONObject param) {
		param.fluentPut("audit", Constants.APPLY_STATUS_PASS);
		if (param.containsKey("name")) {
			if (param.containsKey("city")) {
				param.remove("city");
			}
			if (param.containsKey("type")) {
				param.remove("type");
			}
		}
		Map<String, Object> queryParam = param.toJavaObject(Map.class);
		List<Map<String, Object>> fishingGroundList = fishingGroundMapper.getFishingGroundList(queryParam);
		JSONObject result = new JSONObject();
		result.fluentPut("success", true).fluentPut("fishingGroundList", fishingGroundList);
		return result;
	}

}
