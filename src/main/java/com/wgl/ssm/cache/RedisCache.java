package com.wgl.ssm.cache;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wgl.ssm.utils.ConfigUtils;
import com.wgl.ssm.utils.JedisUtils;

public class RedisCache implements Cache {

	private static Logger logger = LoggerFactory.getLogger(RedisCache.class);

    private String cacheId;

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);

    public RedisCache(String cacheId) {
        if (cacheId == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        this.cacheId = ConfigUtils.key + "." + cacheId;
        logger.info("NTSRedisCache cacheId ========== " + cacheId);

        if(ConfigUtils.redisSwitch){
            JedisUtils.getInstance();
        }
    }

    @Override
    public String getId() {
        return cacheId;
    }

    @Override
    public void putObject(Object key, Object value) {
    	//开关采用一个静态变量,每个需要使用redis的地方,都判断这个变量,比较繁琐
        logger.info("NTSRedisCache putObject = " + cacheId);

        if(ConfigUtils.redisSwitch){
            JedisUtils.put(cacheId, key, value);
        }
    }

    @Override
    public Object getObject(Object key) {
        logger.info("NTSRedisCache getObject = " + cacheId);

        if(ConfigUtils.redisSwitch){
            return JedisUtils.get(cacheId, key);
        }else{
            return null;
        }
    }

    @Override
    public Object removeObject(Object key) {
        logger.info("NTSRedisCache removeObject = " + cacheId);

        if(ConfigUtils.redisSwitch){
            return JedisUtils.remove(cacheId, key);
        }else{
            return null;
        }
    }

    @Override
    public void clear() {
        logger.info("NTSRedisCache clear = " + cacheId);

        if(ConfigUtils.redisSwitch){
            JedisUtils.removeAll(cacheId);
        }
    }

    @Override
    public int getSize() {
        logger.info("NTSRedisCache getSize = " + cacheId);

        if(ConfigUtils.redisSwitch){
            return JedisUtils.getSize(cacheId);
        }else{
            return -1;
        }
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return readWriteLock;
    }
	
}
