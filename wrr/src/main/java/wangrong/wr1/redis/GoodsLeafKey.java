package wangrong.wr1.redis;

public class GoodsLeafKey extends BasePrefix{

	public GoodsLeafKey(int invaidSeconds, String predix) {
		super( invaidSeconds,  predix);
		
	}

	@Override
	public int getInvaidSeconds() {
		// TODO Auto-generated method stub
		return super.InvaidSeconds;
	}

	@Override
	public String getRealPredix() {
		// TODO Auto-generated method stub
		return super.Predix;
	}
	public static GoodsLeafKey GoodsOverKey=new GoodsLeafKey(600,"GoodsOverKey");
	public static GoodsLeafKey GoodsStockKey=new GoodsLeafKey(600,"GoodsStockKey");
	public static GoodsLeafKey GoodsLeafKey=new GoodsLeafKey(600,"GoodsLeafKey");
	public static GoodsLeafKey getGoodsLeafKey()
	{
		return GoodsLeafKey;
	}

	public static GoodsLeafKey getGoodsDetailKey()
	{
		return new GoodsLeafKey(60,"GoodsDetailKey");
	}
}
