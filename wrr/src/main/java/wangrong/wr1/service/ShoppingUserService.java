package wangrong.wr1.service;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;

import wangrong.wr1.REsult.CodeMsg;
import wangrong.wr1.REsult.Result;
import wangrong.wr1.Util.MD5Util;
import wangrong.wr1.Util.UUIDUtil;
import wangrong.wr1.dao.ShoppingUserDao;
import wangrong.wr1.domain.LoginVal;
import wangrong.wr1.domain.ShoppingUser;
import wangrong.wr1.exception.GlobalException;
import wangrong.wr1.redis.RedisService;
import wangrong.wr1.redis.ShoppingUserKey;
@Service
public class ShoppingUserService {
	public static final String COOKI_NAME_TOKEN = "token";
	@Autowired
	ShoppingUserDao suD;
	@Autowired
	RedisService redisService;
	
	public	Result<String> doInsert(LoginVal lv)
	{
		int i=10;
		System.out.println(suD);
		//for(i=0;i<10;i++)
		{
			ShoppingUser user=new ShoppingUser();
			user.setId(Long.valueOf(lv.getMobile()));
			user.setPassword(MD5Util.inputPassToDbPass(lv.getPassword(), "1a2b3c4d"));
			user.setSalt("1a2b3c4d");
			user.setLogin_count(0);
			user.setLast_login_date(new Date());
			user.setRegister_date(new Date());
			user.setNickname(""+i);
			Boolean b=suD.tx(user);
			
			System.out.println(b+"用户插入成功");	
		}
		return Result.success("插入成功");
	}
	public	Result<String> doInsert()
	{
		int i=10;
		System.out.println(suD);
		for(i=0;i<10;i++)
		{
			ShoppingUser user=new ShoppingUser();
			user.setId(Long.valueOf("1234567890"+""+i));
			user.setPassword(MD5Util.inputPassToDbPass("123456", "1a2b3c4d"));
			user.setSalt("1a2b3c4d");
			user.setLogin_count(0);
			user.setLast_login_date(new Date());
			user.setRegister_date(new Date());
			user.setNickname(""+i);
			Boolean b=suD.tx(user);
			
			System.out.println(b+"用户插入成功");	
		}
		return Result.success("插入成功");
	}
public	Result<Boolean> doLogin(HttpServletResponse response,LoginVal lv)
	{
		String id=lv.getMobile();
		String password=lv.getPassword();
		ShoppingUser su=null;
		if(su==null)
		{
		su=suD.getUserById(Long.valueOf(id));
	    System.out.println(password);
			System.out.println( su.getSalt());
			System.out.println(MD5Util.formPassToDBPass(password, su.getSalt()));
			if(MD5Util.formPassToDBPass(password, su.getSalt()).equals(su.getPassword())==true)
			{
				//生成cookie
				String token= UUIDUtil.uuid();
				System.out.println("token是"+token);
				addCookie(response, token, su);
				
				//读取处理
				ShoppingUser su1=redisService.get(ShoppingUserKey.token, token, ShoppingUser.class);
				System.out.println(su1);
				return Result.success(true);
				}
			else
			{return Result.error(CodeMsg.PASSWORDERROR,false);
				
			}
		}
		else
		{
			System.out.println( "su不是null"+su);
			//生成cookie
			String token= UUIDUtil.uuid();
			System.out.println("token是"+token);
			addCookie(response, token, su);
			return Result.success(true);
			
		}
	  
			
		
	}

public ShoppingUser getByToken(HttpServletResponse response, String token) {
	if(StringUtils.isEmpty(token)) {
		return null;
	}
	ShoppingUser user = redisService.get(ShoppingUserKey.token, token, ShoppingUser.class);
	//延长有效期
	if(user != null) {
		addCookie(response, token, user);
	}
	return user;
}
//对象缓存
	public ShoppingUser getById(long id) {
		//获取这个用户
		ShoppingUser user = redisService.get(ShoppingUserKey.idPrefix, ""+id, ShoppingUser.class);
		//延长有效期,
		if(user != null)
		{
			//addCookie(response, token, user);
			return user;
		}
		//去数据库
		user=suD.getUserById(id);
		//放到缓存里面
		if(user!=null) {
			redisService.set(ShoppingUserKey.idPrefix, "" + id, user);
			return user;
		}

		return user;
	}

	public boolean updatePassword(String password,long id, String token)
	{
		//获取这个对象
		ShoppingUser user=getById( id);
		if(user!=null)
		{
			user.setPassword(MD5Util.formPassToDBPass(password, user.getSalt()));
			//更新这个对象在数据库里面
			suD.updatePassword(user);
			//写缓存
			redisService.set(ShoppingUserKey.token, token, user);
			redisService.set(ShoppingUserKey.idPrefix, "" + id, user);
			return true;
		}
		else
		{
			throw new GlobalException(CodeMsg.USERNOFIND);

		}




	}
private void addCookie(HttpServletResponse response, String token, ShoppingUser user) {
	redisService.set(ShoppingUserKey.token, token, user);
	Cookie cookie = new Cookie(COOKI_NAME_TOKEN, token);
	cookie.setMaxAge(ShoppingUserKey.token.getInvaidSeconds());
	cookie.setPath("/");
	response.addCookie(cookie);
}
}
