/*package com.noteshare.dingtalk;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.CorpMessageCorpconversationAsyncsendRequest;
import com.dingtalk.api.response.CorpMessageCorpconversationAsyncsendResponse;
import com.taobao.api.ApiException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



*//**
 * 
 * // 钉钉通知销售
String DDUserid = sales.getDDUserId();
if(!StringUtils.isBlank(DDUserid)){
	String toUrl = ""; // 点击查看详情跳转的路径
	String companyName = cCompany.getCompanyName()==null?"":cCompany.getCompanyName();
	String msgcontent = "{\"message_url\": \""+toUrl+"\",\"head\": {\"bgcolor\": \"FFBBBBBB\",\"text\": \"头部标题\"},\"body\": {\"title\": \"销售机会自动分配\",\"form\": [{\"key\": \"公司名称:\",\"value\": \""+companyName+"\"}],\"content\": \"您的销售机会已分配，请撸起袖子抓紧干吧！\",\"image\": \"\",\"author\": \"云朵课堂 \"}}";
	JSONObject json = DingDingUtil.sendDDMessage(DingDingUtil.AGENTID,DDUserid,msgcontent);
	if (json != null) {
		if (!"true".equals(json.get("success").toString())){
			logger.info("自动分配钉钉通知销售人员失败，失败原因：" + json.get("errorMsg").toString());
		}
	}
}
--------------------- 
作者：七弦桐 
来源：CSDN 
原文：https://blog.csdn.net/zcq_1234/article/details/79705601 
版权声明：本文为博主原创文章，转载请附上博文链接！

https://debug.dingtalk.com/

 *//*

*//**
 * @Description:对接钉钉的工具类
 * @Authod:zhang_cq
 * @Date:2018/3/19 下午2:59
 *//*
public class DingDingUtil {
	private static String CORPID = "Xxx"; // 企业Id
	private static String CORPSECRET = "Xxx"; // 企业应用的凭证密钥
	public static Long AGENTID = 1677000L; // 自动分配微应用的ID

	*//**
	 * @Description:获得token
	 * @Method:getToken
	 * @Authod:zhang_cq
	 * @Date:2018/3/19 下午3:00
	 * @param corpid
	 *            企业Id
	 * @param corpsecret
	 *            企业应用的凭证密钥
	 *//*
	private static String getToken(String corpid, String corpsecret) {
		try {
			String result = ClientUtil.sendGet("https://oapi.dingtalk.com/gettoken?corpid=" + corpid + "&corpsecret=" + corpsecret);
			JSONObject json = JSONObject.fromObject(result);
			if (json != null && "0".equals(json.get("errcode").toString())) {
				return json.get("access_token").toString();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	*//**
	 * @Description:发送消息
	 * @Method:sendDDMessage
	 * @Authod:zhang_cq
	 * @Date:2018/3/19 下午4:55
	 * @param agentId
	 *            微应用的ID
	 * @param userId
	 *            接收者的用户userid列表
	 * @param msgcontent
	 *            消息内容
	 *//*
	public static JSONObject sendDDMessage(Long agentId, String userId, String msgcontent) {
		DingTalkClient client = new DefaultDingTalkClient("https://eco.taobao.com/router/rest");
		CorpMessageCorpconversationAsyncsendRequest req = new CorpMessageCorpconversationAsyncsendRequest();
		req.setMsgtype("oa"); // 消息类型
		req.setAgentId(agentId);
		req.setUseridList(userId);
		req.setToAllUser(false); // 是否发送给企业全部用户
		req.setMsgcontentString(msgcontent);
		try {
			String token = getToken(CORPID, CORPSECRET);
			CorpMessageCorpconversationAsyncsendResponse rsp = client.execute(req, token);
			JSONObject json = JSONObject.fromObject(rsp.getResult());
			if (json != null) {
				return json;
			}
		} catch (ApiException e) {
			e.printStackTrace();
		}
		return null;
	}

	*//**
	 * @Description:根据部门ID获取用户信息
	 * @Method:getDDUsersByDeptId
	 * @Authod:zhang_cq
	 * @Date:2018/3/20 上午11:19
	 * @param departmentId
	 *//*
	public static List<Users> getDDUsersByDeptId(Long departmentId) {
		try {
			String accessToken = getToken(CORPID, CORPSECRET);
			String result = ClientUtil.sendGet("https://oapi.dingtalk.com/user/list?access_token=" + accessToken
					+ "&department_id=" + departmentId);
			JSONObject json = JSONObject.fromObject(result);
			if (json != null && "0".equals(json.get("errcode").toString())) {
				JSONArray userlist = JSONArray.fromObject((json.get("userlist")));
				if (userlist != null && userlist.size() > 0) {
					List<Users> userList = new ArrayList<Users>();
					for (int i = 0; i < userlist.size(); i++) {
						JSONObject user = userlist.getJSONObject(i);
						String mobile = user.getString("mobile");
						String userId = user.getString("userid");
						String name = user.getString("name");
						Users users = new Users();
						if (!StringUtils.isBlank(mobile)) {
							users.setMobile(mobile);
							users.setDDUserId(userId);
							users.setRealName(name);
							userList.add(users);
						}
					}
					return userList;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	*//**
	 * @Description:根据部门ID获取用户信息(userid和name)
	 * @Method:getDDSimpleUsersByDeptId
	 * @Authod:zhang_cq
	 * @Date:2018/3/20
	 * @param accessToken
	 * @param departmentId
	 *//*
	public static void getDDSimpleUsersByDeptId(String accessToken, Long departmentId) {
		try {
			String result = ClientUtil.sendGet("https://oapi.dingtalk.com/user/simplelist?access_token=" + accessToken
					+ "&department_id=" + departmentId);
			JSONObject json = JSONObject.fromObject(result);
			if (json != null && "0".equals(json.get("errcode").toString())) {
				JSONArray userlist = JSONArray.fromObject((json.get("userlist")));
				if (userlist != null && userlist.size() > 0) {
					for (int i = 0; i < userlist.size(); i++) {
						JSONObject user = userlist.getJSONObject(i);
						String name = user.getString("name");
						String userId = user.getString("userid");
						System.out.println(name + ":" + userId);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	*//**
	 * @Description:获取员工的打卡记录
	 * @Method:getRecordInfo
	 * @Authod:zhang_cq
	 * @Date:2018/3/21 下午5:42
	 * @param checkDateFrom
	 *            查询考勤打卡记录的起始工作日
	 * @param checkDateTo
	 *            查询考勤打卡记录的结束工作日。注意，起始与结束工作日最多相隔7天
	 *//*
	private static String getRecordInfo(String userId, String checkDateFrom, String checkDateTo) {
		try {
			String token = getToken(CORPID, CORPSECRET);
			Map<String, String> data = new HashMap<String, String>();
			data.put("userIds", "[" + userId + "]");
			data.put("checkDateFrom", checkDateFrom);
			data.put("checkDateTo", checkDateTo);
			JSONObject dataJson = JSONObject.fromObject(data);
			String result = sendHttpPost("https://oapi.dingtalk.com/attendance/listRecord?access_token=" + token,
					dataJson.toString());
			System.out.println(result);
			JSONObject json = JSONObject.fromObject(result);
			if (json != null && "0".equals(json.get("errcode").toString())) {
				JSONArray recordresult = JSONArray.fromObject((json.get("recordresult")));
				if (recordresult != null && recordresult.size() > 0) {
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String sendHttpPost(String url, String body) throws Exception {
		String result = null;
		HttpClient client = HttpClients.createDefault();
		URIBuilder builder = new URIBuilder();
		try {

			HttpPost post = new HttpPost(url);
			// 设置请求头
			post.setHeader("Content-Type", "application/json");
			// 设置请求体
			post.setEntity(new StringEntity(body));
			// 获取返回信息
			HttpResponse response = client.execute(post);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				entity = new BufferedHttpEntity(entity);

				InputStream in = entity.getContent();
				byte[] read = new byte[1024];
				byte[] all = new byte[0];
				int num;
				while ((num = in.read(read)) > 0) {
					byte[] temp = new byte[all.length + num];
					System.arraycopy(all, 0, temp, 0, all.length);
					System.arraycopy(read, 0, temp, all.length, num);
					all = temp;
				}
				result = new String(all, "UTF-8");
				if (null != in) {
					in.close();
				}
			}
			return result;
		} catch (Exception e) {
			System.out.println("接口请求失败" + e.getStackTrace());
		}
		return result;
	}

	public static String doPost(String url, JSONObject jsonObject, String charset) {
		HttpClient httpClient = null;
		HttpPost httpPost = null;
		String result = null;
		try {
			httpClient = HttpClients.createDefault();
			httpPost = new HttpPost(url);
			// 设置参数
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			StringEntity entity = new StringEntity(jsonObject.toString(), charset);
			entity.setContentEncoding("UTF-8");
			entity.setContentType("application/json");
			httpPost.setEntity(entity);

			HttpResponse response = httpClient.execute(httpPost);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					result = EntityUtils.toString(resEntity, charset);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	*//**
	 * @Description:销售是否上班打卡
	 * @Method:getSignInInfo
	 * @Authod:zhang_cq
	 * @Date:2018/3/22 上午10:08
	 *//*
	public static Boolean getSignInInfo(String userId) {
		String token = getToken(CORPID, CORPSECRET);
		String recordUrl = "https://oapi.dingtalk.com/attendance/list?access_token=" + token;
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("workDateFrom", "2018-03-21 06:00:00");
		jsonObject.put("workDateTo", "2018-03-21 19:00:00");
		List<String> usersList = new ArrayList<String>();
		usersList.add(userId);
		jsonObject.put("userIdList", usersList);
		jsonObject.put("offset", 0);
		jsonObject.put("limit", 1);
		String result = doPost(recordUrl, jsonObject, "utf-8");
		JSONObject resutJSON = jsonObject.fromObject(result);
		String msg = (String) resutJSON.get("errmsg");
		if ("ok".equals(msg)) {
			JSONArray jsonArray = JSONArray.fromObject(resutJSON.get("recordresult"));
			if (jsonArray != null && jsonArray.size() > 0) {
				JSONObject recordInfo = JSONObject.fromObject(jsonArray.get(0));
				String checkType = recordInfo.get("checkType").toString();
				if ("OnDuty".equals(checkType)) {
					return true;
				}
				// System.out.println(new SimpleDateFormat("yyyy-MM-dd
				// HH:mm:ss").format(new Date(time))); // 上班打卡时间
			}
		}
		return false;
	}

	// 测试主方法
	public static void main(String[] args) {
		String msgcontent = "{\"message_url\": \"http://dingtalk.com\",\"head\": {\"bgcolor\": \"FFBBBBBB\",\"text\": \"头部标题\"},\"body\": {\"title\": \"正文标题\",\"form\": [{\"key\": \"姓名:\",\"value\": \"张三\"},{\"key\": \"爱好:\",\"value\": \"打球、听音乐\"}],\"rich\": {\"num\": \"15.6\",\"unit\": \"元\"},\"content\": \"大段文本大段文本大段文本大段文本大段文本大段文本大段文本大段文本大段文本大段文本大段文本大段文本\",\"image\": \"@lADOADmaWMzazQKA\",\"file_count\": \"3\",\"author\": \"李四 \"}}";
		sendDDMessage(AGENTID, "manager4807", "", msgcontent);
	}
}
*/