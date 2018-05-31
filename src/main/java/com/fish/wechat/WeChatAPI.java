package com.fish.wechat;

import java.io.File;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fish.util.HttpRequestUtils;

public class WeChatAPI {

	/**
	 * 获取AccessToken
	 * 
	 * @param appid
	 * @param secret
	 * @return
	 */
	public static JSONObject getAccessToken(String appid, String secret) {
		String getAccessTokenURL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appid
				+ "&secret=" + secret;
		JSONObject accessTokenResult = HttpRequestUtils.httpGet(getAccessTokenURL);
		return accessTokenResult;
	}

	/**
	 * 上传图片到微信服务器
	 * 
	 * @return
	 */
	public static JSONObject uploadImageToWechatServer(String accessToken, String filePath) {
		String uploadImageURL = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=" + accessToken;
		File image = new File(filePath);
		String uploadImageResult = HttpRequestUtils.httpRequestUploadFile(uploadImageURL, image);
		return JSON.parseObject(uploadImageResult);
	}

	/**
	 * 创建微信卡券
	 * 
	 * @return
	 */
	public static JSONObject createdWeChatTicket(String accessToken, JSONObject param) {
		String createdWeChatTicketURL = "https://api.weixin.qq.com/card/create?access_token=" + accessToken;
		JSONObject createdWeChatTicketResult = HttpRequestUtils.httpPost(createdWeChatTicketURL, param);
		return createdWeChatTicketResult;
	}

	/**
	 * 创建微信门店
	 * 
	 * @return
	 */
	public static JSONObject createdWeChatStore(String accessToken, JSONObject param) {
		String createdWeChatStoreURL = "http://api.weixin.qq.com/cgi-bin/poi/addpoi?access_token=" + accessToken;
		JSONObject createdWeChatStoreResult = HttpRequestUtils.httpPost(createdWeChatStoreURL, param);
		return createdWeChatStoreResult;
	}

	/**
	 * 获取领取微信卡券的token
	 */
	public static JSONObject getApiTicket(String accessToken) {
		String getApiTicketURL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + accessToken
				+ "&type=wx_card";
		JSONObject getApiTicketResult = HttpRequestUtils.httpGet(getApiTicketURL);
		return getApiTicketResult;
	}
}
