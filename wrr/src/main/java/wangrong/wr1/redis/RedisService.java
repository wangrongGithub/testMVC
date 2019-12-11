package wangrong.wr1.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import wangrong.wr1.domain.User;

@Service
public class RedisService {
	@Autowired
	JedisPool jedisPool;
	/**
	 * 判断key是否存在
	 * */
	public <T> boolean exists(BasePrefix prefix, String key) {
		 Jedis jedis = null;
		 try {
			 jedis =  jedisPool.getResource();
			//生成真正的key
			 String realKey  = prefix.getRealPredix() + key;
			return  jedis.exists(realKey);
		 }finally {
			  returnToPool(jedis);
		 }
	}
	
	/**
	 * 增加值
	 * */
	public <T> Long incr(BasePrefix prefix, String key) {
		 Jedis jedis = null;
		 try {
			 jedis =  jedisPool.getResource();
			//生成真正的key
			 String realKey  = prefix.getRealPredix() + key;
			return  jedis.incr(realKey);
		 }finally {
			  returnToPool(jedis);
		 }
	}
	
	/**
	 * 减少值
	 * */
	public <T> Long decr(BasePrefix prefix, String key) {
		 Jedis jedis = null;
		 try {
			 jedis =  jedisPool.getResource();
			//生成真正的key
			 String realKey  = prefix.getRealPredix() + key;
			return  jedis.decr(realKey);
		 }finally {
			  returnToPool(jedis);
		 }
	}
	
	public static <T> String BeanToString(T data)
	{
		if(data==null)
		{return null;
			
		}
		Class<?>clazz =data.getClass();
		if(clazz==int.class||clazz==Integer.class||clazz==double.class||clazz==Double.class)
		{
			return  ""+data;
		}
		else if(clazz==String.class)
		{
			return ""+data;
		}
		else 
		{
			return JSON.toJSONString(data);
		}
		
		
	}
	
	public static <T> T StringToBean(String str, Class<T> clazz) {
		System.out.println(str+"********"+clazz);
		if(str == null || str.length() <= 0 || clazz == null) {
			 return null;
		}
		if(clazz == int.class || clazz == Integer.class) {
			 return (T)Integer.valueOf(str);
		}else if(clazz == String.class) {
			 return (T)str;
		}else if(clazz == long.class || clazz == Long.class) {
			return  (T)Long.valueOf(str);
		}else {
			System.out.println(JSON.toJavaObject(JSON.parseObject(str), clazz)+"********fffffff"+clazz);
			
			return JSON.toJavaObject(JSON.parseObject(str), clazz);
		}
	}
	public <T> Boolean set(String key,T data)
	{
		Jedis jedis=null;
		try{
		jedis=jedisPool.getResource();
		String str=BeanToString( data);
		System.out.println("我是service里面的data"+str);
		if(str!=null)
		{
			
		jedis.set(key, str);
		return true;
		}
		else
		{
			return false;
		}
		}
		finally{
			returnToPool(jedis);
		}
		
	}


	public  Boolean delete(BasePrefix  bp,String key) {
		Jedis jedis=null;
		try{
			jedis=jedisPool.getResource();
			long res=jedis.del(bp.getRealPredix()+key);
			return res>0;
		}
		finally{
			returnToPool(jedis);
		}

	}

	
	public <T> Boolean set(BasePrefix  bp,String key,T data)
	{
		Jedis jedis=null;
		try{
		jedis=jedisPool.getResource();
		String str=BeanToString( data);
		System.out.println("我是service里面的data"+str);
		if(str!=null)
		{
			
		jedis.setex(bp.getRealPredix()+""+key,bp.getInvaidSeconds(), str);
		return true;
		}
		else
		{
			return false;
		}
		}
		finally{
			returnToPool(jedis);
		}
		
	}
	
	public <T> T get(BasePrefix  bp,String key,Class<T> clazz)
	{
		Jedis jedis=null;
		try{
		jedis=jedisPool.getResource();
		String str=jedis.get(bp.getRealPredix()+""+key);
		System.out.println("key是"+key+"value是"+str);
		System.out.println("key是"+key+"value是"+StringToBean(str,clazz));
		return StringToBean(str,clazz);
		}
		finally{
			returnToPool(jedis);
		}
		}
	public <T> T get(String key,Class<T> clazz)
	{
		Jedis jedis=null;
		try{
		jedis=jedisPool.getResource();
		String str=jedis.get(key);
		System.out.println("key是"+key+"value是"+str);
		System.out.println("key是"+key+"value是"+StringToBean(str,clazz));
		return StringToBean(str,clazz);
		}
		finally{
			returnToPool(jedis);
		}
		}
	private void returnToPool(Jedis jedis) {
		// TODO Auto-generated method stub
		if(jedis!=null)
		{
			jedis.close();
		}
	}
	@SuppressWarnings("unchecked")
	private static <T> T stringToBean(String str, Class<T> clazz) {
		if(str == null || str.length() <= 0 || clazz == null) {
			 return null;
		}
		if(clazz == int.class || clazz == Integer.class) {
			 return (T)Integer.valueOf(str);
		}else if(clazz == String.class) {
			 return (T)str;
		}else if(clazz == long.class || clazz == Long.class) {
			return  (T)Long.valueOf(str);
		}else {
			return JSON.toJavaObject(JSON.parseObject(str), clazz);
		}
	}
	public static void main(String []args)
	{
		
		String str="{\"id\":1,\"name\":\"wrrr\"}";
		User user=StringToBean(str,User.class);
		System.out.println(user.getId()+"  "+user.getName());
	}




}
