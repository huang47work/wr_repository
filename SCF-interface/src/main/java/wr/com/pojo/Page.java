package wr.com.pojo;

import java.util.Date;
import java.util.List;

public class Page {
    private String pageId;

    private Integer id;

    private Integer modifier;

    private Date createTime;

    private Date lastModifyTime;

    private String isDeleted;

    private String remark;

    private String pageName;

    private String pageType;
    
    private List<Button> buttons;
    
    public List<Button> getButtons() {
		return buttons;
	}

	public void setButtons(List<Button> buttons) {
		this.buttons = buttons;
	}

	public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId == null ? null : pageId.trim();
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

    public String getpageName() {
        return pageName;
    }

    public void setpageName(String pageName) {
        this.pageName = pageName == null ? null : pageName.trim();
    }

    public String getPageType() {
        return pageType;
    }

    public void setPageType(String pageType) {
        this.pageType = pageType == null ? null : pageType.trim();
    }

	@Override
	public String toString() {
		return "Page [pageId=" + pageId + ", id=" + id + ", modifier=" + modifier + ", createTime=" + createTime
				+ ", lastModifyTime=" + lastModifyTime + ", isDeleted=" + isDeleted + ", remark=" + remark
				+ ", pageName=" + pageName + ", pageType=" + pageType + ", buttons=" + buttons + "]";
	}
    
}