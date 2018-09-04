package com.lujie.demo.bean;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * 分页信息实体类
 * @author 卢杰
 * 时间:2018.8.2
 */
@Component
public class PageBean implements Serializable{
    //当前页码
    private Integer page;
    //每页数据量
    private Integer limit;
    //数据总数
    private Long count;
    //数据状态的字段名称
    private String status;
    //状态信息的字段名称
    private String msg;
    //成功的状态码
    private Integer code;
    //每页的所有数据
    private List data;

    public PageBean() {
    }

    public PageBean(Integer page, Integer limit) {
        this.page = page;
        this.limit = limit;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "page=" + page +
                ", limit=" + limit +
                ", count=" + count +
                ", status='" + status + '\'' +
                ", msg='" + msg + '\'' +
                ", code=" + code +
                ", data=" + data +
                '}';
    }
}
