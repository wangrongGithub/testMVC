package wangrong.wr1.dao;

import java.sql.Date;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import wangrong.wr1.domain.*;

@Mapper
public interface orderDao {
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

	@Select("select *from msorder where g_id=#{gid} and u_id=#{uid} ")
	msorder getOrderByUidOid(@Param("uid") long uid, @Param("gid") long gid);

    @Update("update wrgoods set g_stock=g_stock-1 where g_id=#{g_id}")
	void updateStock(goods g);

    @Update("update ms_goods set msg_stock=msg_stock-1 where g_id=#{g_id}")
	void updateMsStock(goods g);
	@Select("select *from `order` where o_id=#{oid} ")
	order getOrderByOid(long oid);
}
