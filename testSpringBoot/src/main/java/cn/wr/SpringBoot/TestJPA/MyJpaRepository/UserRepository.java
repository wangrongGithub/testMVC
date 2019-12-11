package cn.wr.SpringBoot.TestJPA.MyJpaRepository;

import cn.wr.SpringBoot.TestJPA.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
/*
* JpaRepository这个类有很多方法的啊，@repository是用来注解接口,如下图:这个注解是将接口BookMapper的一个实现类(具体这个实现类的name叫什么,
* 还需要再分析源码找找看)交给spring管理(在spring中有开启对@repository注解的扫描),
* 当哪些地方需要用到这个实现类作为依赖时,就可以注入了.当然我们也可以主动给这个实现类命名,如下图.
*
*
*
* */
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    @Query("select t from User t where t.name =?1 and t.email =?2")
    User findByNameAndEmail(String name, String email);

    @Query("select t from User t where t.name like :name")
    Page<User> findByName(@Param("name") String name, Pageable pageRequest);
    //这个会给重写的吗；应该会有AOP来进行处理的啊
    User findByName(String name);

}
