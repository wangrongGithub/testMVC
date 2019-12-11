package wangrong.wr1.controller;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import wangrong.wr1.REsult.CodeMsg;
import wangrong.wr1.REsult.Result;
import wangrong.wr1.Util.MD5Util;
import wangrong.wr1.Util.UUIDUtil;

import wangrong.wr1.domain.*;
import wangrong.wr1.rabbitmq.MQSender;
import wangrong.wr1.rabbitmq.MiaoshaMessage;
import wangrong.wr1.redis.GoodsLeafKey;
import wangrong.wr1.redis.RedisService;
import wangrong.wr1.redis.ShoppingUserKey;
import wangrong.wr1.service.GoodsService;
import wangrong.wr1.service.MiaoshaService;
import wangrong.wr1.service.OrderService;
import wangrong.wr1.service.UserService;
import wangrong.wr1.wr1.access.Access;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;


@Controller
@RequestMapping("/miaosha")
public class MiaoshaController implements InitializingBean {
	@Autowired
	MiaoshaService ms;
	@Autowired
	GoodsService gs;
	@Autowired
	OrderService os;
	@Autowired
	RedisService rs;
	@Autowired
	MQSender mqs;
	HashMap<Long,Boolean> map=new HashMap();
	@RequestMapping("/do_miaosha1")
	String getByID(Model model, ShoppingUser user,
				   @RequestParam("goodsId") long goodsId) {
		if (user == null) {
			return "login";
		} else {
			goodsVo gv = gs.getByGoodid(goodsId);
			System.out.println("秒杀的商品是" + gv);
			long stock = gv.getMsg_stock();
			if (stock > 0) {
				if (os.getOrderByUidOid(user.getId(), gv.getG_id()) != null) {
					model.addAttribute("errmsg", "您已经秒杀过");
					return "miaosha_fail";
				} else {//做秒杀啊
					order mo = ms.MiaoShaGoods(user, gv);
					model.addAttribute("goods", gv);
					model.addAttribute("order", mo);
					return "order_detail";
				}
			} else {
				model.addAttribute("errmsg", "该商品没有库存啦");
				return "miaosha_fail";
			}


		}
	}

	@RequestMapping(value = "/do_miaosha2", method = RequestMethod.POST)
	@ResponseBody
	public Result<OrderInfo> miaosha2(Model model, ShoppingUser user,
									  @RequestParam("goodsId") long goodsId) {

		System.out.println(goodsId);
		if (user == null) {
			return Result.error(CodeMsg.SESSIONERROR, null);
		} else {
			//减少缓存的库存
			if(map.get(goodsId))
			{
				return Result.error(CodeMsg.SESSIONERROR, null);
			}

			long stock = rs.decr(GoodsLeafKey.GoodsStockKey, goodsId + "");
			if (stock < 0) {
				map.put(goodsId,true);
				return Result.error(CodeMsg.SESSIONERROR, null);
			}
            else
			{
				//判断是否已经秒杀过
				if (os.getOrderByUidOid(user.getId(),goodsId) != null) {
					model.addAttribute("errmsg", "您已经秒杀过");
					return Result.error(CodeMsg.SESSIONERROR, null);
				}			else
				{//进行秒杀操作啊
					MiaoshaMessage mm=new MiaoshaMessage();
					mm.setGoodsId(goodsId);
					mm.setUser(user);
					mqs.sendMiaoshaMessage(mm);
                    return Result.success(null);




				}


			}

		}
}
/*
*
* 0排队
* -1失败
*
* */
	@RequestMapping(value="/result", method= RequestMethod.GET)
	@ResponseBody
	public Result<Long> miaoshaResult(Model model, ShoppingUser user,
									 @RequestParam("goodsId")long goodsId)
	{
		if (user == null) {
			return Result.error(CodeMsg.SESSIONERROR, null);
		}


		return Result.success(ms.miaoshaResult(user,goodsId));



	}



	@RequestMapping(value="/do_miaosha", method= RequestMethod.POST)
	@ResponseBody
	public Result<OrderInfo> miaosha(Model model, ShoppingUser user,
									 @RequestParam("goodsId")long goodsId) {

		model.addAttribute("user", user);
		System.out.println(goodsId);
		if(user == null) {
			return Result.error(CodeMsg.SESSIONERROR,null);
		}
		else
		{
			goodsVo gv=gs.getByGoodid(goodsId);
			System.out.println("秒杀的商品是"+gv);
			long stock=gv.getMsg_stock();
			if(stock>0)
			{
				if(os.getOrderByUidOid(user.getId(), gv.getG_id())!=null)
				{
					model.addAttribute("errmsg", "您已经秒杀过");
					return Result.error(CodeMsg.SESSIONERROR,null);
				}
				else
				{//做秒杀啊
					order mo=ms.MiaoShaGoods(user, gv);
					model.addAttribute("goods",gv);
					model.addAttribute("order", mo);
					OrderInfo oi=new OrderInfo();
					oi.setId(mo.getO_id());
					oi.setCreateDate(mo.getO_createdate());
					oi.setGoodsCount((int)mo.getG_count());
					oi.setDeliveryAddrId(1L);
					oi.setGoodsId(gv.getG_id());
					oi.setGoodsName(gv.getG_name());
					oi.setGoodsPrice(gv.getMsg_price());
            System.out.println("订单详情是"+oi);
					return Result.success(oi);
				}

			}
			else
			{
				model.addAttribute("errmsg", "该商品没有库存啦");
				return Result.error(CodeMsg.SESSIONERROR,null);
			}



		}


	}


