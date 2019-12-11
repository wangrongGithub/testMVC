package wangrong.wr1.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Service
public class RedisPoolFa {
	@Autowired
	 RedisConfig jedisCof;
	
	
	@Bean
	public  JedisPool getJedisPool()
	{
		System.out.println("***********************");
		System.out.println("jhhhhjhh*****"+jedisCof);
		JedisPoolConfig jpc=new JedisPoolConfig();
		System.out.println("jhhhhjhh"+jpc);
		jpc.setMaxIdle(jedisCof.getPoolMaxIdle());
		jpc.setMaxTotal(jedisCof.getPoolMaxTotal());
		jpc.setMaxWaitMillis(jedisCof.getPoolMaxWait());
		JedisPool jp=new JedisPool(jpc,jedisCof.getHost(),jedisCof.getPort(),jedisCof.getTimeout()*1000,jedisCof.getPassword(),0/*database*/);
		
		
		return jp;  
		
	}
}
