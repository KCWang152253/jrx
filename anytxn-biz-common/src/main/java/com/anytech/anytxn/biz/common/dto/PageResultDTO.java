package com.anytech.anytxn.biz.common.dto;

import java.util.List;

/**
 * 使用spring mvc中的page翻页
 * @author  Aning
 * @date 2018-11-09
 */
public class PageResultDTO<T> {


    /**
     * 页码
     */
    private Integer page;
    /**
     * 页容量
     */
    private Integer rows;

    /**
     * 总数量
     */
    private Long total;
    /**
     * 总页数
     */
    private Long totalPage;
    /**
     * 总数据
     */
    private List<T> data;

    public PageResultDTO() {
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public PageResultDTO(int page, int rows, long total, long totalPage, List<T> data) {
        this.page = page;
        this.rows = rows;
        this.total = total;
        this.totalPage = totalPage;
        this.data = data;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PageResultDTO{");
        sb.append("page=").append(page);
        sb.append(", rows=").append(rows);
        sb.append(", total=").append(total);
        sb.append(", totalPage=").append(totalPage);
        sb.append('}');
        return sb.toString();
    }
}