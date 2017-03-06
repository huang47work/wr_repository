package wr.com.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class FinancialProductsInstace {
    private String iid;

    private Integer id;

    private Date produceDate;

    private String productName;

    private Double productLimit;
    @DateTimeFormat(pattern = "yyyy-MM-dd")  
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date starDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")  
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date endDate;

    private Double pledgeRate;

    private String interestType;

    private String interestMethod;

    private Double productInterestRate;

    private String balanceInterestMethod;

    private String repaymentMethod;

    private String periods;

    private Double userdLimit;

    private String productKind;

    private String borrowUserId;

    private String borrowUserName;

    private String ifExpire;

    private Double borrowingBalance;

    private String pid;

    public String getIid() {
        return iid;
    }

    public void setIid(String iid) {
        this.iid = iid == null ? null : iid.trim();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getProduceDate() {
        return produceDate;
    }

    public void setProduceDate(Date produceDate) {
        this.produceDate = produceDate;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public Double getProductLimit() {
        return productLimit;
    }

    public void setProductLimit(Double productLimit) {
        this.productLimit = productLimit;
    }

    public Date getStarDate() {
        return starDate;
    }

    public void setStarDate(Date starDate) {
        this.starDate = starDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Double getPledgeRate() {
        return pledgeRate;
    }

    public void setPledgeRate(Double pledgeRate) {
        this.pledgeRate = pledgeRate;
    }

    public String getInterestType() {
        return interestType;
    }

    public void setInterestType(String interestType) {
        this.interestType = interestType == null ? null : interestType.trim();
    }

    public String getInterestMethod() {
        return interestMethod;
    }

    public void setInterestMethod(String interestMethod) {
        this.interestMethod = interestMethod == null ? null : interestMethod.trim();
    }

    public Double getProductInterestRate() {
        return productInterestRate;
    }

    public void setProductInterestRate(Double productInterestRate) {
        this.productInterestRate = productInterestRate;
    }

    public String getBalanceInterestMethod() {
        return balanceInterestMethod;
    }

    public void setBalanceInterestMethod(String balanceInterestMethod) {
        this.balanceInterestMethod = balanceInterestMethod == null ? null : balanceInterestMethod.trim();
    }

    public String getRepaymentMethod() {
        return repaymentMethod;
    }

    public void setRepaymentMethod(String repaymentMethod) {
        this.repaymentMethod = repaymentMethod == null ? null : repaymentMethod.trim();
    }

    public String getPeriods() {
        return periods;
    }

    public void setPeriods(String periods) {
        this.periods = periods == null ? null : periods.trim();
    }

    public Double getUserdLimit() {
        return userdLimit;
    }

    public void setUserdLimit(Double userdLimit) {
        this.userdLimit = userdLimit;
    }

    public String getProductKind() {
        return productKind;
    }

    public void setProductKind(String productKind) {
        this.productKind = productKind == null ? null : productKind.trim();
    }

    public String getBorrowUserId() {
        return borrowUserId;
    }

    public void setBorrowUserId(String borrowUserId) {
        this.borrowUserId = borrowUserId == null ? null : borrowUserId.trim();
    }

    public String getBorrowUserName() {
        return borrowUserName;
    }

    public void setBorrowUserName(String borrowUserName) {
        this.borrowUserName = borrowUserName == null ? null : borrowUserName.trim();
    }

    public String getIfExpire() {
        return ifExpire;
    }

    public void setIfExpire(String ifExpire) {
        this.ifExpire = ifExpire == null ? null : ifExpire.trim();
    }

    public Double getBorrowingBalance() {
        return borrowingBalance;
    }

    public void setBorrowingBalance(Double borrowingBalance) {
        this.borrowingBalance = borrowingBalance;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

	@Override
	public String toString() {
		return "FinancialProductsInstace [iid=" + iid + ", id=" + id + ", produceDate=" + produceDate + ", productName="
				+ productName + ", productLimit=" + productLimit + ", starDate=" + starDate + ", endDate=" + endDate
				+ ", pledgeRate=" + pledgeRate + ", interestType=" + interestType + ", interestMethod=" + interestMethod
				+ ", productInterestRate=" + productInterestRate + ", balanceInterestMethod=" + balanceInterestMethod
				+ ", repaymentMethod=" + repaymentMethod + ", periods=" + periods + ", userdLimit=" + userdLimit
				+ ", productKind=" + productKind + ", borrowUserId=" + borrowUserId + ", borrowUserName="
				+ borrowUserName + ", ifExpire=" + ifExpire + ", borrowingBalance=" + borrowingBalance + ", pid=" + pid
				+ "]";
	}
    
}