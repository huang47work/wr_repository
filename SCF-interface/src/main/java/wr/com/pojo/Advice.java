package wr.com.pojo;

import java.util.Date;

public class Advice {
    private Integer id;

    private String aid;

    private String content;

    private String email;

    private String phone;

    private Date date;

    private String status;

    private String goodId;

    private String goodName;

    private String partId;

    private String partName;

    private String fileUrl;

    private Date astModifiedTime;

    private String modifier;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getGoodId() {
        return goodId;
    }

    public void setGoodId(String goodId) {
        this.goodId = goodId == null ? null : goodId.trim();
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName == null ? null : goodName.trim();
    }

    public String getPartId() {
        return partId;
    }

    public void setPartId(String partId) {
        this.partId = partId == null ? null : partId.trim();
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName == null ? null : partName.trim();
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl == null ? null : fileUrl.trim();
    }

    public Date getAstModifiedTime() {
        return astModifiedTime;
    }

    public void setAstModifiedTime(Date astModifiedTime) {
        this.astModifiedTime = astModifiedTime;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }
    
	public Advice() {
		super();
	}

	public Advice( String aid, String content, String email, String phone, Date date, String status,
			String goodId, String goodName, String partId, String partName, String fileUrl, Date astModifiedTime,
			String modifier) {
		super();
		this.aid = aid;
		this.content = content;
		this.email = email;
		this.phone = phone;
		this.date = date;
		this.status = status;
		this.goodId = goodId;
		this.goodName = goodName;
		this.partId = partId;
		this.partName = partName;
		this.fileUrl = fileUrl;
		this.astModifiedTime = astModifiedTime;
		this.modifier = modifier;
	}

	@Override
	public String toString() {
		return "Advice [id=" + id + ", aid=" + aid + ", content=" + content + ", email=" + email + ", phone=" + phone
				+ ", date=" + date + ", status=" + status + ", goodId=" + goodId + ", goodName=" + goodName
				+ ", partId=" + partId + ", partName=" + partName + ", fileUrl=" + fileUrl + ", astModifiedTime="
				+ astModifiedTime + ", modifier=" + modifier + "]";
	}
    
}