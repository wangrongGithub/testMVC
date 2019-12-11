package cn.wr.SpringBoot;

import cn.wr.SpringBoot.Security.SecurityConfiguration;
import com.alibaba.druid.filter.logging.Slf4jLogFilter;
import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.DispatcherServlet;

@SpringBootApplication
public class MainApplication {
    public static void main(String[] args)
    {
        SpringApplication.run(MainApplication.class, args);
    }
    @Bean
    public ServletRegistrationBean servletRegistrationBean(DispatcherServlet dispatcherServlet) {
        ServletRegistrationBean servletServletRegistrationBean = new ServletRegistrationBean(dispatcherServlet);
        servletServletRegistrationBean.addUrlMappings("*.do");
        return servletServletRegistrationBean;
    }

}
