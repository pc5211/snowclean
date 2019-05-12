package nefu.snow.core.mapper;

import nefu.snow.core.mapper.provider.CommunityProvider;
import nefu.snow.core.model.Article;
import nefu.snow.core.model.Comment;
import nefu.snow.core.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by LiXiwen on 2019/5/10 18:31.
 **/

@Repository
@Mapper
public interface CommunityMapper {

    @Select("SELECT user_name from user WHERE user_id=#{userId}")
    String selectAuthorById(String userId);

    @Insert("INSERT INTO article (user_id,author,title,content,time) VALUES(#{userId},#{author},#{title},#{content},#{time})")
    int insertArticle(Article article);

    @Insert("INSERT INTO comment (snow_id,comment_id,user_id,comment_content,comment_time,comment_author) VALUES(#{snowId},#{commentId},#{userId},#{content},#{commentTime},#{commentAuthor})")
    int insertComment(Comment comment);

    @Select("SELECT user_id AS userId,snow_id AS snowId,title,content,time,author from article ORDER BY time DESC")
    List<Article> selectSnowList();

    @SelectProvider(type = CommunityProvider.class , method = "selectByCondition")
    Article selectSnowById(Article article);

    @Select("SELECT snow_id AS snowId,comment_id AS commentId , user_id AS userId , comment_content AS content,comment_time AS commentTime,comment_author AS commentAuthor from comment WHERE snow_id=#{snowId}")
    List<Comment> selectCommentList(Comment comment);





}
