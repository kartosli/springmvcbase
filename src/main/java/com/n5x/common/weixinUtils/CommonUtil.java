package com.n5x.common.weixinUtils;


import com.n5x.common.weixinUtils.model.Menu;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.*;
import java.net.ConnectException;
import java.net.URL;
import java.util.Iterator;


/**
 * 通用工具类
 */
public class CommonUtil {
	private static Logger log = LoggerFactory.getLogger(CommonUtil.class);
	/**
	 * 发送https请求
	 * @param requestUrl 请求地址
	 * @param requestMethod 请求方式（GET、POST）
	 * @param outputStr 提交的数据
	 * @return 返回微信服务器响应的信息
	 */
	public static String httpsRequest(String requestUrl, String requestMethod, String outputStr) {
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			URL url = new URL(requestUrl);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setSSLSocketFactory(ssf);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			conn.setRequestMethod(requestMethod);
			conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
			// 当outputStr不为null时向输出流写数据
			if (null != outputStr) {
				OutputStream outputStream = conn.getOutputStream();
				// 注意编码格式
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}
			// 从输入流读取返回内容
			InputStream inputStream = conn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			StringBuffer buffer = new StringBuffer();
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			// 释放资源
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			inputStream = null;
			conn.disconnect();
			return buffer.toString();
		} catch (ConnectException ce) {
			log.error("连接超时：{}", ce);
		} catch (Exception e) {
			log.error("https请求异常：{}", e);
		}
		return null;
	}

	/**
	 * 获取接口访问凭证
	 *
	 * @return
	 */
	public static WeixinToken getToken() {
		WeixinToken token = null;
		String requestUrl = ConfigUtil.TOKEN_URL.replace("APPID", ConfigUtil.APPID).replace("APPSECRET", ConfigUtil.APP_SECRECT);
		// 发起GET请求获取凭证
		JSONObject jsonObject = JSONObject.fromObject(httpsRequest(requestUrl, "GET", null));

		if (null != jsonObject) {
			try {
				token = new WeixinToken();
				token.setAccessToken(jsonObject.getString("access_token"));
				token.setExpiresIn(jsonObject.getInt("expires_in"));
			} catch (JSONException e) {
				token = null;
				System.out.println("获取token失败 errcode:{} errmsg:{}  "+jsonObject.getInt("errcode")+" | "+jsonObject.getString("errmsg"));
				// 获取token失败
				log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}
		return token;
	}

	/**
	 * 获取接口访问凭证
	 *
	 * @param code
	 * @return
	 */
	public static WeixinToken getAccessToken(String code) {
		WeixinToken token = null;
		String requestUrl = ConfigUtil.OAUTH2_URL
				.replace("CODE", code)
				.replace("APPID", ConfigUtil.APPID)
				.replace("SECRET", ConfigUtil.APP_SECRECT);
		// 发起GET请求获取凭证
		JSONObject jsonObject = JSONObject.fromObject(httpsRequest(requestUrl, "GET", null));

		if (null != jsonObject) {
			try {
				token = new WeixinToken();
				token.setAccessToken(jsonObject.getString("access_token"));
				token.setExpiresIn(jsonObject.getInt("expires_in"));
				token.setOpenId(jsonObject.getString("openid"));
			} catch (JSONException e) {
				token = null;
				// 获取token失败
				log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}
		return token;
	}

	/**
	 * 获取接口访问凭证
	 *
	 * @return
	 */
	public static WeixinToken getAccessTokenUser(WeixinToken token) {
		String requestUrl = ConfigUtil.USER_URL
				.replace("ACCESS_TOKEN", token.getAccessToken())
				.replace("OPENID", token.getOpenId());
		// 发起GET请求获取凭证
		JSONObject jsonObject = JSONObject.fromObject(httpsRequest(requestUrl, "GET", null));
		
		if (null != jsonObject) {
			try {
				String remark = "";
				for (Iterator iter = jsonObject.keys(); iter.hasNext();) {
					String key = (String)iter.next();
					remark += key+"="+jsonObject.getString(key)+";";
					System.out.println(key+"="+jsonObject.getString(key)+";");
				}
				token.setRemark(remark);
			} catch (JSONException e) {
				token = null;
				// 获取token失败
				log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}
		return token;
	}


	/**
	 * 创建菜单
	 *菜单创建（POST） 限100（次/天）
	 * @param menu 菜单实例
	 * @param accessToken 有效的access_token
	 * @return 0表示成功，其他值表示失败
	 */
	public static int createMenu(Menu menu, String accessToken) {
		int result = 0;
		// 拼装创建菜单的url
		String url = ConfigUtil.MENU_CREATE_URL.replace("ACCESS_TOKEN", accessToken);
		// 将菜单对象转换成json字符串
		String jsonMenu = JSONObject.fromObject(menu).toString();
		// 调用接口创建菜单
		JSONObject jsonObject = JSONObject.fromObject(httpsRequest(url, "POST", jsonMenu));
		if (null != jsonObject) {
			if (0 != jsonObject.getInt("errcode")) {
				result = jsonObject.getInt("errcode");
				log.error("创建菜单失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}

		return result;
	}

	public static String urlEncodeUTF8(String source){
		String result = source;
		try {
			result = java.net.URLEncoder.encode(source,"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
}