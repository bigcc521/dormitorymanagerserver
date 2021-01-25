package com.liang.dormitoryserver.config;


import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import com.liang.dormitoryserver.entity.*;
import com.liang.dormitoryserver.util.ListFastJsonRedisSerializer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName RedisConfig
 * @Description TODO
 * @Author Liang Xi
 * @DATE 2021/1/15 10:18
 * @Version 1.0
 */
@Configuration
@EnableCaching
public class RedisConfig {
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory) {
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();  // 生成一个默认配置，通过config对象即可对缓存进行自定义配置
        config = config.entryTtl(Duration.ofMinutes(30))     // 设置缓存的默认过期时间，也是使用Duration设置
                .disableCachingNullValues()
                .serializeKeysWith(//初始化key的序列化机制
                        RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()));
        config.usePrefix();

        // 设置一个初始化的缓存空间set集合
        Set<String> cacheNames =  new HashSet<>();
        cacheNames.add("activity");
        cacheNames.add("activityjoin");
        cacheNames.add("advice");
        cacheNames.add("apartment");
        cacheNames.add("article");
        cacheNames.add("college");
        cacheNames.add("districtlist");
        cacheNames.add("dormitorylist");
        cacheNames.add("publicnotic");
        cacheNames.add("reply");
        cacheNames.add("role");
        cacheNames.add("selfmanage");
        cacheNames.add("sgrade");
        cacheNames.add("sphoto");
        cacheNames.add("student");
        cacheNames.add("teacher");
        cacheNames.add("teacherdistrict");
        cacheNames.add("tgrade");
        cacheNames.add("topic");
        cacheNames.add("tphoto");
        cacheNames.add("usercode");

        // 对每个缓存空间应用不同的配置
        Map<String, RedisCacheConfiguration> configMap = new HashMap<>();
        configMap.put("activity", config
                .serializeValuesWith(//初始化value的序列化机制，用department对象的Jackson2JsonRedisSerializer
                        RedisSerializationContext.
                                SerializationPair.
                                fromSerializer(new FastJsonRedisSerializer<Activity>(Activity.class))
                ));
        configMap.put("activityjoin", config
                .serializeValuesWith(//初始化value的序列化机制，用employee对象的Jackson2JsonRedisSerializer
                        RedisSerializationContext.
                                SerializationPair.
                                fromSerializer(new FastJsonRedisSerializer<Activityjoin>(Activityjoin.class))
                )
        );
        configMap.put("advice", config
                .serializeValuesWith(//初始化value的序列化机制，用employee对象的Jackson2JsonRedisSerializer
                        RedisSerializationContext.
                                SerializationPair.
                                fromSerializer(new FastJsonRedisSerializer<Advice>(Advice.class))
                )
        );
        configMap.put("apartment", config
                .serializeValuesWith(//初始化value的序列化机制，用employee对象的Jackson2JsonRedisSerializer
                        RedisSerializationContext.
                                SerializationPair.
                                fromSerializer(new FastJsonRedisSerializer<Apartment>(Apartment.class))
                )
        );
        configMap.put("article", config
                .serializeValuesWith(//初始化value的序列化机制，用employee对象的Jackson2JsonRedisSerializer
                        RedisSerializationContext.
                                SerializationPair.
                                fromSerializer(new FastJsonRedisSerializer<Article>(Article.class))
                )
        );
        configMap.put("college", config
                .serializeValuesWith(//初始化value的序列化机制，用employee对象的Jackson2JsonRedisSerializer
                        RedisSerializationContext.
                                SerializationPair.
                                fromSerializer(new FastJsonRedisSerializer<College>(College.class))
                )
        );
        configMap.put("districtlist", config
                .serializeValuesWith(//初始化value的序列化机制，用employee对象的Jackson2JsonRedisSerializer
                        RedisSerializationContext.
                                SerializationPair.
                                fromSerializer(new ListFastJsonRedisSerializer<District>(District.class))
                )
        );
        configMap.put("dormitorylist", config
                .serializeValuesWith(//初始化value的序列化机制，用employee对象的Jackson2JsonRedisSerializer
                        RedisSerializationContext.
                                SerializationPair.
                                fromSerializer(new ListFastJsonRedisSerializer<Dormitory>(Dormitory.class))
                )
        );
        configMap.put("publicnotic", config
                .serializeValuesWith(//初始化value的序列化机制，用employee对象的Jackson2JsonRedisSerializer
                        RedisSerializationContext.
                                SerializationPair.
                                fromSerializer(new FastJsonRedisSerializer<Publicnotic>(Publicnotic.class))
                )
        );
        configMap.put("reply", config
                .serializeValuesWith(//初始化value的序列化机制，用employee对象的Jackson2JsonRedisSerializer
                        RedisSerializationContext.
                                SerializationPair.
                                fromSerializer(new FastJsonRedisSerializer<Reply>(Reply.class))
                )
        );
        configMap.put("role", config
                .serializeValuesWith(//初始化value的序列化机制，用employee对象的Jackson2JsonRedisSerializer
                        RedisSerializationContext.
                                SerializationPair.
                                fromSerializer(new FastJsonRedisSerializer<Role>(Role.class))
                )
        );
        configMap.put("selfmanage", config
                .serializeValuesWith(//初始化value的序列化机制，用employee对象的Jackson2JsonRedisSerializer
                        RedisSerializationContext.
                                SerializationPair.
                                fromSerializer(new FastJsonRedisSerializer<Selfmanage>(Selfmanage.class))
                )
        );

        configMap.put("sgrade", config
                .serializeValuesWith(//初始化value的序列化机制，用employee对象的Jackson2JsonRedisSerializer
                        RedisSerializationContext.
                                SerializationPair.
                                fromSerializer(new FastJsonRedisSerializer<Sgrade>(Sgrade.class))
                )
        );
        configMap.put("sphoto", config
                .serializeValuesWith(//初始化value的序列化机制，用employee对象的Jackson2JsonRedisSerializer
                        RedisSerializationContext.
                                SerializationPair.
                                fromSerializer(new FastJsonRedisSerializer<Sphoto>(Sphoto.class))
                )
        );
        configMap.put("student", config
                .serializeValuesWith(//初始化value的序列化机制，用employee对象的Jackson2JsonRedisSerializer
                        RedisSerializationContext.
                                SerializationPair.
                                fromSerializer(new FastJsonRedisSerializer<Student>(Student.class))
                )
        );
        configMap.put("teacher", config
                .serializeValuesWith(//初始化value的序列化机制，用employee对象的Jackson2JsonRedisSerializer
                        RedisSerializationContext.
                                SerializationPair.
                                fromSerializer(new FastJsonRedisSerializer<Teacher>(Teacher.class))
                )
        );
        configMap.put("teacherdistrict", config
                .serializeValuesWith(//初始化value的序列化机制，用employee对象的Jackson2JsonRedisSerializer
                        RedisSerializationContext.
                                SerializationPair.
                                fromSerializer(new FastJsonRedisSerializer<Teacherdistrict>(Teacherdistrict.class))
                )
        );
        configMap.put("tgrade", config
                .serializeValuesWith(//初始化value的序列化机制，用employee对象的Jackson2JsonRedisSerializer
                        RedisSerializationContext.
                                SerializationPair.
                                fromSerializer(new FastJsonRedisSerializer<Tgrade>(Tgrade.class))
                )
        );
        configMap.put("topic", config
                .serializeValuesWith(//初始化value的序列化机制，用employee对象的Jackson2JsonRedisSerializer
                        RedisSerializationContext.
                                SerializationPair.
                                fromSerializer(new FastJsonRedisSerializer<Topic>(Topic.class))
                )
        );
        configMap.put("tphoto", config
                .serializeValuesWith(//初始化value的序列化机制，用employee对象的Jackson2JsonRedisSerializer
                        RedisSerializationContext.
                                SerializationPair.
                                fromSerializer(new FastJsonRedisSerializer<Tphoto>(Tphoto.class))
                )
        );
        configMap.put("usercode", config
                .serializeValuesWith(//初始化value的序列化机制，用employee对象的Jackson2JsonRedisSerializer
                        RedisSerializationContext.
                                SerializationPair.
                                fromSerializer(new FastJsonRedisSerializer<Usercode>(Usercode.class))
                )
        );
        RedisCacheManager cacheManager = RedisCacheManager.builder(factory)     // 使用自定义的缓存配置初始化一个cacheManager
                .initialCacheNames(cacheNames)  // 注意这两句的调用顺序，一定要先调用该方法设置初始化的缓存名，再初始化相关的配置
                .withInitialCacheConfigurations(configMap)
                .build();
        return cacheManager;

    }
}
