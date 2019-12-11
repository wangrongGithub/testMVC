package wangrong.wr1.domain;

public class goodsDetailVo {

    private int miaoshaStatus = 0;
    private int remainSeconds = 0;
    private goodsVo goods ;
    private ShoppingUser user;


    @Override
    public String toString() {
        return "goodsDetailVo{" +
                "miaoshaStatus=" + miaoshaStatus +
                ", remainSeconds=" + remainSeconds +
                ", goods=" + goods +
                ", user=" + user +
                '}';
    }

    public int getMiaoshaStatus() {
        return miaoshaStatus;
    }

    public int getRemainSeconds() {
        return remainSeconds;
    }

    public goodsVo getGoods() {
        return goods;
    }

    public ShoppingUser getUser() {
        return user;
    }

    public void setMiaoshaStatus(int miaoshaStatus) {
        this.miaoshaStatus = miaoshaStatus;
    }

    public void setRemainSeconds(int remainSeconds) {
        this.remainSeconds = remainSeconds;
    }

    public void setGoods(goodsVo goods) {
        this.goods = goods;
    }

    public void setUser(ShoppingUser user) {
        this.user = user;
    }
}
