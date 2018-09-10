package Mongodb;

import Common.LogHelper;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class MongodbUtils {

    private static MongoClient mongoclient = null;
    private static MongoDatabase mongoDataBase = null;

    private static MongoClient getMongoClient() {
        try {
            Properties props = new Properties();
            props.load(MongodbUtils.class.getClassLoader().getResourceAsStream("mongodb.properties"));
            MongoClientOptions.Builder build = new MongoClientOptions.Builder();
            build.connectionsPerHost(Integer.parseInt(props.getProperty("mongodb.pool.connectionsPerHost")));   //与目标数据库能够建立的最大connection数量为50
            build.threadsAllowedToBlockForConnectionMultiplier(Integer.parseInt(props.getProperty("mongodb.pool.threadsAllowedToBlockForConnectionMultiplier"))); //如果当前所有的connection都在使用中，则每个connection上可以有50个线程排队等待
            build.maxWaitTime(Integer.parseInt(props.getProperty("mongodb.pool.maxWaitTime")));
            build.connectTimeout(Integer.parseInt(props.getProperty("mongodb.pool.connectTimeout")));    //与数据库建立连接的timeout设置为1分钟
            MongoClientOptions myOptions = build.build();
            List<MongoCredential> lstCredentials = Arrays.asList(MongoCredential.createCredential(props.getProperty("mongodb.pool.userName"), props.getProperty("mongodb.pool.DBName"),props.getProperty("mongodb.pool.passWord").toCharArray()));
            List<ServerAddress> lstserveraddress=Arrays.asList(new ServerAddress(props.getProperty("mongodb.pool.ip")));
            mongoclient = new MongoClient(lstserveraddress, lstCredentials,myOptions);

        } catch (Exception ex) {
            LogHelper.Error(ex.getMessage(), ex);
        }
        return mongoclient;
    }



}


