package wr.com.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import io.swagger.annotations.Api;
import wr.com.pojo.User;
import wr.com.result.Constants;
import wr.com.result.Result;

@Controller
@RequestMapping(value ="/file",method = RequestMethod.POST)
@Api(value = "advice", description = "上传接口")
public class UploadController {

	public static Logger logger = LoggerFactory.getLogger(UploadController.class);

	@RequestMapping(value ="/uploadFile",method = RequestMethod.POST)
	public Result<?> uploadFile(HttpServletRequest request, HttpServletResponse response)
			throws IllegalStateException, IOException {
		// 创建一个通用的多部分解析器
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 判断 request 是否有文件上传,即多部分请求
		if (multipartResolver.isMultipart(request)) {
			// 转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 取得request中的所有文件名
			Iterator<String> iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				// 记录上传过程起始时的时间，用来计算上传时间
				int pre = (int) System.currentTimeMillis();
				// 取得上传文件
				MultipartFile file = multiRequest.getFile(iter.next());
				if (file != null) {
					// 取得当前上传文件的文件名称
					String myFileName = file.getOriginalFilename();
					logger.info("上传的文件名：" + myFileName);
					String uploadPath = request.getSession().getServletContext().getRealPath("/")
							+ Constants.UPLOAD_DIRECTORY + File.separator;
					// 如果目录不存在则创建
					File uploadDir = new File(uploadPath);
					if (!uploadDir.exists()) {
						uploadDir.mkdir();
					}
					// 如果名称不为“”,说明该文件存在，否则说明该文件不存在
					if (myFileName.trim() != "") {
						// 重命名上传后的文件名
						String filePath = uploadPath + myFileName;
						// 定义上传路径
						File localFile = new File(filePath);
						file.transferTo(localFile);
					}
				}
				// 记录上传该文件后的时间
				int finaltime = (int) System.currentTimeMillis();
				logger.info("共耗时：" + (finaltime - pre) + "毫秒");
			}
		}
		return Result.wrapSuccessfulResult(Constants.UPLOAD_SUCCESS);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Result<?> addUser(User users, @RequestParam MultipartFile[] myfiles, HttpServletRequest request)
			throws IOException {
		// 如果只是上传一个文件，则只需要MultipartFile类型接收文件即可，而且无需显式指定@RequestParam注解
		// 如果想上传多个文件，那么这里就要用MultipartFile[]类型来接收文件，并且还要指定@RequestParam注解
		// 并且上传多个文件时，前台表单中的所有<input
		// type="file"/>的name都应该是myfiles，否则参数里的myfiles无法获取到所有上传的文件
		// 记录上传过程起始时的时间，用来计算上传时间
		StringBuffer url = new StringBuffer();
		int pre = (int) System.currentTimeMillis();
		for (MultipartFile myfile : myfiles) {
			if (myfile.isEmpty()) {
				logger.info("文件未上传");
			} else {
				logger.info("文件长度: " + myfile.getSize());
				logger.info("文件类型: " + myfile.getContentType());
				logger.info("文件名称: " + myfile.getName());
				logger.info("文件原名: " + myfile.getOriginalFilename());
				logger.info("========================================");
				// 如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\WEB-INF\\upload\\文件夹中
				String realPath = request.getSession().getServletContext().getRealPath("/")
						+ Constants.UPLOAD_DIRECTORY + File.separator;
				// 如果目录不存在则创建
				File uploadDir = new File(realPath);
				if (!uploadDir.exists()) {
					uploadDir.mkdir();
				}
				// 这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的
//				FileUtils.copyInputStreamToFile(myfile.getInputStream(),
//						new File(realPath, myfile.getOriginalFilename()));
				File localFile = new File(realPath, myfile.getOriginalFilename());
				myfile.transferTo(localFile);
				url.append(realPath + myfile.getOriginalFilename());
			}
		}
		// 记录上传该文件后的时间
		int finaltime = (int) System.currentTimeMillis();
		logger.info("共耗时：" + (finaltime - pre) + "毫秒");
//		users.put(users.getUserName(), "");
		return Result.wrapSuccessfulResult("成功！");
	}
}
