package wangrong.wr1.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wangrong.wr1.domain.ShoppingUser;
import wangrong.wr1.domain.goodsVo;
import wangrong.wr1.domain.msorder;
import wangrong.wr1.redis.RedisService;
import wangrong.wr1.service.GoodsService;
import wangrong.wr1.service.MiaoshaService;
import wangrong.wr1.service.OrderService;


@Service
public class MQReceiver {

		private static Logger log = LoggerFactory.getLogger(MQReceiver.class);
		
		@Autowired
		RedisService redisService;
		
		@Autowired
		GoodsService goodsService;
		
		@Autowired
		OrderService orderService;
		
		@Autowired
		MiaoshaService miaoshaService;
		
		@RabbitListener(queues=MQConfig.MIAOSHA_QUEUE)
		public void receiveMiaoshaMessage(String message) {
			log.info("receive message:"+message);
			MiaoshaMessage mm  = RedisService.StringToBean(message, MiaoshaMessage.class);
			ShoppingUser user = mm.getUser();
			long goodsId = mm.getGoodsId();
			goodsVo goods = goodsService.getByGoodid(goodsId);
	    	long stock = goods.getG_stock();
	    	if(stock <= 0)
	    	{
	    		return;
	    	}
	    	//判断是否已经秒杀到了msorder getOrderByUidOid
			msorder order = orderService.getOrderByUidOid(user.getId(), goodsId);
	    	if(order != null)
	    	{
	    		return;
	    	}
	    	//减库存 下订单 写入秒杀订单
	    	miaoshaService.MiaoShaGoods(user, goods);
		}

		@RabbitListener(queues=MQConfig.QUEUE)
		public void receive(String message) {
		log.info("receive message:"+message);
		}

		@RabbitListener(queues=MQConfig.TOPIC_QUEUE1)
		public void receiveTopic1(String message) {
			System.out.println(" topic  queue1 message+++++:"+message);
			log.info(" topic  queue1 message:"+message);
		}

		@RabbitListener(queues=MQConfig.TOPIC_QUEUE2)
		public void receiveTopic2(String message) {
			System.out.println(" topic  queue2 message:"+message);
			log.info(" topic  queue2 message*****:"+message);
		}

		@RabbitListener(queues=MQConfig.HEADER_QUEUE)
		public void receiveHeaderQueue(byte[] message) {
			log.info(" header  queue message:"+new String(message));
		}

		
}
