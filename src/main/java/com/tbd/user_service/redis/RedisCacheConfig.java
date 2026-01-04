package com.tbd.user_service.redis;

import com.tbd.common.utils.RedisUtil;
import com.tbd.proto.user_service.TbdAddressPageProto;
import com.tbd.proto.user_service.TbdAddressProto;
import com.tbd.proto.user_service.TbdUserProto;
import com.tbd.user_service.constant.Constant;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
@EnableCaching
public class RedisCacheConfig {

    @Bean
    public RedisCacheManager redisCacheManager(RedisConnectionFactory connectionFactory) {

        RedisSerializationContext.SerializationPair<String> keySerializer =
                RedisSerializationContext.SerializationPair.fromSerializer(
                        new StringRedisSerializer()
                );

        RedisCacheConfiguration userDetailBySubCacheConfig = RedisUtil.createProtoCacheConfig(TbdUserProto.class, Duration.ofMinutes(15), keySerializer);
        RedisCacheConfiguration addressByIdCacheConfig = RedisUtil.createProtoCacheConfig(TbdAddressProto.class, Duration.ofMinutes(60), keySerializer);
        RedisCacheConfiguration addressPageCacheConfig = RedisUtil.createProtoCacheConfig(TbdAddressPageProto.class, Duration.ofMinutes(30), keySerializer);

        return RedisCacheManager.builder(connectionFactory)
                .withCacheConfiguration(Constant.CACHE_USER_BY_SUB, userDetailBySubCacheConfig)
                .withCacheConfiguration(Constant.CACHE_ADDRESS_BY_ID, addressByIdCacheConfig)
                .withCacheConfiguration(Constant.CACHE_ADDRESS_PAGE, addressPageCacheConfig)
                .build();
    }

    // THIS BEAN IS NOT USED CURRENTLY, IT's FOR LEARNING PURPOSE
    @Bean
    public RedisTemplate<String, TbdAddressProto> addressRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        return RedisUtil.createProtoTemplate(redisConnectionFactory, TbdAddressProto.class);
    }
}
