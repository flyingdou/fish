package com.fish.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
		JSONObject getAccessTokenResult = WeChatAPI.getAccessToken(Constants.APPID_EVENT, Constants.APP_SECRET_EVENT);
		String accessToken = getAccessTokenResult.getString("access_token");
		// 上传卡券logo
		String filePath = Constants.PICTURE_UPLOAD_PATH + "/" + param.getString("poster");
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
		sku.fluentPut("quantity", 50);

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
		cash.fluentPut("base_info", base_info).fluentPut("least_cost", 0).fluentPut("reduce_cost", param.get("price"));

		// weChatCard
		weChatCard.fluentPut("card_type", "CASH").fluentPut("cash", cash);

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
		fishingTicket.setStatus(Constants.APPLY_STATUS_PASS);
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
	public JSONObject getTicketList(JSONObject param) {
		param.fluentPut("productType", Constants.PRODUCT_TYPE_TICKET).fluentPut("isOpen", Constants.OPEN_STATUS)
				.fluentPut("status", Constants.APPLY_STATUS_PASS)
				.fluentPut("productType", Constants.PRODUCT_TYPE_TICKET);
		// 查询所有有效的优惠券列表
		List<Map<String, Object>> ticketList = fishingTicketMapper.getEffectiveTikcetList(param);
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

	/**
	 * 领取卡券
	 */
	public JSONObject addCard(JSONObject param) {
		if (param.containsKey("code")) {
			fishingTicketMapper.addUserTicket(param);
			return new JSONObject().fluentPut("success", true);
		} else {
			// 请求access_token
			JSONObject getAccessTokenResult = WeChatAPI.getAccessToken(Constants.APPID_EVENT,
					Constants.APP_SECRET_EVENT);
			String accessToken = getAccessTokenResult.getString("access_token");
			// 获取卡券的card_id(微信卡券标识)
			String card_id = fishingTicketMapper.getTicketCardIdById(param.getString("ticketId"));
			JSONObject getApiTicketResult = WeChatAPI.getApiTicket(accessToken);
			Map<String, Object> sign = sign(getApiTicketResult.getString("ticket"), card_id);
			return new JSONObject(sign);
		}
	}

	/**
	 * 核销卡券
	 * 
	 * @param param
	 * @return
	 */
	public JSONObject consume(JSONObject param) {
		JSONObject result = new JSONObject();
		// 请求access_token
		JSONObject getAccessTokenResult = WeChatAPI.getAccessToken(Constants.APPID_EVENT, Constants.APP_SECRET_EVENT);
		String accessToken = getAccessTokenResult.getString("access_token");
		// 查询code
		JSONObject requestParam = new JSONObject();
		requestParam.fluentPut("code", param.get("code"));
		requestParam.fluentPut("check_consume", true);
		JSONObject checkCodeResult = WeChatAPI.checkCode(accessToken, requestParam);
		// 检查卡券状态卡券状态
		if (checkCodeResult.getIntValue("errcode") > 0 && !checkCodeResult.getBooleanValue("can_consume")) {
			result.fluentPut("success", false).fluentPut("message", "卡券状态异常或卡券已过期");
		}
		// 检查当前用户是否具有核销卡券的权限
		if (fishingTicketMapper.checkConsumeAuthority(param) < 0) {
			result.fluentPut("success", false).fluentPut("message", "您当前无权核销此卡券");
		}
		// 通过检查,核销卡券
		requestParam.remove("check_consume");
		JSONObject consumeWeChatCardResult = WeChatAPI.consumeWeChatCard(accessToken, requestParam);
		if (consumeWeChatCardResult.getIntValue("errcode") == 0) {
			result.fluentPut("succes", true).fluentPut("message", "核销成功");
		} else {
			result.fluentPut("success", false).fluentPut("message", consumeWeChatCardResult.get("errmsg"));
		}
		return result;
	}

	/**
	 * @Description: 生成卡券需要的签名并返回参数
	 * @param api_ticket：
	 * @param cardId：需要领取的卡券的cardId
	 * @return
	 */
	private static Map<String, Object> sign(String api_ticket, String cardId) {
		Map<String, Object> ret = new HashMap<String, Object>();
		String nonce_str = UUID.randomUUID().toString();
		String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
		String signature = "";

		String param[] = new String[4];

		param[0] = nonce_str;
		param[1] = timestamp;
		param[2] = api_ticket;
		param[3] = cardId;

		Arrays.sort(param);// 对参数的value值进行字符串的字典序排序

		StringBuilder sb = new StringBuilder();
		for (String b : param) {
			sb.append(b);
		}
		// 对上面拼接的字符串进行sha1加密，得到signature
		try {
			char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(sb.toString().getBytes("UTF-8"));

			byte[] md = crypt.digest();
			int j = md.length;
			char buf[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
				buf[k++] = hexDigits[byte0 & 0xf];
			}
			signature = new String(buf);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		// 返回领取卡券需要的参数，其中nonceStr和timestamp必须和签名中的保持一致
		ret.put("card_id", cardId);
		ret.put("api_ticket", api_ticket);
		ret.put("nonceStr", nonce_str);
		ret.put("timestamp", timestamp);
		ret.put("signature", signature);

		return ret;
	}

}
