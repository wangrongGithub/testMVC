package wangrong.wr1.controller;

import com.alibaba.druid.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.thymeleaf.spring4.context.SpringWebContext;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import wangrong.wr1.REsult.Result;
import wangrong.wr1.dao.goodsDao;
import wangrong.wr1.domain.ShoppingUser;
import wangrong.wr1.domain.goodsDetailVo;
import wangrong.wr1.domain.goodsVo;
import wangrong.wr1.redis.GoodsLeafKey;
import wangrong.wr1.redis.RedisService;
import wangrong.wr1.service.GoodsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
///goods/to_list
@RequestMapping("/goods")
public class GoodsController {

	@Autowired
	GoodsService gs;
	@Autowired
	RedisService rs;

	@Autowired
	ThymeleafViewResolver tvr;
	@Autowired
	ApplicationContext applicationContext;
	@RequestMapping("/to_list")
	String login(Model model,@CookieValue(value="token",required=false)String token,@RequestParam(value="token",required=false)String token1,ShoppingUser su)
   {
		
		gs.goodsList();
		System.out.println("我是to_list********");
		System.out.println("我是to_list");
		model.addAttribute("goodsList",gs.goodsList());
	    return "goods_list";
	}
	//页面的缓存啊
	@RequestMapping(value="/to_list1",produces = "text/html")
	@ResponseBody
	String listGoods(HttpServletRequest request, HttpServletResponse response,Model model, @CookieValue(value="token",required=false)String token, @RequestParam(value="token",required=false)String token1, ShoppingUser su)
	{
		//首先：在Redis里面发现这个页面的缓存，如果没有就进行渲染啊;
		String html=rs.get(GoodsLeafKey.getGoodsLeafKey(),"",String.class);
   if(!StringUtils.isEmpty(html))
   {//直接返回
	   return html;

   }
   else
   {//手动渲染存到Redis
	   model.addAttribute("goodsList",gs.goodsList());
	   SpringWebContext ctx = new SpringWebContext(request, response, request.getServletContext(), request.getLocale(),
			   model.asMap(), applicationContext);
// 手动渲染
	   html = tvr.getTemplateEngine().process("goods_list", ctx);

//存入RESIS
	   rs.set(GoodsLeafKey.getGoodsLeafKey(),"",html);
	   return html;

   }



	}
	
	@RequestMapping("/info")
	@ResponseBody
	Result<ShoppingUser> login2(Model model,@CookieValue(value="token",required=false)String token,@RequestParam(value="token",required=false)String token1,ShoppingUser su)
   {
	    return Result.success(su);
	}
	@RequestMapping("/detail/{goodsId}")
	@ResponseBody
	public Result<goodsVo> detailGoods(Model model,ShoppingUser user,
						 @PathVariable("goodsId")long goodsId)
	{
		goodsDetailVo gdv=new goodsDetailVo();
		goodsVo goods=gs.getByGoodid(goodsId);
		gdv.setGoods(goods);
		gdv.setUser(user);

		long startAt = goods.getMsg_start().getTime();
		long endAt = goods.getMsg_end().getTime();
		long now = System.currentTimeMillis();
        int miaoshaStatus = 0;
		int remainSeconds = 0;
		if(now < startAt )
		{//秒杀还没开始，倒计时
			miaoshaStatus = 0;
			remainSeconds = (int)((startAt - now )/1000);
		}else  if(now > endAt){//秒杀已经结束
			miaoshaStatus = 2;
			remainSeconds = -1;
		}else {//秒杀进行中
			miaoshaStatus = 1;
			remainSeconds = 0;
		}
		gdv.setMiaoshaStatus(miaoshaStatus);
		gdv.setRemainSeconds(remainSeconds);
		System.out.println(gdv);
		return new Result(0,"查找成功啊",gdv);
	}


	  @RequestMapping("/to_detail/{goodsId}")
	    public String detail(Model model,ShoppingUser user,
	    		@PathVariable("goodsId")long goodsId)
	  {
		System.out.println("我是to_list********");
		System.out.println("我是to_list");
		System.out.println("商品详情"+gs.getByGoodid(goodsId));
		goodsVo goods=gs.getByGoodid(goodsId);
		model.addAttribute("goods",goods);
		model.addAttribute("user",user);
		long startAt = goods.getMsg_start().getTime();
    	long endAt = goods.getMsg_end().getTime();
    	long now = System.currentTimeMillis();
    	
    	int miaoshaStatus = 0;
    	int remainSeconds = 0;
    	if(now < startAt ) {//秒杀还没开始，倒计时
    		miaoshaStatus = 0;
    		remainSeconds = (int)((startAt - now )/1000);
    	}else  if(now > endAt){//秒杀已经结束
    		miaoshaStatus = 2;
    		remainSeconds = -1;
    	}else {//秒杀进行中
    		miaoshaStatus = 1;
    		remainSeconds = 0;
    	}
    	model.addAttribute("miaoshaStatus", miaoshaStatus);
    	model.addAttribute("remainSeconds", remainSeconds);
		
	    return "goods_detail";
	}
	//URL缓存
	@RequestMapping(value="/to_detail1/{goodsId}",produces = "text/html")
	@ResponseBody
	public String detail1(HttpServletRequest request, HttpServletResponse response,Model model,ShoppingUser user,
						 @PathVariable("goodsId")long goodsId)
	{


		String html=rs.get(GoodsLeafKey.getGoodsDetailKey(),""+goodsId,String.class);
		if(!StringUtils.isEmpty(html))
		{//直接返回
			return html;

		}
		else
		{
			goodsVo goods=gs.getByGoodid(goodsId);
			model.addAttribute("goods",goods);
			model.addAttribute("user",user);
			long startAt = goods.getMsg_start().getTime();
			long endAt = goods.getMsg_end().getTime();
			long now = System.currentTimeMillis();

			int miaoshaStatus = 0;
			int remainSeconds = 0;
			if(now < startAt )
			{//秒杀还没开始，倒计时
				miaoshaStatus = 0;
				remainSeconds = (int)((startAt - now )/1000);
			}else  if(now > endAt){//秒杀已经结束
				miaoshaStatus = 2;
				remainSeconds = -1;
			}else {//秒杀进行中
				miaoshaStatus = 1;
				remainSeconds = 0;
			}
			model.addAttribute("miaoshaStatus", miaoshaStatus);
			model.addAttribute("remainSeconds", remainSeconds);
			SpringWebContext ctx = new SpringWebContext(request, response, request.getServletContext(), request.getLocale(),
					model.asMap(), applicationContext);
// 手动渲染
			html = tvr.getTemplateEngine().process("/goods_detail", ctx);

//存入RESIS
			rs.set(GoodsLeafKey.getGoodsDetailKey(),""+goodsId,html);
			return html;
		}


	}
}
