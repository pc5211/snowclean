package nefu.snow.core.mapper.provider;

import nefu.snow.core.model.User;
import nefu.snow.core.model.Work;
import org.apache.ibatis.jdbc.SQL;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

/**
 *
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

    public String selectUserList() {
        return new SQL() {
            {
                SELECT("system_id AS systemId,user_id AS userId,user_name AS userName, user_password AS userPassword,user_token AS userToken," +
                        "user_type AS userType,user_votenum AS userVoteNum,user_vstate AS userVstate ,user_cstate AS cstate,user_score AS userScore," +
                        "user_workage AS userWorkAge,user_address AS userAddress,user_reward AS userReward,user_sex AS userSex,user_worktime as userWorkTime," +
                        "user_starttime AS userStartTime,user_endtime AS userEndTime");
                FROM("user");
            }
        }.toString();
    }

    public String selectWorkList() {
        return new SQL() {
            {
                SELECT("userId,userName,score,reward");
                FROM("work");
            }
        }.toString();
    }

    /**
     * 批量插入
     * @param map
     * @return
     */
    public String batchInsert(Map map) {
        List<Work> works = (List<Work>) map.get("list");
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO work (userId,userName,score,reward) VALUES ");
        MessageFormat mf = new MessageFormat(
                "(#'{'list[{0}].userId}, #'{'list[{0}].userName}, #'{'list[{0}].score},#'{'list[{0}].reward})"
        );

        for (int i = 0; i <works.size(); i++) {
            sb.append(mf.format(new Object[] {i}));
            if (i < works.size() - 1)
                sb.append(",");
        }
        return sb.toString();
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
