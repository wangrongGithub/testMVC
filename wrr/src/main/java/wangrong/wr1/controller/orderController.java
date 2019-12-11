package wangrong.wr1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import wangrong.wr1.REsult.Result;
import wangrong.wr1.domain.*;
import wangrong.wr1.service.GoodsService;
import wangrong.wr1.service.OrderService;
///order/detail
@Controller
@RequestMapping("/order")

public class orderController {
    @Autowired
    OrderService os;
    @Autowired
    GoodsService gs;
    @RequestMapping("/detail")
    @ResponseBody
    public Result<ordersVo> detailGoods(Model model, ShoppingUser user,
                                     long orderId)
    {
        System.out.println("order detail 进来了");
        order o=os.getOrderByOid(orderId);
        goodsVo goods=gs.getByGoodid(o.getG_id());
        ordersVo ov=new ordersVo();

ov.setGoods(goods);
ov.setOrder(o);


        return  Result.success(ov);
    }


}



