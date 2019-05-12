package nefu.snow.core.model;

import lombok.Data;

import java.util.Date;

/**
 * Created by LiXiwen on 2019/5/9 10:30.
 **/
@Data
public class Comment {

    private Integer snowId;
    private Integer commentId;
    private String userId;
    private String content;
    private Date commentTime;
    private String commentAuthor;



}
