package nefu.snow.service;

import nefu.snow.common.SnowException;
import nefu.snow.core.model.Article;
import nefu.snow.core.model.Comment;
import nefu.snow.core.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * Created by LiXiwen on 2019/5/9 22:37.
 **/
public interface UserService {

    /**
     * 用户登陆
     *
     * @param user 用户实体
     * @return 结果集
     * @throws SnowException 异常信息
     */
    Map<String, Object> postLogin(User user) throws SnowException;

    /**
     *
     * @param user
     * @return
     * @throws SnowException
     */
    int signIn(User user) throws SnowException;

    Map<String,Object> selectHonorList() throws SnowException;

    Map<String,Object> voteState(String userId) ;

    Map<String,Object> voteSysState();

    Map<String,Object> vote(String fromUserId , String toUserId);

    Map<String,Object> newSnow(Article article) throws SnowException;

    Map<String,Object> newComment(Comment comment) throws SnowException;

    Map<String,Object> selectSnowList() throws SnowException;

    Map<String,Object> selectSnowCommentList(Integer snowId,int page) throws SnowException;

    Map<String,Object> selectSnow(Integer snowId) throws SnowException;

    Map<String,Object> selectWork() throws SnowException;

    Map<String,Object> updateCstate(User user);







}
