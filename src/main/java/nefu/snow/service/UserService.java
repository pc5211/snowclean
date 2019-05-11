package nefu.snow.service;

import nefu.snow.common.SnowException;
import nefu.snow.core.model.User;

import java.util.Map;

/**
 * Created by LiXiwen on 2019/5/9 22:37.
 **/
public interface UserService {

    /**
     * 用户登陆
     *
     * @param user 用户实体
     * @return 结果集
     * @throws SnowException 异常信息
     */
    Map<String, Object> postLogin(User user) throws SnowException;

    /**
     *
     * @param user
     * @return
     * @throws SnowException
     */
    int signIn(User user) throws SnowException;

    Map<String,Object> selectHonorList() throws SnowException;


}
