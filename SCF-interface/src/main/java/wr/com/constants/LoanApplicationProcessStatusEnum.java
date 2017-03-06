package wr.com.constants;

/**
 * Created by huangsiqian on 2017/3/6 0006.
 */
public enum LoanApplicationProcessStatusEnum {


    //过程状态：0借款申请，1平台与核心企业审核，2金融机构审核，3网签协议，4放款

    LOAN_APPLICATION("0","借款申请"),

    PLATFORM_OR_CORE_ENTERPRISE_ASSESSMENT ("1","平台与核心企业审核"),

    FINANCIAL_INSTITUDE_ASSESSMENT("2","金融机构审核"),

    TO_SIGN_AGGREEMENT("3","网签协议"),

    LOAN_DISBURSEMENT("4","放款"),

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

    LoanApplicationProcessStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
