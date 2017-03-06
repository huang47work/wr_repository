package wr.com.main;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMain {

	public static void main(String[] args){
		String[] xmls=new String[]{"classpath:spring-mybatis.xml","classpath:dubbo-provider.xml","classpath:spring-ehcache.xml"};
//		String[] xmls=new String[]{"classpath:spring-mybatis.xml","classpath:spring-jms.xml"};
		ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext(xmls);
		  context.start();
	        try {
				System.in.read();
			} catch (IOException e) {
				e.printStackTrace();
			} // 按任意键退出
	}
}
