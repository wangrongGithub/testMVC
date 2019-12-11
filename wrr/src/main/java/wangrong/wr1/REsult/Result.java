package wangrong.wr1.REsult;

public class Result <T>{
	int code;
	String msg;
	T data;
	public static <T> Result<T> success(T data)
	{
		return new Result<T>(CodeMsg.SUCCESS.getCode(),CodeMsg.SESSIONERROR.getMsg(),data);
		
	}
	public static <T> Result<T> error(CodeMsg cm,T data)
	{
		return new Result<T>(cm.getCode(),cm.getMsg(),data);
		
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public Result(int code, String msg, T data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	

}
