package wangrong.wr1.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import wangrong.wr1.REsult.CodeMsg;
import wangrong.wr1.REsult.Result;
import wangrong.wr1.domain.LoginVal;
import wangrong.wr1.domain.ShoppingUser;
import wangrong.wr1.service.ShoppingUserService;

@Controller

@RequestMapping("/Login")
public class LoginController {
	@Autowired
	ShoppingUserService sus;
	
	//private static Logger log=(Logger) LoggerFactory.getLogger(LoginController.class);
	@RequestMapping("/login")
	   String login(Model model)
      {

		System.out.println("我是login********");
		System.out.println("我是login");
		model.addAttribute("name","wangrong");
		System.out.println("^_^   ^_^   ^_^");
	   return "login";
	}
	
	@RequestMapping("/doInsert1")
	@ResponseBody
	   Result<String> doInsert(Model model)
{
		System.out.println("我是hello");
		model.addAttribute("name","wangrong");
	   return sus.doInsert();
	}
	@RequestMapping("/doInsert")
	@ResponseBody
	   Result<String> doInsert1(Model model,LoginVal val)
{
		System.out.println("我是hello");
		if(val==null)
		{
			return Result.success("fsfsf") ;
		}
		
		model.addAttribute("name","wangrong");
	   return sus.doInsert(val);
	}

	@RequestMapping("/hello")
	   String hello(Model model)
   {
		System.out.println("我是hello");
		model.addAttribute("name","wangrong");
	   return "hello";
	}
	@RequestMapping("/login1")
	@ResponseBody
Result home()
{
		Map<String ,String>map=new HashMap();
		map.put("王蓉", "永远18岁");
		map.put("平 ", "永远18岁");
	return Result.success(map);
	}
	
	@RequestMapping("/doLogin")
	@ResponseBody
	
	Result home1(HttpServletResponse response,@Valid LoginVal val)
	{
			//log.info(val.toString());
			if(val!=null)
			{
			System.out.println("我是doLogin1"+val.getPassword());
			//log.info(val.toString());
			//sus.doLogin(val);
			System.out.println("我是doLogin1"+val.getPassword());
			}
			else
			{
				System.out.println("我是doLogin1");
			}
		    return sus.doLogin(response,val);
		}



	@RequestMapping("/doLogin1")
	@ResponseBody
	Result home2(HttpServletResponse response,@Valid LoginVal val)
	{
			//log.info(val.toString());
			if(val!=null)
			{
			System.out.println("我是doLogin1"+val.getPassword());
			//log.info(val.toString());
			//sus.doLogin(val);
			System.out.println("我是doLogin1"+val.getPassword());
			}
			else
			{
				System.out.println("我是doLogin1");
			}
		    return sus.doLogin(response,val);
		}


}
