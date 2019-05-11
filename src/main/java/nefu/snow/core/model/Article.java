package nefu.snow.core.model;

import lombok.Data;

/**
 * Created by LiXiwen on 2019/5/9 10:30.
 **/
@Data
public class Article {

    private Integer systemId;
    private String snowId;
    private String userId;
    private String title;
    private String content;
    private String time;
    private String author;
}
