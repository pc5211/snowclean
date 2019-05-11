package nefu.snow.core.mapper;

import nefu.snow.core.mapper.provider.UserProvider;
import nefu.snow.core.model.Honor;
import nefu.snow.core.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by LiXiwen on 2019/5/9 16:52.
 **/
@Repository
@Mapper
public interface UserMapper {

    /**
     * 根据条件筛选
     *
     * @param user
     * @return user的集合
     */
    @SelectProvider(type = UserProvider.class, method = "selectByCondition")
    List<User> selectByCondition(User user);

    @Select("select count(*) from user where user_id=#{userId}")
    int selectNum(User user);

    @Select("select count(user_id) from user;")
    int selectTotalNum();
    /**
     * 设置token
     *
     * @param user
     * @return 更新数量
     */
    @Update("UPDATE user SET user_token=#{userToken} WHERE user_id=#{userId}")
    int updateTokenBySystemId(User user);

    /**
     *
     * @param user
     * @return
     */
    @Insert("INSERT INTO user(user_id,user_name,user_sex,user_password,user_workage,user_address,user_token,user_type) VALUES(#{userId},#{userName},#{userSex}," +
            "#{userPassword},#{userWorkAge},#{userAddress},#{userToken},'A')")
    int insertUser(User user);


    @Select("SELECT user_name AS userName,user_votenum AS vote ,user_id AS userId from user ORDER BY user_votenum DESC")
    List<Honor> selectHonorList();

}
