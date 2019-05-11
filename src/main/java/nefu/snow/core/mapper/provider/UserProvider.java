package nefu.snow.core.mapper.provider;

import nefu.snow.core.model.User;
import org.apache.ibatis.jdbc.SQL;

/**
 * Created by LiXiwen on 2019/5/9 22:29.
 * system_id	bigint
 * user_id	varchar
 * user_name	varchar
 * user_password	varchar
 * user_token	varchar
 * user_type	varchar
 * user_votenum	int	11
 * user_vstate	int	20
 * user_cstate	int	20
 * user_score	bigint
 * user_workage	varchar
 * user_address	varchar
 * user_reward	varchar
 * user_sex	varchar
 * user_worktime	bigint
 * user_task	varchar
 * user_teamid	varchar
 * user_starttime	bigint
 * user_endtime	bigint
 **/
public class UserProvider {
    public String selectByCondition(User user) {
        return new SQL() {
            {
                SELECT("system_id AS systemId,user_id AS userId,user_name AS userName, user_password AS userPassword,user_token AS userToken," +
                        "user_type AS userType,user_votenum AS userVoteNum,user_vstate AS userVstate ,user_cstate AS cstate,user_score AS userScore," +
                        "user_workage AS userWorkAge,user_address AS userAddress,user_reward AS userReward,user_sex AS userSex,user_worktime as userWorkTime," +
                        "user_starttime AS userStartTime,user_endtime AS userEndTime");
                FROM("user");
                if (null != user.getUserId()) {
                    WHERE("user_id=#{userId}");
                }
                if (null != user.getUserPassword()) {
                    WHERE("user_password=#{userPassword}");
                }
            }
        }.toString();
    }

    public String selectByType() {
        return new SQL() {
            {
                SELECT("user_student_id AS studentId, user_student_name AS studentName");
                FROM("user");
                WHERE("user_type=2");
            }

        }.toString();

    }

    public String selectByToken(User user) {
        return new SQL() {
            {
                SELECT("user_system_id AS systemId, user_student_id AS studentId, user_password AS userPassword, " +
                        "user_type AS type, user_token AS token");
                FROM("user");
                WHERE("user_token=#{token}");
            }
        }.toString();

    }



}
