package nefu.snow.api;

import nefu.snow.common.RestData;
import nefu.snow.common.SnowException;
import nefu.snow.common.util.JsonUtil;
import nefu.snow.core.model.Article;
import nefu.snow.core.model.Comment;
import nefu.snow.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by LiXiwen on 2019/5/10 17:47.
 **/
@CrossOrigin(origins = "*" , allowCredentials = "true" , allowedHeaders = "*")
@RestController
public class UserApi {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserService userService;

    private  Integer newSnowId;

    @Autowired
    public UserApi(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(value = "/snow/honor" , method = RequestMethod.GET)
    public RestData selectHonorList(){
        try{
            Map<String,Object> data = userService.selectHonorList();
            return new RestData(data);
        }catch (SnowException e){
            return new RestData(1,e.getMessage());
        }
    }

    @RequestMapping(value = "/match/vote" , method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> vote(@RequestParam(value = "from") String fromUserId , @RequestParam(value = "to") String toUserId){
        logger.info("GET vote : from = "+fromUserId+" , to = "+toUserId);
        return userService.vote(fromUserId,toUserId);
    }

    @RequestMapping(value = "/snow/voteState/{userId}" , method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> voteState(@PathVariable(value = "userId") String userId) {
        logger.info("GET voteState{userId} : "+userId);
        return userService.voteState(userId);
    }

    @RequestMapping(value = "/snow/voteSysState" , method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> voteSysState (){
        logger.info("GET voteSysState : ");

        return userService.voteSysState();

    }

    @RequestMapping(value = "/snow/newSnow" , method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> postNewSnow(@RequestBody Article article){
        logger.info("POST newSnow : "+ JsonUtil.getJsonString(article));

        Map<String, Object> rtv = null;
        if(null != article){
            try {
                rtv = userService.newSnow(article);
            }catch(SnowException e){
                e.printStackTrace();
            }
        }
        return rtv;
    }

    @RequestMapping(value = "/snow/snowList" , method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> selectSnowList(){
        logger.info("GET snowList : ");

        Map<String, Object> rtv = null;
        try {
            rtv = userService.selectSnowList();
        }catch (SnowException e){
            e.printStackTrace();
        }
        return rtv;
    }

    @RequestMapping(value = "/snow/snowContent" , method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> selectSnow(@RequestParam("snowId") Integer snowId){
        logger.info("GET snowContent : "+JsonUtil.getJsonString(snowId));
        newSnowId = snowId;
        Map<String,Object> rtv = null;
        try{
            rtv = userService.selectSnow(snowId);
        } catch (SnowException e){
            e.printStackTrace();
        }
        return rtv;
    }

    @RequestMapping(value = "/snow/newComment" , method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> postNewComment(@RequestBody Comment comment){
        logger.info("POST newComment : "+JsonUtil.getJsonString(comment));

        Map<String,Object> data = null;
        comment.setSnowId(newSnowId);
        try{
            data = userService.newComment(comment);
        }catch (SnowException e){
            e.printStackTrace();
        }
        return data;
    }

    @RequestMapping(value = "/snow/comments" , method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> selectCommentList(@RequestParam("snowId") Integer snowId,@RequestParam("page") int page){
        logger.info("POST newComment : snowId = "+snowId+" , page = "+page);

        Map<String,Object> data = null;
        try{
            data = userService.selectSnowCommentList(snowId, page);
        }catch (SnowException e){
            e.printStackTrace();
        }
        return data;
    }

    @RequestMapping(value = "/snow/work" , method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> selectWorkList(){
        logger.info("GET work : ");

        Map<String,Object> data = null;
        try{
            data = userService.selectWork();
        }catch (SnowException e){
            e.printStackTrace();
        }
        return data;
    }

    @RequestMapping(value = "/snow/clock" , method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> updateCstate(@RequestParam("state") Integer cstate,@RequestParam("userId") String userId){
        logger.info("GET clock : ");
        Map<String,Object> data = null;
        return data;

    }


}
