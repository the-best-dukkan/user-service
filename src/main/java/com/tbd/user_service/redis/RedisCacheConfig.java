package com.tbd.user_service.redis;

import com.tbd.common.cache.ProtobufRedisSerializer;
import com.tbd.common.utils.CommonUtil;
import com.tbd.user_service.constant.Constant;
import com.tbd.user_service.proto.TbdAddressPageProto;
import com.tbd.user_service.proto.TbdAddressProto;
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

        // ----- Address by id cache -----
        RedisCacheConfiguration addressByIdCacheConfig =
                RedisCacheConfiguration.defaultCacheConfig()
                        .serializeKeysWith(keySerializer)
                        .serializeValuesWith(
                                RedisSerializationContext.SerializationPair.fromSerializer(
                                        new ProtobufRedisSerializer<>(TbdAddressProto.class)
                                )
                        )
                        .entryTtl(Duration.ofMinutes(60));

        RedisCacheConfiguration addressPageCacheConfig =
                RedisCacheConfiguration.defaultCacheConfig()
                        .serializeKeysWith(keySerializer)
                        .serializeValuesWith(
                                RedisSerializationContext.SerializationPair.fromSerializer(
                                        new ProtobufRedisSerializer<>(TbdAddressPageProto.class)
                                )
                        )
                        .entryTtl(Duration.ofMinutes(30));

        return RedisCacheManager.builder(connectionFactory)
                .withCacheConfiguration(Constant.CACHE_ADDRESS_BY_ID, addressByIdCacheConfig)
                .withCacheConfiguration(Constant.CACHE_ADDRESS_PAGE, addressPageCacheConfig)
                .build();
    }

    @Bean
    public RedisTemplate<String, TbdAddressProto> addressRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        return CommonUtil.createProtoTemplate(redisConnectionFactory, TbdAddressProto.class);
    }
}
