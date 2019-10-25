package com.noteshare.dingtalk;

/**
 * 通讯录成员相关的接口调用
 */
public class UserHelper {

	/**
	 * 根据免登授权码查询免登用户userId
	 * <p>
	 * https://open-doc.dingtalk.com/microapp/serverapi2/clotub
	 * 
	 * @param accessToken
	 *            调用接口凭证
	 * @param code
	 *            免登授权码 前端jsapi获取
	 * @return
	 * @throws Exception
	 *//*
	public static CorpUserBaseInfo getUserInfo(String accessToken, String code) throws Exception {
		CorpUserService corpUserService = ServiceFactory.getInstance().getOpenService(CorpUserService.class);
		return corpUserService.getUserinfo(accessToken, code);
	}

	*//**
	 * 获取成员
	 * 
	 * @param accessToken
	 *            调用接口凭证
	 * @param userid
	 *            用户id
	 * @return 获取用户信息
	 * @throws Exception
	 *//*
	public static CorpUserDetail getUser(String accessToken, String userid) throws Exception {
		CorpUserService corpUserService = ServiceFactory.getInstance().getOpenService(CorpUserService.class);
		return corpUserService.getCorpUser(accessToken, userid);
	}

	*//**
	 * 获取部门用户userid列表
	 * 
	 * @param accessToken
	 * @param departmentId
	 * @param offset
	 * @param size
	 * @param order
	 * @return
	 * @throws Exception
	 *//*
	public static CorpUserList getDepartmentUser(String accessToken, long departmentId, Long offset, Integer size,
			String order) throws Exception {
		CorpUserService corpUserService = ServiceFactory.getInstance().getOpenService(CorpUserService.class);
		return corpUserService.getCorpUserSimpleList(accessToken, departmentId, offset, size, order);
	}

	*//**
	 * 获取部门成员（详情）
	 * 
	 * @param accessToken
	 * @param departmentId
	 * @param offset
	 * @param size
	 * @param order
	 * @return
	 * @throws Exception
	 *//*
	public static CorpUserDetailList getUserDetails(String accessToken, long departmentId, Long offset, Integer size,
			String order) throws Exception {
		CorpUserService corpUserService = ServiceFactory.getInstance().getOpenService(CorpUserService.class);
		return corpUserService.getCorpUserList(accessToken, departmentId, offset, size, order);
	}

	*//**
	 * 创建企业成员
	 * <p>
	 * https://open-doc.dingtalk.com/docs/doc.htm?treeId=385&articleId=106816&
	 * docType=1#s1
	 *//*
	public static String createUser(String accessToken, CorpUserDetail userDetail) throws Exception {
		CorpUserService corpUserService = ServiceFactory.getInstance().getOpenService(CorpUserService.class);
		JSONObject js = (JSONObject) JSONObject.parse(userDetail.getOrderInDepts());
		Map<Long, Long> orderInDepts = JsonMapUtil.toHashMap(js);
		String userId = corpUserService.createCorpUser(accessToken, userDetail.getUserid(), userDetail.getName(),
				orderInDepts, userDetail.getDepartment(), userDetail.getPosition(), userDetail.getMobile(),
				userDetail.getTel(), userDetail.getWorkPlace(), userDetail.getRemark(), userDetail.getEmail(),
				userDetail.getJobnumber(), userDetail.getIsHide(), userDetail.getSenior(), userDetail.getExtattr());
		// 员工唯一标识ID
		return userId;
	}

	*//**
	 * 更新成员
	 * <p>
	 * https://open-doc.dingtalk.com/docs/doc.htm?treeId=385&articleId=106816&
	 * docType=1#s2
	 *//*
	public static void updateUser(String accessToken, CorpUserDetail userDetail) throws Exception {
		CorpUserService corpUserService = ServiceFactory.getInstance().getOpenService(CorpUserService.class);
		JSONObject js = (JSONObject) JSONObject.parse(userDetail.getOrderInDepts());
		Map<Long, Long> orderInDepts = JsonMapUtil.toHashMap(js);

		corpUserService.updateCorpUser(accessToken, userDetail.getUserid(), userDetail.getName(), orderInDepts,
				userDetail.getDepartment(), userDetail.getPosition(), userDetail.getMobile(), userDetail.getTel(),
				userDetail.getWorkPlace(), userDetail.getRemark(), userDetail.getEmail(), userDetail.getJobnumber(),
				userDetail.getIsHide(), userDetail.getSenior(), userDetail.getExtattr());
	}

	*//**
	 * 删除成员
	 *//*
	public static void deleteUser(String accessToken, String userid) throws Exception {
		CorpUserService corpUserService = ServiceFactory.getInstance().getOpenService(CorpUserService.class);
		corpUserService.deleteCorpUser(accessToken, userid);
	}

	*//**
	 * 批量删除成员
	 * 
	 * @param accessToken
	 * @param useridlist
	 * @throws Exception
	 *//*
	public static void batchDeleteUser(String accessToken, List<String> useridlist) throws Exception {
		CorpUserService corpUserService = ServiceFactory.getInstance().getOpenService(CorpUserService.class);
		corpUserService.batchdeleteCorpUserListByUserids(accessToken, useridlist);

	}

	*//**
	 * 管理后台免登时通过CODE获取微应用管理员的身份信息
	 *
	 * @param ssoToken
	 * @param code
	 * @return
	 * @throws DingTalkException
	 *//*
	public static JSONObject getAgentUserInfo(String ssoToken, String code) throws DingTalkException {
		String url = Env.OAPI_HOST + "/sso/getuserinfo?" + "access_token=" + ssoToken + "&code=" + code;
		JSONObject response = HttpHelper.httpGet(url);
		return response;
	}*/
}
