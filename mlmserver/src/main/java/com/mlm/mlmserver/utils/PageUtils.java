package com.mlm.mlmserver.utils;

import com.alibaba.fastjson2.JSONArray;

/**
 * 分页工具
 */
public class PageUtils {
    private int page = 1;       // 当前页码
    private int pages = 1;      // 总页码数
    private int total = 0;      // 总条目数
    private int pageSize = 10;  // 每页条目数
    private JSONArray data;     // 存放查询结果
    private int offset = 0;     // 起始条目的下标

    public PageUtils(int page, int pageSize) {
        this.page = page;
        this.pageSize = pageSize;
        this.offset = (page - 1) * pageSize;
    }

    // 下一页
    public int getNextPage(int page, int total) {return Math.min(page + 1, total);}

    // 上一页
    public int getPrevPage(int page) {
        return Math.max(page - 1, 1);
    }

    // 总页码
    public int getPages(int total, int pageSize) {return pageSize != 0 ? (int) Math.ceil((double) total / pageSize) : 1;}

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal() {return total;}

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public JSONArray getData() {
        return data;
    }

    public void setData(JSONArray data) {
        this.data = data;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {this.offset = offset;}
}
