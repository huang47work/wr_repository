package wr.com.result;


import java.util.List;

/**
 * 分页参数
 * 
 * @author 郭杰
 * @since Dec 6,2016
 * @version 1.0.1
 */
public class PageParam {
  public static Integer C_DEFAULT_PAGESIZE = 10;
  public static Integer C_DEFAULT_PAGESIZE_MAX = 10;
  public static Integer C_DEFAULT_CURPAGE= 1;
  private Integer pageSize = C_DEFAULT_PAGESIZE;   //单页显示条数，默认10
  private Integer curPage = C_DEFAULT_CURPAGE;     //当前页，默认为第一页
  private Integer start = 0;       //默认第0个开始
  private Integer offset = 0;       //默认第0个开始
  private Integer limit = C_DEFAULT_PAGESIZE;       //默认第0个开始
  private List<String> sorts = null;
  public Integer getPageSize() {
    return pageSize;
  }

  public void setPageSize(Integer pageSize) {
    if( pageSize == null || pageSize == 0){
      this.pageSize = C_DEFAULT_PAGESIZE;
      return;
    } else if (pageSize > 10000) {
      pageSize = C_DEFAULT_PAGESIZE_MAX;
    }
    this.pageSize = pageSize;
  }

  public Integer getCurPage() {
    return curPage;
  }

  public void setCurPage(Integer curPage) {
    this.curPage = curPage==null?C_DEFAULT_CURPAGE:curPage;
  }

  /**
   * 当前页从那条记录开始
   *
   * @return 记录开始的index
   */
  public Integer getStart() {
    if (start == null || start == 0) {
      if (pageSize == null || curPage == null || curPage <= 0) {
        return 0;
      }
      return pageSize * (curPage - 1);
    } else {
      return start;
    }
  }

  /**
   * 当前页到那条记录为止
   *
   * @return 记录结束的index
   */
  public Integer getEnd() {
    return pageSize;
  }

  public void setStart(Integer start) {
    this.start = start;
  }

  public Integer getOffset(){
    this.offset = this.getStart();
    return this.offset;
  }

  public void setOffset(Integer offset){
    this.offset = offset;
    this.setStart(offset);
  }

  public Integer getLimit(){
    this.limit = this.getPageSize();
    return this.limit;
  }

  public void setLimit(Integer limit){
    this.limit = limit;
//    this.setLimit(limit);
  }

  public void setSorts(List<String> sorts){
    this.sorts = sorts;
  }

  public List<String> getSorts(){
    return this.sorts;
  }
}
