package wr.com.inBase;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import wr.com.mapper.EnterTicketResourceLogMapper;
import wr.com.mapper.EntryticketMapper;
import wr.com.mapper.GoodsQualityMapper;
import wr.com.mapper.TicketGoodsMapper;
import wr.com.pojo.EnterTicketResourceLog;
import wr.com.pojo.Entryticket;
import wr.com.pojo.GoodsQuality;
import wr.com.pojo.TicketGoods;
import wr.com.utils.DateUtil;
import wr.com.utils.UUid;  

  
public class ImportCsvDataToBase {  
	
	public static void main(String[] args) {
//		ImportCsvDataToBase.toEntityList(readCSV("d:\\yclymgb.csv"));
		autoToBase("e:\\yclymgb.csv");
		long startTime=System.currentTimeMillis();   //获取开始时间
//		toFirstBase();  //测试的代码段
//		pringLog();
		long endTime=System.currentTimeMillis(); //获取结束时间
		System.out.println("程序运行时间： "+(endTime-startTime)/1000+"s"+"大约为"+((endTime-startTime)/1000)/60+"min");
		
//		fastToFirstBase();
	}
	
	
//================================变量区===============================
	@Autowired  
	private static HttpServletRequest request; 
	private static BeanFactory factory = getBeanFactory();
	private static GoodsQualityMapper goodsQualityMapper = (GoodsQualityMapper) factory.getBean("goodsQualityMapper");
	private static TicketGoodsMapper ticketGoodsMapper = (TicketGoodsMapper) factory.getBean("ticketGoodsMapper");
	private static EntryticketMapper entryticketMapper = (EntryticketMapper) factory.getBean("entryticketMapper");
	private static EnterTicketResourceLogMapper enterTicketResourceLogMapper= (EnterTicketResourceLogMapper) factory.getBean("enterTicketResourceLogMapper");
	private static DataSourceTransactionManager txManager = (DataSourceTransactionManager)factory.getBean("txManager");

	
	
//================================方法区===============================
//-----------------------------查看未导入数据-------------------------
	
//-----------------------------auto-------------------------------
	public static void autoToBase(String adress){
		List<EnterTicketResourceLog> list = toEntityList(readCSV(adress));
//		获得数据库jsrq最大值
		Date maxDate = enterTicketResourceLogMapper.selectMaxDate();
		System.out.println(maxDate);
		Calendar rightNow = Calendar.getInstance();  
        rightNow.setTime(maxDate);   
        rightNow.add(Calendar.MONTH, -3);
        Date beforeDate = rightNow.getTime(); 
//       查询3个月之内的数据
        List<EnterTicketResourceLog> lista = enterTicketResourceLogMapper.selectAfterDate(beforeDate);
        
        List<String> list1 = new ArrayList<String>();
        Map<String,EnterTicketResourceLog>map  = new HashMap<String,EnterTicketResourceLog>();
//		---------------数据库3月之内的随机编号放入list
        for (int i = 0; i < lista.size(); i++) {
        	list1.add(lista.get(i).getSjbh());
		}
//      -------------导入数据随机编号和实体类放入map-------
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getJsrqsj().after(beforeDate)) {
					map.put(list.get(i).getSjbh(), list.get(i));
			}
		}

//		----------导入数据和数据库数据比较，得出新增数据-----------
		System.out.println("-------------------"+map.keySet().toString());
		for (int i = 0; i < list1.size(); i++) {
			if (map.containsKey(list1.get(i))) {
				map.remove(list1.get(i));
			}
		}
		
//		----------------新数据导入数据库--------------
		 for (String key : map.keySet()) {
			   System.out.println("key= "+ key + " and value= " + map.get(key));
				toEntitySourceLogBase(map.get(key));
//				toThreeBase(map.get(key));
			  }
		String fileName = getNumDate();
		try {
			creatRootTxtFile("/logs/csvLgs",fileName,"导入数据条数："+map.size()+","+"导入数据预览："+map);
		} catch (IOException e) {
			System.out.println("日志输出失败");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("导入数据预览："+map);
		System.out.println("数据库三个月以内数据条数为："+lista.size());
		lista.clear();
		list.clear();
		return;
	}

	
