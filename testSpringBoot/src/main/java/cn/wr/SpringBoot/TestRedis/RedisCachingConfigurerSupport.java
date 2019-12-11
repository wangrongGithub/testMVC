package cn.wr.SpringBoot.TestRedis;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
@EnableCaching//开启注解的Redis缓存模式啊
public class RedisCachingConfigurerSupport extends CachingConfigurerSupport
{
    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate)
    {
        RedisCacheManager rdm=new RedisCacheManager(redisTemplate);
        rdm.setDefaultExpiration(432000);
        return rdm;
    }
}
