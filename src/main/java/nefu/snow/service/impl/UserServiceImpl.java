package nefu.snow.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import nefu.snow.common.SnowException;
import nefu.snow.common.util.JsonUtil;
import nefu.snow.common.util.TokenUtil;
import nefu.snow.core.mapper.UserMapper;
import nefu.snow.core.model.Honor;
import nefu.snow.core.model.User;
import nefu.snow.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by LiXiwen on 2019/5/9 22:37.
 **/

@Service
public class UserServiceImpl implements UserService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper){
        this.userMapper = userMapper;
    }

    @Override
    public Map<String, Object> postLogin(User user) throws SnowException {
        Map<String, Object> rtv = null;
        List<User> users = userMapper.selectByCondition(user);
        if (null != users && 1 == users.size()) {
            user = users.get(0);
            String type = user.getUserType();
            user.setUserToken(TokenUtil.getToken());
            if (0 < userMapper.updateTokenBySystemId(user)) {
                user = users.get(0);
                rtv = new LinkedHashMap<>(3);
                rtv.put("code",0);
                rtv.put("type", user.getUserType());
                rtv.put("token", user.getUserToken());
            }
        } else {
            throw new SnowException("用户不存在!");
        }
        return rtv;
    }

    @Override
    public int signIn(User user) throws SnowException {
        int rtv ;
        if(0 == userMapper.selectNum(user) ) {
            user.setUserToken(TokenUtil.getToken());
            user.setUserId(user.getUserId().substring(user.getUserId().length() - 6, user.getUserId().length()));
            if (0 < userMapper.insertUser(user)) {
                rtv = 0;
            } else {
                rtv = 1;
            }
        } else {
            throw new SnowException("用户已登记过");
        }
        return rtv;
    }

    public Map<String,Object> selectHonorList() {
        Map<String,Object> rtv = new LinkedHashMap<>();
        Map<String,Object> map = new LinkedHashMap<>();
        PageInfo<Honor> pageInfo = null;
        PageHelper.startPage(1, 5);
        List<Honor> honors = userMapper.selectHonorList();
        logger.info("honors : "+JsonUtil.getJsonString(honors));
        if(null != honors && 0 != honors.size()) {
            pageInfo = new PageInfo<>(honors);
        }
        map.put("totalSize",pageInfo.getTotal());
        map.put("totalPage",pageInfo.getPages());
        map.put("eachPage",pageInfo.getPageSize());
        map.put("nowPage",pageInfo.getPageNum());

        rtv.put("honorList",honors);
        rtv.put("pages",map);

        return rtv;
    }
}
