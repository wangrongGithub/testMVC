package wangrong.wr1.Util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import wangrong.wr1.domain.LoginVal;

abstract class wr{
	abstract void ii();
	abstract void gg();
}
abstract class bb extends wr{

	@Override
	void ii() {
		// TODO Auto-generated method stub
		
	}


	
}

class cc extends bb{
	void gg(){}
}
public class createUser {
	static final int er; 
	
	public void createUser()
	{
		
	}
	String createUser(int i)
	{
		long l=2;
		System.out.println("jjj");
		return null;
	}
	static
	{
		er=test();
		System.out.println("*_* *_*");
	}
	{
		System.out.println("((((");
	}
	private static final Pattern mobile_pattern = Pattern.compile("1\\d{10,20}");

static int  test()
{
	final int y;
    
System.out.println("####");	
return 5;
}

	public static boolean isMobile(String src) {
		if(StringUtils.isEmpty(src)) {
			return false;
		}
		Matcher m = mobile_pattern.matcher(src);
		return m.matches();
	}
	
	
	public static void main(String []args)
	{
		
		AllWrite(1,200);
	
	}
	static void AllWrite(int start,int end)
	{
		BufferedWriter bw=null;
		try {
			 bw=new BufferedWriter(new FileWriter(new  File("2.txt")));
			 for(int i=start;i<end;i++)
				{
					LoginVal lv=new LoginVal();
					lv.setMobile("1234567890"+i);
					lv.setPassword("123456");
					lv.setPassword(MD5Util.inputPassToFormPass(lv.getPassword()));
					//InsertToDB(lv);
					String str=lv.getMobile()+","+Login(lv);
					
					if(Write(bw,str)==true)
						{
						System.out.println("写入成功");
						}
					else
					{
						System.out.println("写入失败");
						return;
					}
					bw.newLine();
				}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			
			if(bw!=null)
			{
				try {
					bw.flush();
					bw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally{
					
				}
				
			}
			
		}
		
		
	}
	static boolean Write(BufferedWriter bw,String str)
	{
		boolean result=true;
		try {
			bw.write(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			result=false;
			e.printStackTrace();
		}
		finally{
			return result;	
		}
		
	}
	static String Login(LoginVal lv)
	{
		
try {
			
			String param="mobile="+lv.getMobile()+"&password="+lv.getPassword();
			System.out.println(param);
			URL aURL = new URL("http://localhost:8889/Login/doLogin"+ "?" +param);
			System.out.println(aURL);
			HttpURLConnection con= (HttpURLConnection)aURL.openConnection();
			        //获取连接
			  
			con.setRequestProperty("accept", "*/*");
			con.setRequestProperty("connection", "Keep-Alive");
			con.setRequestProperty("user-agent",
                     "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
             //建立连接
			con.connect();
          // 获取所有响应头字段
             Map<String, List<String>> map = con.getHeaderFields();
             // 遍历所有的响应头字段，获取到cookies等
             for (String key : map.keySet()) {
                 System.out.println(key + "--->" + map.get(key));
             }
             List<String> list= map.get("Set-Cookie");
             for(String str :list)
             {// 把token解析出来
            	 //System.out.println(x);
            	 if(str.substring(0, 5).equals("token")==true)
            	 {
            	   String[] strArr=str.split("token=");
            	   System.out.println(strArr[strArr.length-1].split(";")[0]);
            	  return strArr[strArr.length-1].split(";")[0]; 
            	 }
             }
                          
            
             
             StringBuilder result=new StringBuilder();
             // 定义 BufferedReader输入流来读取URL的响应
             BufferedReader read = new BufferedReader(new InputStreamReader(
                     con.getInputStream(),"UTF-8"));
             String line;//循环读取
             while ((line = read.readLine()) != null) {
            	 result.append(line);
            	 
             }
             System.out.println(result);
			
		          InputStream is = con.getInputStream();
			          BufferedReader buffer = new BufferedReader(new InputStreamReader(is));
			          String str="";
			          StringBuilder sb=new StringBuilder();
			          while((str=buffer.readLine())!=null)
			          {
			        	  sb.append(str);
			          }
			          System.out.println(sb);
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	static void InsertToDB(LoginVal lv)
	{
		
		
		try {
			
			String param="mobile="+lv.getMobile()+"&password="+lv.getPassword();
			System.out.println(param);
			URL aURL = new URL("http://localhost:8889/Login/doInsert"+ "?" +param);
			System.out.println(aURL);
			URLConnection con=aURL.openConnection();
			        //获取连接
		          InputStream is = con.getInputStream();
			          BufferedReader buffer = new BufferedReader(new InputStreamReader(is));
			          String str="";
			          StringBuilder sb=new StringBuilder();
			          while((str=buffer.readLine())!=null)
			          {
			        	  sb.append(str);
			          }
			          System.out.println(sb);
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
