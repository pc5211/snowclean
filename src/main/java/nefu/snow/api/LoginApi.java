package nefu.snow.api;

import nefu.snow.common.ErrorMessage;
import nefu.snow.common.RestData;
import nefu.snow.common.SnowException;
import nefu.snow.common.util.JsonUtil;
import nefu.snow.core.model.User;
import nefu.snow.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LiXiwen on 2019/5/9 16:42.
 **/
@CrossOrigin(origins = "*" , allowCredentials = "true" , allowedHeaders = "*")
@RestController
public class LoginApi {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserService userService;

    @Autowired
    public LoginApi(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/snow/login", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> login(@RequestBody User user){
        logger.info("POST login : " + JsonUtil.getJsonString(user));

        Map<String, Object> map1 = new HashMap<String,Object>();
        if (null == user.getUserId() && 1 > user.getUserId().length() &&
                null == user.getUserPassword() ) {
            Map<String, Object> map = new HashMap<String,Object>();
            map.put("code","1");
            map.put("message",ErrorMessage.PARAMATER_ERROR);
            return map;
        }
        try {
            Map<String, Object> data = userService.postLogin(user);
            return data;
        } catch (SnowException e) {
            map1.put("code","1");
            map1.put("message",e.getMessage());
            return map1;
        }
    }

    /**
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/snow/signin" , method = RequestMethod.POST)
    @ResponseBody
    public RestData  signin(@RequestBody User user){
        logger.info("POST signin : "+JsonUtil.getJsonString(user));

        if(null ==user.getUserName() && null == user.getUserSex() && null == user.getUserId() &&
                null == user.getUserWorkAge() && null == user.getUserAddress() &&
                null ==user.getUserPassword() ){
            return new RestData(1, ErrorMessage.PARAMATER_ERROR);
        }
        try{
            int a = userService.signIn(user);
            return new RestData(a);
        }catch (SnowException e){
            return new RestData(1,e.getMessage());
        }
    }


}