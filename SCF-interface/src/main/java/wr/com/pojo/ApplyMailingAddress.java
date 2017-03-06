package wr.com.pojo;

import java.util.Date;

public class ApplyMailingAddress {
    private Integer id;

    private String applyMid;

    private String recipientsName;

    private String tel;

    private String province;

    private String city;

    private String county;

    private String detailedAdress;

    private Date updateDate;

    private String ifDelete;

    private String updateUserId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApplyMid() {
        return applyMid;
    }

    public void setApplyMid(String applyMid) {
        this.applyMid = applyMid == null ? null : applyMid.trim();
    }

    public String getRecipientsName() {
        return recipientsName;
    }

    public void setRecipientsName(String recipientsName) {
        this.recipientsName = recipientsName == null ? null : recipientsName.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county == null ? null : county.trim();
    }

    public String getDetailedAdress() {
        return detailedAdress;
    }

    public void setDetailedAdress(String detailedAdress) {
        this.detailedAdress = detailedAdress == null ? null : detailedAdress.trim();
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

	@Override
	public String toString() {
		return "ApplyMailingAddress [id=" + id + ", applyMid=" + applyMid + ", recipientsName=" + recipientsName
				+ ", tel=" + tel + ", province=" + province + ", city=" + city + ", county=" + county
				+ ", detailedAdress=" + detailedAdress + ", updateDate=" + updateDate + ", ifDelete=" + ifDelete
				+ ", updateUserId=" + updateUserId + "]";
	}
    
}