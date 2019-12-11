package cn.wr.SpringBoot.TestDruid;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;

import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
/*
* 配置druid连接池的查看页面啊;实现原理是什么啊
*
* */
public class DruidConfiguration
{
    @Bean
    public ServletRegistrationBean statViewServle()
    {
        //配置数据库连接池的管理啊
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
        //白名单：
        servletRegistrationBean.addInitParameter("allow","192.168.1.218,127.0.0.1");
        //IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的即提示:Sorry, you are not permitted to view this page.
      //  servletRegistrationBean.addInitParameter("deny","192.168.1.100");
        //登录查看信息的账号密码.
        servletRegistrationBean.addInitParameter("loginUsername","wrr");
        servletRegistrationBean.addInitParameter("loginPassword","1234");
        //是否能够重置数据.
        servletRegistrationBean.addInitParameter("resetEnable","false");
        return servletRegistrationBean;
    }
        @Bean
        public ServletRegistrationBean myServlet(){
            ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new HttpServlet() {
                @Override
                protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                    //super.doGet(req, resp);

                    resp.getWriter().print("我是Myservlet"+" "+getInitParameter("loginUsername"));
                    System.out.println("我开始运行了啊!!!!!");
                }
            },"/my/*");
            //白名单：
            servletRegistrationBean.addInitParameter("allow","192.168.1.218,127.0.0.1");
            //IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的即提示:Sorry, you are not permitted to view this page.
            //  servletRegistrationBean.addInitParameter("deny","192.168.1.100");
            //登录查看信息的账号密码.
            servletRegistrationBean.addInitParameter("loginUsername","wrrsssss");
            servletRegistrationBean.addInitParameter("loginPassword","1234");
            //是否能够重置数据.
            servletRegistrationBean.addInitParameter("resetEnable","false");
            return servletRegistrationBean;
        }




    @Bean
    public FilterRegistrationBean statFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        //添加过滤规则.
        filterRegistrationBean.addUrlPatterns("/*");
        //// 忽略过滤格式exclusions（除外)
        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }


    @Bean
    public ServletRegistrationBean  statViewSerlet1()
    {
        ServletRegistrationBean srb=new ServletRegistrationBean(new StatViewServlet(),"/druid");
        srb.addInitParameter("allow","127.0.0.1");
        srb.addInitParameter("loginUserName","wrr");
        srb.addInitParameter("loginPassword","1234");
        return  srb;
    }


    @Bean
    public FilterRegistrationBean  statFilter1(){
        FilterRegistrationBean frb=new FilterRegistrationBean(new WebStatFilter());
        frb.addUrlPatterns("/*");
        frb.addInitParameter("exclusions","*.js");
        return frb;
    }
}
