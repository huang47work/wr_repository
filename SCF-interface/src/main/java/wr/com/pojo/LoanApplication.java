package wr.com.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class LoanApplication {
    private Integer id;

    private String aid;

    private String applicationNum;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")  
    private Date applicationDate;

    private String serviceName;

    private String ticketServiceStatus;

    private String ticketStatus;

    private String processStatus;

    private Double sum;

    private String ifAgreement;

    private Integer repayDays;

    private String loanType;

    private String agreementId;

    private String groupId;

    private String applyUserId;

    private String coreUserId;

    private String platformUserId;

    private String financeUserId;

    private Date updateDate;

    private String ifDelete;

    private String updateUserId;

    private String nextStatus;
    
    private String nextRole;
    
    private String applyMid;
    
    private String applyUserName;
    
    private String financeUserName;
    
    private String rejectReason;
    
    private String iid;
    
    public String getIid() {
		return iid;
	}

	public void setIid(String iid) {
		this.iid = iid;
	}

	public String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	public String getFinanceUserName() {
		return financeUserName;
	}

	public void setFinanceUserName(String financeUserName) {
		this.financeUserName = financeUserName;
	}

	public String getApplyUserName() {
		return applyUserName;
	}

	public void setApplyUserName(String applyUserName) {
		this.applyUserName = applyUserName;
	}

	public String getApplyMid() {
		return applyMid;
	}

	public void setApplyMid(String applyMid) {
		this.applyMid = applyMid;
	}

	public String getNextRole() {
		return nextRole;
	}

	public void setNextRole(String nextRole) {
		this.nextRole = nextRole;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid == null ? null : aid.trim();
    }

    public String getApplicationNum() {
        return applicationNum;
    }

    public void setApplicationNum(String applicationNum) {
        this.applicationNum = applicationNum == null ? null : applicationNum.trim();
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName == null ? null : serviceName.trim();
    }

    public String getTicketServiceStatus() {
        return ticketServiceStatus;
    }

    public void setTicketServiceStatus(String ticketServiceStatus) {
        this.ticketServiceStatus = ticketServiceStatus == null ? null : ticketServiceStatus.trim();
    }

    public String getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(String ticketStatus) {
        this.ticketStatus = ticketStatus == null ? null : ticketStatus.trim();
    }

    public String getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(String processStatus) {
        this.processStatus = processStatus == null ? null : processStatus.trim();
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public String getIfAgreement() {
        return ifAgreement;
    }

    public void setIfAgreement(String ifAgreement) {
        this.ifAgreement = ifAgreement == null ? null : ifAgreement.trim();
    }

    public Integer getRepayDays() {
        return repayDays;
    }

    public void setRepayDays(Integer repayDays) {
        this.repayDays = repayDays;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType == null ? null : loanType.trim();
    }

    public String getAgreementId() {
        return agreementId;
    }

    public void setAgreementId(String agreementId) {
        this.agreementId = agreementId == null ? null : agreementId.trim();
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId == null ? null : groupId.trim();
    }

    public String getApplyUserId() {
        return applyUserId;
    }

    public void setApplyUserId(String applyUserId) {
        this.applyUserId = applyUserId == null ? null : applyUserId.trim();
    }

    public String getCoreUserId() {
        return coreUserId;
    }

    public void setCoreUserId(String coreUserId) {
        this.coreUserId = coreUserId == null ? null : coreUserId.trim();
    }

    public String getPlatformUserId() {
        return platformUserId;
    }

    public void setPlatformUserId(String platformUserId) {
        this.platformUserId = platformUserId == null ? null : platformUserId.trim();
    }

    public String getFinanceUserId() {
        return financeUserId;
    }

    public void setFinanceUserId(String financeUserId) {
        this.financeUserId = financeUserId == null ? null : financeUserId.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getIfDelete() {
        return ifDelete;
    }

    public void setIfDelete(String ifDelete) {
        this.ifDelete = ifDelete == null ? null : ifDelete.trim();
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId == null ? null : updateUserId.trim();
    }

    public String getNextStatus() {
        return nextStatus;
    }

    public void setNextStatus(String nextStatus) {
        this.nextStatus = nextStatus == null ? null : nextStatus.trim();
    }

	@Override
	public String toString() {
		return "LoanApplication [id=" + id + ", aid=" + aid + ", applicationNum=" + applicationNum
				+ ", applicationDate=" + applicationDate + ", serviceName=" + serviceName + ", ticketServiceStatus="
				+ ticketServiceStatus + ", ticketStatus=" + ticketStatus + ", processStatus=" + processStatus + ", sum="
				+ sum + ", ifAgreement=" + ifAgreement + ", repayDays=" + repayDays + ", loanType=" + loanType
				+ ", agreementId=" + agreementId + ", groupId=" + groupId + ", applyUserId=" + applyUserId
				+ ", coreUserId=" + coreUserId + ", platformUserId=" + platformUserId + ", financeUserId="
				+ financeUserId + ", updateDate=" + updateDate + ", ifDelete=" + ifDelete + ", updateUserId="
				+ updateUserId + ", nextStatus=" + nextStatus + ", nextRole=" + nextRole + ", applyMid=" + applyMid
				+ ", applyUserName=" + applyUserName + ", financeUserName=" + financeUserName + "]";
	}


    
}