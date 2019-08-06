package com.test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.szboanda.xtglpt.ywxt.wryzxjccb.service.IXcjcService;
import com.test.annotation.ClassA;

/**
 * @Title:TestController.java
 * @Description: 测试类
 * @author:陈海新
 * @date:2019年3月13日 上午11:25:29
 */
public class JunitTest extends BaseJunitTest {

	@Autowired
	private IXcjcService xcjcservice;

	@Test
	@Ignore
	public void test0() {
		System.out.println("第一个测试方法*******");
		List<Map<String, Object>> list = xcjcservice.findTableByCdt("T_ADMIN_RMS_YH", "1=1");
		int i = 0;
		for (Map<String, Object> map : list) {
			i++;
			if (i > 1) {
				break;
			}
			Set<String> keySet = map.keySet();
			for (String key : keySet) {
				System.out.println(key + ":" + map.get(key));
			}
		}
	}
}
