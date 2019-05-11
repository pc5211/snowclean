package nefu.snow.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

/**
 * Created by LiXiwen on 2019/5/9 10:30.
 **/
@Data
public class Team {

    private Integer systemId;
    private String teamId;
    private String teamName;
    @JsonProperty(value = "password")
    private String teamPassword;
    @JsonProperty(value = "taskTitle")
    private String teamTaskTitle;
    @JsonProperty(value = "taskContent")
    private String teamTaskContent;
    @JsonProperty(value = "publishTime")
    private Date teamTaskPublishTime;


}
