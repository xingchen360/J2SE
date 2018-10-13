
package com.noteshare.mongo.gridfs.pool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientOptions.Builder;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import com.noteshare.mongo.gridfs.exceptions.MongoDBException;

/**
 * @Title: mongo连接管理
 * @author itnoteshare
 * @since JDK1.6
 * @history 2017年4月1日 itnoteshare 新建
 */
public final class MongoManager {

    /**
     * 数据库名称
     */
    private static final String DATABASE_NAME = "dataBaseName";

    /**
     * 用户名
     */
    private static final String USER_NAME = "userName";
    /**
     * 密码
     */
    private static final String PASSWORD = "password";
    /**
     * 主机地址
     */
    private static final String HOST_NAME = "hostName";
    /**
     * 端口
     */
    private static final String PORT = "port";
    /**
     * 连接池数量
     */
    private static final String CONNECTIONS_PERHOST = "connectionsPerHost";
    /**
     * 连接超时时间，毫秒单位
     */
    private static final String CONNECT_TIMEOUT = "connectTimeout";
    /**
     * 等待时间,毫秒单位
     */
    private static final String MAX_WAIT_TIME = "maxWaitTime";
    /**
     * socket时间,毫秒单位
     */
    private static final String SOCKET_TIMEOUT = "socketTimeout";

    /**
     * 配置文件路径
     */
    private static final String PATH = "/conf/mongo.properties";

    /**
     * 单例模式
     */
    private static final MongoManager INSTANCE = new MongoManager();

    /**
     * mongo客户端
     */
    private MongoClient mongoClient;

    /**
     * 数据库名称
     */
    private final MongoDatabase dataBase;

    /**
     * 获取实例
     * 
     * @return
     */
    public static MongoManager getInstance() {
        return INSTANCE;
    }

    /**
     * 获取数据库实例对象
     * 
     * @return
     */
    public MongoDatabase getMongoDatabase() {
        return dataBase;
    }

    @SuppressWarnings("deprecation")
	private MongoManager() {
        Properties properties = new Properties();
        try {
            properties.load(MongoManager.class.getResourceAsStream(PATH));
        } catch (IOException e) {
            throw new MongoDBException("加载" + PATH + "失败", e);
        }

        // 主机地址、端口、数据库名称、用户、密码
        String hostName = properties.getProperty(HOST_NAME);
        String userName = properties.getProperty(USER_NAME);
        String password = properties.getProperty(PASSWORD);

        MongoClientOptions options = this.getOptions(properties);
        // 安全验证
        List<MongoCredential> credentials = new ArrayList<MongoCredential>();
        if (this.isNotEmpty(userName) && this.isNotEmpty(password)) {
            MongoCredential credential = MongoCredential.createCredential(userName,
                    properties.getProperty(DATABASE_NAME), password.toCharArray());
            credentials.add(credential);
        }
        if (hostName.indexOf(',') == -1) {
            // 非replica副本集
            ServerAddress address = new ServerAddress(hostName, Integer.parseInt(properties.getProperty(PORT)));
            mongoClient = new MongoClient(address, credentials, options);
        } else { // replica副本集
            List<ServerAddress> hosts = this.getHosts(properties);
            mongoClient = new MongoClient(hosts, credentials, options);
        }
        dataBase = mongoClient.getDatabase(properties.getProperty(DATABASE_NAME));

    }

    /**
     * 获取配置对象
     * 
     * @param properties
     * @return
     */
    private MongoClientOptions getOptions(Properties properties) {
        // 链接池数量、连接超时时间、最大等待时间、scoket超时时间
        String connectionsPerHost = properties.getProperty(CONNECTIONS_PERHOST);
        String connectTimeout = properties.getProperty(CONNECT_TIMEOUT);
        String maxWaitTime = properties.getProperty(MAX_WAIT_TIME);

        Builder builder = MongoClientOptions.builder();
        if (this.isNotEmpty(connectionsPerHost)) {
            builder.connectionsPerHost(Integer.parseInt(connectionsPerHost));
        }
        if (this.isNotEmpty(connectTimeout)) {
            builder.connectTimeout(Integer.parseInt(connectTimeout));
        }
        if (this.isNotEmpty(maxWaitTime)) {
            builder.maxWaitTime(Integer.parseInt(maxWaitTime));
        }
        
        String socketTimeout = properties.getProperty(SOCKET_TIMEOUT);
        if (this.isNotEmpty(socketTimeout)) {
            builder.socketTimeout(Integer.parseInt(socketTimeout));
        }
        return builder.build();
    }

    /**
     * 副本集连接
     * 
     * @param hostName
     * @param port
     */
    private List<ServerAddress> getHosts(Properties properties) {
        String[] hostNames = properties.getProperty(HOST_NAME).split(",");
        String[] ports = properties.getProperty(PORT).split(",");
        // 所有主机
        List<ServerAddress> hosts = new ArrayList<ServerAddress>(hostNames.length);
        for (int i = 0; i < hostNames.length; i++) {
            if (this.isNotEmpty(hostNames[i])) {
                if (ports.length > i) {
                    hosts.add(new ServerAddress(hostNames[i], Integer.parseInt(ports[i])));
                } else {
                    hosts.add(new ServerAddress(hostNames[i], Integer.parseInt(ports[ports.length - 1])));
                }
            }
        }
        return hosts;
    }

    /**
     * 是否不为为空
     * 
     * @param str
     * @return
     */
    private boolean isNotEmpty(Object str) {
        return !(str == null || "".equals(str));
    }

}