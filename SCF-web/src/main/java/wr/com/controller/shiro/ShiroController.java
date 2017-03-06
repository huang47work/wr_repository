package wr.com.controller.shiro;

import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import wr.com.controller.BaseController;
import wr.com.mapper.IdentityNumMapper;
import wr.com.mapper.UserMapper;
import wr.com.pojo.IdentityNum;
import wr.com.pojo.User;
import wr.com.utils.DateUtil;
import wr.com.utils.ErrorCode;
import wr.com.utils.Result;
import wr.com.utils.UmpPostMsgUtil;



/**
 * @author linaz
 * @created 2016.08.16 11:02
 */
@RestController
@RequestMapping("/user")
@Api(value = "advice", description = "登录注册接口")

public class ShiroController extends BaseController{
	private  int flow=0;
    private static final Logger logger = LoggerFactory.getLogger(ShiroController.class);

    @Autowired
    private UserMapper userRepository;
    @Autowired
    private IdentityNumMapper identityNumMapper;
    //跳转到主页
    @RequestMapping(value="/loginss",method=RequestMethod.GET)
    public Result<Object> loginForm(){
        return  Result.wrapSuccessfulResult("跳转成功");
    }
    //登录验证
    @RequestMapping(value="/login",method = RequestMethod.POST)
    @ApiOperation(value = "通过username和password进行用户登录")
    public Result<Object> login(@Valid User user,HttpServletRequest request){
        String username = user.getUserName();
       System.out.println(username);
       System.out.println(user.getPassword());
       UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());
        //获取当前的Subject
        Subject currentUser = SecurityUtils.getSubject();
        try {
            currentUser.login(token);
        }catch(Exception e){
            e.printStackTrace();
            return Result.wrapErrorResult(ErrorCode.USER_FAIL_LOGIN);
        }
        //验证是否登录成功
        if(currentUser.isAuthenticated()){
            logger.info("用户[" + username + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
            User user2 = userRepository.findByUserNameOrPhone(username);
            Session session = currentUser.getSession(); 
            System.out.println(session.getId());
            session.setAttribute("user", user2);
            user2.setPassword("不告诉你");
            lookSession();
//            session.setAttribute(user, user2);
//            getSession().setAttribute("user", user2);
//            lookSession();
            return Result.wrapSuccessfulResult(user2);
        }else{
            token.clear();
            return Result.wrapErrorResult(ErrorCode.USER_FAIL_LOGIN);
        }
    }
    //创建用户
    @RequestMapping(value = "/userAdd", method = RequestMethod.POST)
    @ApiOperation(value = "用户注册")
    public Result<Object> createUser( 
    		@Valid User user,
    		@Valid IdentityNum identity) throws Exception {
    	/**
    	 * 短信验证
    	 * */
    	if (identity.getIdentitynum().equals("")||identity.getIdentitynum().equals(null)) {
    		return Result.wrapErrorResult("","请输入验证码");
    	}
    	if (user.getPhone().equals("")||user.getPhone().equals(null)||user.getPassword().equals(null)||user.getPassword().equals(""   )) {
    		return Result.wrapErrorResult("","请输入手机号或者密码");

		}
    	IdentityNum ident = null;
    	System.out.println(identity.getIdentitynum().equals(""));
    	try {
        	 ident = identityNumMapper.selectByMobile(user.getPhone());
		} catch (Exception e) {
			return Result.wrapErrorResult(ErrorCode.USER_MESSAGE_VERIFY_CODE);
		}
    	
    	String identityNum = identity.getIdentitynum();
    	String identi = ident.getIdentitynum();
    	System.out.println("访问到"+identityNum+"&&&"+identi);
    	if (!identityNum.equals(identi)) {
    		identityNumMapper.deleteByMobile(user.getPhone());
    		return Result.wrapErrorResult(ErrorCode.USER_IDENTITUNUMBER_WRONG);
		}
		identityNumMapper.deleteByMobile(user.getPhone());
    	User user1;
    	System.out.println(user);
    	/**
    	 * 用户名查重
    	 * */
    	try {
    	    user1 = userRepository.findByUserNameOrPhone(user.getPhone());
    	    System.out.println(user1);
		} catch (Exception e) {
			System.out.println("该用户名已存在");
			return Result.wrapErrorResult(ErrorCode.USER_ALREADY_EXISTS);
		}
    	if (user1 != null) {
    		System.out.println("该用户名已存在");
			return Result.wrapErrorResult(ErrorCode.USER_ALREADY_EXISTS);
        } else {
        	flow++;
    		if (flow==10000) {
    			flow = 0;
    		}
        	user.setName("");
        	user.setUserId("1"+DateUtil.getNumDate().substring(6,8)+String.format("%04d",flow));
        	userRepository.insert(user);
        	System.out.println("注册成功");
        	return Result.wrapSuccessfulResult("注册成功");
        }
        
    }
    @RequestMapping(value="/getIdentityNum",method = RequestMethod.POST)
    @ApiOperation(value = "给指定手机号发送验证码")
    public Result<Object> sendIdentityNum(IdentityNum identity){
    	String mobile = identity.getMobile();
    	System.out.println(mobile);
    	try {
			UmpPostMsgUtil.sendMsg(mobile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Result.wrapSuccessfulResult("发送失败");
			
		}
    	identity.setIdentitynum(UmpPostMsgUtil.getidentityNum());
		identityNumMapper.insertSelective(identity);
		Timer timer = new Timer();  
        timer.schedule(new TimerTask() {  
            public void run() { 
            	identityNumMapper.deleteByMobile(identity.getMobile());	
             }  
        }, 300000);
//    	1min = 6000ms
        return Result.wrapSuccessfulResult("发送成功");
    }
    @RequestMapping(value="/logout",method=RequestMethod.POST)
    @ApiOperation(value = "退出登录")
    public Result<Object> logout(){
        //使用权限管理工具进行用户的退出，跳出登录，给出提示信息
        SecurityUtils.getSubject().logout();
        return Result.wrapSuccessfulResult("退出成功");
    }
    
   // ------------------------------------------------------
  
    
//    @RequestMapping("/user")
//    public String getUserList(Map<String, Object> model) throws ParseException {
//        model.put("userList", userService.search("","",1,15));
//        return "/shiro/user";
//    }

//    @RequestMapping("/user/edit/{id}")
//    public String getUserList(
//            @PathVariable int id){
//        logger.info("------进入用户信息修改-------");
//        return "/shiro/user_edit";
//    }
}