package wr.com.controller.enterticket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import wr.com.controller.BaseController;
import wr.com.mapper.EntryticketMapper;
import wr.com.mapper.GoodsQualityMapper;
import wr.com.mapper.TicketGoodsMapper;
import wr.com.pojo.Entryticket;
import wr.com.pojo.GoodsQuality;
import wr.com.pojo.SearchEntryticket;
import wr.com.pojo.TicketGoods;
import wr.com.service.EntryticketService;
import wr.com.utils.ArrColUtil;
import wr.com.utils.BeanAllFieldUtil;
import wr.com.utils.DateUtil;
import wr.com.utils.ErrorCode;
import wr.com.utils.QiNiuUtil;
import wr.com.utils.Result;
import wr.com.utils.UUid;

@RestController
@RequestMapping("/enterticket")
@Api(value = "advice", description = "入库单接口")

public class EnterticketController extends BaseController{  
	
	private String enterId;
	@Autowired
	GoodsQualityMapper goodsQualityMapper;
	@Autowired
	TicketGoodsMapper ticketGoodsMapper;
	@Autowired
	EntryticketMapper entryticketMapper;
	@Autowired
	EntryticketService entryticketServiceImpl;
	@RequestMapping(value ="/addd",method = RequestMethod.POST)
	@ApiOperation(value = "增加入库单//暂时不用")
	public Result<Object> submitUserList_3(String list){
		System.out.println("访问到ddd*****************************************************");
		System.out.println(list);
//		String a = "[{\"ID\":0,\"Biaoti\":0,\"Author\":0},{\"ID\":1,\"Biaoti\":2,\"Author\":3},{\"ID\":2,\"Biaoti\":4,\"Author\":6}]";
//		[{"gname":"玉米","piece":"KG","amount":"1","univalent":"1","sum":"1"},{"gname":"玉米","piece":"KG","amount":"2","univalent":"2","sum":"2"}]
		if (enterId==null) {
			return Result.wrapErrorResult(ErrorCode.OPERATION_FAILED);
		}
		JSONArray array = JSONArray.fromObject(list);
		System.out.println(array.size());
		String[] arrId = new String[array.size()];
		String[] arrName = new String[array.size()];
		try {
			for (int i = 0; i < array.size(); i++) {
				TicketGoods goods = new TicketGoods();
				goods.setGid(UUid.getUuid());
				goods.setGname(array.getJSONObject(i).getString("gname"));
				goods.setPiece(array.getJSONObject(i).getString("piece"));
				goods.setAmount(array.getJSONObject(i).getDouble("amount"));
				goods.setUnivalent(array.getJSONObject(i).getDouble("univalent"));
				goods.setSum(array.getJSONObject(i).getDouble("sum"));
				System.out.println(goods);
				arrId[i] = goods.getGid();
				arrName[i] = goods.getGname();
				ticketGoodsMapper.insertSelective(goods);
			}
		} catch (Exception e) {
			return Result.wrapErrorResult(ErrorCode.OPERATION_FAILED);
		}
		
		Entryticket entryticket = new Entryticket();
		entryticket.setGoodsId(ArrColUtil.arrToColString(arrId));
		entryticket.setGoodsName(ArrColUtil.arrToColString(arrName));
		entryticket.setEnterId(enterId);
		entryticketServiceImpl.changeTicket(entryticket);
		return Result.wrapSuccessfulResult("添加成功");
    }
	@RequestMapping(value ="/add",method = RequestMethod.POST)
	@ApiOperation(value = "增加入库单//暂时不用")
	public Result<Object> addTicket(Entryticket entryticket,@RequestParam(required=false,value="files") MultipartFile[] files) throws IOException {
		System.out.println(entryticket);
		entryticket.setUserId(UUid.getUuid());
		String[] arr = new String[files.length];
		if (files.length==0) {
			return Result.wrapErrorResult("", "请上传图片哦。。");
		}
		for (int i = 0; i < files.length; i++) {
//			CommonsMultipartFile cf= (CommonsMultipartFile)files[i]; //这个myfile是MultipartFile的
//	        System.out.println(files[i].getOriginalFilename());
//			DiskFileItem fi = (DiskFileItem)cf.getFileItem(); 
//	        File file1 = fi.getStoreLocation();
			if (files[i].isEmpty()) {
				return Result.wrapErrorResult("", "上传图片失败。。");
			}
			byte[] bytes = files[i].getBytes();
	        String fileName = DateUtil.getNumDate()+files[i].getOriginalFilename();
			String url;
			try {
				url = QiNiuUtil.upload(bytes, fileName);
				arr[i] = url;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				
				return Result.wrapErrorResult(ErrorCode.OPERATION_FAILED);
			}
		}
		enterId = UUid.getUuid();
		entryticket.setEnterId(enterId);
		String listString = ArrColUtil.arrToColString(arr);
		entryticket.setPicUrl(listString);
		entryticketServiceImpl.addTicket(entryticket);
		return Result.wrapSuccessfulResult("等待成功");
	};  
	@RequestMapping(value ="/updatePics",method = RequestMethod.POST)
	@ApiOperation(value = "增加图片")
	public Result<Object> changeTicket(String enterId,@RequestParam(required=false,value="myfiles") MultipartFile[] myfiles){
		System.out.println("增加图片的入库单id"+enterId);
		System.out.println("文件数组的大小"+myfiles.length);
		Entryticket entryticket = new Entryticket();
		String[] arr = new String[myfiles.length];
		if (myfiles.length==0) {
			return Result.wrapErrorResult("", "请上传图片哦。。");
		}
		for (int i = 0; i < myfiles.length; i++) {
//			CommonsMultipartFile cf= (CommonsMultipartFile)files[i]; //这个myfile是MultipartFile的
//	        System.out.println(files[i].getOriginalFilename());
//			DiskFileItem fi = (DiskFileItem)cf.getFileItem(); 
//	        File file1 = fi.getStoreLocation();
			if (myfiles[i].isEmpty()) {
				return Result.wrapErrorResult("", "上传图片失败。。");
			}
			byte[] bytes;
			try {
				bytes = myfiles[i].getBytes();
			} catch (IOException e1) {
				e1.printStackTrace();
				return Result.wrapErrorResult("", "文件上传失败");
			}
	        String fileName = DateUtil.getNumDate()+myfiles[i].getOriginalFilename();
			String url;
			try {
				url = QiNiuUtil.upload(bytes, fileName);
				arr[i] = url;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				
				return Result.wrapErrorResult(ErrorCode.OPERATION_FAILED);
			}
		}
		String listString = ArrColUtil.arrToColString(arr);
//		String picUrl = entryticketServiceImpl.selectByEnterId(enterId).getPicUrl();
//		try {
//			if (!picUrl.equals(null)&&!picUrl.equals("")) {
//				listString = picUrl+","+listString;
//			}
//		} catch (Exception e) {
//			System.out.println("原本图片字段为空");
//			// TODO: handle exception
//		}
		entryticket.setEnterId(enterId);
		entryticket.setPicUrl(listString);
		entryticketServiceImpl.changeTicket(entryticket);
		
		return Result.wrapSuccessfulResult("增加图片成功");
	};
	@RequestMapping(value ="/update",method = RequestMethod.POST)
	@ApiOperation(value = "更新入库单状态")
	public Result<Object> changeTicket(@Valid Entryticket entryticket){
		entryticketServiceImpl.changeTicket(entryticket);
		return Result.wrapSuccessfulResult("修改成功");
	};
	@RequestMapping(value ="/updates",method = RequestMethod.POST)
	@ApiOperation(value = "通过id数组来更改入库单状态")
	public Result<Object> changeTickets(String[] ids,String status){
		if (ids.length==0) {
			return Result.wrapErrorResult("", "请选择操作");
		}
		Entryticket entryticket = new Entryticket();
		entryticket.setStatus(status);
		for (int i = 0; i < ids.length; i++) {
			System.out.println(ids[i]+"$$$$$$$$$$$$$$$$$$");
			entryticket.setEnterId(ids[i]);
			entryticketServiceImpl.changeTicket(entryticket);
		}
		return Result.wrapSuccessfulResult("修改成功");
	};
	@RequestMapping(value ="deletById",method = RequestMethod.POST)
	@ApiOperation(value = "通过enterId来删除入库单")
	public Result<Object> deleteTicket(String enterId){
		entryticketServiceImpl.deleteTicket(enterId);
		return Result.wrapSuccessfulResult("删除成功");
	};
	@RequestMapping(value ="/selectByCondition",method = RequestMethod.POST)
	@ApiOperation(value = "通过复杂条件查询入库单")
	public Result<Object> selectByCondition(@Valid SearchEntryticket searchEntryticket){
		System.out.println(searchEntryticket);
		System.out.println(searchEntryticket.getBuyerName().equals("")+"%^&&*"+searchEntryticket.getSellerName().equals(""));
		//session中取userId
		String userId = getSessionUser().getUserId();
		searchEntryticket.setUserId(userId);
		List<Entryticket> list = entryticketServiceImpl.selectByCondition(searchEntryticket);
		System.out.println(list);
		return Result.wrapSuccessfulResult(list);
	};
	@RequestMapping(value ="/selectByUserId",method = RequestMethod.POST)
	@ApiOperation(value = "通过userid来查找入库单")
	public Result<Object> selectByUserId(String userId){
		List<Entryticket> list = entryticketServiceImpl.selectByUserId(userId);
		return Result.wrapSuccessfulResult(list);
	};
	@RequestMapping(value ="/selectByEnterIds",method = RequestMethod.POST)
	@ApiOperation(value = "通过入库单数组来查询入库单list")
	public Result<Object> selectByEnterIds(String[] enterIds){
			List list = new ArrayList<>();
		for (int i = 0; i < enterIds.length; i++) {
			Entryticket entryticket = entryticketMapper.selectByenterId(enterIds[i]);
			String pics = entryticket.getPicUrl();
			String goodsnames = entryticket.getGoodsName();
			Map map = BeanAllFieldUtil.beanToMap(entryticket);
			if (pics!=null) {
				map.put("pics",ArrColUtil.colToList(pics));	
			}
			if (pics!=null) {
				map.put("goodsnames",ArrColUtil.colToList(goodsnames));	
			}
			list.add(map);
		}
		return Result.wrapSuccessfulResult(list);
	}
	@RequestMapping(value ="/selectByEnterId",method = RequestMethod.POST)
	@ApiOperation(value = "通过enterId来查询入库单")
	public Result<Object> selectByEnterId(String enterId){
		List list4 = new ArrayList<>();
		System.out.println(enterId+"@$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		Entryticket entryticket = entryticketMapper.selectByenterId(enterId);
		entryticket.setSettlementAmount(entryticket.getSum());
		System.out.println(entryticket.getSettlementAmount());
		List<String> list1 = ArrColUtil.colToList(entryticket.getGoodsId());
		try {
			list4 = ArrColUtil.colToList(entryticket.getPicUrl());
		} catch (Exception e) {
			list4 = null;
		}
		System.out.println(list1);
		List list = new ArrayList();
		List list2 = new ArrayList();
		List list3 = new ArrayList();
		for (int i = 0; i < list1.size(); i++) {
			TicketGoods ticketGoods = ticketGoodsMapper.selectByPrimaryKey(list1.get(i));
			double a = ticketGoods.getAmount()*ticketGoods.getUnivalent();
			int b = (int) a;
			double c = b; 
			ticketGoods.setSum(c);
			GoodsQuality goodsQuality = goodsQualityMapper.selectByPrimaryKey(ticketGoods.getQid());
			list2.add(ticketGoods);
			list3.add(goodsQuality);
		}
		list.add(list2);
		list.add(list3);
		list.add(entryticket);
		list.add(list4);
		return Result.wrapSuccessfulResult(list);
	};
	@RequestMapping(value ="/getAll",method = RequestMethod.POST)
	@ApiOperation(value = "获得个人所有入库单")
	public Result<Object> getAll(String userId){
		System.out.println("******************所有入库单********************");
		lookSession();
		userId = getSessionUser().getUserId();
		List<Entryticket> list = entryticketServiceImpl.selectByUserId(userId);
		System.out.println(list);
		return Result.wrapSuccessfulResult(list);
	};
}
