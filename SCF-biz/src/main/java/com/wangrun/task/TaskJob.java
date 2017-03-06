package com.wangrun.task;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import wr.com.inBase.ImportCsvDataToBase;
import wr.com.utils.DateUtils;  
  
/** 
 *  
 * Project Name：spring-quartz 
 * Type Name：ScheduledJob 
 * Type Description： 
 * Author：Defonds 
 * Create Date：2015-10-29 
 * @version  
 *  
 */  
public class TaskJob extends QuartzJobBean {  
      
  private Logger logger = (Logger) LoggerFactory.getLogger(getClass());
    @Override  
    protected void executeInternal(JobExecutionContext arg0)  
            throws JobExecutionException {  
        System.out.println("I am FirstScheduledJob");  
  
    }  
    
    public String work(){
    	try {
    		logger.info("************************************************************");
    		logger.info("*                     "+ DateUtils.getNow("yyyy-MM-dd HH:mm:ss")+ "                  *");
    		logger.info("*                         任务开始执行                                                          *");
    		logger.info("*                                                          *");
    		logger.info("************************************************************");
    		ImportCsvDataToBase.autoToBase("/var/www/html/yclymgb.csv");
    		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "0";
    }
}  
