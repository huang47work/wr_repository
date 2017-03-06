package wr.com.controller.advice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import wr.com.mapper.AdviceMapper;
import wr.com.mapper.UserMapper;
import wr.com.pojo.Advice;
import wr.com.pojo.Message;
import wr.com.service.MessageService;
import wr.com.service.impl.AdviceServiceImp;
import wr.com.utils.BeanAllFieldUtil;
import wr.com.utils.DateUtil;
import wr.com.utils.ErrorCode;
import wr.com.utils.QiNiuUtil;
import wr.com.utils.Result;
import wr.com.utils.UUid;

@RestController
@RequestMapping("/advice")
@Api(value = "advice", description = "建议接口")
public class AdviceController {
	@Autowired
	AdviceMapper adviceMapper;
	@Autowired
	AdviceServiceImp adviceServiceImp;
	@Autowired
	UserMapper userMapper;
	@Autowired
	MessageService messageService;
	@RequestMapping(value ="/addAdvice",method = RequestMethod.POST)
	@ApiOperation(value = "增加建议")
	public Result<Object> addAdvice(@Valid Advice advice,@RequestParam(required=false,value="file") MultipartFile  file) throws IOException {
		System.out.println("%%%%%%%%%%%%%%%%%%");
		System.out.println(file.getOriginalFilename());
		System.out.println(advice);
		byte[] bytes = file.getBytes();
//		CommonsMultipartFile cf= (CommonsMultipartFile)file; //这个myfile是MultipartFile的
//        DiskFileItem fi = (DiskFileItem)cf.getFileItem(); 
//        File file1 = fi.getStoreLocation(); 
		advice.setAid(UUid.getUuid());
		advice.setDate(new Date());
		advice.setStatus("0");
		advice.setAstModifiedTime(new Date());
		Random ne=new Random();//实例化一个random的对象ne
		int random=ne.nextInt(9999-1000+1)+1000;//为变量赋随机值1000-9999
		String fileName = advice.getPhone()+DateUtil.getNumDate()+random+file.getOriginalFilename();
		System.out.println(fileName);
		String url = null;
		if (!file.isEmpty()) {
			try {
				url = QiNiuUtil.upload(bytes, fileName);
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("上传失败");
				return Result.wrapErrorResult(ErrorCode.OPERATION_FAILED);
			}
		}
		System.out.println(url);
		advice.setFileUrl(url);
		adviceServiceImp.addAdvice(advice);
		try {
			userMapper.findByPhone(advice.getPhone());
		} catch (Exception e) {
			return Result.wrapSuccessfulResult("添加成功");
		}
			Message message = new Message();
			message.setDate(DateUtil.getDate());
			message.setMid(UUid.getUuid());
			message.setType("3");
			message.setContent(advice.getContent());
			message.setContentHead("建议消息"+DateUtil.getNumDate());
			messageService.add(message);
		return Result.wrapSuccessfulResult("添加成功");
	}
	
	@RequestMapping(value ="/findByStatus",method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "通过建议来查询")
	public Result<Object> findByStatus(String status){
		if (status.equals("2")) {
			List<Advice> list = adviceServiceImp.findAll();
			System.out.println(list);
			List<Map> listMap = new ArrayList<Map>();
			for (int i = 0; i < list.size(); i++) {
				Map map = BeanAllFieldUtil.beanToMap(list.get(i));
				if (map.get("status").equals("0")) {
					map.put("aa", "active");
					map.put("ss", "qq");
				}else  {
					map.put("ss", "active");
					map.put("aa", "qq");
				}
				listMap.add(map);
			}
			return Result.wrapSuccessfulResult(listMap);
		}
		System.out.println(status);
		List<Advice> list =  adviceServiceImp.findByStatus(status);
		List<Map> listMap = new ArrayList<Map>();
		for (int i = 0; i < list.size(); i++) {
			Map map = BeanAllFieldUtil.beanToMap(list.get(i));
			if (status.equals("0")) {
				map.put("aa", "active");
				map.put("ss", "qq");
			}else  {
				map.put("ss", "active");
				map.put("aa", "qq");
			}
			listMap.add(map);
		}
		return Result.wrapSuccessfulResult(listMap);
	}
	@RequestMapping(value ="/findByAid",method = RequestMethod.POST)
	@ApiOperation(value = "通过aid查询")
	public Result<Object> findByAid(String aid){
		Advice advice = adviceMapper.selectByPrimaryKey(aid);
		return Result.wrapSuccessfulResult(advice);
	}
	@RequestMapping(value ="/getAll",method = RequestMethod.POST)
	@ApiOperation(value = "获得所有建议")
	public Result<Object> findAll(){
		List<Advice> list = adviceServiceImp.findAll();
		return Result.wrapSuccessfulResult(list);
	}
	
	@RequestMapping(value = "/changeStatus",method = RequestMethod.POST)
	@ApiOperation(value = "更改状态")
	public Result<Object> changeStatusById(String mid, String status){
		adviceServiceImp.changeStatusById(mid, status);
		return Result.wrapSuccessfulResult("更改成功");
	}
}
