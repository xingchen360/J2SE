package com.noteshare.http.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 新疆对接
 * @author itnoteshare
 *
 */
@SuppressWarnings("deprecation")
public class YthdjUtil {
	private static final String CONTENTTYPE = "application/x-www-form-urlencoded";
	private static final String CLIENT_ID = "f8726452-dd11-40b0-83b6-66a1e3442982";
	private static final String CLIENT_SECRET = "e3bbcfec-5e3c-48db-a328-7bca8d945dcd";
	private static final String REDIRECT_URI = "http://localhost:8080/wssb/pages/ythdj.jsp";
	private static final String URI = "http://220.171.42.82:8082/xjwwrz";

	public static void main(String[] args) throws Exception {
		//authorize();
		// logout();
		//token("551dd1ea40b075c77b364afdc54dfebb");
		//authorize();
		//token();
		gettoken();
	}
	/**
	 * 失敗
	 * 只能在浏览器上调用
	 */
	public static void authorize() {
		String url = URI + "/rest/oauth2/authorize?display=outside&client_id=" + CLIENT_ID + "&state=1111&response_type=code&redirect_uri=" + REDIRECT_URI;
		HttpPost httpPost = null;
		try {
			httpPost = new HttpPost(url);
			httpPost.setHeader("Content-Type", CONTENTTYPE);
			HttpClient httpClient = new DefaultHttpClient();
			HttpResponse response = httpClient.execute(httpPost);
			int codeT = response.getStatusLine().getStatusCode();
			if(200 == codeT){
				// 请求结果处理
				HttpEntity reEntity = response.getEntity();
				if (reEntity != null) {
					InputStream content = reEntity.getContent();
					String returnStr = HttpClientUtil.convertStreamToString(content);
					System.out.println(returnStr);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (httpPost != null) {
				httpPost.releaseConnection();
			}
		}
	}
	
	/**
	 * 成功
	 * {"access_token":"f1f381feee32b219d5dbc80ce440f8c2","refresh_token":"dff748cc47c392264ffc8e8906270b43","expires_in":1800}
	 * @param code
	 */
	public static void token(String code) {
		String url = URI + "/rest/oauth2/token?client_id=" + CLIENT_ID + "&client_secret=" + CLIENT_SECRET
				+ "&grant_type=authorization_code&code=" + code + "&redirect_uri=" + "http://localhost:8080/wssb/pages/ythdj.jsp";
		HttpPost httpPost = null;
		try {
			httpPost = new HttpPost(url);
			httpPost.setHeader("Content-Type", CONTENTTYPE);
			HttpClient httpClient = new DefaultHttpClient();
			HttpResponse response = httpClient.execute(httpPost);
			int codeT = response.getStatusLine().getStatusCode();
			if(200 == codeT){
				// 请求结果处理
				HttpEntity reEntity = response.getEntity();
				if (reEntity != null) {
					InputStream content = reEntity.getContent();
					String returnStr = HttpClientUtil.convertStreamToString(content);
					System.out.println(returnStr);
					JSONObject jsonObject = JSONObject.fromObject(returnStr);
					String access_token = jsonObject.getString("access_token");
					getUserData(access_token);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (httpPost != null) {
				httpPost.releaseConnection();
			}
		}
	}
	
	/**
	 * 成功
	 * {"controls":[],"custom":{"contactemail":"1@qq.com","contactaddress":"","contactperson":null,"certenddate":"","loginid":"zy11111","birthdate":"","idnumber":"320582199508165411","lastlogindate":"2018-12-27 15:31:40","sex":"1","usertype":"1","contactfax":null,"contactpostcode":null,"registerdate":"","rowguid":"b22be70f-6359-4ebc-b397-17c1237ef6c8","certstartdate":"","certtype":null,"clientname":"xxxx","certificationgrade":"2","is_del":"0","contactphone":null,"contactmobile":"18888888800","accountguid":"92ac2cc5-20de-4094-b921-41419dec34c2"},"status":{"code":1,"state":"error","text":"成功","top":false,"url":""}}
	 * @param access_token
	 */
	private static void getUserData(String access_token) {
		String url = URI + "/rest/extranetNeedLoginInterface/getUserData?access_token=" + access_token;
		HttpPost httpPost = null;
		try {
			httpPost = new HttpPost(url);
			httpPost.setHeader("Content-Type", CONTENTTYPE);
			HttpClient httpClient = new DefaultHttpClient();
			HttpResponse response = httpClient.execute(httpPost);
			int codeT = response.getStatusLine().getStatusCode();
			if(200 == codeT){
				// 请求结果处理
				HttpEntity reEntity = response.getEntity();
				if (reEntity != null) {
					InputStream content = reEntity.getContent();
					String returnStr = HttpClientUtil.convertStreamToString(content);
					System.out.println(returnStr);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (httpPost != null) {
				httpPost.releaseConnection();
			}
		}
	}
	
	public static void token() {
		String url = URI + "/rest/oauth2/token?client_id=" + CLIENT_ID + "&client_secret=" + CLIENT_SECRET
				+ "&grant_type=client_credentials" + "&redirect_uri=" + "http://localhost:8080/wssb/pages/ythdj.jsp";
		HttpPost httpPost = null;
		try {
			httpPost = new HttpPost(url);
			httpPost.setHeader("Content-Type", CONTENTTYPE);
			HttpClient httpClient = new DefaultHttpClient();
			HttpResponse response = httpClient.execute(httpPost);
			int codeT = response.getStatusLine().getStatusCode();
			if(200 == codeT){
				// 请求结果处理
				HttpEntity reEntity = response.getEntity();
				if (reEntity != null) {
					InputStream content = reEntity.getContent();
					String returnStr = HttpClientUtil.convertStreamToString(content);
					System.out.println(returnStr);
					JSONObject jsonObject = JSONObject.fromObject(returnStr);
					JSONObject custom = jsonObject.getJSONObject("custom");
					String access_token = custom.getString("access_token");
					//4.1.6获取经办人经办的企业
					String resultC = getAuthCompanyData(access_token,"92ac2cc5-20de-4094-b921-41419dec34c2");
					//{"controls":[],"custom":{"companylist":[{"legalname":"赵址源","organname":"测试公司","companyguid":"0ff0961a-18cc-463d-b1ef-c17381f90b94"}],"count":1},"status":{"code":1,"state":"error","text":"成功","top":false,"url":""}}
					System.out.println("===" + resultC);
					JSONObject resultCJson = JSONObject.fromObject(resultC);
					JSONObject custom2 = resultCJson.getJSONObject("custom");
					JSONArray companylist = custom2.getJSONArray("companylist");
					JSONObject company = (JSONObject) companylist.get(0);
					String companyguid = company.getString("companyguid");
					//4.1.7获取企业信息
					getCompanyData(access_token,companyguid);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (httpPost != null) {
				httpPost.releaseConnection();
			}
		}
	}
	/**
	 * {"controls":[],"custom":{"contactemail":null,"creditcode":"410423199303040012","contactperson":null,"certenddate":"","legalname":"赵址源","organname":"测试公司","remark":null,"rowguid":"a3401d5c-04c4-4600-acd8-dc44a0dc2f5f","deptinfo":null,"certstartdate":"","is_del":null,"contactphone":null,"contactmobile":null,"accountguid":"0ff0961a-18cc-463d-b1ef-c17381f90b94","contactaddress":null,"loginid":"testzzy","agentidnumber":"410423199303040012","lastlogindate":"2018-12-19 15:26:26","organcode":null,"postcode":null,"legalidnumber":"410423199388889999","contactpostcode":null,"contactfax":null,"registerdate":"2018-12-11 23:21:41","employeecount":null,"agentmobile":"15638183505","legaltype":"c01","agentname":"赵址源"},"status":{"code":1,"state":"error","text":"成功","top":false,"url":""}}
	 * @param access_token
	 * @param companyguid
	 * @return
	 */
	private static String getCompanyData(String access_token, String companyguid) {
		String url = URI + "/rest/achieveInfoForThird/getCompanyData";
		HttpPost httpPost = null;
		try {
			httpPost = new HttpPost(url);
			httpPost.setHeader("Authorization", "Bearer  " + access_token);
			List<BasicNameValuePair> list = new LinkedList<>();
	        BasicNameValuePair param1 = new BasicNameValuePair("params", "{\"companyguid\":\""+companyguid+"\"}");
	        list.add(param1);
	        // 使用URL实体转换工具
	        UrlEncodedFormEntity entityParam = new UrlEncodedFormEntity(list, "UTF-8");
	        httpPost.setEntity(entityParam);

			HttpClient httpClient = new DefaultHttpClient();
			HttpResponse response = httpClient.execute(httpPost);
			int codeT = response.getStatusLine().getStatusCode();
			if(200 == codeT){
				// 请求结果处理
				HttpEntity reEntity = response.getEntity();
				if (reEntity != null) {
					InputStream content = reEntity.getContent();
					String returnStr = HttpClientUtil.convertStreamToString(content);
					System.out.println(returnStr);
					return returnStr;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (httpPost != null) {
				httpPost.releaseConnection();
			}
		}
		return null;
	}
	/**
	 * 4.1.6获取经办人经办的企业
	 * {"controls":[],"custom":{"companylist":[{"legalname":"赵址源","organname":"测试公司","companyguid":"0ff0961a-18cc-463d-b1ef-c17381f90b94"}],"count":1},"status":{"code":1,"state":"error","text":"成功","top":false,"url":""}}
	 * @param access_token
	 */
	private static String getAuthCompanyData(String access_token,String accountguid) {
		String url = URI + "/rest/achieveInfoForThird/getAuthCompanyData";
		HttpPost httpPost = null;
		try {
			httpPost = new HttpPost(url);
			httpPost.setHeader("Authorization", "Bearer  " + access_token);
			List<BasicNameValuePair> list = new LinkedList<>();
	        BasicNameValuePair param1 = new BasicNameValuePair("params", "{\"accountguid\":\""+accountguid+"\"}");
	        list.add(param1);
	        // 使用URL实体转换工具
	        UrlEncodedFormEntity entityParam = new UrlEncodedFormEntity(list, "UTF-8");
	        httpPost.setEntity(entityParam);

			HttpClient httpClient = new DefaultHttpClient();
			HttpResponse response = httpClient.execute(httpPost);
			int codeT = response.getStatusLine().getStatusCode();
			if(200 == codeT){
				// 请求结果处理
				HttpEntity reEntity = response.getEntity();
				if (reEntity != null) {
					InputStream content = reEntity.getContent();
					String returnStr = HttpClientUtil.convertStreamToString(content);
					System.out.println(returnStr);
					return returnStr;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (httpPost != null) {
				httpPost.releaseConnection();
			}
		}
		return null;
	}
	
	
	public static void gettoken() {
		String url = "http://220.171.42.82:8082/public/aouth/gettoken?grant_type=client_credentials&client_id=cde4949f-376c-40d2-99fd-227d9c93bc95&client_secret=30d9b103-754f-4a23-ae47-f5d9e54cdbef";
		HttpPost httpPost = null;
		try {
			httpPost = new HttpPost(url);
			httpPost.setHeader("Content-Type", CONTENTTYPE);
			HttpClient httpClient = new DefaultHttpClient();
			HttpResponse response = httpClient.execute(httpPost);
			int codeT = response.getStatusLine().getStatusCode();
			if(200 == codeT){
				// 请求结果处理
				HttpEntity reEntity = response.getEntity();
				if (reEntity != null) {
					InputStream content = reEntity.getContent();
					String returnStr = HttpClientUtil.convertStreamToString(content);
					System.out.println(returnStr);
					JSONObject jsonObject = JSONObject.fromObject(returnStr);
					JSONObject custom = jsonObject.getJSONObject("custom");
					String access_token = custom.getString("access_token");
					System.out.println("access_token:" + access_token);
					//获取办件事项编号
					String flowsn = getflowsn(access_token);
					System.out.println("flowsn:" + flowsn);
					//办件状态实时推送--此接口需要传递对应的事项编号
					setstatus(access_token,flowsn);
					//setstatus(access_token,"11650000010183859F265011601300120181228100000001");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (httpPost != null) {
				httpPost.releaseConnection();
			}
		}
	}
	/**
	 * {"custom":{"code":1,"text":"数据插入成功!"},"status":{"code":200,"text":""}}
	 * {"custom":{"code":1,"text":"方法执行成功！","flowsn":"11650000010183859F265011601300120181228100000010"},"status":{"code":200,"text":""}}
	 * @param access_token
	 */
	private static void setstatus(String access_token,String projectno) {
		String url = "http://220.171.42.82:8082/public/onlineproject/setstatus";
		HttpPost httpPost = null;
		try {
			httpPost = new HttpPost(url);
			httpPost.setHeader("Authorization", "Bearer " + access_token);
			httpPost.setHeader("Content-Type", "application/json");
			//构建表单数据
			JSONObject jsonT = new JSONObject();
			//该事件编号需要是已经存在的事件-需要先把事件推送过去后才能拿到-此处是在政务一体化中已有测试事件中拷贝过来的固定值
			jsonT.put("projectno", projectno);
			//办件状态-查看附件3
			jsonT.put("projectstatus", "12");
			jsonT.put("projectname", "测试办件");
			jsonT.put("applyername", "申请人");
			jsonT.put("applyerpagecode", "申请人证件号码");
			jsonT.put("contactname", "联系人姓名");
			jsonT.put("contactcode", "联系人证件号码");
			jsonT.put("applydate", "2018-12-28 11:25:00");
			jsonT.put("contactcode", "联系人证件号码");
			jsonT.put("createdate", "2018-12-28 11:25:00");
			jsonT.put("operate_url", "http://localhost:8080/wssb");
			
			JSONObject paramsJson = new JSONObject();
			paramsJson.put("params", jsonT);
			System.out.println(paramsJson.toString());
			StringEntity se = new StringEntity(paramsJson.toString());
	        se.setContentType("text/json");
	        httpPost.setEntity(se);
			HttpClient httpClient = new DefaultHttpClient();
			HttpResponse response = httpClient.execute(httpPost);
			int codeT = response.getStatusLine().getStatusCode();
			System.out.println(codeT);
			if(200 == codeT){
				// 请求结果处理
				HttpEntity reEntity = response.getEntity();
				if (reEntity != null) {
					InputStream content = reEntity.getContent();
					String returnStr = convertStreamToString(content);
					System.out.println(returnStr);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (httpPost != null) {
				httpPost.releaseConnection();
			}
		}
	}
	/**
	 * {"params":{"taskcode":"11650000010183859F2650116013001","applytype":1}}
	 * @param access_token
	 */
	public static String getflowsn(String access_token) {
		String url = "http://220.171.42.82:8082/public/flowsn/getflowsn";
		HttpPost httpPost = null;
		//事项编号
		String  flowsn = "";
		try {
			httpPost = new HttpPost(url);
			httpPost.setHeader("Authorization", "Bearer " + access_token);
			httpPost.setHeader("Content-Type", "application/json");
			JSONObject jsonT = new JSONObject();
			jsonT.put("taskcode", "11650000010183859F2650116013001");
			jsonT.put("applytype", 1);
			
			JSONObject paramsJson = new JSONObject();
			paramsJson.put("params", jsonT);
			System.out.println(paramsJson.toString());
			StringEntity se = new StringEntity(paramsJson.toString());
	        se.setContentType("text/json");
	        httpPost.setEntity(se);
			HttpClient httpClient = new DefaultHttpClient();
			HttpResponse response = httpClient.execute(httpPost);
			int codeT = response.getStatusLine().getStatusCode();
			System.out.println(codeT);
			if(200 == codeT){
				// 请求结果处理
				HttpEntity reEntity = response.getEntity();
				if (reEntity != null) {
					InputStream content = reEntity.getContent();
					String returnStr = HttpClientUtil.convertStreamToString(content);
					System.out.println(returnStr);
					
					JSONObject jsonObject = JSONObject.fromObject(returnStr);
					JSONObject custom = jsonObject.getJSONObject("custom");
					flowsn = custom.getString("flowsn");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (httpPost != null) {
				httpPost.releaseConnection();
			}
		}
		return flowsn;
	}
	
	public static String convertStreamToString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
