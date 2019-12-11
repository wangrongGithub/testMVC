package wangrong.wr1.domain;

public class ordersVo {
    goodsVo goods;
    order order;

    public goodsVo getGoods() {
        return goods;
    }

    public wangrong.wr1.domain.order getOrder() {
        return order;
    }

    public void setGoods(goodsVo    goods) {
        this.goods = goods;
    }

    public void setOrder(wangrong.wr1.domain.order order) {
        this.order = order;
    }
}
