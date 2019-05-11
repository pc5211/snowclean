package nefu.snow.api;

import nefu.snow.common.RestData;
import nefu.snow.common.SnowException;
import nefu.snow.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by LiXiwen on 2019/5/10 17:47.
 **/
@CrossOrigin(origins = "*" , allowCredentials = "true" , allowedHeaders = "*")
@RestController
public class UserApi {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserService userService;

    @Autowired
    public UserApi(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(value = "/snow/honor" , method = RequestMethod.GET)
    public RestData selectHonorList(){
        try{
            Map<String,Object> data = userService.selectHonorList();
            return new RestData(data);
        }catch (SnowException e){
            return new RestData(1,e.getMessage());
        }
    }


}
