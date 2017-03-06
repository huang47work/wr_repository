package wr.com.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
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
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import wr.com.initBinder.DoubleEditor;
import wr.com.initBinder.FloatEditor;
import wr.com.initBinder.IntegerEditor;
import wr.com.initBinder.LongEditor;
import wr.com.pojo.WrMenu;
import wr.com.pojo.WrMenuPage;
import wr.com.pojo.historyLog.WrHistoryLogVO;
import wr.com.result.Result;
import wr.com.service.impl.WrHistoryLogService;
import wr.com.service.impl.WrMenuService;

/**
 * 菜单
 * 
 * @author guojie
 * @since JAN 18,2017
 * @version 1.0.1
 *
 */
@RequestMapping("menu/manage")
@Controller
@Api(value = "menu/manage", description = "菜单接口")
public class WrMenuController extends BaseController {

	@Autowired
	WrHistoryLogService<WrHistoryLogVO> wrHistoryLogService;

	@Autowired
	WrMenuService<WrMenu> menuService;

	private static Logger logger = (Logger) LoggerFactory.getLogger(WrMenuController.class);

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class,new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
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
	@RequestMapping(value ="/list",method = RequestMethod.POST)
	@ApiOperation(value = "菜单数据列表查询")
	@ResponseBody
	public Result<?> queryInvoiceList(Model model, @ApiParam(name = "menuId", value = "菜单编号") String menuId,
					@ApiParam(name = "parentId", value = "父编号") String parentId) {
		logger.info("*****************菜单数据列表开始******************");
		WrMenuPage apage = new WrMenuPage();
		if(StringUtils.isNotBlank(menuId)){
			apage.setMenuId(menuId);
		}
		if(StringUtils.isNotBlank(parentId)){
			apage.setParentId(parentId);
		}
		return Result.wrapSuccessfulResult(menuService.queryByList(apage));
	}
}
