package wangrong.wr1.REsult;

/**
 * @author Administrator
 *
 */
public class CodeMsg {
	public static final CodeMsg BIND_ERROR =  new CodeMsg(500015,"手机号格式不正确");
	public static final CodeMsg HANDLE_ERROR =  new CodeMsg(500016,"中间处理除错 ");;
	private int Code;
	private String Msg;
	/*
	 * 用户登陆问题
	 * 
	 * 
	 * */
	public static CodeMsg MSLAST= new CodeMsg(5444,"已经秒杀过");
	public static CodeMsg SUCCESS= new CodeMsg(0,"请求成功");
	public static CodeMsg SESSIONERROR= new CodeMsg(500011,"session失效");
	public static CodeMsg MOBILEERROR= new CodeMsg(500012,"手机号不存在");
	public static CodeMsg MOBILEEMPTY= new CodeMsg(500013,"手机号是NULL");
	public static CodeMsg PASSWORDERROR= new CodeMsg(500014,"密码错误");
	public static CodeMsg PASSWORDEMPTY= new CodeMsg(500015,"密码是NULL");
	public static CodeMsg USERNOFIND= new CodeMsg(500016,"用户不存在");
	public CodeMsg(int code, String msg) {
		super();
		Code = code;
		Msg = msg;
	}
	public int getCode() {
		return Code;
	}
	public void setCode(int code) {
		Code = code;
	}
	public String getMsg() {
		return Msg;
	}
	public void setMsg(String msg) {
		Msg = msg;
	}
	public static CodeMsg success()
	{
		return new CodeMsg(1,"请求成功");
		
	}
	public static CodeMsg error()
	{
		
		return new CodeMsg(0,"服务器挂掉");
		
	}

}