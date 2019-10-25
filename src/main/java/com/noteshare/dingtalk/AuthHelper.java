package com.noteshare.dingtalk;

/**
 * AccessToken
 */
public class AuthHelper {

    /**
     * 调整到50分钟
     */
    public static final long CACHETIME = 1000 * 60 * 50;
    /**
     * 随机串，自己定义
     */
    public static final String NONCESTR = "noteshare";
    
    /**
     * 根据本地常量信息获取accesstoken的值
     * @return 返回accessTokenMap，如果为null则表示没有获取到对应的授权
     * @throws AccessTokenException
     */
    /*public static Map<String, Object> getAccessToken() throws AccessTokenException {
    	return getAccessToken(null);
    }*/
    
    /**
     * 根据appkey和appsecret获取accesstoken的值，先查数据库中持久化的数据是否满足要求，满足则从数据库拿，否则重新获取。
     * accessToken过期时间是2小时
     * @param accessTokenDAO
     * @return 返回accessTokenMap，如果为null则表示没有获取到对应的授权
     * @throws AccessTokenException
     */
    /*public static Map<String, Object> getAccessToken(AccessTokenDAO accessTokenDAO) throws AccessTokenException {
        long curTime = System.currentTimeMillis();
        HashMap<String, Object> model = new HashMap<>();
        String appKey = Toolkit.getConfigValue("bd.sys.config.dingtalk.APP_KEY");
        String appSecret = Toolkit.getConfigValue("bd.sys.config.dingtalk.APP_SECRET");
        if(StringUtils.isEmpty(appKey) || StringUtils.isEmpty(appSecret)){
        	appKey = Env.APP_KEY;
        	appSecret = Env.APP_SECRET;
        }
        model.put("APPKEY", appKey);
        Map<String, Object> accessTokenMap = null;
        if(null != accessTokenDAO){
        	accessTokenMap = accessTokenDAO.getAccessTokenByAppkey(model);
        }
        if (accessTokenMap == null || curTime - (null == accessTokenMap.get("PREACCESSTOKENTIME") ? 0 : ((Date)accessTokenMap.get("PREACCESSTOKENTIME")).getTime()) >= CACHETIME) {
            try {
                ServiceFactory serviceFactory = ServiceFactory.getInstance();
                CorpConnectionService corpConnectionService = serviceFactory.getOpenService(CorpConnectionService.class);
            	String accessToken = corpConnectionService.getCorpToken(appKey, appSecret);
            	model.put("APPKEY", appKey);
            	model.put("APPSECRET", appSecret);
                model.put("PREACCESSTOKENTIME", new Date());
                model.put("ACCESSTOKEN", accessToken);
                // 持久化到数据中
                if(null != accessTokenDAO){
                	if(null == accessTokenMap){
                		model.put("ID", UUID.randomUUID().toString());
                		accessTokenDAO.addAccessToken(model);
                	}else{
                		accessTokenMap.put("PREACCESSTOKENTIME", new Date());
                		accessTokenMap.put("ACCESSTOKEN", accessToken);
                		accessTokenDAO.updateAccessTokenByAppkey(accessTokenMap);
                	}
                }
            } catch (Exception e) {
            	model = null;
                LoggerUtil.error(AuthHelper.class, "获取accessToken异常，msg:" + e.getMessage());
            }
            return model;
        } else {
        	return accessTokenMap;
        }
    }*/
    /**
     * 根据accesstoken获取jsapiticket
     * jsapiticket过期时间是2小时
     * @param accessTokenMap
     * @param accessTokenDAO
     * @return
     * @throws AccessTokenException
     */
   /* public static String getJsapiTicket(Map<String, Object> accessTokenMap,AccessTokenDAO accessTokenDAO) throws AccessTokenException {
    	long curTime = System.currentTimeMillis();
    	String accessToken = accessTokenMap == null ? null : (String) accessTokenMap.get("ACCESSTOKEN");
        // 如果数据库中持久化的值没过期则从数据库中获取
        if(StringUtils.isEmpty(accessToken)){
        	LoggerUtil.error(AuthHelper.class, "获取getJsapiTicket时传入的参数不合理,accessToken为null");
        	return null;
        }else{
        	long pretickettime = null == accessTokenMap.get("PRETICKETTIME") ? 0 : ((Date)accessTokenMap.get("PRETICKETTIME")).getTime();
        	String ticket = (String) accessTokenMap.get("TICKET");
        	if (curTime - pretickettime >= CACHETIME || StringUtils.isEmpty(ticket)) {
        		//重新获取jsapi鉴权ticket
        		ServiceFactory serviceFactory;
                try {
                    serviceFactory = ServiceFactory.getInstance();
                    JsapiService jsapiService = serviceFactory.getOpenService(JsapiService.class);
                    JsapiTicket JsapiTicket = jsapiService.getJsapiTicket(accessToken, "jsapi");
                    ticket = JsapiTicket.getTicket();
                    accessTokenMap.put("ACCESSTOKEN", accessToken);
                    accessTokenMap.put("TICKET", ticket);
                    accessTokenMap.put("PRETICKETTIME", new Date());
                    // 持久化到数据中
                    if(null != accessTokenDAO){
                    	accessTokenDAO.updateAccessTokenByAppAccessToken(accessTokenMap);
                    }
                } catch (Exception e) {
                	ticket = null;
                    LoggerUtil.error(AuthHelper.class, "获取accessToken异常，msg:" + e.getMessage());
                }
                return ticket;
        	}else{
        		return ticket;
        	}
        }
    }
    *//**
     * 
     * @param ticket
     * @param request
     * @return 返回签名信息和签名jsapi鉴权需要的配置信息
     * @throws AccessTokenException
     *//*
	@SuppressWarnings("deprecation")
	public static Map<String, Object> sign(String ticket,String url,String queryString) throws AccessTokenException {
        String queryStringEncode = null;
        if (null != queryString && !"".equals(queryString)) {
            queryStringEncode = URLDecoder.decode(queryString);
            url = url + "?" + queryStringEncode;
        }
        //时间戳 当前时间
        long timeStamp = System.currentTimeMillis();
        String signedUrl = url;
        String signature = sign(ticket, NONCESTR, timeStamp, signedUrl);;
        Map<String, Object> config = new HashMap<>();
        config.put("jsticket", ticket);
        config.put("signature", signature);
        config.put("nonceStr", NONCESTR);
        config.put("timeStamp", timeStamp);
        String corp_id = Toolkit.getConfigValue("bd.sys.config.dingtalk.CORP_ID");
        String agent_id = Toolkit.getConfigValue("bd.sys.config.dingtalk.AGENT_ID");
        if(StringUtils.isEmpty(corp_id) || StringUtils.isEmpty(agent_id)){
        	corp_id = Env.CORP_ID;
        	agent_id = Env.AGENT_ID;
        }
        config.put("corpId", corp_id);
        config.put("agentId", agent_id);
        return config;
    }
    
    *//**
     * @param ticket
     * @param nonceStr
     * @param timeStamp
     * @param url
     * @return 计算签名
     * @throws AccessTokenException
     *//*
    public static String sign(String ticket, String nonceStr, long timeStamp, String url) throws AccessTokenException {
        try {
            return DingTalkJsApiSingnature.getJsApiSingnature(url, nonceStr, timeStamp, ticket);
        } catch (Exception ex) {
            throw new AccessTokenException("获取签名信息异常" + ex.getMessage(), ex);
        }
    }*/
}
