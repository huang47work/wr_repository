package wr.com.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Entryticket {
    private Integer id;

    private String enterId;

    private String enterNumber;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")  
    private Date enterDate;
    
    private String carNumber;

    private Double sum;

    private String picUrl;

    private String status;

    private String resourse;

    private String groupId;

    private String userId;

    private String goodsName;

    private String goodsId;

    private String buyerName;

    private String buyerId;

    private String sellerName;

    private String sellerId;

    private String invoiceNum;

    private String invoiceId;
  
    private String repayDays;
    
    private String loanType;
    
    private Double coolieHire;
    
    private Double settlementAmount;
    
	public String getRepayDays() {
		return repayDays;
	}

	public void setRepayDays(String repayDays) {
		this.repayDays = repayDays;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEnterId() {
        return enterId;
    }

    public void setEnterId(String enterId) {
        this.enterId = enterId == null ? null : enterId.trim();
    }

    public String getEnterNumber() {
        return enterNumber;
    }

    public void setEnterNumber(String enterNumber) {
        this.enterNumber = enterNumber == null ? null : enterNumber.trim();
    }

    public Date getEnterDate() {
        return enterDate;
    }

    public void setEnterDate(Date enterDate) {
        this.enterDate = enterDate;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber == null ? null : carNumber.trim();
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getResourse() {
        return resourse;
    }

    public void setResourse(String resourse) {
        this.resourse = resourse == null ? null : resourse.trim();
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId == null ? null : groupId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId == null ? null : goodsId.trim();
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName == null ? null : buyerName.trim();
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId == null ? null : buyerId.trim();
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName == null ? null : sellerName.trim();
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId == null ? null : sellerId.trim();
    }

    public String getInvoiceNum() {
        return invoiceNum;
    }

    public void setInvoiceNum(String invoiceNum) {
        this.invoiceNum = invoiceNum == null ? null : invoiceNum.trim();
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId == null ? null : invoiceId.trim();
    }

	public Double getCoolieHire() {
		return coolieHire;
	}

	public void setCoolieHire(Double coolieHire) {
		this.coolieHire = coolieHire;
	}

	public Double getSettlementAmount() {
		return settlementAmount;
	}

	public void setSettlementAmount(Double settlementAmount) {
		this.settlementAmount = settlementAmount;
	}

	@Override
	public String toString() {
		return "Entryticket [id=" + id + ", enterId=" + enterId + ", enterNumber=" + enterNumber + ", enterDate="
				+ enterDate + ", carNumber=" + carNumber + ", sum=" + sum + ", picUrl=" + picUrl + ", status=" + status
				+ ", resourse=" + resourse + ", groupId=" + groupId + ", userId=" + userId + ", goodsName=" + goodsName
				+ ", goodsId=" + goodsId + ", buyerName=" + buyerName + ", buyerId=" + buyerId + ", sellerName="
				+ sellerName + ", sellerId=" + sellerId + ", invoiceNum=" + invoiceNum + ", invoiceId=" + invoiceId
				+ ", repayDays=" + repayDays + ", loanType=" + loanType + ", coolieHire=" + coolieHire
				+ ", settlementAmount=" + settlementAmount + "]";
	}


}