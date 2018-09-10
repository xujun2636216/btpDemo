package Redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Properties;

public class RedisClient {
    private static JedisPool pool = null;
    private static String IP_ADDRESS = null;
    private  static  Jedis jedis=null;



    public static void initRedisCOnfig() {
        try {
            Properties props = new Properties();
            props.load(RedisClient.class.getClassLoader().getResourceAsStream("redis.properties"));
            IP_ADDRESS = props.getProperty("redis.ip");
            // 创建jedis池配置实例
            JedisPoolConfig config = new JedisPoolConfig();

            // 设置池配置项值
            config.setTestWhileIdle(false);
            config.setMaxTotal(Integer.valueOf(props.getProperty("redis.pool.maxTotal")));
            config.setMaxIdle(Integer.valueOf(props.getProperty("redis.pool.maxIdle")));
            config.setMaxWaitMillis(Long.valueOf(props.getProperty("redis.pool.maxWaitMillis")));
            config.setTestOnBorrow(Boolean.valueOf(props.getProperty("redis.pool.testOnBorrow")));
            config.setTestOnReturn(Boolean.valueOf(props.getProperty("redis.pool.testOnReturn")));
            pool = new JedisPool(config, IP_ADDRESS, Integer.valueOf(props.getProperty("redis.port")));

        } catch (Exception e) {
            throw new Error("IP:" + IP_ADDRESS + ",设置redis服务器出错", e);
        }
    }

    public static boolean isConnected() {
        return getRedis().isConnected();
    }


    //关闭
    public static void close() {
        jedis.close();
    }

    public static Jedis getRedis() {
        if (jedis==null) {
            initRedisCOnfig();
        }
        jedis = pool.getResource();
        jedis.select(0);
        return jedis;
    }

    public static Jedis getRedis(int index) {
        if (jedis==null) {
            initRedisCOnfig();
        }
        Jedis jedis = pool.getResource();
        jedis.select(index);
        return jedis;
    }


}