	@RequestMapping(value = "/{path}/do_miaosha3", method = RequestMethod.POST)
	@ResponseBody
	public Result<OrderInfo> miaosha3(Model model, ShoppingUser user,
									  @RequestParam("goodsId") long goodsId,@PathVariable("path")String path) {
//验证path,验证不通过的时候
    if(!rs.get(ShoppingUserKey.msPath,""+user.getId()+"123456"+goodsId,String.class).equals(path))
	{
		return Result.error(null,null);
	}

		System.out.println("检测通过");




		System.out.println(goodsId);
		if (user == null) {
			return Result.error(CodeMsg.SESSIONERROR, null);
		} else {
			//减少缓存的库存
			if(map.get(goodsId))
			{
				return Result.error(CodeMsg.SESSIONERROR, null);
			}

			long stock = rs.decr(GoodsLeafKey.GoodsStockKey, goodsId + "");
			if (stock < 0) {
				map.put(goodsId,true);
				return Result.error(CodeMsg.SESSIONERROR, null);
			}
			else
			{
				//判断是否已经秒杀过
				if (os.getOrderByUidOid(user.getId(),goodsId) != null) {
					model.addAttribute("errmsg", "您已经秒杀过");
					return Result.error(CodeMsg.MSLAST, null);
				}			else
				{//进行秒杀操作啊
					MiaoshaMessage mm=new MiaoshaMessage();
					mm.setGoodsId(goodsId);
					mm.setUser(user);
					mqs.sendMiaoshaMessage(mm);
					return Result.success(null);




				}


			}

		}
	}

	@RequestMapping("/msFail")
	@ResponseBody
	String getByID()
	{
		return "";
	}
	@RequestMapping("/msSucess")
	@ResponseBody
	String getByID2()
	{
		return "";
	}
/*
* 系统初始化的时候会回调这个接口啊
* */
	@Override
	public void afterPropertiesSet() throws Exception
	{//将商品数量加载到缓存中

		List<goodsVo> listGoods=gs.goodsList();
		for(goodsVo gv:listGoods)
		{
			rs.set(GoodsLeafKey.GoodsStockKey,gv.getG_id()+"",gv.getMsg_stock());
			map.put(gv.getG_id(),false);
		}
	}
	@RequestMapping(value="/path", method= RequestMethod.GET)
	@ResponseBody
	public Result<String> getpath(HttpServletRequest request , ShoppingUser user,
									 @RequestParam("goodsId")long goodsId,@RequestParam("verifyCode")int verifyCode)
	{
		if (user == null) {
			return Result.error(CodeMsg.SESSIONERROR, null);
		}

		if(ms.checkVerifyCode(user,goodsId,verifyCode)==false)
		{
			return Result.error(CodeMsg.SESSIONERROR, null);
		}
		String path= MD5Util.md5(UUIDUtil.uuid()+""+user.getId()+""+goodsId	);
		rs.set(ShoppingUserKey.msPath,""+user.getId()+"123456"+goodsId,path);
		System.out.println("创建path");
		return Result.success(path);
	}
	@RequestMapping(value="/verifyCode", method= RequestMethod.GET)
	@ResponseBody
	public Result<String> verifyCode(Model model, HttpServletResponse response, ShoppingUser user,
									@RequestParam("goodsId")long goodsId)
	{
		if (user == null) {
			return null;
		}

		String path= MD5Util.md5(UUIDUtil.uuid()+""+user.getId()+""+goodsId	);
		rs.set(ShoppingUserKey.msPath,""+user.getId()+"123456"+goodsId,path);
		System.out.println("创建path");

		try {
			BufferedImage image  = ms.createVerifyCode(user, goodsId);
			OutputStream out = response.getOutputStream();
			ImageIO.write(image, "JPEG", out);
			out.flush();
			out.close();
			return Result.success("成功");
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
    @Access(seconds = 10,accessNum = 5,needLogin = true)
	@RequestMapping("/msFail1")
	@ResponseBody
	String getByID(HttpServletRequest request,HttpServletResponse response,ShoppingUser user) throws IOException {
		request.setAttribute("key","wangrong");
		OutputStream out = response.getOutputStream();
		OutputStream out1 = response.getOutputStream();
        out.write("hjfdsfh".getBytes(),0,"hjfdsfh".getBytes().length);
		out.flush();
		out.close();
        return "8888";
	}
}