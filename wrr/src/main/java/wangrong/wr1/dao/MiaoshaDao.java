package wangrong.wr1.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectKey;

import wangrong.wr1.domain.msorder;
import wangrong.wr1.domain.order;

@Mapper
public interface MiaoshaDao {
/*
 * long o_id;   
	String g_name; 
	long g_id; 
	long u_id;   
       String o_address;  
       double g_price; 
        long  g_count;  
         int o_status;   
          Date o_createdate;  
          Date  o_paydate;    
           int   o_channel;
 * 
 * */
/*
*     <selectKey keyProperty="id" order="AFTER" resultType="java.lang.Integer">
* */

	@Insert("insert into  `order`(g_name,g_id,u_id,o_address, g_price,g_count,o_status,o_createdate,o_paydate,o_channel) "
			+ "values(#{g_name},#{g_id},#{u_id},#{o_address},#{g_price},#{g_count},#{o_status},#{o_createdate},#{o_paydate},#{o_channel})")
	@SelectKey(keyProperty="o_id",resultType=long.class,before=false,statement="select max(o_id) o_id from  `order`")

	public long createOrder(order o);
	/* long g_id;
	 long u_id;
	  
	 long  o_id;
	 * */
	@Insert("insert into  msorder(g_id,u_id,o_id) "
			+ "values(#{g_id},#{u_id},#{o_id})")
	@SelectKey(keyProperty="mso_id",resultType=long.class,before=false,statement="select max(mso_id)  from  `msorder`")

	public long MiaoShaGoods(msorder mo) ;
	

}
