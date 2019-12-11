package wangrong.wr1.redis;

public class UserPrefic extends BasePrefix{
	public UserPrefic(int invaidSeconds, String predix) {
		super( invaidSeconds,  predix);
		
	}

	@Override
	public int getInvaidSeconds() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getRealPredix() {
		// TODO Auto-generated method stub
		return getClass().getSimpleName()+super.Predix;
	}
public static UserPrefic getById()
{
	return new UserPrefic(0,"id"); 
	}
	
}
