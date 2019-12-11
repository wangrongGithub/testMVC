package wangrong.wr1.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import wangrong.wr1.REsult.CodeMsg;
import wangrong.wr1.REsult.Result;

@Controller
@EnableAutoConfiguration
@RequestMapping("/")
public class testC {
	@RequestMapping("/wr")
	@ResponseBody
Result home()
{
		Map<String ,String>map=new HashMap();
		map.put("王蓉", "永远18岁");
		map.put("平 ", "永远18岁");
	return Result.success(map);
	}
	
	@RequestMapping("/wr1")
	@ResponseBody
Result home1()
{
		Map<String ,String>map=new HashMap();
		map.put("王蓉", "永远18岁");
		map.put("平 ", "永远18岁");
	return Result.error(CodeMsg.SESSIONERROR, null);
	}

	@RequestMapping("/wr4")
   
String hello(Model model)
{
		model.addAttribute("name","wangrong");
	return "hello";}
}



