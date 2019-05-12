package nefu.snow.core.model;

import lombok.Data;

import java.util.Date;

/**
 * Created by LiXiwen on 2019/5/9 10:30.
 **/
@Data
public class Article {

    private Integer snowId;
    private String userId;
    private String title;
    private String content;
    private Date time;
    private String author;
}
