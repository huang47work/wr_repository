package wr.com.constants;

/**
 * Created by huangsiqian on 2017/3/6 0006.
 */
public enum  LoanApplicationTicketServiceStatusEnum {


    //单据业务状态：0新建，1待确定，21已确认（平台或核心企业），
    // 31已发布 （平台或核心企业），41已反馈（金融机构），40已作废

    NEW_LOAN_APPLICATION("0","新建贷款申请单"),

    TO_BE_CONFIRM("1","待确定"),

    CONFIRMED("21","已确认"),

    PUBLISHED("31","已发布"),

    FEEDBACKED("41","已反馈"),

    INVALIDATED_("40","已作废")



    ;


    private String code;

    private String desc;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    LoanApplicationTicketServiceStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
