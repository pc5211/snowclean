package nefu.snow.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 示例
 */

@Data
public class User {

    private Integer systemId;

    @JsonProperty(value = "userId")
    private String userId;

    @JsonProperty(value = "name")
    private String userName;

    @JsonProperty(value = "password")
    private String userPassword;

    private String userToken;
    /**
     * type: A = 普通组员 | B = 组长 | C = 管理员
     */
    private String userType;
    private Integer userVoteNum;

    @JsonProperty(value = "vstate")
    private Integer userVstate;

    private Integer cstate;

    private Integer userScore;

    @JsonProperty(value = "workAge")
    private String userWorkAge;

    @JsonProperty(value = "address")
    private String userAddress;

    private String userReward;

    @JsonProperty(value = "sex")
    private String userSex;

    private Integer userStartTime;
    private Integer userEndTime;
    private Integer userWorkTime;
    private String userTask;
    private String userTeamId;

}
