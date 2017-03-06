package wr.com.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SearchEntryticket {
	private String enterNumber;
	private String buyerName;
	private String sellerName;
	private String invoiceNum;
	private String goodsName;
	private String status;
	@DateTimeFormat(pattern = "yyyy-MM-dd")  
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	private Date startDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")  
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	private Date endDate;
	private String userId;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getEnterNumber() {
		return enterNumber;
	}
	public void setEnterNumber(String enterNumber) {
		this.enterNumber = enterNumber;
	}
	public String getBuyerName() {
		return buyerName;
	}
	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}
	public String getSellerName() {
		return sellerName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	public String getInvoiceNum() {
		return invoiceNum;
	}
	public void setInvoiceNum(String invoiceNum) {
		this.invoiceNum = invoiceNum;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "SearchEntryticket [enterNumber=" + enterNumber + ", buyerName=" + buyerName + ", sellerName="
				+ sellerName + ", invoiceNum=" + invoiceNum + ", goodsName=" + goodsName + ", status=" + status
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", userId=" + userId + "]";
	}

	
	
}
