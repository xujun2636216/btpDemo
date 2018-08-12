package Redis;

import Common.FastJsonUtil;
import Common.LogHelper;
import redis.clients.jedis.Jedis;

import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RedisHelper {

    /**
     * 数据库索引
     *
     * @param key
     * @param value
     * @return
     */
    public static Boolean setString(int index, String key, String value) {

        Boolean flag = false;
        Jedis jedis = RedisClient.getRedis(index);
        try {
            jedis.set(key, value);
            flag = true;
        } catch (Exception ex) {
            LogHelper.Error(ex.getMessage(), ex);
        } finally {
            jedis.close();
        }
        return flag;
    }

    /**
     * 数据库索引
     *
     * @param key
     * @param value 设置过期时间
     * @return
     */
    public static Boolean setString(int index, String key, String value, int seconds) {

        Boolean flag = false;
        Jedis jedis = RedisClient.getRedis(index);
        try {
            jedis.set(key, value);
            jedis.expire(key, seconds);
            flag = true;
        } catch (Exception ex) {
            LogHelper.Error(ex.getMessage(), ex);
        } finally {
            jedis.close();
        }
        return flag;
    }

    /**
     * 数据库索引
     *
     * @param key 获取字符串的值
     */
    public static String getString(int index, String key) {
        String str = null;
        Jedis jedis = RedisClient.getRedis(index);
        try {
            str = jedis.get(key);
        } catch (Exception ex) {
            LogHelper.Error(ex.getMessage(), ex);
        } finally {
            jedis.close();
        }
        return str;
    }

    /**
     * 数据库索引
     *
     * @param key 判断是否存在
     */
    public static Boolean isExists(int index, String key) {
        Boolean flag = false;
        Jedis jedis = RedisClient.getRedis(index);
        try {
            flag = jedis.exists(key);
        } catch (Exception ex) {
            LogHelper.Error(ex.getMessage(), ex);
        } finally {
            jedis.close();
        }
        return flag;
    }

    /**
     * 数据库索引
     *
     * @param key 删除一个值
     */
    public static Boolean Del(int index, String key) {
        Boolean flag = false;
        Jedis jedis = RedisClient.getRedis(index);
        try {
            Long num = jedis.del(key);
            if (num > 0) {
                flag = true;
            }

        } catch (Exception ex) {
            LogHelper.Error(ex.getMessage(), ex);
        } finally {
            jedis.close();
        }
        return flag;
    }

    /**
     * 数据库索引
     *
     * @param keys 删除多个值
     */
    public static Boolean Dels(int index, String[] keys) {
        Boolean flag = false;
        Jedis jedis = RedisClient.getRedis(index);
        try {
            Long nums = jedis.del(keys);
            if (nums > 0) {
                flag = true;
            }

        } catch (Exception ex) {
            LogHelper.Error(ex.getMessage(), ex);
        } finally {
            jedis.close();
        }
        return flag;
    }

    /**
     * 数据库DBIndex
     *
     * @param HashId
     * @param key
     * @param value
     * @return
     */
    public static Boolean setHash(int index, String HashId, String key, String value) {

        Boolean flag = false;
        Jedis jedis = RedisClient.getRedis(index);
        try {
            jedis.hset(HashId, key, value);
            flag = true;
        } catch (Exception ex) {
            LogHelper.Error(ex.getMessage(), ex);
        } finally {
            jedis.close();
        }
        return flag;
    }


    /**
     * 数据库DBIndex
     *
     * @param HashId
     * @param key    判断hash是否存在
     */
    public static Boolean isExistsHash(int index, String HashId, String key) {

        Boolean flag = false;
        Jedis jedis = RedisClient.getRedis(index);
        try {
            flag = jedis.hexists(HashId, key);
        } catch (Exception ex) {
            LogHelper.Error(ex.getMessage(), ex);
        } finally {
            jedis.close();
        }
        return flag;
    }

    /**
     * @param index
     * @param HashId
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getHash(int index, String HashId, String key, Class<T> clazz) {

        Boolean flag = false;
        T model = null;
        Jedis jedis = RedisClient.getRedis(index);
        try {
            String value = jedis.hget(HashId, key);
            model = FastJsonUtil.toBean(value, clazz);
        } catch (Exception ex) {
            LogHelper.Error(ex.getMessage(), ex);
        } finally {
            jedis.close();
        }
        return model;
    }

    /**
     * @param index
     * @param HashId
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> getHash(int index, String HashId, Class<T> clazz) {

        List<T> objlist = new ArrayList<T>();
        Jedis jedis = RedisClient.getRedis(index);
        try {
            Map<String, String> objmap = jedis.hgetAll(HashId);
            if (!objmap.isEmpty()) {
                for (Map.Entry<String, String> entry : objmap.entrySet()) {
                    T model = FastJsonUtil.toBean(entry.getValue(), clazz);
                    objlist.add(model);
                }
            }
        } catch (Exception ex) {
            LogHelper.Error(ex.getMessage(), ex);
        } finally {
            jedis.close();
        }
        return objlist;
    }

    /**
     * @param index
     * @param HashId
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> gethvals(int index, String HashId, Class<T> clazz) {

        List<T> objlist = new ArrayList<T>();
        Jedis jedis = RedisClient.getRedis(index);
        try {
            List<String> objString = jedis.hvals(HashId);
            if (objString.size() > 0) {
                for (String item : objString) {
                    T model = FastJsonUtil.toBean(item, clazz);
                    objlist.add(model);
                }
            }
        } catch (Exception ex) {
            LogHelper.Error(ex.getMessage(), ex);
        } finally {
            jedis.close();
        }
        return objlist;
    }

    /**
     * @param index
     * @param HashId
     * @return
     */
    public static Map<String, String> getHash(int index, String HashId) {

        Map<String, String> maplist = new HashMap<String, String>();
        Jedis jedis = RedisClient.getRedis(index);
        try {
            maplist = jedis.hgetAll(HashId);

        } catch (Exception ex) {
            LogHelper.Error(ex.getMessage(), ex);
        } finally {
            jedis.close();
        }
        return maplist;
    }


    /**
     * 数据库DBIndex
     *
     * @param HashId
     * @param key
     * @return
     */
    public static Boolean delHash(int index, String HashId, String key) {

        Boolean flag = false;
        Jedis jedis = RedisClient.getRedis(index);
        try {
            Long num = jedis.hdel(HashId, key);
            if (num > 0) {
                flag = true;
            }
        } catch (Exception ex) {
            LogHelper.Error(ex.getMessage(), ex);
        } finally {
            jedis.close();
        }
        return flag;
    }

    /**
     * 数据库DBIndex
     *
     * @param HashId
     * @param key
     * @return
     */
    public static Boolean delHash(int index, String HashId, String[] key) {

        Boolean flag = false;
        Jedis jedis = RedisClient.getRedis(index);
        try {
            Long num = jedis.hdel(HashId, key);
            if (num > 0) {
                flag = true;
            }
        } catch (Exception ex) {
            LogHelper.Error(ex.getMessage(), ex);
        } finally {
            jedis.close();
        }
        return flag;
    }

}
