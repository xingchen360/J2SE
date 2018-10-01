package com.noteshare.designPatterns.businessDelegate;

/**
 * 业务代理类：一个为客户端实体提供的入口类，它提供了对业务服务方法的访问。
 */
public class BusinessDelegate {
	private BusinessLookUp lookupService = new BusinessLookUp();
	private BusinessService businessService;
	private String serviceType;

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public void doTask() {
		// 可以做些代表的其它事情
		businessService = lookupService.getBusinessService(serviceType);
		businessService.doProcessing();
		// 可以做些代表的其它事情
	}
}
