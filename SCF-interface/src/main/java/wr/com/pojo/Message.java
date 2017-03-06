package wr.com.pojo;

public class Message {
    private Integer id;

    private String mid;

    private String type;

    private String contentHead;

    private String content;

    private String date;

    private String statue;

    private String userId;

    private String createTime;

    private String astModifiedTime;

    private String modifier;

    private String creator;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid == null ? null : mid.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getContentHead() {
        return contentHead;
    }

    public void setContentHead(String contentHead) {
        this.contentHead = contentHead == null ? null : contentHead.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date == null ? null : date.trim();
    }

    public String getStatue() {
        return statue;
    }

    public void setStatue(String statue) {
        this.statue = statue == null ? null : statue.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getAstModifiedTime() {
        return astModifiedTime;
    }

    public void setAstModifiedTime(String astModifiedTime) {
        this.astModifiedTime = astModifiedTime == null ? null : astModifiedTime.trim();
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

	public Message(String mid, String type, String contentHead, String content, String date, String statue,
			String userId, String createTime, String astModifiedTime, String modifier, String creator) {
		super();
		this.mid = mid;
		this.type = type;
		this.contentHead = contentHead;
		this.content = content;
		this.date = date;
		this.statue = statue;
		this.userId = userId;
		this.createTime = createTime;
		this.astModifiedTime = astModifiedTime;
		this.modifier = modifier;
		this.creator = creator;
	}

	public Message() {
		super();
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", mid=" + mid + ", type=" + type + ", contentHead=" + contentHead + ", content="
				+ content + ", date=" + date + ", statue=" + statue + ", userId=" + userId + ", createTime="
				+ createTime + ", astModifiedTime=" + astModifiedTime + ", modifier=" + modifier + ", creator="
				+ creator + "]";
	}
    
}

