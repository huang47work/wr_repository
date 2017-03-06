package wr.com.controller.message;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import wr.com.controller.BaseController;
import wr.com.pojo.Message;
import wr.com.service.impl.MessageServiceImpl;
import wr.com.utils.DateUtil;
import wr.com.utils.ErrorCode;
import wr.com.utils.Result;
import wr.com.utils.UUid;
/**
 * @author zhaode
 * @created 2016.12.6
 * */
@RestController
@RequestMapping(value="/message")
@Api(value = "advice", description = "消息中心接口")

public class MessageController extends BaseController{
	
	@Autowired
	MessageServiceImpl messageServiceImpl;
	
    @RequestMapping(value = "/addMessage",method = RequestMethod.POST)
    @ApiOperation(value = "增加消息")
	public Result<Object> addMessage(@Valid Message message){	
		if (message == null) {
			return Result.wrapErrorResult(ErrorCode.MESSAGE_NOT_NULL);
		} 
    	message.setDate(DateUtil.getDate());
		message.setMid(UUid.getUuid());
		message.setStatue("0");
		messageServiceImpl.add(message);		
		return Result.wrapSuccessfulResult("添加成功");
	}
    
    @RequestMapping(value = "/getMessage", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    @ApiOperation(value = "通过type来获取消息")
    public Result<Object> getMessagesByType(String type){
    	//此处为session中的userId；
    	lookSession();
    	String userId = getSessionUser().getUserId();
    	if (type.equals("3")) {
	    	List<Message> list = messageServiceImpl.selectRecentlyDate(DateUtil.getNearDay(-3),userId);
	    	return Result.wrapSuccessfulResult(list);
		}
    	List<Message> list = messageServiceImpl.getMessagesByType(type,userId);
	    	System.out.println(Result.wrapSuccessfulResult(list));
	    	return Result.wrapSuccessfulResult(list);
    }
    
    @RequestMapping(value = "/changeStatue", method = RequestMethod.POST)
    @ApiOperation(value = "通过mid数组来更改多个消息的状态")
    public Result<Object> changeStatues1(String[] arr,String statue){
    	System.out.println(Arrays.toString(arr));
    	if (arr == null) {
			return Result.wrapErrorResult(ErrorCode.MESSAGE_NOT_NULL);
		}
    	List<String> mids = Arrays.asList(arr); 
    	messageServiceImpl.changeStatues(mids, statue);
		return Result.wrapSuccessfulResult("更改已读成功");
    }

    @RequestMapping(value = "/deleteByMids", method = RequestMethod.POST)
    @ApiOperation(value = "通过mid数组来删除多个消息")
    public Result<Object> deleteByMids(String[] arr){
    	System.out.println(Arrays.toString(arr));
    	if (arr == null) {
			return Result.wrapErrorResult(ErrorCode.MESSAGE_NOT_NULL);
		}
    	List<String> mids = Arrays.asList(arr); 
    	messageServiceImpl.deleteByMids(mids);
    	return Result.wrapSuccessfulResult("删除成功");
    }
}
