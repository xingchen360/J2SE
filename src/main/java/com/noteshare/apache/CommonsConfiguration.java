package com.noteshare.apache;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Iterator;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class CommonsConfiguration {
    
    public void configuration() throws ConfigurationException, UnsupportedEncodingException {
    	String url = getClass().getClassLoader().getResource("info.properties").getPath();
        System.out.println(url);
        url = URLDecoder.decode(url,"UTF-8");
        PropertiesConfiguration config = new PropertiesConfiguration(url);
        config.setProperty("age", "22");
        config.save();

        @SuppressWarnings("boxing")
        Integer age = config.getInt("age");
        String username = config.getString("username");
        System.out.println(age);
        System.out.println(username);
        
        for(Iterator<String> it = config.getKeys();it.hasNext();){
            String key = (String)it.next();
            String value = config.getString(key);
            System.out.println(key +"="+value);
        }
    }

    public static void main(String[] args) throws ConfigurationException, UnsupportedEncodingException {
        CommonsConfiguration config = new CommonsConfiguration();
        config.configuration();
    }
}
