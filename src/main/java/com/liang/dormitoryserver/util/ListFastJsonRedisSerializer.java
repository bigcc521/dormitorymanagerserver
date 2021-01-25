package com.liang.dormitoryserver.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.liang.dormitoryserver.entity.District;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @ClassName DistrictFastJsonRedisSerializer
 * @Description TODO
 * @Author Liang Xi
 * @DATE 2021/1/19 19:15
 * @Version 1.0
 */
public class ListFastJsonRedisSerializer<T> implements RedisSerializer<List<T>> {
    private FastJsonConfig fastJsonConfig = new FastJsonConfig();
    private Class<T> type;

    public ListFastJsonRedisSerializer(Class<T> type) {
        this.type = type;
    }

    public FastJsonConfig getFastJsonConfig() {
        return fastJsonConfig;
    }

    public void setFastJsonConfig(FastJsonConfig fastJsonConfig) {
        this.fastJsonConfig = fastJsonConfig;
    }

    @Override
    public byte[] serialize(List<T> ts) throws SerializationException {
        if (ts == null) {
            return new byte[0];
        }
        try {
            return JSON.toJSONBytes(ts, fastJsonConfig.getSerializeConfig(), fastJsonConfig.getSerializerFeatures());
        } catch (Exception ex) {
            throw new SerializationException("Could not serialize: " + ex.getMessage(), ex);
        }
    }

    @Override
    public List<T> deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        try {
            return JSON.parseArray(new String(bytes),type);
        } catch (Exception ex) {
            throw new SerializationException("Could not deserialize: " + ex.getMessage(), ex);
        }
    }
}
