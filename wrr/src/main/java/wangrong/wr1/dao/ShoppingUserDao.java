package wangrong.wr1.dao;

import java.sql.Date;

import org.apache.ibatis.annotations.*;

import wangrong.wr1.domain.ShoppingUser;
import wangrong.wr1.domain.User;

@Mapper
public interface ShoppingUserDao {
	@Select("select *from shoppinguser where id=#{id}")
	ShoppingUser getUserById(@Param("id") long id);
	@Update("update  shoppinguser set password=#{password} where id=#{id}")
	boolean updatePassword(ShoppingUser user);
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
