package com.fastjson.poc;
import com.alibaba.fastjson.parser.ParserConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class JSONController {
	  @RequestMapping(value={"/"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json;charset=UTF-8"})
	  @ResponseBody
	  public Object getUser()
	  {
	    User user = new User();
	    user.setName("Bob");
	    user.setAge(Integer.valueOf(25));
	    
	    return user;
	  }
	  
	  @RequestMapping(value={"/"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, produces={"application/json;charset=UTF-8"})
	  @ResponseBody
	  public Object setUser(@RequestBody User user)
	  {
		ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
	    user.setAge(Integer.valueOf(20));
	    return user;
	  }
}
