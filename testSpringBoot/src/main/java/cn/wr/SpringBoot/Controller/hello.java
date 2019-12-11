package cn.wr.SpringBoot.Controller;

import cn.wr.SpringBoot.TestJPA.Entity.User;
import cn.wr.SpringBoot.TestJPA.MyJpaRepository.UserRepository;
import cn.wr.SpringBoot.TestRedis.UserAnnRedis;
import cn.wr.SpringBoot.TestRedis.UserRedis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import sun.nio.ch.sctp.SctpStdSocketOption;

import java.util.List;

@RestController
public class hello {
    @Autowired
     UserRepository userRepository;
    @Autowired
    UserRedis userRedis;
    @Autowired
    UserAnnRedis userAnnRedis;
    static {
        System.out.println("ssss");

    }
static int i=0;

    @RequestMapping("/insertUser/{username}")
    String insertUser(@PathVariable String username)
    {
        //  userRepository.deleteAll();
        User user=new User();
        user.setName(username);
        User u=userRepository.save(user);
        System.out.println(userRepository.findAll());
        if(u==null)
        {
            return "";
        }
        System.out.println("*******************************************");
        userRedis.addUser("WRKey"+u.getId(),3000L,user);
        System.out.println(userRedis.getUser("WRKey"+u.getId()));
        return "OK";
    }





    @RequestMapping("/getUserById/{userId}")
    String getUserById(@PathVariable("userId") Integer id)
    {
      //  userRepository.deleteAll();
        User user=userAnnRedis.findById(id);
        System.out.println("controller:  "+i++);

        return "OK";
    }
    @RequestMapping("/userList")
    public ModelAndView findUser(ModelMap model)
    {
        //查询用户啊
        List<User> users=userRepository.findAll();
        model.addAttribute("users",users);
        return new ModelAndView("userDetail");
    }

    @RequestMapping("/userDelete/{id}")
    //@PathVariable 获取URL中的动态参数啊
    // @RequestParam:获取request请求中的参数啊
    public ModelAndView deleteUser(@PathVariable Integer id, ModelMap model)
    {
        //查询用户啊
        userRepository.delete(id);
        List<User> users=userRepository.findAll();
        model.addAttribute("users",users);
        return new ModelAndView("userDetail");
    }

    @RequestMapping("/insert")
    //@PathVariable 获取URL中的动态参数啊
    // @RequestParam:获取request请求中的参数啊

    public ModelAndView insert( ModelMap model)
    {
        //查询用户啊

        return new ModelAndView("userInsert");
    }

}
