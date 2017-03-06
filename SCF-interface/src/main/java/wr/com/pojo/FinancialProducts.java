package wr.com.pojo;

public class FinancialProducts {
    private String pid;

    private Integer id;

    private String productName;

    private Double pledgeRate;

    private String interestType;

    private String interestMethod;

    private String balanceInterestMethod;

    private String repaymentMethod;

    private String productLimitUnit;

    private Double productInterestRate;

    private Double highestLimit;

    private String productKindOne;

    private String productKindTwo;

    private String financeUserId;

    private String financeUserName;

    private Double productLimit;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
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

    public String getProductLimitUnit() {
        return productLimitUnit;
    }

    public void setProductLimitUnit(String productLimitUnit) {
        this.productLimitUnit = productLimitUnit == null ? null : productLimitUnit.trim();
    }

    public Double getProductInterestRate() {
        return productInterestRate;
    }

    public void setProductInterestRate(Double productInterestRate) {
        this.productInterestRate = productInterestRate;
    }

    public Double getHighestLimit() {
        return highestLimit;
    }

    public void setHighestLimit(Double highestLimit) {
        this.highestLimit = highestLimit;
    }

    public String getProductKindOne() {
        return productKindOne;
    }

    public void setProductKindOne(String productKindOne) {
        this.productKindOne = productKindOne == null ? null : productKindOne.trim();
    }

    public String getProductKindTwo() {
        return productKindTwo;
    }

    public void setProductKindTwo(String productKindTwo) {
        this.productKindTwo = productKindTwo == null ? null : productKindTwo.trim();
    }

    public String getFinanceUserId() {
        return financeUserId;
    }

    public void setFinanceUserId(String financeUserId) {
        this.financeUserId = financeUserId == null ? null : financeUserId.trim();
    }

    public String getFinanceUserName() {
        return financeUserName;
    }

    public void setFinanceUserName(String financeUserName) {
        this.financeUserName = financeUserName == null ? null : financeUserName.trim();
    }

    public Double getProductLimit() {
        return productLimit;
    }

    public void setProductLimit(Double productLimit) {
        this.productLimit = productLimit;
    }
}