/*
 * Copyright (c) 2014-2018 www.itgardener.cn. All rights reserved.
 */

package nefu.snow.common.util;

import nefu.snow.core.mapper.UserMapper;
import nefu.snow.core.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

/**
 * Created by LiXiwen on 2019/5/9 17:00.
 **/

@Component
public class TokenUtil {

    private static UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        TokenUtil.userMapper = userMapper;
    }


    public static User getUserByToken(HttpServletRequest request) {
        User user = null;
        String token = request.getHeader("token");
        if (null != token) {
            User userCondition = new User();
            userCondition.setUserToken(token);
            List<User> users = userMapper.selectByCondition(userCondition);
            if (1 == users.size()) {
                user = users.get(0);
            }
        }
        return user;
    }

    public static String getPassword() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    public static String getToken() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
