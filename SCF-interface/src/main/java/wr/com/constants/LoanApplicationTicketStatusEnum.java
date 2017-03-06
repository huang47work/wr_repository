package wr.com.constants;

/**
 * Created by huangsiqian on 2017/3/6 0006.
 */
public enum LoanApplicationTicketStatusEnum {


    //单据状态：0未借款状态，1作废,2还款完成

    NOT_BORROWED_YET("0","未借款状态"),

    BORROW_INVALIDATED("1","作废"),

    REPAYED("2","还款完成"),

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

    LoanApplicationTicketStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
