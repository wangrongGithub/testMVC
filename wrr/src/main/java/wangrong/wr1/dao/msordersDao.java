package wangrong.wr1.dao;

import java.sql.Date;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import wangrong.wr1.domain.ShoppingUser;
import wangrong.wr1.domain.User;

@Mapper
public interface msordersDao {
	@Select("select *from shoppinguser where id=#{id}")
	ShoppingUser getUserById(@Param("id") long id);
	
	
	/*
	 * int id;
	String nickname;
	String password;
	String salt;
	Date register_date;
	Date last_login_date;
	int login_count;
	 * */
	@Insert("insert into  shoppinguser(id,nickname,password,salt,register_date,last_login_date,login_count) "
			+ "values(#{id},#{nickname},#{password},#{salt},#{register_date},#{last_login_date},#{login_count})")
	Boolean tx(ShoppingUser user);

}
