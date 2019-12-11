package wangrong.wr1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;



@SpringBootApplication
public class MainApp{
	
	 public static void main(String[] args) {
			SpringApplication.run(MainApp.class, "--server.port=8889");
		}


}
