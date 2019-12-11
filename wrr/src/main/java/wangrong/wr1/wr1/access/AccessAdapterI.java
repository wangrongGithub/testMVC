package wangrong.wr1.wr1.access;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import wangrong.wr1.REsult.CodeMsg;

import wangrong.wr1.domain.ShoppingUser;
import wangrong.wr1.redis.RedisService;
import wangrong.wr1.redis.ShoppingUserKey;
import wangrong.wr1.service.ShoppingUserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
@Service
public class AccessAdapterI  extends HandlerInterceptorAdapter{
    @Autowired
    ShoppingUserService userService;
    @Autowired
    RedisService rs;
    ShoppingUser user;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        getCookieValue(request, "JSESSIONID");
System.out.println(request.getRequestURI()+"的请求SESSION是"+request.getSession().toString());
       if(handler instanceof HandlerMethod)
       {
           HandlerMethod hm=(HandlerMethod)handler;
           Access acc= hm.getMethodAnnotation(Access.class);
           if(acc==null)
           {
               return true;
           }
           int an=acc.accessNum();
           int se=acc.seconds();
           boolean nl=acc.needLogin();

           if(nl==true)//需要登陆
           {

               if(isLogin(request,response))
               {
                   Integer num=rs.get(ShoppingUserKey.getMiaoshaAccess, request.getRequestURI() + "" + user.getId(),Integer.class);
                   if(num==null) {
                       ShoppingUserKey.getMiaoshaAccess.setInvaidSeconds(se);
                       rs.set(ShoppingUserKey.getMiaoshaAccess, request.getRequestURI() + "" + user.getId(),1 );
                   }
                   else
                   {
                       if(num>=an)
                       {

                           Render(response,new CodeMsg(50089,"响应次数超过限制，请稍后尝试"));
                           return false;

                       }
                       else {
                           rs.incr(ShoppingUserKey.getMiaoshaAccess, request.getRequestURI() + "" + user.getId());
                           return true;
                       }
                   }

               }
               else
               {
                   Render(response,new CodeMsg(50039,"请登陆后重新尝试"));
                   return false;
               }
           }
       }

        return true ;
    }
    private void Render(HttpServletResponse response,CodeMsg cm) throws IOException {
        OutputStream out = response.getOutputStream();

        response.setContentType("text/html;charset=utf-8");
        out.write(JSON.toJSONString(cm).getBytes("UTF-8"),0,JSON.toJSONString(cm).getBytes("UTF-8").length);
        out.flush();
        out.close();

    }
    private boolean isLogin(HttpServletRequest request, HttpServletResponse response)
    {

        String paramToken = request.getParameter(ShoppingUserService.COOKI_NAME_TOKEN);
        String cookieToken = getCookieValue(request, ShoppingUserService.COOKI_NAME_TOKEN);
        if(StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)) {
            return false;
        }
        String token = StringUtils.isEmpty(paramToken)?cookieToken:paramToken;
        user=userService.getByToken(response, token);
        UserContext.setUser(user);
        return user!=null ? true:false;
    }
    private String getCookieValue(HttpServletRequest request, String cookiName) {
        Cookie[]  cookies = request.getCookies();
        if(cookies!=null)
        {
            for(Cookie cookie : cookies) {
                System.out.println(cookie.getName());
                if(cookie.getName().equals(cookiName)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

}
