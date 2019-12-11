package cn.wr.SpringBoot.TestRedis;

import cn.wr.SpringBoot.TestJPA.Entity.User;
import cn.wr.SpringBoot.TestJPA.MyJpaRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;

@Service
public class UserAnnRedis {
    @Autowired
    UserRepository userRepository;
    static int i=0;

    //存取缓存的注解;keyGenerator是key的生成规则;value参数是key的前缀
    @Cacheable(value="mysql:findById:user",keyGenerator = "simpleKey")
    public User findById(Integer id)
    {
        System.out.println("findById:  "+i++);
        return userRepository.findOne(id);
    }

}
