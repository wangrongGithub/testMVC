package wangrong.wr1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wangrong.wr1.dao.goodsDao;
import wangrong.wr1.domain.goods;
import wangrong.wr1.domain.goodsVo;

import java.util.List;

@Service
public class GoodsService {
    @Autowired
    goodsDao gd;
    public  List<goodsVo> goodsList() {
        List<goodsVo> list = gd.getGoods();

       // System.out.println(gd.g_detail()+ gd.g_name()+gd.g_title());
    	List<goods> g=gd.getgid(2);
        System.out.println("goods是"+g);
        
        System.out.println(gd.getGoodsList()+""+list);
        return list;
    }
    public goodsVo getByGoodid(long id)
    {
    	return gd.getByGoods(id);
    }
}
