package wangrong.wr1.redis;

public class ShoppingUserKey extends BasePrefix{
	
	public ShoppingUserKey(int invaidSeconds, String predix) {
		super( invaidSeconds,  predix);
		
	}
	public void setInvaidSeconds(int invaidSeconds)
	{
		super.setInvaidSeconds(invaidSeconds);

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
	public static ShoppingUserKey  getMiaoshaAccess=new ShoppingUserKey(60,"MiaoshaAccess");

	public static ShoppingUserKey  getMiaoshaVerifyCode=new ShoppingUserKey(60,"VerifyCode");
	public static ShoppingUserKey msPath=new ShoppingUserKey(60,"maPath");
	public static ShoppingUserKey token=new ShoppingUserKey(3000,"token");
	public static ShoppingUserKey idPrefix=new ShoppingUserKey(0,"id");//永久有效啊
}
