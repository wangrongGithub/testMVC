package wangrong.wr1.redis;

public abstract class BasePrefix implements KeyPredix{
       int InvaidSeconds=0;
       String Predix;
	public abstract int getInvaidSeconds() ;

	public abstract String getRealPredix();


	public void setInvaidSeconds(int invaidSeconds) {
		InvaidSeconds = invaidSeconds;
	}

	public void setPredix(String predix) {
		Predix = predix;
	}

	public BasePrefix(int invaidSeconds, String predix) {
		super();
		InvaidSeconds = invaidSeconds;
		Predix = predix;
	}



}
