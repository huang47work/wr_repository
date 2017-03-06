package wr.com.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import wr.com.initBinder.DoubleEditor;
import wr.com.initBinder.FloatEditor;
import wr.com.initBinder.IntegerEditor;
import wr.com.initBinder.LongEditor;
import wr.com.pojo.WrRegion;
import wr.com.pojo.historyLog.WrHistoryLogVO;
import wr.com.result.ErrorCode;
import wr.com.result.Result;
import wr.com.service.impl.WrHistoryLogService;
import wr.com.service.impl.WrRegionService;

/**
 * 发票Controller
 * 
 * @author guojie
 * @since Dec 12,2016
 * @version 1.0.1
 *
 */
@RequestMapping("region/manage")
@Controller
@Api(value = "region/manage", description = "地区接口")
public class WrRegionController extends BaseController {

	@Autowired
	WrHistoryLogService<WrHistoryLogVO> wrHistoryLogService;

	@Autowired
	WrRegionService<WrRegion> regionService;

	private static Logger logger = (Logger) LoggerFactory.getLogger(WrRegionController.class);

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class,
				new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
		binder.registerCustomEditor(int.class, new IntegerEditor());
		binder.registerCustomEditor(long.class, new LongEditor());
		binder.registerCustomEditor(double.class, new DoubleEditor());
		binder.registerCustomEditor(float.class, new FloatEditor());
	}

	/**
	 * 查询列表
	 * 
	 * @param model
	 * @param status
	 * @return
	 */
	@RequestMapping(value = "/list",method = RequestMethod.POST)
	@ApiOperation(value = "地区列表查询")
	public Result<?> queryregionList(Model model, @ApiParam(name = "id", value = "id") Long id) {
		logger.info("*****************查询地区列表开始******************");
		try {
			return Result.wrapSuccessfulResult(regionService.getSonRegionList(id));
		} catch (Exception e) {
			logger.error("*************查询地区信息错误信息:" + e.getMessage());
			logger.error("查询地区信息错误信息结束***************");
			return Result.wrapErrorResult(ErrorCode.QUERY_FAIL);
		}

	}

}
