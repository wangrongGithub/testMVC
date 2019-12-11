package cn.wr.SpringBoot.TestRedis;

import cn.wr.SpringBoot.TestJPA.Entity.User;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;
@Repository
public class UserRedis
{
    //谷歌的json包啊
    Gson gson=new Gson();
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    public void addUser(String key,Long time,User user)
    {

        String content=gson.toJson(user);
        redisTemplate.opsForValue().set(key,content,time, TimeUnit.SECONDS);
    }
    public User getUser(String key)
    {
        return gson.fromJson(redisTemplate.opsForValue().get(key),User.class);
    }
}