//-----------------------------初始化-------------------------------
	public static void fastToFirstBase(){
		List<EnterTicketResourceLog> list = toEntityList(readCSV("e:\\yclymgb.csv"));
		list.subList(0, 1);
		fastToEntitySourceLogBase(list);
	}
	public static void toFirstBase(){
		List<EnterTicketResourceLog> list = toEntityList(readCSV("e:\\yclymgb.csv"));
		DefaultTransactionDefinition def = new DefaultTransactionDefinition(); 
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED); 
		TransactionStatus status = txManager.getTransaction(def); 
		List<EnterTicketResourceLog> list1 = list.subList(0, 10000);
		try{ 
			for (int i = 0; i < list1.size(); i++) {
				EnterTicketResourceLog enterTicketResourceLog = list1.get(i);
				toEntitySourceLogBase(enterTicketResourceLog);
//					toThreeBase(enterTicketResourceLog);
			} 
		}catch(Exception ex){ 
			txManager.rollback(status); 
			return;
		} 
		txManager.commit(status); 
		
		return;
	}
	
// ----------------------------解析字符串到转换----------------------------  
	
	public static List<EnterTicketResourceLog> toEntityList(List<List<String>> list1){
		System.out.println("------------------------转为对象----------------------------");
		List<EnterTicketResourceLog> listenti = new  ArrayList<EnterTicketResourceLog>();
		Date date1 = new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//小写的mm表示的是分钟  
//		System.out.println("&&"+list1);
		System.out.println("List《String》一共的条数是"+list1.size());
		for (int i = 0; i < list1.size(); i++) {
			List<String>list = list1.get(i);
//			System.out.println(list1.get(i));
			EnterTicketResourceLog enterTicketResourceLog = null;
			String c = list.get(24);
			if (c.equals(".Y.")||c.equals("   ")) {
				c = 1+"";
			}
			String sjbh = list.get(0);
			try {
				enterTicketResourceLog = new EnterTicketResourceLog(sjbh, list.get(1), list.get(2), Double.valueOf(list.get(3).toString()), list.get(4), list.get(5), list.get(6), Double.valueOf(list.get(7)), Double.valueOf(list.get(8)), Double.valueOf(list.get(9)), Double.valueOf(list.get(10)), Double.valueOf(list.get(11)), Double.valueOf(list.get(12)), Double.valueOf(list.get(13)), Double.valueOf(list.get(14)), Double.valueOf(list.get(15)), Double.valueOf(list.get(16)), list.get(17), Double.valueOf(list.get(18)), Double.valueOf(list.get(19)), Double.valueOf(list.get(20)), Double.valueOf(list.get(21)), sdf.parse(list.get(22)), Double.valueOf(list.get(23)), Double.valueOf(c), sdf.parse(list.get(25)), sdf.parse(list.get(26)), list.get(27), sdf.parse(list.get(28)), list.get(29), list.get(30), list.get(31),Double.valueOf(list.get(32)), Double.valueOf(list.get(33)), Double.valueOf(list.get(34)), list.get(35), Double.valueOf(list.get(36)), date1);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("************** new 对象出错*************");
				return null;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("************** 日期转换出错*************");
				return null;
			}
//			System.out.println(enterTicketResourceLog);
			listenti.add(enterTicketResourceLog);
			list1.clear();
		
		}
		System.out.println("List《Entity》的数量为："+listenti.size());
//		System.out.println("List《Entity》的数据为："+listenti);
		System.out.println("------------------------读取完毕----------------------------");
		return listenti;
	}

	
	
	//	----------------------------读取字符串------------------------------------
	
	public static List<List<String>>readCSV(String filePath){
		
		InputStreamReader isr;
		try {
			isr = new InputStreamReader(new FileInputStream(new File(filePath)),"gbk");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("------------ 编码不支持----------");
			return null;
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("----------  io问题-----------");
			return null;
		}
		BufferedReader bf = new BufferedReader(isr);
		List<List<String>> list = new ArrayList<List<String>>();
		List<String> list1 = new ArrayList<String>();
		try {
		String readLine;
		   while ((readLine = bf.readLine())!=null) {
//			   System.out.println("%%"+readLine);
			   if (readLine.startsWith("_")) {
				   readLine = readLine.substring(0);
			}
			   list1 = Arrays.asList(readLine.split(","));
			   list.add(list1);
			   
		   }
		   bf.close();
		   isr.close();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
	   return list;
   }
//-----------------------------获得BeanFactory---------------------------
		
	private static BeanFactory getBeanFactory(){
			String[] xmls=new String[]{"classpath:spring-mybatis.xml"};
			factory = new ClassPathXmlApplicationContext(xmls);
			return factory;
		}
//--------------------------写入数据库-----------------------------------
		
		public  static void toEntitySourceLogBase(EnterTicketResourceLog enterTicketResourceLog){
			
				enterTicketResourceLogMapper.insertSelective(enterTicketResourceLog);
			
		}
		public static void fastToEntitySourceLogBase(List<EnterTicketResourceLog> list){
				enterTicketResourceLogMapper.insertList(list);
		}
		public static void toThreeBase(EnterTicketResourceLog enterTicketResource){
			
//			---------------------质检表-------------------------
			GoodsQuality goodsQuality = new GoodsQuality();
			goodsQuality.setQid(UUid.getUuid());
			goodsQuality.setGoodsName(enterTicketResource.getMc());
			goodsQuality.setGrossWeight(enterTicketResource.getMz());
			goodsQuality.setFrameWeight(enterTicketResource.getPz());
			goodsQuality.setRealWeight(enterTicketResource.getJz());
			goodsQuality.setRemoveWater(enterTicketResource.getSfkj());
			goodsQuality.setRemoveImpurity(enterTicketResource.getZzkj());
			goodsQuality.setRemoveGrain(enterTicketResource.getBwqlkj());
			goodsQuality.setRemoveOthers(enterTicketResource.getQt());
			goodsQuality.setRemoveSum(enterTicketResource.getLjkj());
			goodsQuality.setSum(enterTicketResource.getZzjz());
			goodsQuality.setUnit("kg");
			goodsQualityMapper.insertSelective(goodsQuality);
			
//			----------------------货物表-------------------------
			TicketGoods goods = new TicketGoods();
			goods.setGid(UUid.getUuid());;
			goods.setGname(enterTicketResource.getMc());
			goods.setPiece("kg");
			goods.setAmount(enterTicketResource.getZzjz());
			goods.setUnivalent(enterTicketResource.getDj());
			goods.setSum(enterTicketResource.getZzjz());
			goods.setQid(goodsQuality.getQid());
			goods.setTypeName("农作物");
			ticketGoodsMapper.insertSelective(goods);
			
//			---------------------入库单---------------------------------
			Entryticket e = new Entryticket();
			e.setEnterId(UUid.getUuid());
			e.setEnterNumber(enterTicketResource.getDbbh());
			e.setEnterDate(enterTicketResource.getGbrq());
			e.setCarNumber(enterTicketResource.getCh());
			e.setSum(enterTicketResource.getJsje());
			e.setStatus("0");	
			e.setResourse("接口数据");
			e.setGoodsName(enterTicketResource.getMc());
			e.setGoodsId(goods.getGid());
			e.setBuyerName("祥瑞药业");
			e.setSellerName(enterTicketResource.getYsdw());
			e.setCoolieHire(enterTicketResource.getZxf());
			e.setSettlementAmount(enterTicketResource.getJsje());
			entryticketMapper.insertSelective(e);
			return;
		}
//		---------------------在项目中创建txt---------------------------------
		public  static void creatRootTxtFile(String roadPath,String fileName,String content) throws IOException{
			System.out.println(new File("").getAbsolutePath()+roadPath);
			System.out.println(new File(new File("").getAbsolutePath()+roadPath).exists());
			String path = new File("").getAbsolutePath()+roadPath;
			if (!new File(path).exists()) {
				new File(path).mkdirs();
			}
			File file = new File(path+"/"+fileName+".txt");
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.flush();
			bw.close();
		}
//		---------------------获得一串时间字符串---------------------------------
		public static String getNumDate(){
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
	    	String str=sdf.format(new Date());
	    	return str;
		}
//	==================================================================

}