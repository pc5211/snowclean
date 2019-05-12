package nefu.snow.core.mapper;

import nefu.snow.core.mapper.provider.UserProvider;
import nefu.snow.core.model.Honor;
import nefu.snow.core.model.User;
import nefu.snow.core.model.Work;
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
    @Insert("INSERT INTO user(user_id,user_name,user_sex,user_password,user_workage,user_address,user_token,user_type,user_vstate,user_cstate,user_votenum,user_score,user_worktime,user_starttime,user_endtime) VALUES(#{userId},#{userName},#{userSex}," +
            "#{userPassword},#{userWorkAge},#{userAddress},#{userToken},'A','0','1','0','0','0','0','0')")
    int insertUser(User user);


    @Select("SELECT user_name AS userName,user_votenum AS vote ,user_id AS userId from user ORDER BY user_votenum DESC")
    List<Honor> selectHonorList();


    @Select("SELECT user_vstate from user WHERE user_id=#{userId}")
    int voteState(String userId);

    @Select("SELECT system_state from system")
    int voteSysState();

    @Select("SELECT user_votenum from user WHERE user_id=#{toUserId}")
    int selectVoteNumByUserId(String toUserId);

    @Update("UPDATE user SET user_votenum=#{voteNum} WHERE user_id=#{toUserId} ")
    int updateVote(@Param("voteNum") int voteNum , @Param("toUserId") String toUserId);

    @Update("UPDATE user SET user_vstate=#{vstate} WHERE user_id=#{fromUserId} ")
    int updateVstate(@Param("vstate") int vstate , @Param("fromUserId") String fromUserId);

    @SelectProvider(type = UserProvider.class , method = "selectUserList")
    List<User> selectWork();

    @Select("SELECT user_id AS userId,user_name AS userName,user_score AS userScore,user_reward AS userReward from user ORDER BY user_score")
    List<User> selectOrder();

    /**
     * 注解批量插入数据
     * MyBatis会把UserDAO的insertAll方法中的List类型的参数存入一个Map中,
     * 默认的key是”list”, 可以用@Param注解自定义名称,
     * MyBatis在调用@InsertProvide指定的方法时将此map作为参数传入,
     * 所有代码中使用List users = (List) map.get(“list”);获取list参数.
     *
     * @param works
     * @return
     */
    @InsertProvider(type = UserProvider.class , method = "batchInsert")
    boolean insertNewWork(List<Work> works);

    @SelectProvider(type = UserProvider.class , method = "selectWorkList")
    List<Work> selectNewWork();

    @Delete("TRUNCATE TABLE work")
    boolean deleteNewwork();


}
