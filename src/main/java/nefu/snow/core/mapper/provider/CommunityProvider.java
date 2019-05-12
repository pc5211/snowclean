package nefu.snow.core.mapper.provider;

import nefu.snow.core.model.Article;
import nefu.snow.core.model.User;
import org.apache.ibatis.jdbc.SQL;

/**
 * Created by LiXiwen on 2019/5/12 13:26.
 **/
public class CommunityProvider {
    public String selectByCondition(Article article) {
        return new SQL() {
            {
                SELECT("user_id AS userId,snow_id AS snowId , title,content,time,author");
                FROM("article");
                if (null != article.getSnowId()) {
                    WHERE("snow_id=#{snowId}");
                }
            }
        }.toString();
    }
}
