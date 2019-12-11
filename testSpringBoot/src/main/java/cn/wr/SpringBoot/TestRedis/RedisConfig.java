package cn.wr.SpringBoot.TestRedis;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.lang.reflect.Method;

@Configuration
public class RedisConfig {
    //用工厂产生一个redis模板啊
    @Bean
    public RedisTemplate<String,String> redisTemplate(RedisConnectionFactory factory)
    {
        StringRedisTemplate template=new StringRedisTemplate(factory);
        System.out.println(factory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return template;
    }

    @Bean
    public KeyGenerator simpleKey()
    {
        return new KeyGenerator() {
            @Override
            public Object generate(Object o, Method method, Object... objects) {
                StringBuilder sb=new StringBuilder();
                sb.append(o.getClass().getName()+":");
                for(Object obj:objects)
                {
                    sb.append(obj.toString());
                }
                return  sb.toString();
            }
        };
    }
}
