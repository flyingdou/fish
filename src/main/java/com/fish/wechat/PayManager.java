package com.fish.wechat;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.fish.pojo.Order;
import com.fish.util.HttpRequestUtils;
import com.fish.util.commentsUtil;

import net.sf.json.JSONObject;

public class PayManager {

	private String ip;

	public PayManager(HttpServletRequest request) {
		this.ip = commentsUtil.getIpAddr(request);
	}

	/**
	 * 微信公众号支付签名
	 * 
	 * @param openid
	 *            String 微信公众号用户id
	 * @param productPrice
	 *            String 商品价格
	 * @param productDetail
	 *            String 商品描述
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String paySign(PayRequest payRequest, Order order) throws Exception {
		// 获取发送给微信的参数
		// 随机字符串
		String nonce_str = UUID.randomUUID().toString();
		// 商品描述
		String body = "ecartoon";
		// 商品订单号
		String out_trade_no = order.getNo();
		// 商品价格(单位:分)
		String total_fee = order.getMoney().multiply(new BigDecimal(100)).toString();
		// 交易类型
		String trade_type = "JSAPI";
		// 微信回调通知地址
		String notify_url = "http://www.ecartoon.com.cn/mwpaynotify.asp";
		// 终端设备类型
		String deviceInfo = "WEB";
		// 微信支付分配的商户号
		String MCH_ID = payRequest.getMCH_ID();
		// key为商户平台设置的密钥key
		String KEY = payRequest.getKey();
		// openid
		String openid = payRequest.getOpenId();
		// appid
		String appid = payRequest.getAppId();
		// 微信签名
		String sign = "";

		// 参数
		TreeMap paramMap = new TreeMap();
		paramMap.put("appid", appid);// 公众账号ID
		paramMap.put("device_info", deviceInfo);// 支付终端型号
		paramMap.put("mch_id", MCH_ID);// 商户号
		paramMap.put("nonce_str", nonce_str);// 随机字符串
		paramMap.put("body", body);// 商品描述
		paramMap.put("out_trade_no", out_trade_no);// 商户订单号
		// 交易金额默认为人民币交易，接口中参数支付金额单位为【分】，参数值不能带小数。
		paramMap.put("total_fee", total_fee);// 标价金额
		paramMap.put("spbill_create_ip", ip);// 终端IP
		paramMap.put("notify_url", notify_url);// 通知地址
		paramMap.put("trade_type", trade_type);// 交易类型
		paramMap.put("openid", openid);// 用户标识

		// 调用加密方法生成签名
		// 注：MD5签名方式
		sign = createSign(paramMap, KEY);
		// 生成xml发送消息
		StringBuilder xml = new StringBuilder("<xml>");
		xml.append("<appid>").append(appid).append("</appid>");
		xml.append("<device_info>").append(deviceInfo).append("</device_info>");
		xml.append("<mch_id>").append(MCH_ID).append("</mch_id>");
		xml.append("<nonce_str>").append(nonce_str).append("</nonce_str>");
		xml.append("<sign>").append(sign).append("</sign>");
		xml.append("<body><![CDATA[").append(body).append("]]></body>");
		xml.append("<out_trade_no>").append(out_trade_no).append("</out_trade_no>");
		xml.append("<total_fee>").append(total_fee).append("</total_fee>");
		xml.append("<spbill_create_ip>").append(ip).append("</spbill_create_ip>");
		xml.append("<notify_url>").append(notify_url).append("</notify_url>");
		xml.append("<trade_type>").append(trade_type).append("</trade_type>");
		xml.append("<openid>").append(openid).append("</openid>");
		xml.append("</xml>");
		// 调用微信统一支付接口
		String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		String prepay_id = getPayNo(createOrderURL, xml.toString());
		String timeStamp = Long.toString(System.currentTimeMillis() / 1000);
		String packageValue = "prepay_id=" + prepay_id;
		TreeMap paySignMap = new TreeMap();
		paySignMap.put("appId", appid);
		paySignMap.put("timeStamp", timeStamp);
		paySignMap.put("nonceStr", nonce_str);
		paySignMap.put("package", packageValue);
		paySignMap.put("signType", "MD5");
		// 參數返回給前台
		JSONObject res = new JSONObject();
		res.accumulate("appId", appid);
		res.accumulate("timeStamp", timeStamp);
		res.accumulate("nonceStr", nonce_str);
		res.accumulate("packageValue", packageValue);
		res.accumulate("signType", "MD5");
		res.accumulate("paySign", createSign(paySignMap, KEY));
		res.accumulate("success", true);
		return res.toString();
	}

	/**
	 * 生成签名
	 * 
	 * @param parameters
	 * @param key
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static String createSign(SortedMap<String, Object> parameters, String key) throws Exception {
		StringBuffer sb = new StringBuffer();
		Set es = parameters.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			Object v = entry.getValue();
			if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		// 注：key为商户平台设置的密钥key
		sb.append("key=" + key);
		System.out.println("签名参数：" + sb.toString());
		String xx = sb.toString();
		String sign = commentsUtil.MD5(xx).toUpperCase();
		System.out.println("签名: " + sign);
		return sign;
	}

	/**
	 * 获取支付id
	 * 
	 * @param url
	 * @param param
	 * @return
	 */
	private static String getPayNo(String url, String param) {
		try {
			String xml = HttpRequestUtils.httpRequestToString(url, param);
			Document doc = DocumentHelper.parseText(xml);
			// 指向根节点
			Element root = doc.getRootElement();
			// 获得perpay_id节点
			Element element = root.element("prepay_id");
			return element.getText();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
