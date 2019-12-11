package wangrong.wr1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import wangrong.wr1.Test;
import wangrong.wr1.rabbitmq.MQSender;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

@Controller

@RequestMapping("/mq")
public class testController {
    @Autowired
    MQSender ms;
    @RequestMapping("/mq1")
    @ResponseBody
    public String mq()
    {
      //  ms.sendTopic("wangrongaaa");
       // ms.sendFanout("wangrongaaa");
        ms.sendHeader("wangrongaaa");
        return "sucesss";
    }
    @RequestMapping("/test")
    @ResponseBody
    public String test(HttpServletResponse response) throws Exception {

       // OutputStream os=response.getOutputStream();

        return Test.main5(null);
    }

}
