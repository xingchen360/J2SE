package com.noteshare.http;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

public class HttpClient {
	
    @Test
    public void doJsonPost() throws HttpHostConnectException, IOException{
    	String url = "http://localhost:8080/restful/service/com.noteshare.resource.RestfulResource/getAccount";
		Map<String,String> param = new HashMap<String, String>();
		param.put("username", "admin");
		param.put("password", "123456");
		System.out.println("body:" + HttpClientUtils.doJsonPost(url, param));
    }
    @Test
    public void doJsonPostByFluent() throws ClientProtocolException, IOException {
    	String url = "http://localhost:8080/restful/service/com.noteshare.resource.RestfulResource/getAccount";
    	Map<String,String> param = new HashMap<String, String>();
		param.put("username", "admin");
		param.put("password", "123456");
    	String response = Request.Post(url)
    						.connectTimeout(1000)
    						.socketTimeout(1000)  
    						.bodyString(JSON.toJSONString(param),ContentType.APPLICATION_JSON)
    						.execute()
    						.returnContent()
    						.asString();
    	System.out.println(response);
    }
    
    @Test
    public void doXmlPost() throws HttpHostConnectException, IOException{
    	String url = "http://localhost:8080/restful/service/com.noteshare.resource.RestfulResource/getAccount2";
		Map<String,String> param = new HashMap<String, String>();
		param.put("username", "admin");
		param.put("password", "123456");
		System.out.println("body:" + HttpXMLUtils.doXmlPost(url, param));
    }
    
    @Test
    public void doPathParamGet() throws HttpHostConnectException, IOException, URISyntaxException {
    	String url = "http://www.baidu.com/";
		System.out.println("body:" + HttpClientUtils.doGet(url));
		System.out.println("body:" + HttpClientUtils.doGet(url));
    }
    
    @Test
    public void doPathParamGetByFluent() throws ClientProtocolException, IOException {
    	String response = Request.Get("http://www.baidu.com/").execute()
				.returnContent().asString();
		System.out.println(response);
    }

    @Test
    public void doQueryParamGet() throws HttpHostConnectException, IOException, URISyntaxException {
    	String url = "http://localhost:8080/restful/service/com.noteshare.resource.RestfulResource/getAccount4";
		Map<String,String> param = new HashMap<String, String>();
		param.put("username", "admin");
		param.put("password", "123456");
		System.out.println("body:" + HttpClientUtils.doGet(url, param));
    }
    
    @Test
    public void doQueryParamGetByFluent() throws ClientProtocolException, IOException, URISyntaxException {
    	String url = "http://localhost:8080/restful/service/com.noteshare.resource.RestfulResource/getAccount4";
    	String response = Request.Get(new URIBuilder(url)
    									.addParameter("username", "admin")
    									.addParameter("password", "123456")
    									.build()
    								)
    					.execute().returnContent().asString();
		System.out.println(response);
    }

    @Test
    public void doFormParamPost() throws HttpHostConnectException, IOException{
    	String url = "http://localhost:8080/restful/service/com.noteshare.resource.RestfulResource/getAccount5";
		Map<String,String> param = new HashMap<String, String>();
		param.put("username", "admin");
		param.put("password", "123456");
		System.out.println("body:" + HttpClientUtils.doPost(url, param));
    }
    
    @Test
    public void doFormParamPostByFluent() throws ClientProtocolException, IOException{
    	String url = "http://localhost:8080/restful/service/com.noteshare.resource.RestfulResource/getAccount5";
    	String response = Request.Post(url).bodyForm(Form.form()
    									.add("username", "admin")
    									.add("password", "123456")
    									.build()
    							)
    					.execute().returnContent().asString();
    	System.out.println(response);
    }
    
    @Test
    public void doUpload() throws ClientProtocolException, IOException{
    	String url = "http://localhost:8080/SpringMVC/databind/doUpload";
    	String path = getClass().getClassLoader().getResource("excel/80034.xls").getPath();
    	String response = Request.Post(url)
    					.body(MultipartEntityBuilder.create().addBinaryBody("file", new File(path)).build())
    					.execute().returnContent().asString();
    	System.out.println(response);
    }
}
