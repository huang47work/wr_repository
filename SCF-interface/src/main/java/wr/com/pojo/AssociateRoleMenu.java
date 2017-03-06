package wr.com.pojo;

import java.util.Date;

public class AssociateRoleMenu {
    private Integer id;

    private Integer modifier;

    private Date createTime;

    private Date lastModifyTime;

    private String isDeleted;

    private String remark;

    private String roleId;

    private String menuId;

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

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId == null ? null : menuId.trim();
    }

	@Override
	public String toString() {
		return "AssociateRoleMenu [id=" + id + ", modifier=" + modifier + ", createTime=" + createTime
				+ ", lastModifyTime=" + lastModifyTime + ", isDeleted=" + isDeleted + ", remark=" + remark + ", roleId="
				+ roleId + ", menuId=" + menuId + "]";
	}
    
}