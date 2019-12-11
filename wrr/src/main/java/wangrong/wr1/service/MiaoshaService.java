package wangrong.wr1.service;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wangrong.wr1.dao.MiaoshaDao;
import wangrong.wr1.dao.orderDao;
import wangrong.wr1.domain.ShoppingUser;
import wangrong.wr1.domain.goods;
import wangrong.wr1.domain.goodsVo;
import wangrong.wr1.domain.msorder;
import wangrong.wr1.domain.order;
import wangrong.wr1.redis.GoodsLeafKey;
import wangrong.wr1.redis.RedisService;
import wangrong.wr1.redis.ShoppingUserKey;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

@Service
public class MiaoshaService {
	@Autowired
	MiaoshaDao ms;
	@Autowired
	orderDao od;
	@Autowired
	 OrderService os;
	@Autowired
	RedisService rs;
  @Transactional
 public order MiaoShaGoods(ShoppingUser user, goodsVo gv) {
		// TODO Auto-generated method stub
	  
	  //先减库存
	  goods g=new goods();
	  g.setG_id(user.getId());
	  od.updateStock(gv);
	  od.updateMsStock(gv);
	  //生成订单
	  order o= new order();
	  o.setG_id(gv.getG_id());
	  o.setU_id(user.getId());
	  o.setG_count(1L);
	  o.setG_name(gv.getG_name());
	  o.setG_price(gv.getMsg_price());
	  o.setO_address("西安电子科技大学");
	  o.setO_channel(1);
	  o.setO_status(0);
	  o.setO_createdate(new Date(System.currentTimeMillis()));
	  o.setO_paydate(new Date(System.currentTimeMillis()));
	  msorder mo=new msorder();
	  long oid=ms.createOrder(o);
	  oid=o.getO_id();
	  System.out.println("生成的订单是"+o.getO_id());
	  o.setO_id(oid);
	  mo.setG_id(gv.getG_id());
	  mo.setO_id(oid);
	  mo.setU_id(user.getId());
	  long mosid=ms.MiaoShaGoods( mo);
	  if(mosid>0)
	  {
		  mosid=mo.getMso_id();
		  System.out.println("生成的秒杀订单是"+mo.getMso_id());
		  mo.setMso_id(mosid);
		  return o;
	  }
	  else
	  {//减少库存
	  	setGoodsOver(g.getG_id());
         return null;
	  }

	}

	private void setGoodsOver(long g_id) {
		rs.set(GoodsLeafKey.GoodsOverKey,g_id+"",true);
	}
	private boolean getGoodsOver(long g_id) {
  	if(rs.get(GoodsLeafKey.GoodsOverKey,g_id+"",Boolean.class)!=null)
  	return true;
  	else
	{
		return false;
	}
	}
	public long miaoshaResult(ShoppingUser user, long goodsId) {

		msorder mso=os.getOrderByUidOid(user.getId(),goodsId);
		if(mso==null)
		{
			if(getGoodsOver(goodsId)) {
				return -1;
			}
			else
			{
				return 0;
			}
		}
		else
		{
			return mso.getO_id();
		}

	}

	public BufferedImage createVerifyCode(ShoppingUser user, long goodsId) {
		if(user == null || goodsId <=0) {
			return null;
		}
		int width = 80;
		int height = 32;
		//create the image
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		// set the background color
		g.setColor(new Color(0xDCDCDC));
		g.fillRect(0, 0, width, height);
		// draw the border
		g.setColor(Color.black);
		g.drawRect(0, 0, width - 1, height - 1);
		// create a random instance to generate the codes
		Random rdm = new Random();
		// make some confusion
		for (int i = 0; i < 50; i++) {
			int x = rdm.nextInt(width);
			int y = rdm.nextInt(height);
			g.drawOval(x, y, 0, 0);
		}
		// generate a random code
		String verifyCode = generateVerifyCode(rdm);
		g.setColor(new Color(0, 100, 0));
		g.setFont(new Font("Candara", Font.BOLD, 24));
		g.drawString(verifyCode, 8, 24);
		g.dispose();
		//把验证码存到redis中
		int rnd = calc(verifyCode);
		rs.set(ShoppingUserKey.getMiaoshaVerifyCode, user.getId()+","+goodsId, rnd);
		//输出图片
		return image;




	}

	public boolean checkVerifyCode(ShoppingUser user, long goodsId, int verifyCode) {
		if(user == null || goodsId <=0) {
			return false;
		}
		Integer codeOld = rs.get(ShoppingUserKey.getMiaoshaVerifyCode, user.getId()+","+goodsId, Integer.class);
		if(codeOld == null || codeOld - verifyCode != 0 ) {
			return false;
		}
		rs.delete(ShoppingUserKey.getMiaoshaVerifyCode, user.getId()+","+goodsId);
		return true;
	}

	private static int calc(String exp) {
		try {
			ScriptEngineManager manager = new ScriptEngineManager();
			ScriptEngine engine = manager.getEngineByName("JavaScript");
			return (Integer)engine.eval(exp);
		}catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	private static char[] ops = new char[] {'+', '-', '*'};
	/**
	 * + - *
	 * */
	private String generateVerifyCode(Random rdm) {
		int num1 = rdm.nextInt(10);
		int num2 = rdm.nextInt(10);
		int num3 = rdm.nextInt(10);
		char op1 = ops[rdm.nextInt(3)];
		char op2 = ops[rdm.nextInt(3)];
		String exp = ""+ num1 + op1 + num2 + op2 + num3;
		return exp;
	}
}

