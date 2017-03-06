package wr.com.pojo;

import java.util.Date;

public class GoodsQuality {
    private Integer id;

    private String qid;

    private String goodsName;

    private Double grossWeight;

    private Double frameWeight;

    private Double realWeight;

    private Double removeWater;

    private Double removeImpurity;

    private Double removeGrain;

    private Double removeOthers;

    private Double removeSum;

    private Double sum;

    private String unit;

    private String creatorId;

    private Date createTime;

    private Date lastModifyTime;

    private String lastModifyUser;

    private Integer isDelete;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQid() {
        return qid;
    }

    public void setQid(String qid) {
        this.qid = qid == null ? null : qid.trim();
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public Double getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(Double grossWeight) {
        this.grossWeight = grossWeight;
    }

    public Double getFrameWeight() {
        return frameWeight;
    }

    public void setFrameWeight(Double frameWeight) {
        this.frameWeight = frameWeight;
    }

    public Double getRealWeight() {
        return realWeight;
    }

    public void setRealWeight(Double realWeight) {
        this.realWeight = realWeight;
    }

    public Double getRemoveWater() {
        return removeWater;
    }

    public void setRemoveWater(Double removeWater) {
        this.removeWater = removeWater;
    }

    public Double getRemoveImpurity() {
        return removeImpurity;
    }

    public void setRemoveImpurity(Double removeImpurity) {
        this.removeImpurity = removeImpurity;
    }

    public Double getRemoveGrain() {
        return removeGrain;
    }

    public void setRemoveGrain(Double removeGrain) {
        this.removeGrain = removeGrain;
    }

    public Double getRemoveOthers() {
        return removeOthers;
    }

    public void setRemoveOthers(Double removeOthers) {
        this.removeOthers = removeOthers;
    }

    public Double getRemoveSum() {
        return removeSum;
    }

    public void setRemoveSum(Double removeSum) {
        this.removeSum = removeSum;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId == null ? null : creatorId.trim();
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

    public String getLastModifyUser() {
        return lastModifyUser;
    }

    public void setLastModifyUser(String lastModifyUser) {
        this.lastModifyUser = lastModifyUser == null ? null : lastModifyUser.trim();
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

	@Override
	public String toString() {
		return "GoodsQuality [id=" + id + ", qid=" + qid + ", goodsName=" + goodsName + ", grossWeight=" + grossWeight
				+ ", frameWeight=" + frameWeight + ", realWeight=" + realWeight + ", removeWater=" + removeWater
				+ ", removeImpurity=" + removeImpurity + ", removeGrain=" + removeGrain + ", removeOthers="
				+ removeOthers + ", removeSum=" + removeSum + ", sum=" + sum + ", unit=" + unit + ", creatorId="
				+ creatorId + ", createTime=" + createTime + ", lastModifyTime=" + lastModifyTime + ", lastModifyUser="
				+ lastModifyUser + ", isDelete=" + isDelete + "]";
	}
}