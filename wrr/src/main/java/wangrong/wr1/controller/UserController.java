package wangrong.wr1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import wangrong.wr1.REsult.Result;
import wangrong.wr1.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;
	@RequestMapping("/getById")
	@ResponseBody
	Result getByID()
	{
		return Result.success(userService.getUserById(1));
	}
	@RequestMapping("/tx")
	@ResponseBody
	Result tx()
	{
		return Result.success(userService.tx());
	}
}
