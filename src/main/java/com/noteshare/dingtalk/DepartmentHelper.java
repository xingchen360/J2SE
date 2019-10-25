package com.noteshare.dingtalk;

/*import com.dingtalk.open.client.ServiceFactory;
import com.dingtalk.open.client.api.model.corp.Department;
import com.dingtalk.open.client.api.model.corp.DepartmentDetail;
import com.dingtalk.open.client.api.service.corp.CorpDepartmentService;
import com.dingtalk.open.client.common.SdkInitException;
import com.dingtalk.open.client.common.ServiceException;
import com.dingtalk.open.client.common.ServiceNotExistException;*/

/**
 * 部门相关API
 * https://open-doc.dingtalk.com/microapp/serverapi2/dubakq
 */
public class DepartmentHelper {
	
	/**
	 * 根据部门id获取部门信息
	 * @param accessToken 调用接口凭证
	 * @param deptid 部门id
	 * @return 返回部门信息
	 * @throws ServiceException
	 * @throws ServiceNotExistException
	 * @throws SdkInitException
	 */
	/*public static DepartmentDetail getDepartmentDetail(String accessToken, String deptid) throws ServiceException, ServiceNotExistException, SdkInitException{
		CorpDepartmentService corpDepartmentService = ServiceFactory.getInstance().getOpenService(CorpDepartmentService.class);
		DepartmentDetail departmentDetail = corpDepartmentService.getDeptDetail(accessToken, deptid);
		return departmentDetail;
	}*/
	
	/**
	 * 根据父部门id获取部门列表（递归查询，包含子部门）
	 * @param accessToken：调用接口凭证
	 * @param parentDeptId：父部门id（如果不传，默认部门为根部门，根部门ID为1）
	 * @return 返回部门基础信息列表
	 * @throws ServiceNotExistException
	 * @throws SdkInitException
	 * @throws ServiceException
	 * https://open-doc.dingtalk.com/microapp/serverapi2/dubakq
	 */
//    public static List<Department> listDepartments(String accessToken, String parentDeptId)
//            throws ServiceNotExistException, SdkInitException, ServiceException {
//        CorpDepartmentService corpDepartmentService = ServiceFactory.getInstance().getOpenService(CorpDepartmentService.class);
//        List<Department> deptList = corpDepartmentService.getDeptList(accessToken, parentDeptId);
//        return deptList;
//    }

    /**
     *  创建部门
     */
   /* public static String createDepartment(String accessToken, String name,
                                          String parentId, String order, boolean createDeptGroup) throws Exception {
        CorpDepartmentService corpDepartmentService = ServiceFactory.getInstance().getOpenService(CorpDepartmentService.class);
        return corpDepartmentService.deptCreate(accessToken, name, parentId, order, createDeptGroup);
    }

    *//**
     * 删除部门
     *//*
    public static void deleteDepartment(String accessToken, Long id) throws Exception {
        CorpDepartmentService corpDepartmentService = ServiceFactory.getInstance().getOpenService(CorpDepartmentService.class);
        corpDepartmentService.deptDelete(accessToken, id);
    }

    *//**
     * 更新部门
     *//*
    public static void updateDepartment(String accessToken, long id, String name,
                                        String parentId, String order, Boolean createDeptGroup,
                                        boolean autoAddUser, String deptManagerUseridList, boolean deptHiding, String deptPerimits,
                                        String userPerimits, Boolean outerDept, String outerPermitDepts,
                                        String outerPermitUsers, String orgDeptOwner) throws Exception {
        CorpDepartmentService corpDepartmentService = ServiceFactory.getInstance().getOpenService(CorpDepartmentService.class);
        corpDepartmentService.deptUpdate(accessToken, id, name, parentId, order, createDeptGroup,
                autoAddUser, deptManagerUseridList, deptHiding, deptPerimits, userPerimits,
                outerDept, outerPermitDepts, outerPermitUsers, orgDeptOwner);

    }*/
}
