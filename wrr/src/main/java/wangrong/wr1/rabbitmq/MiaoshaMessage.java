package wangrong.wr1.rabbitmq;


import wangrong.wr1.domain.ShoppingUser;

public class MiaoshaMessage {
	private ShoppingUser user;
	private long goodsId;
	public ShoppingUser getUser() {
		return user;
	}
	public void setUser(ShoppingUser user) {
		this.user = user;
	}
	public long getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(long goodsId) {
		this.goodsId = goodsId;
	}
}
