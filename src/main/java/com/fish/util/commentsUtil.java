package com.fish.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.security.AlgorithmParameters;
import java.security.Key;
import java.security.MessageDigest;
import java.security.Security;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class commentsUtil {

	// 算法名
	public static final String KEY_NAME = "AES";
	// 加解密算法/模式/填充方式
	// ECB模式只用密钥即可对数据进行加密解密，CBC模式需要添加一个iv
	public static final String CIPHER_ALGORITHM = "AES/CBC/PKCS7Padding";

	/**
	 * 微信 数据解密<br/>
	 * 对称解密使用的算法为 AES-128-CBC，数据采用PKCS#7填充<br/>
	 * 对称解密的目标密文:encrypted=Base64_Decode(encryptData)<br/>
	 * 对称解密秘钥:key = Base64_Decode(session_key),aeskey是16字节<br/>
	 * 对称解密算法初始向量:iv = Base64_Decode(iv),同样是16字节<br/>
	 *
	 * @param encrypted
	 *            目标密文
	 * @param session_key
	 *            会话ID
	 * @param iv
	 *            加密算法的初始向量
	 */
	@SuppressWarnings("unused")
	public static String wxDecrypt(String encrypted, String session_key, String iv) {
		String json = null;
		byte[] encrypted64 = Base64.decodeBase64(encrypted);
		byte[] key64 = Base64.decodeBase64(session_key);
		byte[] iv64 = Base64.decodeBase64(iv);
		byte[] data;
		try {
			init();
			json = new String(decrypt(encrypted64, key64, generateIV(iv64)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * 初始化密钥
	 */
	public static void init() throws Exception {
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		KeyGenerator.getInstance(KEY_NAME).init(128);
	}

	/**
	 * 生成iv
	 */
	public static AlgorithmParameters generateIV(byte[] iv) throws Exception {
		// iv 为一个 16 字节的数组，这里采用和 iOS 端一样的构造方法，数据全为0
		// Arrays.fill(iv, (byte) 0x00);
		AlgorithmParameters params = AlgorithmParameters.getInstance(KEY_NAME);
		params.init(new IvParameterSpec(iv));
		return params;
	}

	/**
	 * 生成解密
	 */
	public static byte[] decrypt(byte[] encryptedData, byte[] keyBytes, AlgorithmParameters iv) throws Exception {
		Key key = new SecretKeySpec(keyBytes, KEY_NAME);
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		// 设置为解密模式
		cipher.init(Cipher.DECRYPT_MODE, key, iv);
		return cipher.doFinal(encryptedData);
	}

	/**
	 * 日期格式化
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String dateFormat(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * 日期格式化
	 * 
	 * @param list
	 * @return
	 */
	public static Map<String, Object> dateFormat(Map<String, Object> map, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Set<String> keys = map.keySet();
		Iterator<String> it = keys.iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			if (map.get(key) instanceof Date) {
				map.put(key, sdf.format(map.get(key)));
			}
		}
		return map;
	}

	/**
	 * 日期格式化
	 * 
	 * @param list
	 * @return
	 */
	@SuppressWarnings("unused")
	public static List<Map<String, Object>> dateFormat(List<Map<String, Object>> list, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		for (Map<String, Object> map : list) {
			dateFormat(map, format);
		}
		return list;
	}

	/**
	 * 把String转成Date
	 * 
	 * @param dateStr
	 * @return
	 */
	public static Date formatStringToDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return sdf.parse(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取随机数字编号(指定位数)
	 * 
	 * @param length
	 * @return
	 */
	public static String getRandomName(int length) {
		int Multiple = 1;
		for (int i = 0; i < length; i++) {
			Multiple *= 10;
		}
		return dateFormat(new Date(), "yyyyMMdd") + Math.round(Math.random() * Multiple);
	}

	/**
	 * 从解密数据中获取需要的字段
	 * 
	 * @param AESstring
	 *            解密后的数据
	 * @param target
	 *            需要获取的字段
	 * @return
	 */
	public static String getTargetFromAES(String AESstring, String target) {
		String[] douStrx = AESstring.split(",");
		for (String string : douStrx) {
			if (string.indexOf(target) > 0) {
				string = string.replace("'", "");
				string = string.replace("\"", "");
				String[] doux = string.split(":");
				return doux[1];
			}
		}
		return "";
	}

	/**
	 * 通过URL下载图片
	 * 
	 * @param urlString
	 * @param filename
	 * @param savePath
	 * @throws Exception
	 */
	public static void download(String urlString, String filename, String savePath) throws Exception {
		// 构造URL
		URL url = new URL(urlString);
		// 打开连接
		URLConnection con = url.openConnection();
		// 设置请求超时为5s
		con.setConnectTimeout(5 * 1000);
		// 输入流
		InputStream is = con.getInputStream();

		// 1K的数据缓冲
		byte[] bs = new byte[1024];
		// 读取到的数据长度
		int len;
		// 输出的文件流
		File sf = new File(savePath);
		if (!sf.exists()) {
			sf.mkdirs();
		}
		OutputStream os = new FileOutputStream(sf.getPath() + "\\" + filename);
		// 开始读取
		while ((len = is.read(bs)) != -1) {
			os.write(bs, 0, len);
		}
		// 完毕，关闭所有链接
		os.close();
		is.close();
	}

	/**
	 * 获取reques流中的xml转换成map
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> getInputStreamXMLToMap(HttpServletRequest request) {
		// 解析结果存储在HashMap
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			InputStream inputStream = request.getInputStream();
			// 读取输入流
			SAXReader reader = new SAXReader();
			Document document = reader.read(inputStream);
			// 得到xml根元素
			Element root = document.getRootElement();
			// 得到根元素的所有子节点
			List<Element> elementList = root.elements();
			// 遍历所有子节点
			for (Element e : elementList) {
				map.put(e.getName(), e.getText());
			}
			// 释放资源
			inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;
	}
	
	/**
	 * 获取客户端公网ip
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个ip值，第一个ip才是真实ip
			if (ip.indexOf(",") != -1) {
				ip = ip.split(",")[0];
			}
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
			System.out.println("Proxy-Client-IP ip: " + ip);
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
			System.out.println("WL-Proxy-Client-IP ip: " + ip);
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
			System.out.println("HTTP_CLIENT_IP ip: " + ip);
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
			System.out.println("HTTP_X_FORWARDED_FOR ip: " + ip);
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Real-IP");
			System.out.println("X-Real-IP ip: " + ip);
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
			System.out.println("getRemoteAddr ip: " + ip);
		}
		return ip;
	}

	
	/**
	 * MD5加密
	 * 
	 * @param buffer
	 * @return
	 */
	public final static String MD5(String buffer) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(buffer.getBytes("UTF-8"));
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}
}
