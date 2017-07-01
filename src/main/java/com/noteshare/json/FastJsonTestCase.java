package com.noteshare.json;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.PropertyFilter;

public class FastJsonTestCase {
	/**
     * 简单序列化
     * 	QuoteFieldNames———-输出key时是否使用双引号,默认为true 
     * 	WriteMapNullValue——–是否输出值为null的字段,默认为false 
     * 	WriteNullNumberAsZero—-数值字段如果为null,输出为0,而非null 
     * 	WriteNullListAsEmpty—–List字段如果为null,输出为[],而非null 
     * 	WriteNullStringAsEmpty—字符类型字段如果为null,输出为"",而非null 
     * 	WriteNullBooleanAsFalse–Boolean字段如果为null,输出为false,而非null
     */
	@Test
    public void simpleTest() {
        User user = new User("admin",null,20);
        user.setBirthday(new Date());
        List<Plot> list = Arrays.asList(new Plot("diudiu"),new Plot("dudu") );
        user.setList(list);
        
        String jsonStr = JSON.toJSONString(user, new PropertyFilter(){
  
            @Override
            public boolean apply(Object object, String name, Object value) {
                if(name.equalsIgnoreCase("username")){
                    //false表示username字段将被排除在外 
                    return false;
                }
                return true;
            }
              
        });
        System.out.println(jsonStr);
    }
	
	/**
     * 复杂序列化
     */
	@Test
    public void complexTest() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("username", "owen");
        map.put("age", 25);
        map.put("sex", "男");

        Map<String, Object> temp = new HashMap<String, Object>();
        temp.put("name", "jack");
        temp.put("age", 18);
        
        map.put("girinfo", temp);

        List<String> list = new ArrayList<String>();
        list.add("爬山");
        list.add("电影");
        list.add("旅游");
        
        map.put("hobby", list);

        String jsonStr = JSON.toJSONString(map);
        System.out.println(jsonStr);
    }
	
	/**
     * 简单反序列化
     */
	@Test
    public void simpleDeserializeTest() {
        String jsonStr = "{\"username\":\"owen\",\"password\":\"passw0rd\", \"age\":24}";
        User user = JSON.parseObject(jsonStr, User.class);
        System.out.println(user);
    }
	
	/**
	 * 数组反序列化
	 */
	@Test
    public void arrayDeserializeTest() {
        String jsonStr = "[{\"username\":\"owen\",\"password\":\"passw0rd\", \"age\":24},{\"username\":\"owen\",\"password\":\"passw0rd\", \"age\":24}]";
        List<User> list = JSON.parseArray(jsonStr, User.class);
        System.out.println(list);
    }
	
	/**
     * 泛型反序列化
     */
	@Test
    public void genericTypeDeserializeTest() {
        String jsonStr = "{\"user\":{\"username\":\"owen\",\"password\":\"passw0rd\", \"age\":24}}";
        Map<String, User> map = JSON.parseObject(jsonStr, new TypeReference<Map<String, User>>(){});
        System.out.println(map.get("user")+"\n");

        String jsonStr2 = "{\"user\":[{\"username\":\"owen\", \"age\":24}, {\"username\":\"jack\", \"age\":18}]}";
        Map<String, List<User>> users = JSON.parseObject(jsonStr2, new TypeReference<Map<String, List<User>>>(){});
        for (User user : users.get("user")) {
        	System.out.println(user);
            System.out.println("------------------");
        }
        
        System.out.println("\n");
        
        String jsonStr3 = "[{\"username\":\"owen\", \"age\":24}, {\"username\":\"jack\", \"age\":18}]";
        List<User> list = JSON.parseObject(jsonStr3, new TypeReference<List<User>>(){});
        for (User user : list) {
        	System.out.println(user);
            System.out.println("------------------");
        }
    }
	
	static class User {
		/*@JSONField(serialize = false)*/
		private String username;
		
		private String password;
		
		private int age;
		
		private List<Plot> list;
		
		@JSONField (format="yyyy-MM-dd HH:mm:ss") 
		private Date birthday;
		
		private Map<String,List<String>> map;
		
		public User() {
			super();
		}
		
		public User(String username, String password, int age) {
			super();
			this.username = username;
			this.password = password;
			this.age = age;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public List<Plot> getList() {
			return list;
		}
		public void setList(List<Plot> list) {
			this.list = list;
		}
		public Map<String, List<String>> getMap() {
			return map;
		}
		public void setMap(Map<String, List<String>> map) {
			this.map = map;
		}
		
		public Date getBirthday() {
			return birthday;
		}

		public void setBirthday(Date birthday) {
			this.birthday = birthday;
		}

		public String toString() {  
	    	return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);   
	    } 
	}
	
	static class Plot{
		
		public Plot() {
			super();
		}
		public Plot(String name){
			this.name = name;
		}
		private String name;

		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
	}
}
