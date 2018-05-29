package com.fish.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.fish.constants.Constants;
import com.fish.dao.UserMapper;
import com.fish.pojo.User;
import com.fish.service.UserService;
import com.fish.util.commentsUtil;
import com.fish.wechat.GetUserInfoResponse;

/**
 * 
 * @author dou
 * user   Service逻辑层
 */
@Service
@Transactional
public class UserServiceImple implements UserService {
	
	@Autowired
	private UserMapper userMaper;

	/**
	 * 微信用户登录
	 * @throws Exception 
	 */
	public JSONObject wechatLogin(JSONObject param) throws Exception {
		JSONObject ret = new JSONObject();
		// 初始化user
		User user = new User();
		
		// 从微信服务器获取unionId, session_key, iv
		GetUserInfoResponse  getUserInfoRespone = new GetUserInfoResponse();
		getUserInfoRespone = (GetUserInfoResponse) getUserInfoRespone.getUserInfo(param.getString("code"));
		
		// 解密信息
		String decodeStr = "";
		decodeStr = commentsUtil.wxDecrypt(param.getString("encryptedData"), getUserInfoRespone.getSession_key(), param.getString("iv"));
		
		String unionId = commentsUtil.getTargetFromAES(decodeStr, "unionId");
		
		// 获取前台传递过来的数据
		JSONObject userInfo = JSONObject.parseObject(param.getString("userInfo"));
		
		// 根据uniodId查询用户
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("wechat_id", unionId);
		user = userMaper.getUserByWechatid(queryMap);
		
		if (user == null || user.getId() == null) {
			// 新用户，注册操作
			String fileName = commentsUtil.dateFormat(new Date(), "yyyyMMdd") + commentsUtil.getRandomName(4) + ".jpg";
			commentsUtil.download(userInfo.getString("avatarUrl"), fileName, Constants.PICTURE_UPLOAD_PATH);
			user.setNick(new String(userInfo.getString("nickName").getBytes("ISO-8859-1"),"UTF-8"));
		
			user.setImage(fileName);
			user.setSex("1".equals(userInfo.getString("gender")) ? Constants.USER_SEX_MALE : Constants.USER_SEX_FEMALE);
			user.setRegisterDate(new Date());
			user.setLoginDate(new Date());
			user.setWechatId(unionId);
			
			// 持久化数据
			userMaper.insertSelective(user);
			
		} else {
			// 老用户，更新登录时间，更新用户头像
		    commentsUtil.download(userInfo.getString("avatarUrl"), user.getImage(), Constants.PICTURE_UPLOAD_PATH);
		    user.setLoginDate(new Date());
		    
		    // 持久化数据
		    userMaper.updateByPrimaryKeySelective(user);
			
		}
		
		ret.fluentPut("success", true)
		   .fluentPut("message", "OK")
		   .fluentPut("session_key", getUserInfoRespone.getSession_key())
		   .fluentPut("openId", getUserInfoRespone.getOpenid())
		   .fluentPut("key", user.getId());
		   ;
		
		return ret;
	}

}
