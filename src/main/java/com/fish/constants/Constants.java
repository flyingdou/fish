package com.fish.constants;

/**
 * fish常量类
 * 
 * @author dou
 *
 */
public class Constants {

	/**
	 * fish APPID
	 */
	public static String APPID = "wx2e8d35be5f888eb3";

	/**
	 * fish APP_SECRET
	 */
	public static String APP_SECRET = "c46624d7b33293cd30a7e008986e4cd2";
	
	/**
	 * event APPID
	 */
	public static String APPID_EVENT = "wxbc83d7dbb3a30e14";
	
	/**
	 * event APP_SECRET
	 */
	public static String APPID_SECRET_EVENT = "049c7653e2b44a72bbbee5c8e065b8be";

	public static String GET_USERINFO_URL = " https://api.weixin.qq.com/sns/jscode2session?";

	/**
	 * 开启
	 */
	public static final Integer OPEN_STATUS = 1;

	/**
	 * 关闭
	 */
	public static final Integer CLOSE_SATUS = 0;

	/**
	 * 审核状态(审核中)
	 */
	public static final Integer APPLY_STATUS_AUDITING = 0;

	/**
	 * 审核状态(通过)
	 */
	public static final Integer APPLY_STATUS_PASS = 1;

	/**
	 * 审核状态(未通过)
	 */
	public static final Integer APPLY_STATUS_UNPASS = 2;

	/**
	 * 推荐
	 */
	public static final Integer RECOMMEND_STATUS = 1;

	/**
	 * 未推荐
	 */
	public static final Integer UN_RECOMMEND_STATUS = 0;

	/**
	 * 商品类型(拜师)
	 */
	public static final String PRODUCT_TYPE_LEARNING = "A";

	/**
	 * 商品类型(参加活动)
	 */
	public static final String PRODUCT_TYPE_ACTIVE = "B";

	/**
	 * 商品类型(打赏)
	 */
	public static final String PRODUCT_TYPE_AWARD = "C";

	/**
	 * 商品类型(结算)
	 */
	public static final String PRODUCT_TYPE_BALANCE = "D";

	/**
	 * 商品类型(提现)
	 */
	public static final String PRODUCT_TYPE_CASH = "E";

	/**
	 * 商品类型(卡券)
	 */
	public static final String PRODUCT_TYPE_TICKET = "F";

	/**
	 * 订单状态(未付款)
	 */
	public static final Integer ORDER_STATUS_BEPAY = 0;

	/**
	 * 订单状态(已付款)
	 */
	public static final Integer ORDER_STATUS_PAYED = 1;

	/**
	 * 订单状态(已结算)
	 */
	public static final Integer ORDER_STATUS_BALANCED = 2;

	/**
	 * 订单状态(结算金额分配)
	 */
	public static final Integer ORDER_STATUS_BALANCE_AMOUNT_ALLOCATION = 3;

	/**
	 * 订单状态(提现支出)
	 */
	public static final Integer ORDER_STATUS_CASH = 4;

	/**
	 * 用户图片上传地址
	 */
	public static final String PICTURE_UPLOAD_PATH = "D://java/fish/picture";

	/**
	 * 用户性别 男
	 */
	public static final String USER_SEX_MALE = "M";

	/**
	 * 用户性别 女
	 */
	public static final String USER_SEX_FEMALE = "F";
}
