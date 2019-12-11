package wangrong.wr1.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import wangrong.wr1.domain.User;

@Mapper
public interface UserDao {
	@Select("select *from user where id=#{id}")
	User getUserById(@Param("id") int id);
	@Select("insert into  user(id,name) values(#{id},#{name})")
	Boolean tx(User user);
}
