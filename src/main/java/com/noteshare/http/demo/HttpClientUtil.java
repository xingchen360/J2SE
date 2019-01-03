package com.noteshare.http.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HttpClientUtil {
    private static final String CHARSET = "UTF-8";
    private static Logger logger = LoggerFactory.getLogger(HttpClient.class);

    /**
     * 调用http请求,返回string结果数据
     *
     * @param url 请求的地址
     * @return 接口返回数据的字符串
     */
    public static String get(String url) {
        String returnStr = "";
        HttpResponse httpResponse;

        DefaultHttpClient httpClient = new DefaultHttpClient();
        enableSSL(httpClient);
        httpClient.getParams().setParameter(ClientPNames.HANDLE_REDIRECTS, false);

        // 连接超时
        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 30000);

        // 请求超时
        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 30000);
        httpClient.getParams().setParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE, false);

        try {
            // 请求地址
            HttpGet httpget = new HttpGet(url);
            httpResponse = httpClient.execute(httpget);

            // 请求结果处理
            HttpEntity entity = httpResponse.getEntity();
            if (entity != null) {
                InputStream content = entity.getContent();
                returnStr = convertStreamToString(content);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (httpClient != null) {
                httpClient.getConnectionManager().shutdown();
                httpClient = null;
            }
        }

        return returnStr;
    }

    /**
     * 调用http请求,返回string结果数据
     *
     * @param url 请求的地址
     * @return 接口返回数据的字符串
     */
    public static String get(String url, Map<String, Object> paramList) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        for (Map.Entry<String, Object> entry : paramList.entrySet()) {
            params.add(new BasicNameValuePair(entry.getKey(), (String) entry.getValue()));
        }

        String parameters = "";
        try {
            UrlEncodedFormEntity paramStr = new UrlEncodedFormEntity(params, HTTP.UTF_8);
            parameters = EntityUtils.toString(paramStr);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (url.contains("?")) {
            url = url + "&" + parameters;
        } else {
            url = url + "?" + parameters;
        }

        return get(url);
    }

    /**
     * 调用http请求,返回string结果数据
     *
     * @param url 请求的地址
     * @return 接口返回数据的字符串
     */
    public static String post(String url) {
        return post(url, null, null);
    }

    /**
     * 调用http请求,返回string结果数据
     *
     * @param url       请求的地址
     * @param paramList json格式请求参数
     * @return
     */
    public static String post(String url, Map<String, Object> paramList, Map<String, Object> headers) {
        String returnStr = "";
        HttpResponse httpResponse;
        DefaultHttpClient httpClient;

        if (null == headers) {
            headers = new HashMap<String, Object>();
        }
        if (null == paramList) {
            paramList = new HashMap<String, Object>();
        }

        // 请求参数
        httpClient = new DefaultHttpClient();// http客户端
        enableSSL(httpClient);
        httpClient.getParams().setParameter(ClientPNames.HANDLE_REDIRECTS, false);

        // 连接超时
        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 30000);

        // 请求超时
        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 30000);
        httpClient.getParams().setParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE, false);

        //拼装参数
        List<NameValuePair> params = new ArrayList<NameValuePair>(paramList.size());
        for (Map.Entry<String, Object> entry : paramList.entrySet()) {
            params.add(new BasicNameValuePair(entry.getKey(), (String) entry.getValue()));
        }

        Header[] headerArr = new Header[headers.size()];
        int index = 0;
        for (Map.Entry<String, Object> entry : headers.entrySet()) {
            headerArr[index] = new BasicHeader(entry.getKey(), (String) entry.getValue());
            index++;
        }

        try {
            // 请求地址
            HttpPost httpPost = new HttpPost(url);
            UrlEncodedFormEntity para = new UrlEncodedFormEntity(params, HTTP.UTF_8);
            httpPost.setEntity(para);
            httpPost.setHeaders(headerArr);

            // httpPost.setHeader("Content-type", "application/json");
            httpResponse = httpClient.execute(httpPost);

            // 请求结果处理
            HttpEntity entity = httpResponse.getEntity();
            if (entity != null) {
                InputStream content = entity.getContent();
                returnStr = convertStreamToString(content);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放连接
            if (httpClient != null) {
                httpClient.getConnectionManager().shutdown();
                httpClient = null;
            }
        }

        return returnStr;
    }

    /**
     * 将流转换为字符串
     *
     * @param is
     * @return
     */
    public static String convertStreamToString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, CHARSET));
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            logger.error(Arrays.toString(e.getStackTrace()));
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                logger.error(Arrays.toString(e.getStackTrace()));
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    /**
     * 调用ssl
     *
     * @param httpclient
     */
    private static void enableSSL(DefaultHttpClient httpclient) {
        try {
            SSLContext sslcontext = SSLContext.getInstance("TLS");
            sslcontext.init(null, new TrustManager[]{truseAllManager}, null);
            SSLSocketFactory sf = new SSLSocketFactory(sslcontext);
            sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            Scheme https = new Scheme("https", sf, 443);
            httpclient.getConnectionManager().getSchemeRegistry().register(https);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 重写验证方法，取消检测ssl
     */
    private static TrustManager truseAllManager = new X509TrustManager() {

        public void checkClientTrusted(java.security.cert.X509Certificate[] arg0, String arg1) {
        }

        public void checkServerTrusted(java.security.cert.X509Certificate[] arg0, String arg1) {
        }

        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    };

    public static void main(String[] args) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("username", "souco 超");
        data.put("age", 18);
        data.put("message", "你是？");

        Map<String, Object> headers = new HashMap<String, Object>();
        headers.put("Authorization", "Bearer  access_token");

        String result = post("http://localhost:8090/soucot/test", data, headers);
        data.remove("username");
        String test2 = get("http://localhost:8090/soucot/test2?username=souco重", data);
        System.out.println(result);
        System.out.println(test2);
    }
}
