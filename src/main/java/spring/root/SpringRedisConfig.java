package spring.root;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;


@Configuration
//@EnableCaching //启动Redis缓存机制
//@ComponentScan("com.wgc.instance.aop")
public class SpringRedisConfig {


    /*Redis*/
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    /*redis模板*/


    /*   @Bean
       public RedisTemplate redisTemplate() {
           return new StringRedisTemplate(redisConnectionFactory());
       }
   */
    /*RedisCacheManager配置后就不用配置RedisTemplate了*/
    @Bean
    public RedisCacheManager redisCacheManager() {
        RedisCacheConfiguration configuration = RedisCacheConfiguration
                .defaultCacheConfig()
                .computePrefixWith(cacheName -> cacheName)
                //头部使用字符串序列化
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.string()))
                //有四种方法序列化、默认jdk的序列化
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.json()));

        return RedisCacheManager.builder(redisConnectionFactory()).cacheDefaults(configuration).build();

    }
}
