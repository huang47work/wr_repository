package wr.com.service.impl;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.base.service.BaseService;

import wangrun.exception.BizException;
import wr.com.mapper.WrInvoiceApplyMapper;
import wr.com.mapper.WrInvoiceMapper;
import wr.com.mapper.base.BaseDao;
import wr.com.pojo.WrInvoice;
import wr.com.pojo.WrInvoiceApply;
import wr.com.result.Constants;
import wr.com.result.Result;

/**
 * 发票
 * 
 * @author 郭杰
 * @since Dec 8,2016
 * @version 1.0.1
 * @param <T>
 *
 */
@Service("WrInvoiceService")
public class WrInvoiceService<T> extends BaseService<T> {

	private static Logger logger = (Logger) LoggerFactory.getLogger(WrInvoiceService.class);

	@Autowired
	WrInvoiceMapper<T> wrInvoiceMapper;

	@Override
	public BaseDao<T> getDao() {
		return this.wrInvoiceMapper;
	}

	@Autowired
	WrInvoiceApplyMapper<WrInvoiceApply> wrInvoiceApplyMapper;

	/**
	 * 删除发票和应收账款里发票ID
	 * 
	 * @param id
	 * @param applyId
	 * @return
	 * @throws Exception
	 */
	@Cacheable(value = "userCache")
	public int deleteByApplyId(Integer id, String applyId){

		try {
			// 删除发票
			WrInvoice invoice = (WrInvoice) getDao().selectById(id);
			String invoiceNum = invoice.getInvoiceNum();
			int result = getDao().deleteById(id);
			StringBuffer invoiceIds = new StringBuffer();
			// 授信批复信息
			WrInvoiceApply apply = (WrInvoiceApply) wrInvoiceApplyMapper.selectById(applyId);
			String applyIds = apply.getInvoiceNum();
			String[] ids = applyIds.split(",");
			for (int i = 0; i < ids.length; i++) {
				if (ids[i].equals(invoiceNum)) {
					logger.info("*************减去删除发票的金额**************");
					// 减去删除发票的金额
				} else {
					if (invoiceIds.length() > 0) {
						invoiceIds.append(",");
						invoiceIds.append(ids[i]);
					} else {
						invoiceIds.append(ids[i]);
					}
				}
			}
			/*if (invoiceIds.length() == Constants.NUMBER_0) {
				//如果没有发票，则赋值 入库单总金额
//				apply.setAmount(new BigDecimal(Constants.STRING_ZERO));
//				apply.setBuyer("");
			} else {
				if(apply.getAmount().doubleValue() != 0){
					apply.setAmount(
							BigDecimal.valueOf(apply.getAmount().doubleValue() - invoice.getInvoiceAmount().doubleValue()));
				}else{
					apply.setAmount(BigDecimal.valueOf(Constants.NUMBER_0));
				}
			}*/
			apply.setInvoiceNum(invoiceIds.toString());
			apply.setInvoiceGroupNum(applyId);
			return wrInvoiceApplyMapper.updateById(apply);
		} catch (BizException e) {
			throw new BizException(e.getMessage());
		}
	}

	/**
	 * 新增发票，然后修改应收账款里的发票号码
	 * 
	 * @see 如果存在，就追加，如果不存在，就添加
	 * @param t
	 * @param applyId
	 * @param newInvoiceNum
	 * @return
	 * @throws BizException
	 */
	@Cacheable(value = "userCache")
	public Result<?> addAndUpdateApply(T t, String applyId, String newInvoiceNum,double invoiceAmount) throws BizException {
		// 新增发票
		getDao().insert(t);
		// 修改应收账款里发票Id

		WrInvoiceApply apply = wrInvoiceApplyMapper.selectById(applyId);
		String invoiceNum = "";
		BigDecimal amount = new BigDecimal(Constants.NUMBER_0);
		if (null != apply) {
			invoiceNum = apply.getInvoiceNum();
			if (StringUtils.isNotBlank(invoiceNum)) {
				invoiceNum = invoiceNum + Constants.DOU_HAO + newInvoiceNum;
			} else {
				invoiceNum = newInvoiceNum;
			}
			amount = BigDecimal.valueOf(apply.getAmount().doubleValue() + invoiceAmount);
		}
		WrInvoiceApply iApply = new WrInvoiceApply();
		Constants.init(iApply);
		// updateById:id=invoiceGroupNum
		iApply.setInvoiceGroupNum(applyId);
		iApply.setInvoiceNum(invoiceNum);
//		iApply.setAmount(amount);
		return Result.wrapSuccessfulResult(wrInvoiceApplyMapper.updateById(iApply));
	}
}
