package wangrong.wr1.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import wangrong.wr1.REsult.CodeMsg;
import wangrong.wr1.REsult.Result;
import wangrong.wr1.domain.User;
import wangrong.wr1.redis.RedisService;
import wangrong.wr1.redis.UserPrefic;
import wangrong.wr1.service.UserService;
@Controller
@RequestMapping("/Redis")
public class RedisController {
	@Autowired
	RedisService redisService;
	@Autowired
	UserService userService;
	@RequestMapping("/wr1")
	@ResponseBody
Result home1()
{
		User user=userService.getUserById(1);
		userService.getUserById(1);
		redisService.set("key2", user);
		System.out.println("我是小可爱用户啊"+redisService.get("key2", User.class));
		redisService.get("key1", String.class);
		User user1=redisService.get("key2", User.class);
		System.out.println("我是王蓉小可爱用户啊"+user1.getId()+user1.getName());
	    return Result.success( user1);
	}
	@RequestMapping("/wr2")
	@ResponseBody
	Result home2()
	{
			User user=userService.getUserById(25);
			userService.getUserById(25);
			redisService.set(UserPrefic.getById(),"key2", user);
			System.out.println("我是小可爱用户啊"+redisService.get("key2", User.class));
			redisService.get("key1", String.class);
			User user1=redisService.get(UserPrefic.getById(),"key2", User.class);
			System.out.println("我是王蓉小可爱用户啊"+user1.getId()+user1.getName());
		    return Result.success( user1);
		}
}
