package wangrong.wr1.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import wangrong.wr1.domain.ShoppingUser;
import wangrong.wr1.domain.goods;
import wangrong.wr1.domain.goodsVo;

@Mapper
public interface goodsDao
{
	/**
	 *long gid;
	String gname;
	String gtitle;
	String gimg;
	String gdetail;
	double gprice;
	long gstock; 
	 */
    @Select("select * from wrgoods where g_id=#{id}")
    List<goods> getgid(@Param(value = "id") long id);
	
    
    @Select("select * from wrgoods")
	List<goods> getGoodsList();
	
	
	@Select("select g.* ,mg.msg_id, mg.msg_price,mg.msg_stock,mg.msg_start,mg.msg_end from ms_goods mg left join wrgoods g on mg.g_id=g.g_id  ")
	List<goodsVo> getGoods();
	@Select("select g.* ,mg.msg_id, mg.msg_price,mg.msg_stock,mg.msg_start,mg.msg_end from ms_goods mg left join wrgoods g on mg.g_id=g.g_id  where g.g_id=#{id}")
	goodsVo getByGoods(@Param(value = "id") long id);

}
