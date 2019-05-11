package nefu.snow.core.model;

import lombok.Data;

/**
 * Created by LiXiwen on 2019/5/9 10:30.
 **/
@Data
public class Comment {

    private Integer systemId;
    private String snowId;
    private String commentId;
    private String userId;
    private String content;
    private String commentTime;
    private String commentAuthor;



}
