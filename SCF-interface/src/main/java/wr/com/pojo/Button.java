package wr.com.pojo;

import java.util.Date;

public class Button {
   

	private String bBid;

    private Integer id;

    private Integer modifier;

    private Date createTime;

    private Date lastModifyTime;

    private String isDeleted;

    private String remark;

    private String bBname;

    private String operation;

    private String roleId;

    private String pageId;

    private String url;

    public String getbBid() {
        return bBid;
    }

    public void setbBid(String bBid) {
        this.bBid = bBid == null ? null : bBid.trim();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getModifier() {
        return modifier;
    }

    public void setModifier(Integer modifier) {
        this.modifier = modifier;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getbBname() {
        return bBname;
    }

    public void setbBname(String bBname) {
        this.bBname = bBname == null ? null : bBname.trim();
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation == null ? null : operation.trim();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId == null ? null : pageId.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }
    @Override
   	public String toString() {
   		return "Button [bBid=" + bBid + ", id=" + id + ", modifier=" + modifier + ", createTime=" + createTime
   				+ ", lastModifyTime=" + lastModifyTime + ", isDeleted=" + isDeleted + ", remark=" + remark + ", bBname="
   				+ bBname + ", operation=" + operation + ", roleId=" + roleId + ", pageId=" + pageId + ", url=" + url
   				+ "]";
   	}
}