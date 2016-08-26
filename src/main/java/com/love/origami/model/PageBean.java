package com.love.origami.model;


public class PageBean{
    private Integer offset;
    private Integer count;
	private Integer pageSize;
	private Integer pageIndex;
    public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getOffset() {
		return offset;
	}
	public void setOffset(Integer offset) {
		this.offset = offset;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}
	public void offset(){
		this.offset=(this.pageIndex-1)*this.pageSize;
	}
}
